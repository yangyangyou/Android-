package cn.itcast.ssm.service.impl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.itcast.ssm.mapper.FuliaoMapper;
import cn.itcast.ssm.mapper.PushShopPlanMapper;
import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.GetSecDetail;
import cn.itcast.ssm.po.GetSecDetailVo;
import cn.itcast.ssm.po.GetSecMaterials;
import cn.itcast.ssm.po.HeightGauge;
import cn.itcast.ssm.po.SecondaryMaterials;
import cn.itcast.ssm.po.SpecialGauge;
import cn.itcast.ssm.service.FuliaoService;

public class FuliaoServiceImpl implements FuliaoService {
	@Autowired
	private FuliaoMapper fuliaoMapper;
	
	@Autowired
	private PushShopPlanMapper pushShopPlanMapper;
	
	@Override
	public List<SecondaryMaterials> findFuliaoDetails(String name) throws Exception {
		// TODO Auto-generated method stub
		return fuliaoMapper.findFuliaoDetails(name);
	}
	
	@Override
	public JSONObject insertFuliao(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   String shop_name = allData.getString("shop_name");
			   String accepter = allData.getString("person");
			   String date = String.format("%tF",new Date());
			   GetSecMaterials getSecMaterials = new GetSecMaterials();
			   getSecMaterials.setShop_name(shop_name);
			   getSecMaterials.setApply_data(date);
			   getSecMaterials.setApplyer(accepter);
			   getSecMaterials.setIs_apply(0);
			   getSecMaterials.setIs_approve(0);
			   getSecMaterials.setIs_pushed(0);
			   getSecMaterials.setIs_return(0);
			   fuliaoMapper.insertFuliao(getSecMaterials);
			   JSONArray jsonArray = allData.getJSONArray("detail");
			   List<GetSecDetail> getSecDetails = new ArrayList<>();
			   for(int i=0;i<jsonArray.size();i++) {
				   JSONObject jsonObject = jsonArray.getJSONObject(i);
				   GetSecDetail getSecDetail = new GetSecDetail();
				   getSecDetail.setGet_materials_id(getSecMaterials.getGet_materials_id());
				   getSecDetail.setReshop_name(shop_name);
				   getSecDetail.setSec_material_no(jsonObject.getString("fuliao_no"));
				   getSecDetail.setSec_material_name(jsonObject.getString("fuliao_name"));
				   getSecDetail.setType(jsonObject.getString("type"));
				   getSecDetail.setUnit(jsonObject.getString("unit"));
				   getSecDetail.setNum(jsonObject.getString("num"));
				   getSecDetail.setIs_return(0);
				   getSecDetails.add(getSecDetail);
			   }
			   GetSecDetailVo getSecDetailVo = new GetSecDetailVo();
			   getSecDetailVo.setGet_sec_details(getSecDetails);
			   fuliaoMapper.insertFuliaoDetail(getSecDetails);
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "1");
	 		   return is_ok;
		   }catch(Exception e) {
			   e.printStackTrace();
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "0");
	 		   return is_ok;
		   }
	}

	@Override
	public JSONObject findNoGetFuliao(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   String shop_name = allData.getString("shop_name");
			   String accepter = allData.getString("person");
			   JSONObject response = new JSONObject();
	 		   List<GetSecMaterials> list = fuliaoMapper.findNoGetFuliao(shop_name, accepter);
	 		   if(list.size()!=0){
	 			   JSONArray jsonArray = JSONArray.fromObject(list);
	 			   response.put("is_success","1");
	 			   response.put("all", jsonArray);
	 			   return response;
	 		   }else{
				   response.put("is_success", "-1");
		 		   return response;
	 		   }
		   }catch(Exception e) {
			   e.printStackTrace();
			   JSONObject response = new JSONObject();
			   response.put("is_success", "0");
	 		   return response;
		   }
	}

	@Override
	public JSONObject updateFuliao(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   Integer get_sec_material_id = allData.getInt("get_sec_material_id");
			   String accepter = allData.getString("accpetor");
			   String provider = allData.getString("provider");
			   String remark = allData.getString("remark");
			   //String shop_name = allData.getString("shop_name");
			   JSONArray jsonArray = allData.getJSONArray("detail_id");
			   String date = String.format("%tF",new Date());
			   GetSecMaterials getSecMaterials = new GetSecMaterials();
			   getSecMaterials.setGet_materials_id(get_sec_material_id);
			   getSecMaterials.setAcceptor(accepter);
			   getSecMaterials.setProvider(provider);
			  // getSecMaterials.setShop_name(shop_name);
			   getSecMaterials.setApply_data(date);
			   getSecMaterials.setRemark(remark);
			   getSecMaterials.setIs_apply(1);
			   getSecMaterials.setIs_return(0);
			   fuliaoMapper.updateGetSecMaterial(getSecMaterials);
			   List<Integer> list = JSONArray.toList(jsonArray);
			   List<GetSecDetail> getSecDetails = new ArrayList<>();
			   for(int i=0;i<list.size();i++) {
				   GetSecDetail getSecDetail = new GetSecDetail();
				   getSecDetail.setDetail_id(list.get(i));
				   getSecDetail.setRemark(remark);
				   getSecDetail.setTime(date);
				   getSecDetails.add(getSecDetail);
			   }
			   fuliaoMapper.updateGetSecDetail(getSecDetails);
			   List<GetSecDetail> getSecDetails2 = fuliaoMapper.findGetSecDetailById(get_sec_material_id);
			   JSONObject jsonObject = new JSONObject();
			   jsonObject.put("type", "sec_material_get_back");
			   jsonObject.put("is",1);
			   getSecMaterials.setGet_sec_details(getSecDetails2);
			   jsonObject.put("sec_material_get_back", JSONArray.fromObject(getSecMaterials));
			   List<String> cangku = pushShopPlanMapper.findCangKuGM();
			   getMaterialmessToSocket(jsonObject.toString(), cangku);
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "1");
	 		   return is_ok;
		   }catch(Exception e) {
			   e.printStackTrace();
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "0");
	 		   return is_ok;
		   }
	}

	@Override
	public JSONObject insertBackFuliao(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   String shop_name = allData.getString("shop_name");
			   String returner = allData.getString("returner");
			   String geter = allData.getString("geter");
			   String date = String.format("%tF",new Date());
			   JSONArray jsonArray = allData.getJSONArray("detail");
			   GetSecMaterials getSecMaterials = new GetSecMaterials();
			   getSecMaterials.setShop_name(shop_name);
			   getSecMaterials.setAcceptor(geter);
			   getSecMaterials.setProvider(returner);
			   getSecMaterials.setApply_data(date);
			   getSecMaterials.setIs_approve(1);
			   getSecMaterials.setIs_apply(1);
			   getSecMaterials.setIs_return(1);
			   getSecMaterials.setIs_pushed(1);
			   getSecMaterials.setIs_recorded(0);
			   fuliaoMapper.insertFuliao(getSecMaterials);
			   List<GetSecDetail> getSecDetails = new ArrayList<>();
			   for(int i=0;i<jsonArray.size();i++) {
				   JSONObject jsonObject = jsonArray.getJSONObject(i);
				   GetSecDetail getSecDetail = new GetSecDetail();
				   getSecDetail.setReshop_name(shop_name);
				   getSecDetail.setGet_materials_id(getSecMaterials.getGet_materials_id());
				   getSecDetail.setSec_material_name(jsonObject.getString("fuliao_name"));
				   getSecDetail.setType(jsonObject.getString("type"));
				   getSecDetail.setUnit(jsonObject.getString("unit"));
				   getSecDetail.setNum(jsonObject.getString("num"));
				   getSecDetail.setSec_material_no(jsonObject.getString("fuliao_no"));
				   getSecDetail.setReturner(returner);
				   getSecDetail.setReceiver(geter);
				   getSecDetail.setTime(date);
				   getSecDetail.setRemark(jsonObject.getString("remark"));
				   getSecDetail.setIs_return(1);
				   getSecDetails.add(getSecDetail);
			   }
			   fuliaoMapper.insertBackFuliao(getSecDetails);
			   getSecMaterials.setGet_sec_details(getSecDetails);
			   JSONObject jsonObject = new JSONObject();
			   jsonObject.put("type", "sec_material_get_back");
			   jsonObject.put("is", 1);
			   jsonObject.put("sec_material_get_back", JSONArray.fromObject(getSecMaterials));
			   List<String> cangku = pushShopPlanMapper.findCangKuGM();
			   getMaterialmessToSocket(jsonObject.toString(), cangku);
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "1");
	 		   return is_ok;
		   }catch(Exception e) {
			   e.printStackTrace();
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			   JSONObject is_ok = new JSONObject();
	 		   is_ok.put("is_ok", "0");
	 		   return is_ok;
		   }
	}

	@Override
	public JSONObject findGauge(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		JSONObject responseData = new JSONObject();
		   try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   Integer type = allData.getInt("type");
			   String jianju_name = allData.getString("jianju_name");
			   if(type==1){
				   List<SpecialGauge> specialGauges = fuliaoMapper.findSpecialGauge("%"+jianju_name+"%", "%"+jianju_name+"%");
		   			if(specialGauges.size()!=0){
		   				JSONArray jsonArray = JSONArray.fromObject(specialGauges);
		   				responseData.put("is_success", "1");
		   				responseData.put("type", 1);
		   				responseData.put("all", jsonArray);
		   				return responseData;
		   			}else{
		   				responseData.put("is_success", "0");
		   				return responseData;
		   			}
			   }else if(type==2){
				   List<HeightGauge> heightGauges = fuliaoMapper.findHeightGauge("%"+jianju_name+"%");
		   			if(heightGauges.size()!=0){
		   				JSONArray jsonArray = JSONArray.fromObject(heightGauges);
		   				responseData.put("is_success", "1");
		   				responseData.put("type", 2);
		   				responseData.put("all", jsonArray);
		   				return responseData;
		   			}else{
		   				responseData.put("is_success", "0");
		   				return responseData;
		   			}
			   }else{
				   responseData.put("is_success", "-1");
				   return responseData; 
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
			   responseData.put("is_success", "-1");
			   return responseData;
		   }
	}

	@Override
	public List<GetSecMaterials> findNoPushSecMaterial() throws Exception {
		// TODO Auto-generated method stub
		return fuliaoMapper.findNoPushSecMaterial();
	}

	@Override
	public void updateNoPushSecMaterial(int get_sec_materials_id)
			throws Exception {
		// TODO Auto-generated method stub
		fuliaoMapper.updateNoPushSecMaterial(get_sec_materials_id);
	}

	@Override
	public List<GetSecMaterials> findNoGetSecMaterial() throws Exception {
		// TODO Auto-generated method stub
		return fuliaoMapper.findNoGetSecMaterial();
	}

	@Override
	public void updateNoGetSecMaterial(int get_sec_material_id)
			throws Exception {
		// TODO Auto-generated method stub
		fuliaoMapper.updateNoGetSecMaterial(get_sec_material_id);
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
	public List<GetSecMaterials> findNoRecordGetSec() throws Exception {
		// TODO Auto-generated method stub
		return fuliaoMapper.findNoRecordGetSec();
	}

	@Override
	public void updateNoRecordGetSec(int get_sec_material_id) throws Exception {
		// TODO Auto-generated method stub
		fuliaoMapper.updateNoRecordGetSec(get_sec_material_id);
	}
}
