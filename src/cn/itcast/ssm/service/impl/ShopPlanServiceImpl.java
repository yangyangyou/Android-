package cn.itcast.ssm.service.impl;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.itcast.ssm.mapper.DailyCheckMapper;
import cn.itcast.ssm.mapper.MessMapper;
import cn.itcast.ssm.mapper.PushShopPlanMapper;
import cn.itcast.ssm.mapper.ShopPlanMapper;
import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.ChangeTemPriceVo;
import cn.itcast.ssm.po.CipinNew;
import cn.itcast.ssm.po.CipinType;
import cn.itcast.ssm.po.DailyCheck_1;
import cn.itcast.ssm.po.ErrorMessCache;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.po.MoldReady;
import cn.itcast.ssm.po.MoldReadyFindVo;
import cn.itcast.ssm.po.MonthPlan;
import cn.itcast.ssm.po.OperatorVo;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.PlanMessCache;
import cn.itcast.ssm.po.ProcessTransition;
import cn.itcast.ssm.po.ProcessTransitionVo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.po.TrackCard;
import cn.itcast.ssm.po.WorkCardNew;
import cn.itcast.ssm.service.ShopPlanService;

public class ShopPlanServiceImpl implements ShopPlanService {
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	
	@Autowired
	private MessMapper messMapper;
	
	@Autowired
	private DailyCheckMapper dailyCheckMapper;
	
	@Autowired
	private PushShopPlanMapper pushShopPlanMapper;

	@Override
	public List<String> findProcess(String material_no,String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findProcess(material_no, shop_name);
	}

	@Override
	public List<Person> findOperator(String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findOperator(shop_name);
	}

