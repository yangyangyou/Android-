package cn.itcast.ssm.service.impl;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.itcast.ssm.mapper.MaterialMapper;
import cn.itcast.ssm.mapper.PushShopPlanMapper;
import cn.itcast.ssm.mapper.ShopPlanMapper;
import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.GetDetail;
import cn.itcast.ssm.po.GetDetailsVo;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.Material;
import cn.itcast.ssm.po.MaterialBatchNo;
import cn.itcast.ssm.service.MaterialService;

public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	public MaterialMapper materialMapper;
	
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	
	@Autowired
	private PushShopPlanMapper pushShopPlanMapper;
	

	@Override
	public List<GetDetail> findMaterial(String material_no,
			String batch_no,String shop_name) {
		return materialMapper.findMaterial(material_no,batch_no,shop_name);
	}

	@Override
	public void updateGet_detail(GetDetail getDetail) {
		         materialMapper.updateGet_detail(getDetail);
		
	}

	@Override
	public List<MaterialBatchNo> findMaterialBatch_no(String batch_no) {
		// TODO Auto-generated method stub
		return materialMapper.findMaterialBatch_no(batch_no);
	}

	@Override
	public void insertGet_detail(Integer get_material_id, String cailiao_mc,
			String cailiao_bh, String material_num, String provider,
			String acceptor, String get_date, String remark,String unit) {
		// TODO Auto-generated method stub
		materialMapper.insertGet_detail(get_material_id, cailiao_mc, cailiao_bh, material_num, provider, acceptor, get_date, remark,unit);
	}

	@Override
	public List<Integer> findGet_material_id(String material_no,
			String batch_no, String shop_name) {
		// TODO Auto-generated method stub
		return materialMapper.findGet_material_id(material_no, batch_no, shop_name);
	}

	@SuppressWarnings("finally")
	@Override
	public JSONObject updateMaterial(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try{
			request.setCharacterEncoding("UTF-8");
			String provider= request.getParameter("provider");
			String acceptor= request.getParameter("acceptor");
			String remark= request.getParameter("remark");
			String pici = request.getParameter("pici");
			String batch_no = request.getParameter("batch_no");
			String material_no = request.getParameter("material");
			String shop_name = request.getParameter("shop_name");
			Integer get_material_id = Integer.valueOf(request.getParameter("get_material_id"));
			JSONArray pici_J = JSONArray.fromObject(pici);
			List<GetDetail> getDetails = (List<GetDetail>) JSONArray.toList(pici_J,new GetDetail(),new JsonConfig());
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
			String get_date = time.format(new Date());
			for(int i=0;i<getDetails.size();i++){
			 /* JSONObject jObject = pici_J.getJSONObject(i);*/
			  GetDetail getDetail = getDetails.get(i);
			  /*getDetail.setGet_date(get_date);
			  getDetail.setDetail_id(jObject.getInt("detail_id"));
			  getDetail.setGet_material_id(jObject.getInt("get_material_id"));
			  getDetail.setBatch_no(batch_no);
			  getDetail.setCailiao_bh(jObject.getString("cailiao_bh"));
			  getDetail.setCailiao_mc(jObject.getString("cailiao_mc"));
			  getDetail.setMaterial_batch_no(jObject.getString("material_batch_no"));
			  getDetail.setMaterial_num(jObject.getString("material_num"));
			  getDetail.setUnit(jObject.getString("unit"));*/
			  getDetail.setAcceptor(acceptor);
			  getDetail.setProvider(provider);
			  getDetail.setBatch_no(batch_no);
			  getDetail.setGet_date(get_date);
			  materialMapper.updateGet_detail(getDetail);
			  }
			  shopPlanMapper.updateMaterialGet(get_material_id);
			  List<String> cangku = pushShopPlanMapper.findCangKuGM();
			  JSONObject jsonObject2 = new JSONObject();
			  JSONObject lingliao_dan = new JSONObject();
			  lingliao_dan.put("batch_no", batch_no);
			  lingliao_dan.put("material_no", material_no);
			  lingliao_dan.put("shop_name", shop_name);
			  lingliao_dan.put("remark", remark);
			  lingliao_dan.put("acceptor", acceptor);
			  lingliao_dan.put("provider", provider);
			  lingliao_dan.put("date", get_date);
			  lingliao_dan.put("is_return", 0);
			  lingliao_dan.put("getDetails", pici_J);
			  jsonObject2.put("type", "get_or_back");
			  JSONArray jsonArray = new JSONArray();
			  jsonArray.add(lingliao_dan);
			  jsonObject2.put("get_or_back", jsonArray);
			  getMaterialmessToSocket(jsonObject2.toString(), cangku);
			  jsonObject.put("is_ok", "1");
		}catch(Exception e){
			e.printStackTrace();
			jsonObject.put("is_ok", "0");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			return jsonObject;
		}
	}
	
	public static void getMaterialmessToSocket(String string,List<String> user) throws Exception {
		Collection<IoSession> sessions = null;
		HashMap<String,String> map = new HashMap<>();
		if(user.size()!=0) {
			for(int i=0;i<user.size();i++) {
				map.put(user.get(i),"1");
			}
		}
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
			CangkuMessCache planMessCache = new CangkuMessCache();
			planMessCache.setMessage(string);
			planMessCache.setPush_people(map);
			MessCacheManager.getInstance().insertCangkuMessCache(planMessCache);
		}else {
			int a = 0;
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(map.get(c[0])!=null) {
					s.write(string);
					map.remove(c[0]);
					a++;
				}
			}
			if(a != user.size()) {
				CangkuMessCache planMessCache = new CangkuMessCache();
				planMessCache.setMessage(string);
				planMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertCangkuMessCache(planMessCache);
			}
		}
	}

	@Override
	public List<GetMaterial> findNoRecordGetMaterial() throws Exception {
		// TODO Auto-generated method stub
		return materialMapper.findNoRecordGetMaterial();
	}

	@Override
	public void updateNoRecordGetMaterial(int get_material_id) throws Exception {
		// TODO Auto-generated method stub
		materialMapper.updateNoRecordGetMaterial(get_material_id);
	}

	@Override
	public List<GetDetailsVo> findNoRecordReturnMaterial() throws Exception {
		// TODO Auto-generated method stub
		return materialMapper.findNoRecordReturnMaterial();
	}

	@Override
	public void updateNoRecordReturnMaterial(int detail_id) throws Exception {
		// TODO Auto-generated method stub
		materialMapper.updateNoRecordReturnMaterial(detail_id);
	}

	@Override
	public String findCailiao_bh(String batch_no, String material_no,
			String cailiao_mc) {
		// TODO Auto-generated method stub
		return materialMapper.findCailiao_bh(batch_no, material_no, cailiao_mc);
	}

}
