package cn.itcast.ssm.service.impl;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import cn.itcast.ssm.mapper.DailyCheckMapper;
import cn.itcast.ssm.mapper.MessMapper;
import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.DailyCheck_1;
import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.ErrorMessCache;
import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.service.MessService;

public class MessServiceImpl implements MessService {
	private static int online_number;
	private int mess_number=0;
	@Autowired
	private DailyCheckMapper dailyCheckMapper;
	@Autowired
	private MessMapper messMapper;
	String date;
	private static PrintWriter pw_1 =null;

	public JSONObject receiveMess(List<Message> mess) throws Exception {
		JSONObject result = new JSONObject();
		try {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		date = time.format(new Date());
		if(mess.size()!=0){
			DailyCheck_1 dailyCheck = new DailyCheck_1();
			dailyCheck.setBatchNo(mess.get(0).getBatch_no());
			dailyCheck.setShopName(mess.get(0).getShop_name());
			dailyCheck.setProcessName(mess.get(0).getProcess_name());
			dailyCheck.setAssetNo(mess.get(0).getAsset_no());
			dailyCheck.setOperator(mess.get(0).getOperator());
			dailyCheck.setState(1);
			dailyCheck.setErrorDate(date);
			dailyCheck.setErrorContent(mess.get(0).getError_content());
			dailyCheck.setSendPerson(mess.get(0).getSend_person());
			dailyCheck.setIsRijian(1);
			dailyCheck.setIsLatest(1);
			dailyCheck.setIs_mold(0);
			dailyCheckMapper.insert(dailyCheck);
			JSONObject json = new JSONObject();
			json.put("type","exception");
			List<Message> message = new ArrayList<>();
			Message message_one = new Message();
			message_one.setBatch_no(mess.get(0).getBatch_no());
			message_one.setAsset_no(mess.get(0).getAsset_no());
			message_one.setError_content(mess.get(0).getError_content());
			message_one.setError_date(date);
			message_one.setIs_latest(1);
			message_one.setOperator(mess.get(0).getOperator());
			message_one.setProcess_name(mess.get(0).getProcess_name());
			message_one.setSend_person(mess.get(0).getSend_person());
			message_one.setShop_name(mess.get(0).getShop_name());
			message.add(message_one);
			if(message.size()!=0) {
				json.put("message", message);
			}
			messToSocket(json,message);
			result.put("is_ok", "1");
		    System.out.print(result);
		}
		}catch (Exception e) {
			e.printStackTrace();
			result.put("is_ok","0");
		}
		return result;
	}
	
	public JSONObject getMess(int position) throws Exception {
		JSONObject result = new JSONObject();
		if(position == 1){
			List<Message> message = messMapper.getMess();
			if(message.size()!=0){
				result.put("message", message);
				mess_number++;
				System.out.println(mess_number+"/"+online_number);
				if(mess_number==online_number) {
					mess_number=0;
					messMapper.updateMess(message.get(0).getBatch_no(),
								message.get(0).getAsset_no(),
								message.get(0).getShop_name(),
								message.get(0).getProcess_name(),
								message.get(0).getOperator(),
								message.get(0).getSend_person());
				}
			}else{
				
			}
		}
		return result;
	}
	
	public void messToSocket(JSONObject jsonObject,List<Message> message) throws Exception {
		Collection<IoSession> sessions = null;
		ErrorMessCache errorMessCache = new ErrorMessCache();
		HashMap<String,String> map = new HashMap<>();
		List<String> weixiu = messMapper.findShebeiWeiXiu();
		for(int j=0;j<weixiu.size();j++) {
			map.put(weixiu.get(j),"1");
		}
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
			errorMessCache.setCheck_id(001);
			errorMessCache.setMessage(message);
			errorMessCache.setPush_people(map);
			MessCacheManager.getInstance().insertErrorMessCache(errorMessCache);
		}else {
			System.out.println("推送数目："+sessions.size());
			int a = 0;
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(c[1].equals("维修员")) {
					s.write(jsonObject.toString());
					map.remove(c[0]);
					a++;
				}
			}
			if(a != weixiu.size()) {
				errorMessCache.setCheck_id(001);
				errorMessCache.setMessage(message);
				errorMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertErrorMessCache(errorMessCache);
			}
			System.out.println(map.toString());
		}
	}
	
	@Override
	public void updateState(String person,String tranDate,String batch_no, String asset_no) {
		// TODO Auto-generated method stub
		messMapper.updateState(person,tranDate,batch_no, asset_no);
	}


	@Override
	public List<String> findMujuWeiXiu() throws Exception {
		// TODO Auto-generated method stub
		return messMapper.findMujuWeiXiu();
	}

	@Override
	public List<String> findShebeiWeiXiu() throws Exception {
		// TODO Auto-generated method stub
		return messMapper.findShebeiWeiXiu();
	}

	@Override
	public List<DailyCheck_2> getdailyCheck(int is_mold, String shop_name) {
		// TODO Auto-generated method stub
		if(is_mold == 2){
			return messMapper.getMyDailyCheck(shop_name);
		}else{
			return messMapper.getdailyCheck(is_mold);
		}
	}
}