	@Override
	public List<Integer> findPlanId(String material_no, String batch_no, String plan_no, String shop_name)
			throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findPlanId(material_no, batch_no, plan_no, shop_name);
	}

	@Override
	public List<Task_New> findTask(int plan_id, String process_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findTask(plan_id, process_name);
	}

	@Override
	public List<ProductionPlan> findProductionPlan(String plan_no, String material_no) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findProductionPlan(plan_no, material_no);
	}

	@Override
	public void insertNewShopPlan(ShopPlan shopPlan) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertNewShopPlan(shopPlan);
	}

	@Override
	public void insertTask(Task_New task) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertTask(task);
	}

	@Override
	public void updateShopPlan(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateShopPlan(plan_id);
	}
	@Override
	public List<WorkCardNew> findWorkCard(int card_id, String shop_name, String process_name, String asset,
			String operator) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findWorkCard(card_id, shop_name, process_name, asset, operator);
	}

	@Override
	public List<CipinNew> findCipin(int track_id) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findCipin(track_id);
	}

	@Override
	public List<CipinType> findCipinType(String shop_name, String cipin_type) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findCipinType(shop_name, cipin_type);
	}

	@Override
	public void insertWorKCard(WorkCardNew workCardNew) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertWorKCard(workCardNew);
	}

	@Override
	public void insertCipin(CipinNew cipinNew) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertCipin(cipinNew);
	}

	@Override
	public void updateWorkCard(WorkCardNew workCardNew) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateWorkCard(workCardNew);
	}

	@Override
	public void updateCipin(CipinNew cipinNew) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateCipin(cipinNew);
	}

	@Override
	public void deleteCipin(int track_id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.deleteCipin(track_id);
	}

	@Override
	public List<CipinType> findLiaofeiType() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findLiaofeiType();
	}

	@Override
	public List<ProductionPlan> findNewProductionPlan(String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findNewProductionPlan(shop_name);
	}

	@Override
	public List<ShopDelivery> findShopDelivery(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findShopDelivery(plan_id);
	}

	@Override
	public List<ProductionPlan> findPushProductionPlan() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findPushProductionPlan();
	}

	@Override
	public void updatePushProductionPlan(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updatePushProductionPlan(plan_id);
	}

	@Override
	public List<String> findRealProcess(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findRealProcess(plan_id);
	}

	@Override
	public String findPlanNum(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findPlanNum(plan_id);
	}

	@Override
	public String findPrice(String material_no, String shop_name,
			String process_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findPrice(material_no, shop_name, process_name);
	}


	@Override
	public List<WorkCardNew> findWorkCardTogether(OperatorVo operator) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findWorkCardTogether(operator);
	}

	@Override
	public String findAssetMold(String asset) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findAssetMold(asset);
	}

	@Override
	public JSONObject updateGenZong(String json) throws Exception {
		// TODO Auto-generated method stub
		try {
			   JSONObject allData = JSONObject.fromObject(json);
			   int card_id = Integer.valueOf(allData.getString("card_id"));
			   String track_id = allData.getString("track_id");
			   String shop_name = allData.getString("shop_name");
			   String material_no = allData.getString("material_no");
			   String batch_no = allData.getString("batch_no");
			   String process_name = allData.getString("process_name");
			   String operator = allData.getString("operator");
			   String asset = allData.getString("asset");
			   String asset_state = allData.getString("asset_state");
			   String mold_state = allData.getString("mold_state");
			   String hege_num = allData.getString("hege_num");
			   String buhege_num = allData.getString("buhege_num");
			   String send_people = allData.getString("send_people");
			   String temPrice = allData.getString("price");
			   String geter = allData.getString("geter");
			   String client_material_no = allData.getString("client_material_no");
			   String last_process = allData.getString("last_process");
			   String plan_no = allData.getString("plan_no");
			   String mold = shopPlanMapper.findAssetMold(asset);
			   if(track_id.equals("0")) {
				   String[] operator_s = operator.split(",");
				   WorkCardNew workCardNew = new WorkCardNew();
				   workCardNew.setCard_id(card_id);
				   workCardNew.setShop_name(shop_name);
				   workCardNew.setProcess_name(process_name);
				   workCardNew.setAsset(asset);
				   workCardNew.setAsset_state(asset_state);
				   if(mold!=null){
					   workCardNew.setMold(mold);
				   }
				   workCardNew.setMold_state(mold_state);
				   workCardNew.setTotal_num(String.valueOf((Integer.valueOf(hege_num)/operator_s.length)+(Integer.valueOf(buhege_num)/operator_s.length)));				   workCardNew.setHege_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
				   workCardNew.setTotal_cipin_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
				   String dateString = String.format("%tF", new Date());
				   workCardNew.setProduce_date(dateString);
				   if(temPrice.equals("")){
					   String price = shopPlanMapper.findPrice(material_no, shop_name, process_name);
					   workCardNew.setPrice(price);  
				   }else{
					   workCardNew.setTem_price(temPrice);
				   }
				   for(int i=0;i<operator_s.length;i++) {
					   workCardNew.setOperator(operator_s[i]);
					   if(temPrice.equals("")){
						   shopPlanMapper.insertWorKCard(workCardNew);
						   ProcessTransition processTransition = new ProcessTransition();
						   processTransition.setPlan_no(plan_no);
						   processTransition.setClient_material_no(client_material_no);
						   processTransition.setMaterial_no(material_no);
						   processTransition.setBatch_no(batch_no);
						   processTransition.setShop_name(shop_name);
						   processTransition.setProvider(operator_s[i]);
						   processTransition.setProcess1(process_name);
						   processTransition.setAcceptor(geter);
						   processTransition.setProcess2(last_process);
						   processTransition.setTran_date(dateString);
						   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
						   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
						   shopPlanMapper.insertProcessTransition(processTransition);
					   }else{
						   shopPlanMapper.insertTemPriceWorKCard(workCardNew);
						   ProcessTransition processTransition = new ProcessTransition();
						   processTransition.setPlan_no(plan_no);
						   processTransition.setClient_material_no(client_material_no);
						   processTransition.setMaterial_no(material_no);
						   processTransition.setBatch_no(batch_no);
						   processTransition.setShop_name(shop_name);
						   processTransition.setProvider(operator_s[i]);
						   processTransition.setProcess1(process_name);
						   processTransition.setAcceptor(geter);
						   processTransition.setProcess2(last_process);
						   processTransition.setTran_date(dateString);
						   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
						   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
						   shopPlanMapper.insertProcessTransition(processTransition);
					   }
					   //shopPlanMapper.insertWorKCard(workCardNew);
					   int track_id_n = workCardNew.getTrack_id();
					   JSONArray cipin_j = allData.getJSONArray("cipin");
					   for(int j=0;j<cipin_j.size();j++) {
						   JSONObject jsonObject = cipin_j.getJSONObject(j);
						   CipinNew cipinNew = new CipinNew();
						   cipinNew.setTrack_id(track_id_n);
						   cipinNew.setCipin_type(jsonObject.getString("cipin_type"));
						   cipinNew.setCipin_species(jsonObject.getString("cipin_species"));
						   if(jsonObject.getString("cipin_num").equals("")){
							   cipinNew.setCipin_num("0");
						   }else{
							   int a = 0;
							   a = (Integer.valueOf(jsonObject.getString("cipin_num"))/operator_s.length);
							   cipinNew.setCipin_num(String.valueOf(a));
						   }
						   shopPlanMapper.insertCipin(cipinNew);
					   }
				   }
				   if(asset_state.equals("异常")) {
					   DailyCheck_1 dailyCheck = new DailyCheck_1();
					   if(!(shop_name.equals("后道工段"))){
						   String[] a = asset.split("\\(");
						   char[] c = a[1].toCharArray();
						   char[] asset_no_c = new char[c.length-4];
						   for(int x=0;x<asset_no_c.length;x++){
							   asset_no_c[x] = c[x+3];
						   }
						   String asset_no = String.valueOf(asset_no_c);
						   dailyCheck.setAssetName(a[0]);
						   dailyCheck.setAssetNo(asset_no);
					   }else{
						   dailyCheck.setAssetName(asset);
						   dailyCheck.setAssetNo(asset);
					   }
						dailyCheck.setBatchNo(batch_no);
						dailyCheck.setShopName(shop_name);
						dailyCheck.setProcessName(process_name);
						/*List<Asset> a = shopPlanMapper.findAssetName(asset);
						if(a.size()!=0){
							Asset ass = a.get(0);
							if(ass.getAsset_name()==null){
								ass.setAsset_name("");
							}
							if(ass.getAsset_type()==null){
								ass.setAsset_type("");
							}
							dailyCheck.setAssetName(ass.getAsset_name()+ass.getAsset_type());
						}else{
							dailyCheck.setAssetName("未知设备");
						}
						dailyCheck.setAssetNo(asset);*/
						dailyCheck.setOperator(operator);
						dailyCheck.setState(1);
						dailyCheck.setErrorDate(String.format("%tF", new Date()));
						if(allData.getString("asset_remark").equals("")){
							dailyCheck.setErrorContent("设备异常");
						}else{
							dailyCheck.setErrorContent(allData.getString("asset_remark"));
						}
						dailyCheck.setSendPerson(send_people);
						dailyCheck.setIsRijian(1);
						dailyCheck.setIs_mold(0);
						dailyCheckMapper.insert(dailyCheck);
					   JSONObject mess = new JSONObject();
					   mess.put("type","exception");
					   List<Message> messages = new ArrayList<>();
					   Message message = new Message();
					   message.setBatch_no(batch_no);
					   message.setAsset_no(asset);
					   message.setError_date(String.format("%tF", new Date()));
					   if(allData.getString("asset_remark").equals("")){
							message.setError_content("设备异常");
					   }else{
							message.setError_content(allData.getString("asset_remark"));
					   }
					   message.setIs_latest(1);
					   message.setOperator(operator);
					   message.setProcess_name(process_name);
					   message.setSend_person(send_people);
					   message.setShop_name(shop_name);
					   messages.add(message);
					   if(messages.size()!=0) {
						   mess.put("message", messages);
						   mess.put("what", "设备");
					   }
					   messToSocket(0,mess, messages);
				   }
				   if(mold_state.equals("异常")){
					   DailyCheck_1 dailyCheck = new DailyCheck_1();
						dailyCheck.setBatchNo(batch_no);
						dailyCheck.setShopName(shop_name);
						dailyCheck.setProcessName(process_name);
						dailyCheck.setAssetNo(asset);
						dailyCheck.setOperator(operator);
						dailyCheck.setState(1);
						List<Asset> a = shopPlanMapper.findAssetName(asset);
						if(a.size()!=0){
							Asset ass = a.get(0);
							if(ass.getAsset_name()==null){
								ass.setAsset_name("");
							}
							if(ass.getAsset_type()==null){
								ass.setAsset_type("");
							}
							dailyCheck.setAssetName(ass.getAsset_name()+ass.getAsset_type());
						}else{
							dailyCheck.setAssetName("未知设备");
						}
						dailyCheck.setErrorDate(String.format("%tF", new Date()));
						if(allData.getString("mold_remark").equals("")){
							dailyCheck.setErrorContent("模具异常");
						}else{
							dailyCheck.setErrorContent(allData.getString("mold_remark"));
						}
						dailyCheck.setSendPerson(send_people);
						dailyCheck.setIsRijian(1);
						dailyCheck.setIs_mold(1);
						dailyCheckMapper.insert(dailyCheck);
					   JSONObject mess = new JSONObject();
					   mess.put("type","exception");
					   List<Message> messages = new ArrayList<>();
					   Message message = new Message();
					   message.setBatch_no(batch_no);
					   if(mold==null || mold.equals("")){
						   message.setAsset_no(asset+"的模具:无");
					   }else{
						   message.setAsset_no(asset+"的模具:"+mold);
					   }
					   message.setError_date(String.format("%tF", new Date()));
					   if(allData.getString("mold_remark").equals("")){
							message.setError_content("模具异常");
					   }else{
							message.setError_content(allData.getString("mold_remark"));
					   }
					   message.setIs_latest(1);
					   message.setOperator(operator);
					   message.setProcess_name(process_name);
					   message.setSend_person(send_people);
					   message.setShop_name(shop_name);
					   messages.add(message);
					   if(messages.size()!=0) {
						   mess.put("message", messages);
						   mess.put("what", "模具");
					   }
					   messToSocket(1,mess, messages);
				   }
			   }else {
				   String[] operator_s = operator.split(",");
				   WorkCardNew workCardNew = new WorkCardNew();
				   workCardNew.setCard_id(card_id);
				   workCardNew.setShop_name(shop_name);
				   workCardNew.setProcess_name(process_name);
				   workCardNew.setAsset(asset);
				   workCardNew.setAsset_state(asset_state);
				   /*if(mold!=null){
					   workCardNew.setMold(mold);
				   }*/
				   workCardNew.setMold_state(mold_state);
				   workCardNew.setTotal_num(String.valueOf((Integer.valueOf(hege_num)/operator_s.length)+(Integer.valueOf(buhege_num)/operator_s.length)));
				   workCardNew.setHege_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
				   workCardNew.setTotal_cipin_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
				   String dateString = String.format("%tF", new Date());
				   workCardNew.setProduce_date(dateString);
				   if(temPrice.equals("")){
					   String price = shopPlanMapper.findPrice(material_no, shop_name, process_name);
					   workCardNew.setPrice(price);  
				   }else{
					   workCardNew.setTem_price(temPrice);
				   }
				   JSONObject track_j = JSONObject.fromObject(track_id);
				   for(int j=0;j<operator_s.length;j++) {
					   workCardNew.setOperator(operator_s[j]);
					  /* workCardNew.setTrack_id(Integer.valueOf(track_j.getString(operator_s[j])));
					   if(temPrice.equals("")){
						   shopPlanMapper.updateWorkCard(workCardNew); 
					   }else{
						   shopPlanMapper.updateTemPriceWorkCard(workCardNew);
					   }*/
					   if(track_j.has(operator_s[j])){
						   workCardNew.setTrack_id(Integer.valueOf(track_j.getString(operator_s[j])));
						   if(temPrice.equals("")){
							   shopPlanMapper.updateWorkCard(workCardNew);
							   ProcessTransition processTransition = new ProcessTransition();
							   processTransition.setPlan_no(plan_no);
							   processTransition.setClient_material_no(client_material_no);
							   processTransition.setMaterial_no(material_no);
							   processTransition.setBatch_no(batch_no);
							   processTransition.setShop_name(shop_name);
							   processTransition.setProvider(operator_s[j]);
							   processTransition.setProcess1(process_name);
							   processTransition.setAcceptor(geter);
							   processTransition.setProcess2(last_process);
							   processTransition.setTran_date(dateString);
							   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
							   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
							   shopPlanMapper.updateProcessTransition(processTransition);
						   }else{
							   shopPlanMapper.updateTemPriceWorkCard(workCardNew);
							   ProcessTransition processTransition = new ProcessTransition();
							   processTransition.setPlan_no(plan_no);
							   processTransition.setClient_material_no(client_material_no);
							   processTransition.setMaterial_no(material_no);
							   processTransition.setBatch_no(batch_no);
							   processTransition.setShop_name(shop_name);
							   processTransition.setProvider(operator_s[j]);
							   processTransition.setProcess1(process_name);
							   processTransition.setAcceptor(geter);
							   processTransition.setProcess2(last_process);
							   processTransition.setTran_date(dateString);
							   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
							   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
							   shopPlanMapper.updateProcessTransition(processTransition);
						   } 
						   shopPlanMapper.deleteCipin(track_j.getInt(operator_s[j]));
					   }else{
						   if(temPrice.equals("")){
							   shopPlanMapper.insertWorKCard(workCardNew);
							   ProcessTransition processTransition = new ProcessTransition();
							   processTransition.setPlan_no(plan_no);
							   processTransition.setClient_material_no(client_material_no);
							   processTransition.setMaterial_no(material_no);
							   processTransition.setBatch_no(batch_no);
							   processTransition.setShop_name(shop_name);
							   processTransition.setProvider(operator_s[j]);
							   processTransition.setProcess1(process_name);
							   processTransition.setAcceptor(geter);
							   processTransition.setProcess2(last_process);
							   processTransition.setTran_date(dateString);
							   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
							   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
							   shopPlanMapper.insertProcessTransition(processTransition);
						   }else{
							   shopPlanMapper.insertTemPriceWorKCard(workCardNew);
							   ProcessTransition processTransition = new ProcessTransition();
							   processTransition.setPlan_no(plan_no);
							   processTransition.setClient_material_no(client_material_no);
							   processTransition.setMaterial_no(material_no);
							   processTransition.setBatch_no(batch_no);
							   processTransition.setShop_name(shop_name);
							   processTransition.setProvider(operator_s[j]);
							   processTransition.setProcess1(process_name);
							   processTransition.setAcceptor(geter);
							   processTransition.setProcess2(last_process);
							   processTransition.setTran_date(dateString);
							   processTransition.setQualified_num(String.valueOf(Integer.valueOf(hege_num)/operator_s.length));
							   processTransition.setUnqualified_num(String.valueOf(Integer.valueOf(buhege_num)/operator_s.length));
							   shopPlanMapper.insertProcessTransition(processTransition);
						   }
					   }
					   JSONArray cipin_j = allData.getJSONArray("cipin");
					   for(int i=0;i<cipin_j.size();i++) {
						   JSONObject jsonObject = cipin_j.getJSONObject(i);
						   CipinNew cipinNew = new CipinNew();
						   cipinNew.setTrack_id(workCardNew.getTrack_id());
						   cipinNew.setCipin_type(jsonObject.getString("cipin_type"));
						   cipinNew.setCipin_species(jsonObject.getString("cipin_species"));
						   if(jsonObject.getString("cipin_num").equals("")){
							   cipinNew.setCipin_num("0");
						   }else{
							   int a = 0;
							   a = (Integer.valueOf(jsonObject.getString("cipin_num"))/operator_s.length);
							   cipinNew.setCipin_num(String.valueOf(a));
						   }
						   shopPlanMapper.insertCipin(cipinNew);
					   }
				   }
				   if(asset_state.equals("异常")) {
					   DailyCheck_1 dailyCheck = new DailyCheck_1();
					   if(!(shop_name.equals("后道工段"))){
						   String[] a = asset.split("\\(");
						   char[] c = a[1].toCharArray();
						   char[] asset_no_c = new char[c.length-4];
						   for(int x=0;x<asset_no_c.length;x++){
							   asset_no_c[x] = c[x+3];
						   }
						   String asset_no = String.valueOf(asset_no_c);
						   dailyCheck.setAssetName(a[0]);
						   dailyCheck.setAssetNo(asset_no);
					   }else{
						   dailyCheck.setAssetName(asset);
						   dailyCheck.setAssetNo(asset);
					   }
					   dailyCheck.setBatchNo(batch_no);
					   dailyCheck.setShopName(shop_name);
					   dailyCheck.setProcessName(process_name);
						/*dailyCheck.setAssetNo(asset);
						List<Asset> a = shopPlanMapper.findAssetName(asset);
						if(a.size()!=0){
							Asset ass = a.get(0);
							if(ass.getAsset_name()==null){
								ass.setAsset_name("");
							}
							if(ass.getAsset_type()==null){
								ass.setAsset_type("");
							}
							dailyCheck.setAssetName(ass.getAsset_name()+ass.getAsset_type());
						}else{
							dailyCheck.setAssetName("未知设备");
						}*/
						dailyCheck.setOperator(operator);
						dailyCheck.setState(1);
						dailyCheck.setErrorDate(String.format("%tF", new Date()));
						if(allData.getString("asset_remark").equals("")){
							dailyCheck.setErrorContent("设备异常");
						}else{
							dailyCheck.setErrorContent(allData.getString("asset_remark"));
						}
						dailyCheck.setSendPerson(send_people);
						dailyCheck.setIsRijian(1);
						dailyCheck.setIs_mold(0);
						dailyCheckMapper.insert(dailyCheck);
					   JSONObject mess = new JSONObject();
					   mess.put("type","exception");
					   List<Message> messages = new ArrayList<>();
					   Message message = new Message();
					   message.setBatch_no(batch_no);
					   message.setAsset_no(asset);
					   message.setError_date(dateString);
					   if(allData.getString("asset_remark").equals("")){
							message.setError_content("设备异常");
					   }else{
							message.setError_content(allData.getString("asset_remark"));
					   }
					   message.setIs_latest(1);
					   message.setOperator(operator);
					   message.setProcess_name(process_name);
					   message.setSend_person(send_people);
					   message.setShop_name(shop_name);
					   messages.add(message);
					   if(messages.size()!=0) {
						   mess.put("message", messages);
						   mess.put("what", "设备");
					   }
					   messToSocket(0,mess, messages);
				   }
				   if(mold_state.equals("异常")){
					   DailyCheck_1 dailyCheck = new DailyCheck_1();
						dailyCheck.setBatchNo(batch_no);
						dailyCheck.setShopName(shop_name);
						dailyCheck.setProcessName(process_name);
						dailyCheck.setAssetNo(asset);
						List<Asset> a = shopPlanMapper.findAssetName(asset);
						if(a.size()!=0){
							Asset ass = a.get(0);
							if(ass.getAsset_name()==null){
								ass.setAsset_name("");
							}
							if(ass.getAsset_type()==null){
								ass.setAsset_type("");
							}
							dailyCheck.setAssetName(ass.getAsset_name()+ass.getAsset_type());
						}else{
							dailyCheck.setAssetName("未知设备");
						}
						dailyCheck.setOperator(operator);
						dailyCheck.setState(1);
						dailyCheck.setErrorDate(String.format("%tF", new Date()));
						if(allData.getString("mold_remark").equals("")){
							dailyCheck.setErrorContent("模具异常");
						}else{
							dailyCheck.setErrorContent(allData.getString("mold_remark"));
						}
						dailyCheck.setSendPerson(send_people);
						dailyCheck.setIsRijian(1);
						dailyCheck.setIs_mold(1);
						dailyCheckMapper.insert(dailyCheck);
					   JSONObject mess = new JSONObject();
					   mess.put("type","exception");
					   List<Message> messages = new ArrayList<>();
					   Message message = new Message();
					   message.setBatch_no(batch_no);
					   if(mold==null || mold.equals("")){
						   message.setAsset_no(asset+"的模具:无");
					   }else{
						   message.setAsset_no(asset+"的模具:"+mold);
					   }
					   message.setError_date(dateString);
					   if(allData.getString("mold_remark").equals("")){
							message.setError_content("模具异常");
					   }else{
							message.setError_content(allData.getString("mold_remark"));
					   }
					   message.setIs_latest(1);
					   message.setOperator(operator);
					   message.setProcess_name(process_name);
					   message.setSend_person(send_people);
					   message.setShop_name(shop_name);
					   messages.add(message);
					   if(messages.size()!=0) {
						   mess.put("message", messages);
						   mess.put("what", "模具");
					   }
					   messToSocket(1,mess, messages);
				   }
			   }
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
	
	public void messToSocket(int type,JSONObject jsonObject,List<Message> message) throws Exception {
		Collection<IoSession> sessions = null;
		ErrorMessCache errorMessCache = new ErrorMessCache();
		HashMap<String,String> map = new HashMap<>();
		List<String> weixiu = new ArrayList<>(); //0是设备，1是模具
		if(type==0){
			weixiu = messMapper.findShebeiWeiXiu();
		}else{
			weixiu = messMapper.findMujuWeiXiu();
		}	
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
			//errorMessCache.setMessage(message);
			errorMessCache.setJson_message(jsonObject.toString());
			errorMessCache.setPush_people(map);
			MessCacheManager.getInstance().insertErrorMessCache(errorMessCache);
		}else {
			System.out.println("推送数目："+sessions.size());
			int a = 0;
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(map.get(c[0])!=null) {
					s.write(jsonObject.toString());
					map.remove(c[0]);
					a++;
				}
			}
			if(a != weixiu.size()) {
				errorMessCache.setCheck_id(001);
				//errorMessCache.setMessage(message);
				errorMessCache.setJson_message(jsonObject.toString());
				errorMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertErrorMessCache(errorMessCache);
			}
			System.out.println(map.toString());
		}
	}

	@Override
	public JSONObject updateTemPrice(String json) throws Exception {
		// TODO Auto-generated method stub
		try{
			JSONObject allData = JSONObject.fromObject(json);
			String material_no = allData.getString("material_no");
			String batch_no = allData.getString("batch_no");
			String plan_no = allData.getString("plan_no");
			JSONArray jsonArray = allData.getJSONArray("all");
			ChangeTemPriceVo changeTemPriceVo = new ChangeTemPriceVo();
			changeTemPriceVo.setMaterial_no(material_no);
			changeTemPriceVo.setBatch_no(batch_no);
			changeTemPriceVo.setPlan_no(plan_no);
			List<WorkCardNew> workCardNews = new ArrayList<>();
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				WorkCardNew workCardNew = new WorkCardNew();
				workCardNew.setProcess_name(jsonObject.getString("process_name"));
				workCardNew.setTem_price(jsonObject.getString("price"));
				workCardNews.add(workCardNew);
			}
			changeTemPriceVo.setWorkCards(workCardNews);
			shopPlanMapper.updateTemPrice(changeTemPriceVo);
			JSONObject is_ok = new JSONObject();
	 		is_ok.put("is_ok", "1");
	 		return is_ok;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			JSONObject is_ok = new JSONObject();
	 		is_ok.put("is_ok", "0");
	 		return is_ok;
		}
	}

	@Override
	public void insertNeedMold(MoldReady moldReady) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertNeedMold(moldReady);
	}

	@Override
	public List<MoldReadyFindVo> findNoReadyMold() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findNoReadyMold();
	}

	@Override
	public void updateReadyMold(int id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateReadyMold(id);
	}

	@Override
	public JSONObject findAndUpdateMold(String json) throws Exception {
		// TODO Auto-generated method stub
		try{
			JSONObject allData = JSONObject.fromObject(json);
			Integer type = allData.getInt("type");
			if(type == 1){
				List<MoldReadyFindVo> list = shopPlanMapper.findNoReadyMold();
				if(list.size()!=0){
					JSONArray jsonArray = new JSONArray();
					for(int i=0;i<list.size();i++){
						MoldReadyFindVo moldReadyFindVo = list.get(i);
						String material_no = moldReadyFindVo.getMaterial_no();
						String batch_no = moldReadyFindVo.getBatch_no();
						String plan_no = moldReadyFindVo.getPlan_no();
						String shop_name = moldReadyFindVo.getShop_name();
						List<MoldReady> molds = moldReadyFindVo.getMolds();
						for(int j=0;j<molds.size();j++){
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("material_no",material_no);
							jsonObject.put("batch_no", batch_no);
							jsonObject.put("plan_no", plan_no);
							jsonObject.put("shop_name", shop_name);
							jsonObject.put("ready_id", molds.get(j).getId());
							jsonObject.put("mold", molds.get(j).getMold());
							jsonObject.put("process_name", molds.get(j).getProcess_name());
							jsonArray.add(jsonObject);
						}
					}
					JSONObject is_ok = new JSONObject();
					is_ok.put("is_success", 1);
					is_ok.put("all", jsonArray);
					return is_ok;
				}else{
					JSONObject is_ok = new JSONObject();
			 		is_ok.put("is_success",2);
			 		return is_ok;
				}
			}else{
				Integer ready_id = allData.getInt("ready_id");
				shopPlanMapper.updateReadyMold(ready_id);
				JSONObject is_ok = new JSONObject();
				is_ok.put("is_success", 1);
				return is_ok;
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			JSONObject is_ok = new JSONObject();
	 		is_ok.put("is_success",0);
	 		return is_ok;
		}
	}

	@Override
	public String findNeedMold(String material_no, String shop_name,
			String process_name, String client_material_no) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findNeedMold(material_no, shop_name, process_name, client_material_no);
	}

	@Override
	public JSONObject updateShopPlan(String jsonStr) throws Exception {
		// TODO Auto-generated method stub
		try {
			JSONObject allData = JSONObject.fromObject(jsonStr);
			   String material = allData.getString("material_no");
			   String plan_no = allData.getString("plan_no");
			   String plan_num = allData.getString("plan_num");
			   String batch_no = allData.getString("batch_no");
			   String shop_name = allData.getString("shop_name");
			   String process_name = allData.getString("process_name");
			   int is_change = Integer.valueOf(allData.getString("is_change"));
			   int plan_id_yuan = Integer.valueOf(allData.getString("plan_id"));
			   List<ProductionPlan> productionPlans = shopPlanMapper.findProductionPlan(plan_no, material);
			   if(productionPlans.size()!=0) {
				   ProductionPlan plan = productionPlans.get(0);
				   ShopPlan shopPlan = new ShopPlan();
				   shopPlan.setClient(plan.getClient());
				   shopPlan.setPlan_no(plan_no);
				   shopPlan.setClient_material_no(plan.getClient_material_no());
				   shopPlan.setMaterial_no(material);
				   shopPlan.setProduct_spec(plan.getProduct_spec());
				   shopPlan.setBatch_no(batch_no);
				   shopPlan.setShop_name(shop_name);
				   shopPlan.setMaterial_name(plan.getProduct_name());
				   Date currentTime = new Date();
				   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				   String dateString = formatter.format(currentTime);
				   shopPlan.setPlan_date(dateString);
				   shopPlan.setPlan_num(plan_num);
				   shopPlan.setProcess_sort(process_name);
				   shopPlanMapper.insertNewShopPlan(shopPlan);
				   if(is_change == 1) {
					   shopPlanMapper.updateShopPlan(plan_id_yuan);
				   }
				   int plan_id = shopPlan.getPlan_id();
				   String[] process = process_name.split(",");
				   for(int i=0;i<process.length;i++) {
					   String s = process[i];
					   JSONArray jsonArray = allData.getJSONArray(s);
					   if(jsonArray!=null) {
						   for(int j=0;j<jsonArray.size();j++) {
							   Task_New task_New = new Task_New();
							   JSONObject jsonObject = jsonArray.getJSONObject(j);
							   task_New.setAsset(jsonObject.getString("asset"));
							   task_New.setOperator(jsonObject.getString("operator"));
							   task_New.setPlan_id(plan_id);
							   task_New.setProcess_name(process[i]);
							   if(shop_name.equals("冲压工段") && is_change!=1){
								   String mold = shopPlanMapper.findNeedMold(material, shop_name, process[i],shopPlan.getClient_material_no());
								   if(!(mold==null || mold.equals(""))){
									   MoldReady moldReady = new MoldReady();
									   moldReady.setMold(mold);
									   moldReady.setPlan_id(plan_id);
									   moldReady.setProcess_name(process[i]);
									   task_New.setMold(mold);
									   shopPlanMapper.insertNeedMold(moldReady); 
								   }else{
									   MoldReady moldReady = new MoldReady();
									   moldReady.setMold("无");
									   moldReady.setPlan_id(plan_id);
									   moldReady.setProcess_name(process[i]);
									   task_New.setMold("无");
									   shopPlanMapper.insertNeedMold(moldReady); 
								   }
							   }
							   shopPlanMapper.insertTask(task_New);
						   }
					   }else if(is_change == 1) {
						   List<Task_New> task_News = shopPlanMapper.findTask(plan_id_yuan,process[i]);
						   if(task_News.size()!=0) {
							   for(int j=0;j<task_News.size();j++) {
								   Task_New task_New = task_News.get(j);
								   task_New.setPlan_id(plan_id);
								   shopPlanMapper.insertTask(task_New);
								   /*String mold = shopPlanMapper.findNeedMold(material, shop_name, process[i],shopPlan.getClient_material_no());
								   if(!(mold==null || mold.equals(""))){
									   MoldReady moldReady = new MoldReady();
									   moldReady.setMold(mold);
									   moldReady.setPlan_id(plan_id);
									   moldReady.setProcess_name(task_New.getProcess_name());
									   task_New.setMold(mold);
									   shopPlanMapper.insertTask(task_New);
									   shopPlanMapper.insertNeedMold(moldReady); 
								   }else{
									   MoldReady moldReady = new MoldReady();
									   moldReady.setMold("无");
									   moldReady.setPlan_id(plan_id);
									   moldReady.setProcess_name(process[i]);
									   task_New.setMold("无");
									   shopPlanMapper.insertTask(task_New);
									   shopPlanMapper.insertNeedMold(moldReady); 
								   }*/
							   }
						   }
					   }
				   }
				   if(shop_name.equals("冲压工段") && is_change!=1){
					   List<String> gongduanzhang = new ArrayList<>();
					   gongduanzhang = messMapper.findMujuWeiXiu();
					   ShopPlan shopPlan_s  = pushShopPlanMapper.findShopPlanAndProcess(plan_id);
					   if(shopPlan_s!=null) {
							  String material_no_s = shopPlan_s.getMaterial_no();
							  String batch_no_s = shopPlan_s.getBatch_no();
							  String shop_name_s = shopPlan_s.getShop_name();
							  String plan_no_s = shopPlan_s.getPlan_no();
							  String client_material_no_s = shopPlan_s.getClient_material_no();
							  String plan_num_s = shopPlan_s.getPlan_num();
							  JSONObject message = new JSONObject();
							  List<Task> tasks_s = shopPlan_s.getTasks();
							  try {
								  message.put("type", "plan");
								  message.put("material_no",material_no_s);
								  message.put("batch_no",batch_no_s);
								  message.put("shop_name",shop_name_s);
								  message.put("plao_no",plan_no_s);
								  message.put("client_material_no",client_material_no_s);
								  message.put("plan_num", plan_num_s);
								  JSONArray tasks_J = JSONArray.fromObject(tasks_s);
								  message.put("details",tasks_J);
								  message.put("content", "冲压工段有新的排产计划，请查看并准备模具！！");
								  planToSocket(message.toString(), gongduanzhang);
						}catch(Exception e) {
							e.printStackTrace();
							JSONObject is_ok = new JSONObject();
							is_ok.put("is_ok", "0");
					 		return is_ok;
						}
					}
				   }
				JSONObject is_ok = new JSONObject();
		 		is_ok.put("is_ok", "1");
		 		return is_ok;
			}else{
				JSONObject is_ok = new JSONObject();
		 		is_ok.put("is_ok", "0");
		 		return is_ok;
			}
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			JSONObject is_ok = new JSONObject();
	 		is_ok.put("is_ok", "0");
	 		return is_ok;
		}
	}
	
	public static void planToSocket(String string,List<String> user) throws Exception {
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
			PlanMessCache planMessCache = new PlanMessCache();
			planMessCache.setMessage(string);
			planMessCache.setPush_people(map);
			MessCacheManager.getInstance().insertPlanMessCache(planMessCache);
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
				PlanMessCache planMessCache = new PlanMessCache();
				planMessCache.setMessage(string);
				planMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertPlanMessCache(planMessCache);
			}
		}
	}

	@Override
	public List<WorkCardNew> findTemPrice(String material_no, String plan_no,
			String batch_no, String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findTemPrice(material_no, plan_no, batch_no, shop_name);
	}

	@Override
	public List<WorkCardNew> findAndUpdateTemPrice(String material_no,
			String plan_no, String batch_no, String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findAndUpdateTemPrice(material_no, plan_no, batch_no, shop_name);
	}

	@Override
	public List<Asset> findAssetName(String asset_no) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findAssetName(asset_no);
	}

	@Override
	public List<GetMaterial> findNoPushGetMaterial() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findNoPushGetMaterial();
	}

	@Override
	public void updateGetMaterialPush(int get_material_id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateGetMaterialPush(get_material_id);
	}

	@Override
	public List<GetMaterial> findNoGetGetMaterial() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findNoGetGetMaterial();
	}

	@Override
	public void updateMaterialGet(int get_material_id) throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.updateMaterialGet(get_material_id);
	}

	@Override
	public List<String> findRealProcessOneWorker(int plan_id, String operator)
			throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findRealProcessOneWorker(plan_id, operator);
	}

	@Override
	public void insertProcessTransition(ProcessTransition processTransition)
			throws Exception {
		// TODO Auto-generated method stub
		shopPlanMapper.insertProcessTransition(processTransition);
	}

	@Override
	public List<TrackCard> findCardId(String plan_no, String material_no,
			String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findCardId(plan_no, material_no, batch_no);
	}

	@Override
	public List<String> findProcessAcceptor(ProcessTransition processTransition)
			throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findProcessAcceptor(processTransition);
	}

	@Override
	public List<String> findProcessAcceptorTogether(
			ProcessTransitionVo processTransitionVo) throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findProcessAcceptorTogether(processTransitionVo);
	}

	@Override
	public List<MonthPlan> findMonthPlan() throws Exception {
		// TODO Auto-generated method stub
		return shopPlanMapper.findMonthPlan();
	}
}
