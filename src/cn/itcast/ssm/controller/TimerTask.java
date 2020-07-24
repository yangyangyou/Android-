package cn.itcast.ssm.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.mina.core.session.IoSession;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.DepartmentVo;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.GetSecMaterials;
import cn.itcast.ssm.po.PlanMessCache;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.po.UserOnline;
import cn.itcast.ssm.service.FuliaoService;
import cn.itcast.ssm.service.PushShopPlanService;
import cn.itcast.ssm.service.ShopPlanService;
import cn.itcast.ssm.service.impl.MessServiceImpl;
import cn.itcast.ssm.service.impl.PushShopPlanServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("taskJob")
public class TimerTask {

@Autowired
private PushShopPlanService pushShopPlanService;

@Autowired
private ShopPlanService shopPlanService;

@Autowired
private FuliaoService fuliaoService;
  /*@Scheduled(cron="0/10 * * * * ?")
  public static void pushShopPlan(){
	  String allData = null;
	  CloseableHttpClient httpClient = HttpClients.createDefault();
	  HttpGet httpGet = new HttpGet("http://localhost:8080/HongQiao/push/pushShopPlan.action");
	  try {
		  HttpResponse httpResponse = httpClient.execute(httpGet);
		  allData = EntityUtils.toString(httpResponse.getEntity());
		  JSONObject allData_J = JSONObject.fromObject(allData);
		  int number = Integer.valueOf(allData_J.getInt("number"));
		  JSONArray plan_id = allData_J.getJSONArray("plan_id");
		  JSONArray shop_plan = allData_J.getJSONArray("shop_plan");
		  List<String> gongduan = (List<String>) allData_J.get("user");
		  //System.out.println(gongduan);
		  for(int i=0;i<number;i++) {
			  JSONObject shop_plan_J = shop_plan.getJSONObject(i);
			  if(shop_plan_J!=null) {
				  messToSocket(shop_plan_J.toString(),gongduan);
			  }
		  }
		  if(plan_id.size()!=0){
		  CloseableHttpClient httpClient_post = HttpClients.createDefault();
		  HttpPost httpPost = new HttpPost("http://localhost:8080/HongQiao/push/updateShopPlan.action");
		  httpPost.setHeader("Accept","application/json");
		  httpPost.setHeader("Content-Type","application/json");
		  StringEntity entity = new StringEntity(plan_id.toString(),"UTF-8");
		  httpPost.setEntity(entity);
		  CloseableHttpResponse response = httpClient_post.execute(httpPost);
		  }
	  }catch(Exception e) {
		  e.printStackTrace();
	  }finally {
		  try {
			  httpClient.close();
		  }catch(IOException e) {
			  e.printStackTrace();
		  }
	  }
  }*/
  
  @Scheduled(cron="0/20 * * * * ?")
  public void pushProductionPlan(){
	  	JSONObject responseData = new JSONObject();
		try{
			//List<String> plan_id = new ArrayList<>();
			JSONArray productionPlan_J = new JSONArray();
			JSONObject shopdelivert_J = new JSONObject();
			List<ProductionPlan> productionPlans = shopPlanService.findPushProductionPlan();
			List<GetMaterial> getMaterials = shopPlanService.findNoPushGetMaterial();
			List<GetSecMaterials> getSecMaterials = fuliaoService.findNoPushSecMaterial();
			if(productionPlans.size()!=0){
				//responseData.put("is","1");
				responseData.put("type","production_plan");
				for(int i=0;i<productionPlans.size();i++){
					ProductionPlan productionPlan = productionPlans.get(i);
					JSONObject jsonObject = JSONObject.fromObject(productionPlan);
					Integer plan_id = productionPlan.getPlan_id();
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
					productionPlan_J.add(jsonObject);
					JSONArray productionPlan_send = new JSONArray();
					productionPlan_send.add(jsonObject);
					JSONObject allData_send = new JSONObject();
					allData_send.put("type", "production_plan");
					allData_send.put("production_plan", productionPlan_J);
					allData_send.put("is", "1");
					try{
						  messToSocket(allData_send.toString(), gongduanzhang);
						  try{
								try{
									shopPlanService.updatePushProductionPlan(plan_id);
								}catch(Exception e1){
									e1.printStackTrace();
								}
							}catch(Exception e2){
								e2.printStackTrace();
							}
					  }catch(Exception e3){
						  e3.printStackTrace();
					  }
				}
				
			}
			if(getMaterials.size()!=0){
				for(int i=0;i<getMaterials.size();i++){
					JSONArray jsonArray = JSONArray.fromObject(getMaterials.get(i));
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("type", "get_material");
					jsonObject.put("get_material", jsonArray);
					jsonObject.put("is", 1);
					List<String> cangku = pushShopPlanService.findCangKuGM();
					try{
						  getMaterialmessToSocket(jsonObject.toString(), cangku);
						  try{
								try{
									shopPlanService.updateGetMaterialPush(getMaterials.get(i).getGet_material_id());
								}catch(Exception e1){
									e1.printStackTrace();
								}
							}catch(Exception e2){
								e2.printStackTrace();
							}
					  }catch(Exception e3){
						  e3.printStackTrace();
					  }
				}
			}
			if(getSecMaterials.size()!=0){
				for(int i=0;i<getSecMaterials.size();i++){
					JSONArray jsonArray = JSONArray.fromObject(getSecMaterials.get(i));
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("type", "approve_sec_material");
					jsonObject.put("approve_sec_material", jsonArray);
					jsonObject.put("is", 1);
					List<String> cangku = pushShopPlanService.findCangKuGM();
					try{
						  getMaterialmessToSocket(jsonObject.toString(), cangku);
						  try{
								try{
									fuliaoService.updateNoPushSecMaterial(getSecMaterials.get(i).getGet_materials_id());
								}catch(Exception e1){
									e1.printStackTrace();
								}
							}catch(Exception e2){
								e2.printStackTrace();
							}
					  }catch(Exception e3){
						  e3.printStackTrace();
					  }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
}

  public static void messToSocket(String string,List<String> user) throws Exception {
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
  
  /*@Scheduled(cron="0 0 2 * * ?")
  public static void clearPlanCache(){
	  MessCacheManager.getInstance().clearAllCache();
  }*/
 
}
