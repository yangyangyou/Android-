package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.mapper.DailyCheckMapper;
import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.CipinNew;
import cn.itcast.ssm.po.CipinType;
import cn.itcast.ssm.po.DepartmentVo;
import cn.itcast.ssm.po.ErrorMessCache;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.po.MonthPlan;
import cn.itcast.ssm.po.OperatorVo;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.ProcessTransition;
import cn.itcast.ssm.po.ProcessTransitionVo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.po.TrackCard;
import cn.itcast.ssm.po.WorkCardNew;
import cn.itcast.ssm.service.MessService;
import cn.itcast.ssm.service.PushShopPlanService;
import cn.itcast.ssm.service.ShopPlanService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/shopPlan")
public class ShopPlanController {
	@Autowired
	private ShopPlanService shopPlanService;
	@Autowired
	private MessService messService;
	@Autowired
	private PushShopPlanService pushShopPlanService; 
	@Autowired
	private DailyCheckMapper dailyCheckMapper;
	
	@RequestMapping("/findProcess")
	@ResponseBody
	public void findProcess(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject allData = JSONObject.fromObject(jsonStr);
		   JSONObject responseData = new JSONObject();
		   JSONObject process_json = new JSONObject();
		   JSONObject operator_json = new JSONObject();
		   JSONArray task_json = new JSONArray();
		   String shop_name = allData.getString("shop_name");
		   String material_no = allData.getString("material_no");
		   String batch_no = allData.getString("batch_no");
		   String plan_no = allData.getString("plan_no");
		   List<Integer> plan_id = shopPlanService.findPlanId(material_no, batch_no, plan_no, shop_name);
		   List<ProductionPlan> plans = shopPlanService.findProductionPlan(plan_no, material_no);
		   List<String> process_name = new ArrayList<>();
		   if(plans.size()==0) {
			   responseData.put("plan_id", "-1");
			   responseData.put("num","");
		   }else if(plan_id.size()!=0) {
			   responseData.put("plan_id",String.valueOf(plan_id.get(0)));
			   responseData.put("num", shopPlanService.findPlanNum(plan_id.get(0)));
			   //process_name = shopPlanService.findRealProcess(plan_id.get(0));
			   process_name = shopPlanService.findProcess(material_no, shop_name);
		   }else {
			   responseData.put("plan_id","0");
			   responseData.put("num","");
			   process_name = shopPlanService.findProcess(material_no, shop_name);
		   }
		   List<Person> operator = shopPlanService.findOperator(shop_name);
		   try {
			   if(process_name.size()!=0 && operator.size()!=0){
				   for(int i = 0;i<process_name.size();i++) {
					  process_json.put(String.valueOf(i), process_name.get(i));
					  if(plan_id.size()!=0) {
						  List<Task_New> task_News = shopPlanService
								  .findTask(plan_id.get(0), process_name.get(i));
						  if(task_News.size()!=0) {
							  for(int k=0;k<task_News.size();k++) {
								  JSONObject jsonObject = new JSONObject();
								  jsonObject.put("asset", task_News.get(k).getAsset());
								  jsonObject.put("operator", task_News.get(k).getOperator());
								  jsonObject.put("process_name", process_name.get(i));
								  task_json.add(jsonObject);
							  }
						  }
					  }
					  responseData.put("task", task_json);
				   }
				   for(int j =0;j<operator.size();j++) {
					   operator_json.put(String.valueOf(j), operator.get(j).getPerson_name()
							   +"(工号:"+operator.get(j).getNumber()+")");
				   }
				   responseData.put("size_process",process_name.size());
				   responseData.put("size_operator",operator.size());
				   responseData.put("process_name",process_json);
				   responseData.put("operator",operator_json);
		           response.setContentType("text/html;charset=utf-8");
		   		   PrintWriter print = response.getWriter();
		   		   print.write(responseData.toString());
		   		   System.out.println(responseData.toString());
		   		   print.flush();
		   		   print.close();
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	}
	
	@RequestMapping("/findProcessOneWorker")
	@ResponseBody
	public void findProcessOneWorker(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject allData = JSONObject.fromObject(jsonStr);
		   JSONObject responseData = new JSONObject();
		   JSONObject process_json = new JSONObject();
		   JSONArray task_json = new JSONArray();
		   String shop_name = allData.getString("shop_name");
		   String material_no = allData.getString("material_no");
		   String batch_no = allData.getString("batch_no");
		   String plan_no = allData.getString("plan_no");
		   String operator = allData.getString("operator");
		   List<Integer> plan_id = shopPlanService.findPlanId(material_no, batch_no, plan_no, shop_name);
		   List<ProductionPlan> plans = shopPlanService.findProductionPlan(plan_no, material_no);
		   List<String> process_name = new ArrayList<>();
		   if(plans.size()==0) {
			   responseData.put("plan_id", "-1");
			   responseData.put("num","");
		   }else if(plan_id.size()!=0) {
			   responseData.put("plan_id",String.valueOf(plan_id.get(0)));
			   responseData.put("num", shopPlanService.findPlanNum(plan_id.get(0)));
			   process_name = shopPlanService.findRealProcessOneWorker(plan_id.get(0),"%"+operator+"%");
		   }else {
			   responseData.put("plan_id","0");
			   responseData.put("num","");
		   }
		   try {
			   if(process_name.size()!=0) {
				   for(int i = 0;i<process_name.size();i++) {
					  process_json.put(String.valueOf(i), process_name.get(i));
					  if(plan_id.size()!=0) {
						  List<Task_New> task_News = shopPlanService
								  .findTask(plan_id.get(0), process_name.get(i));
						  if(task_News.size()!=0) {
							  for(int k=0;k<task_News.size();k++) {
								  JSONObject jsonObject = new JSONObject();
								  jsonObject.put("asset", task_News.get(k).getAsset());
								  jsonObject.put("operator", task_News.get(k).getOperator());
								  jsonObject.put("process_name", process_name.get(i));
								  task_json.add(jsonObject);
							  }
						  }
					  }
					  responseData.put("task", task_json);
				   }
				   responseData.put("size_process",process_name.size());
				   responseData.put("process_name",process_json);
		           response.setContentType("text/html;charset=utf-8");
		   		   PrintWriter print = response.getWriter();
		   		   print.write(responseData.toString());
		   		   System.out.println(responseData.toString());
		   		   print.flush();
		   		   print.close();
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	}
	
	@RequestMapping("/findTask")
	@ResponseBody
	public void findTask(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject allData = JSONObject.fromObject(jsonStr);
		   JSONObject responseData = new JSONObject();
		   JSONArray task = new JSONArray();
		   int plan_id = Integer.valueOf(allData.getString("plan_id"));
		   String process_name = allData.getString("process_name");
		   List<Task_New> task_list = shopPlanService.findTask(plan_id, process_name);
		   if(task_list.size()!=0) {
			   responseData.put("size", String.valueOf(task_list.size()));
			   for(int i=0;i<task_list.size();i++) {
				   JSONObject jsonObject = new JSONObject();
				   jsonObject.put("asset", task_list.get(i).getAsset());
				   jsonObject.put("operator",task_list.get(i).getOperator());
				   task.add(jsonObject);
			   }
			   responseData.put("task",task);
	           response.setContentType("text/html;charset=utf-8");
	   		   PrintWriter print = response.getWriter();
	   		   print.write(responseData.toString());
	   		   print.flush();
	   		   print.close();
		   }else {
			   responseData.put("size","0");
	           response.setContentType("text/html;charset=utf-8");
	   		   PrintWriter print = response.getWriter();
	   		   print.write(responseData.toString());
	   		   print.flush();
	   		   print.close();
		   }
	}
	
	@RequestMapping("/makeShopPlan")
	@ResponseBody
	public void makeShopPlan(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = shopPlanService.updateShopPlan(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/getGenZong")
	@ResponseBody
	public void getGenZong(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject allData = JSONObject.fromObject(jsonStr);
		   JSONObject responseData = new JSONObject();
		   try {
			   String together = allData.getString("together");
			   String plan_no = allData.getString("plan_no");
			   String material_no = allData.getString("material_no");
			   String batch_no = allData.getString("batch_no");
			   String shop_name = allData.getString("shop_name");
			   String process_name = allData.getString("process_name");
			   String asset = allData.getString("asset");
			   String operator = allData.getString("operator");
			   List<TrackCard> card = shopPlanService.findCardId(plan_no, material_no, batch_no);
			   if(card.size()!=0) {
				   List<CipinType> gongfei_Types = shopPlanService.findCipinType(shop_name, "工废");
				   List<CipinType> liaofei_Types = shopPlanService.findCipinType(shop_name, "料废");
				   JSONObject gongfei_json = new JSONObject();
				   JSONObject liaofei_json = new JSONObject();
				   for(int i=0;i<gongfei_Types.size();i++) {
					   gongfei_json.put(String.valueOf(i),gongfei_Types.get(i).getCipin_detail());
				   }
				   for(int i=0;i<liaofei_Types.size();i++) {
					   liaofei_json.put(String.valueOf(i), liaofei_Types.get(i).getCipin_detail());
				   }
				   responseData.put("gongfei_type", gongfei_json);
				   responseData.put("liaofei_type", liaofei_json);
				   responseData.put("client_material_no",card.get(0).getClient_material_no());
				   responseData.put("card_id", String.valueOf(card.get(0).getCard_id()));
				   if(together.equals("0")) {
					   ProcessTransition processTransition = new ProcessTransition();
					   processTransition.setPlan_no(plan_no);
					   processTransition.setClient_material_no(card.get(0).getClient_material_no());
					   processTransition.setMaterial_no(material_no);
					   processTransition.setBatch_no(batch_no);
					   processTransition.setShop_name(shop_name);
					   processTransition.setProvider(operator);
					   processTransition.setProcess1(process_name);
					   List<String> acceptor_s = shopPlanService.findProcessAcceptor(processTransition);
					   if(acceptor_s.size()!=0){
						   responseData.put("acceptor", acceptor_s.get(0));
					   }else{
						   responseData.put("acceptor", "");
					   }
					   List<WorkCardNew> workCardNews = shopPlanService.findWorkCard(card.get(0).getCard_id(), shop_name, process_name, asset, operator);
					    if(workCardNews.size()!=0) {
					    	WorkCardNew workCardNew = workCardNews.get(0);
					    	Map<String,String> map = new HashMap<>();
					    	map.put(operator, String.valueOf(workCardNew.getTrack_id()));
					    	responseData.put("track_id",JSONObject.fromObject(map).toString());
					    	JSONObject jsonObject = new JSONObject();
					    	jsonObject.put("asset_state", workCardNew.getAsset_state());
					    	jsonObject.put("mold_state", workCardNew.getMold_state());
					    	jsonObject.put("hege_num", workCardNew.getHege_num());
					    	jsonObject.put("buhege_num", workCardNew.getTotal_cipin_num());
					    	if(workCardNew.getTem_price()==null || workCardNews.get(0).getTem_price().equals("")){
					    		jsonObject.put("tem_price","");
					    	}else{
					    		jsonObject.put("tem_price",workCardNew.getTem_price());
					    	}
					    	responseData.put("work_card", jsonObject);
					    	List<CipinNew> cipinNews = shopPlanService.findCipin(workCardNew.getTrack_id());
					    	JSONArray cipin_json = new JSONArray();
					    	if(cipinNews.size()!=0) {
					    		for(int i=0;i<cipinNews.size();i++) {
					    			CipinNew cipinNew = cipinNews.get(i);
							    	JSONObject cipin_j = new JSONObject();
							    	cipin_j.put("cipin_type",cipinNew.getCipin_type());
							    	cipin_j.put("cipin_species", cipinNew.getCipin_species());
							    	cipin_j.put("cipin_num", cipinNew.getCipin_num());
							    	cipin_json.add(cipin_j);
					    		}
					    		responseData.put("cipin",cipin_json);
						    	response.setContentType("text/html;charset=utf-8");
					 			PrintWriter print = response.getWriter();
					 			print.write(responseData.toString());
					 			print.flush();
					 			print.close();
					    	}
					    }else {
					    	responseData.put("track_id","0");
					    	response.setContentType("text/html;charset=utf-8");
				 			PrintWriter print = response.getWriter();
				 			print.write(responseData.toString());
				 			print.flush();
				 			print.close();
					    }
				   }else if(together.equals("1")) {
					   JSONArray cipin_json = new JSONArray();
					   String[] operator_s = operator.split(",");
					   OperatorVo operatorVo = new OperatorVo();
					   operatorVo.setAsset(asset);
					   operatorVo.setCard_id(card.get(0).getCard_id());
					   operatorVo.setProcess_name(process_name);
					   operatorVo.setShop_name(shop_name);
					   List<String> s = new ArrayList<>();
					   for(int i=0;i<operator_s.length;i++) {
						   s.add(operator_s[i]);
					   }
					   operatorVo.setOperator(s);
					   ProcessTransitionVo processTransition = new ProcessTransitionVo();
					   processTransition.setPlan_no(plan_no);
					   processTransition.setClient_material_no(card.get(0).getClient_material_no());
					   processTransition.setMaterial_no(material_no);
					   processTransition.setBatch_no(batch_no);
					   processTransition.setShop_name(shop_name);
					   processTransition.setOperators(s);
					   processTransition.setProcess1(process_name);
					   List<String> acceptor_s = shopPlanService.findProcessAcceptorTogether(processTransition);
					   if(acceptor_s.size()!=0){
						   StringBuilder builder = new StringBuilder();
						   for(int k=0;k<acceptor_s.size();k++){
							   if(k!=acceptor_s.size()-1){
								   builder.append(acceptor_s.get(k)+",");
							   }else{
								   builder.append(acceptor_s.get(k));
							   }
						   }
						   responseData.put("acceptor", builder.toString());
					   }else{
						   responseData.put("acceptor", "");
					   }
					   List<WorkCardNew> workCardNews = shopPlanService.findWorkCardTogether(operatorVo);
					   if(workCardNews.size()!=0) {
					    	Map<String,String> map = new HashMap<>();
					    	int hegepin = 0;
					    	int buhegepin = 0;
					    	Map<String,Integer> gongfei = new HashMap<>();
				    		Map<String,Integer> liaofei = new HashMap<>();
					    	for(int i=0;i<workCardNews.size();i++) {
					    		WorkCardNew workCardNew = workCardNews.get(i);
					    		String track_id = String.valueOf(workCardNew.getTrack_id());
					    		map.put(workCardNew.getOperator(),track_id);
					    		hegepin += Integer.valueOf(workCardNew.getHege_num());
					    		buhegepin += Integer.valueOf(workCardNew.getTotal_cipin_num());
					    		List<CipinNew> cipinNews = shopPlanService.findCipin(workCardNew.getTrack_id());
					    		if(cipinNews.size()!=0) {
						    		for(int j=0;j<cipinNews.size();j++) {
						    			CipinNew cipinNew = cipinNews.get(j);
								    	if(cipinNew.getCipin_type().equals("工废")) {
								    		if(gongfei.get(cipinNew.getCipin_species())==null) {
								    			gongfei.put(cipinNew.getCipin_species(),Integer.valueOf(cipinNew.getCipin_num()));
								    		}else {
								    			Integer a = gongfei.get(cipinNew.getCipin_species());
								    			gongfei.put(cipinNew.getCipin_species(), a+Integer.valueOf(cipinNew.getCipin_num()));
								    		}
								    	}
								    	if(cipinNew.getCipin_type().equals("料废")) {
								    		if(liaofei.get(cipinNew.getCipin_species())==null) {
								    			liaofei.put(cipinNew.getCipin_species(),Integer.valueOf(cipinNew.getCipin_num()));
								    		}else {
								    			Integer a = liaofei.get(cipinNew.getCipin_species());
								    			liaofei.put(cipinNew.getCipin_species(), a+Integer.valueOf(cipinNew.getCipin_num()));
								    		}
								    	}
						    		}
						    	}
					    	}
					    	for(Map.Entry<String,Integer> entry:gongfei.entrySet()) {
					    		JSONObject jsonObject = new JSONObject();
					    		jsonObject.put("cipin_type","工废");
					    		jsonObject.put("cipin_species", entry.getKey());
					    		jsonObject.put("cipin_num",String.valueOf(entry.getValue()));
					    		cipin_json.add(jsonObject);
					    	}
					    	for(Map.Entry<String,Integer> entry:liaofei.entrySet()) {
					    		JSONObject jsonObject = new JSONObject();
					    		jsonObject.put("cipin_type","料废");
					    		jsonObject.put("cipin_species", entry.getKey());
					    		jsonObject.put("cipin_num",String.valueOf(entry.getValue()));
					    		cipin_json.add(jsonObject);
					    	}
					    	responseData.put("track_id",JSONObject.fromObject(map).toString());
					    	JSONObject jsonObject = new JSONObject();
					    	jsonObject.put("asset_state", workCardNews.get(0).getAsset_state());
					    	jsonObject.put("mold_state", workCardNews.get(0).getMold_state());
					    	jsonObject.put("hege_num", String.valueOf(hegepin));
					    	jsonObject.put("buhege_num", String.valueOf(buhegepin));
					    	if(workCardNews.get(0).getTem_price()==null || workCardNews.get(0).getTem_price().equals("")){
					    		jsonObject.put("tem_price","");
					    	}else{
					    		jsonObject.put("tem_price",workCardNews.get(0).getTem_price());
					    	}
					    	responseData.put("work_card", jsonObject);
				    		responseData.put("cipin",cipin_json);
					    	response.setContentType("text/html;charset=utf-8");
				 			PrintWriter print = response.getWriter();
				 			print.write(responseData.toString());
				 			print.flush();
				 			print.close();
					    }else {
					    	responseData.put("track_id","0");
					    	response.setContentType("text/html;charset=utf-8");
				 			PrintWriter print = response.getWriter();
				 			print.write(responseData.toString());
				 			print.flush();
				 			print.close();
					    }
				   }
			   }else {
				    responseData.put("card_id", "0");
		 		    response.setContentType("text/html;charset=utf-8");
		 			PrintWriter print = response.getWriter();
		 			print.write(responseData.toString());
		 			print.flush();
		 			print.close();
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	}
	
	@RequestMapping("/makeGenzong")
	@ResponseBody
	public void makeGenzong(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = shopPlanService.updateGenZong(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
 	}
	
	public void messToSocket(int type,JSONObject jsonObject,List<Message> message) throws Exception {
		Collection<IoSession> sessions = null;
		ErrorMessCache errorMessCache = new ErrorMessCache();
		HashMap<String,String> map = new HashMap<>();
		List<String> weixiu = new ArrayList<>(); //0是设备，1是模具
		if(type==0){
			weixiu = messService.findShebeiWeiXiu();
		}else{
			weixiu = messService.findMujuWeiXiu();
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
			errorMessCache.setMessage(message);
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
				errorMessCache.setMessage(message);
				errorMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertErrorMessCache(errorMessCache);
			}
			System.out.println(map.toString());
		}
	}
	
	@RequestMapping("/getProductionPlan")
	@ResponseBody
	public void getProductionPlan(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine())!=null) {
			json.append(line);
		}
		String jsonStr = json.toString();
		JSONObject jsonObject_1 = JSONObject.fromObject(jsonStr);
		List<ProductionPlan> productionPlans = shopPlanService.findNewProductionPlan("%"+jsonObject_1.getString("shop_name")+"%");
		JSONObject responseData = new JSONObject();
		JSONArray productionPlan_J = new JSONArray();
		JSONObject shopdelivert_J = new JSONObject();
		if(productionPlans.size()!=0){
			responseData.put("is","1");
			for(int i=0;i<productionPlans.size();i++){
				ProductionPlan productionPlan = productionPlans.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("plan_id",String.valueOf(productionPlan.getPlan_id()));
				jsonObject.put("client", productionPlan.getClient());
				jsonObject.put("order_date",productionPlan.getOrder_date());
				jsonObject.put("order_no", productionPlan.getOrder_no());
				jsonObject.put("plan_no",productionPlan.getPlan_no());
				jsonObject.put("client_material_no", productionPlan.getClient_material_no());
				jsonObject.put("material_no", productionPlan.getMaterial_no());
				jsonObject.put("product_spec", productionPlan.getProduct_spec());
				jsonObject.put("product_name", productionPlan.getProduct_name());
				jsonObject.put("plan_num", productionPlan.getPlan_num());
				if(productionPlan.getStart_num()==null || productionPlan.getStart_num().equals("")){
					jsonObject.put("start_num","无");
				}else{
					jsonObject.put("start_num",productionPlan.getStart_num());
				}
				jsonObject.put("sort", productionPlan.getSort());
				jsonObject.put("remark", productionPlan.getRemark());
				jsonObject.put("make_people",productionPlan.getMake_people());
				jsonObject.put("make_time", productionPlan.getMake_time());
				productionPlan_J.add(jsonObject);
				List<ShopDelivery> shopDeliveries = shopPlanService.findShopDelivery(productionPlan.getPlan_id());
				JSONArray jsonArray = new JSONArray();
				if(shopDeliveries.size()!=0){
					for(int j=0;j<shopDeliveries.size();j++){
						ShopDelivery shopDelivery = shopDeliveries.get(j);
						JSONObject jsonObject2 = new JSONObject();
						jsonObject2.put("plan_id",shopDelivery.getPlan_id());
						jsonObject2.put("shop_name",shopDelivery.getShop_name());
						if(shopDelivery.getDelivery_num()==null || shopDelivery.getDelivery_num().equals("")){
							jsonObject2.put("delivery_num", "无");
						}else{
							jsonObject2.put("delivery_num", shopDelivery.getDelivery_num());
						}
						jsonObject2.put("plan_finish_date", shopDelivery.getPlan_finish_date());
						if(shopDelivery.getSend_num()==null || shopDelivery.getSend_num().equals("")){
							jsonObject2.put("send_num", "无");
						}else{
							jsonObject2.put("send_num", shopDelivery.getSend_num());
						}
						jsonObject2.put("send_date", shopDelivery.getSend_date());
						jsonArray.add(jsonObject2);
					}
				}
				shopdelivert_J.put(String.valueOf(i),jsonArray);
			}
			responseData.put("production_plan",productionPlan_J);
			responseData.put("shop_delivery", shopdelivert_J);
			response.setContentType("text/html;charset=utf-8");
 			PrintWriter print = response.getWriter();
 			print.write(responseData.toString());
 			print.flush();
 			print.close();
		}else{
			responseData.put("is","0");
			response.setContentType("text/html;charset=utf-8");
 			PrintWriter print = response.getWriter();
 			print.write(responseData.toString());
 			print.flush();
 			print.close();
		}
	}
	
	@RequestMapping("/pushProductionPlan")
	@ResponseBody
	public void pushProductionPlan(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject responseData = new JSONObject();
		List<String> plan_id = new ArrayList<>();
		try{
			JSONArray productionPlan_J = new JSONArray();
			JSONObject shopdelivert_J = new JSONObject();
			List<ProductionPlan> productionPlans = shopPlanService.findPushProductionPlan();
			if(productionPlans.size()!=0){
				responseData.put("is","1");
				responseData.put("type","production_plan");
				for(int i=0;i<productionPlans.size();i++){
					ProductionPlan productionPlan = productionPlans.get(i);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("plan_id",String.valueOf(productionPlan.getPlan_id()));
					plan_id.add(String.valueOf(productionPlan.getPlan_id()));
					jsonObject.put("client", productionPlan.getClient());
					jsonObject.put("order_date",productionPlan.getOrder_date());
					jsonObject.put("order_no", productionPlan.getOrder_no());
					jsonObject.put("plan_no",productionPlan.getPlan_no());
					jsonObject.put("client_material_no", productionPlan.getClient_material_no());
					jsonObject.put("material_no", productionPlan.getMaterial_no());
					jsonObject.put("product_spec", productionPlan.getProduct_spec());
					jsonObject.put("product_name", productionPlan.getProduct_name());
					jsonObject.put("plan_num", productionPlan.getPlan_num());
					jsonObject.put("start_num",productionPlan.getStart_num());
					jsonObject.put("sort", productionPlan.getSort());
					jsonObject.put("remark", productionPlan.getRemark());
					jsonObject.put("make_people",productionPlan.getMake_people());
					jsonObject.put("make_time", productionPlan.getMake_time());
					DepartmentVo departmentVo = new DepartmentVo();
					List<String> d = new ArrayList<>();
					List<String> gongduanzhang = new ArrayList<>();
					String[] e = productionPlan.getSort().split(",");
					for(int k=0;k<e.length;k++){
						d.add(e[k]);
					}
					departmentVo.setDepartment(d);
					gongduanzhang = pushShopPlanService.findGongDuanZhang(departmentVo);
					List<String> fahuo = pushShopPlanService.findFahuoAndBafahuoyoqian();
					gongduanzhang.addAll(fahuo);
					jsonObject.put("user",gongduanzhang);
					productionPlan_J.add(jsonObject);
					List<ShopDelivery> shopDeliveries = shopPlanService.findShopDelivery(productionPlan.getPlan_id());
					if(shopDeliveries.size()!=0){
						JSONArray jsonArray = new JSONArray();
						for(int j=0;j<shopDeliveries.size();j++){
							ShopDelivery shopDelivery = shopDeliveries.get(j);
							JSONObject jsonObject2 = new JSONObject();
							jsonObject2.put("plan_id",shopDelivery.getPlan_id());
							jsonObject2.put("shop_name",shopDelivery.getShop_name());
							jsonObject2.put("delivery_num", shopDelivery.getDelivery_num());
							jsonObject2.put("plan_finish_date", shopDelivery.getPlan_finish_date());
							jsonObject2.put("send_num", shopDelivery.getSend_num());
							jsonObject2.put("send_date", shopDelivery.getSend_date());
							jsonArray.add(jsonObject2);
						}
						shopdelivert_J.put(String.valueOf(i),jsonArray);
					}
				}
				responseData.put("production_plan",productionPlan_J);
				responseData.put("shop_delivery", shopdelivert_J);
				responseData.put("plan_id", plan_id);
				response.setContentType("text/html;charset=utf-8");
	 			PrintWriter print = response.getWriter();
	 			print.write(responseData.toString());
	 			print.flush();
	 			print.close();
			}else{
				responseData.put("is","0");
				response.setContentType("text/html;charset=utf-8");
	 			PrintWriter print = response.getWriter();
	 			print.write(responseData.toString());
	 			print.flush();
	 			print.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateProductionPlan")
	@ResponseBody
	public void updateProductionPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader str = request.getReader();
		if((line = str.readLine())!=null) {
			json.append(line);
		}
		String jsonStr = json.toString();
		try{
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			List<String> plan_id = new ArrayList<>();
			plan_id = (List<String>) jsonObject.get("plan_id");
			for(int i=0;i<plan_id.size();i++){
				int plan_id_o = Integer.valueOf(plan_id.get(i));
				try{
					shopPlanService.updatePushProductionPlan(plan_id_o);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping("/findTemporary")
	@ResponseBody
	public void findTemporary(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader str = request.getReader();
		if((line = str.readLine())!=null) {
			json.append(line);
		}
		JSONObject allData = new JSONObject();
		String jsonStr = json.toString();
		try{
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String material_no = jsonObject.getString("material_no");
			String plan_no = jsonObject.getString("plan_no");
			String batch_no = jsonObject.getString("batch_no");
			String shop_name = jsonObject.getString("shop_name");
			Integer type = jsonObject.getInt("type");
			List<WorkCardNew> workCardNews = new ArrayList<>();
			if(type == 2){ //查看是否通过
				workCardNews = shopPlanService.findTemPrice(material_no, plan_no, batch_no,shop_name);
			}else if(type == 1){ //查看并修改
				workCardNews = shopPlanService.findAndUpdateTemPrice(material_no, plan_no, batch_no, shop_name);
			}
			if(workCardNews.size()==0){
				allData.put("is_success",2);
				response.setContentType("text/html;charset=utf-8");
	 			PrintWriter print = response.getWriter();
	 			print.write(allData.toString());
	 			print.flush();
	 			print.close();
			}else{
				JSONArray jsonArray = new JSONArray();
				for(int i=0;i<workCardNews.size();i++){
					JSONObject jsonObject2 = new JSONObject();
					WorkCardNew workCardNew = workCardNews.get(i);
					jsonObject2.put("price", workCardNew.getTem_price());
					jsonObject2.put("process_name", workCardNew.getProcess_name());
					jsonObject2.put("is_approval", workCardNew.getIs_approval());
					jsonArray.add(jsonObject2);
				}
				allData.put("is_success",1);
				allData.put("all", jsonArray);
				response.setContentType("text/html;charset=utf-8");
	 			PrintWriter print = response.getWriter();
	 			print.write(allData.toString());
	 			print.flush();
	 			print.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			allData.put("is_success",0);
			response.setContentType("text/html;charset=utf-8");
 			PrintWriter print = response.getWriter();
 			print.write(allData.toString());
 			print.flush();
 			print.close();
		}
	}
	
	@RequestMapping("/changeTemPrice")
	@ResponseBody
	public void changeTemPrice(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = shopPlanService.updateTemPrice(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/findAndUpdateMold")
	@ResponseBody
	public void findAndUpdateMold(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = shopPlanService.findAndUpdateMold(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/findAssetName")
	@ResponseBody
	public void findAssetName(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   try{
			   JSONObject responseData = new JSONObject();
			   JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			   String asset_no = jsonObject.getString("asset_no");
			   String asset = new String();
			   List<Asset> a = shopPlanService.findAssetName(asset_no);
				if(a.size()!=0){
					Asset ass = a.get(0);
					if(ass.getAsset_name()==null){
						ass.setAsset_name("");
					}
					if(ass.getAsset_type()==null){
						ass.setAsset_type("");
					}
					asset = ass.getAsset_name()+ass.getAsset_type();
					responseData.put("asset_name", asset);
				}else{
					responseData.put("asset_name", "没有该设备信息");
				}
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(responseData.toString());
				print.flush();
				print.close();
		   }catch(Exception e){
			   response.setContentType("text/html;charset=utf-8");
			   JSONObject responseData = new JSONObject();
			   responseData.put("asset_name", "查询出错，请重试！");
			   PrintWriter print = response.getWriter();
			   print.write(responseData.toString());
			   print.flush();
			   print.close();
		   }		   
 	}
	
	@RequestMapping("/findNoGetCailiao")
	@ResponseBody
	public void findNoGetCailiao(HttpServletResponse response) throws Exception {
		   JSONObject responseData = new JSONObject();
		   JSONArray task = new JSONArray();
		   List<GetMaterial> getMaterials = shopPlanService.findNoGetGetMaterial();
		   if(getMaterials.size()!=0) {
			   responseData.put("size","1");
			   JSONArray jsonArray = JSONArray.fromObject(getMaterials);
			   responseData.put("get_material", jsonArray);
			   response.setContentType("text/html;charset=utf-8");
	   		   PrintWriter print = response.getWriter();
	   		   print.write(responseData.toString());
	   		   print.flush();
	   		   print.close();
		   }else {
			   responseData.put("size","0");
	           response.setContentType("text/html;charset=utf-8");
	   		   PrintWriter print = response.getWriter();
	   		   print.write(responseData.toString());
	   		   print.flush();
	   		   print.close();
		   }
	}
	
	@RequestMapping("/findMonthPlan")
	@ResponseBody
	public void findMonthPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<MonthPlan> monthPlans=shopPlanService.findMonthPlan();
		JSONObject object=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(monthPlans);
		object.put("monthPlans", jsonArray);
        response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(object .toString());
		Writer.flush();
		Writer.close();   
		
		
	}
}
