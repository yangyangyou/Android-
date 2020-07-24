package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.DepartmentVo;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.po.WaiXieMessCache;
import cn.itcast.ssm.service.PushShopPlanService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/push")
public class PushShopPlanController {
	
	@Autowired
	private PushShopPlanService pushShopPlanService;
	
	@RequestMapping("/pushShopPlan")
	public void pushShopPlan(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Integer> plan_id = new ArrayList<>();
		System.out.println(BindPortAccpector.ioSession.size());
		  try {
			  plan_id = pushShopPlanService.findNewestShopPlan();
			  JSONObject allData = new JSONObject();
			  JSONArray shop_plan_J = new JSONArray();
			  JSONArray plan_id_J = new JSONArray();
			  for(int i=0;i<plan_id.size();i++) {
				  JSONObject jsonObject = new JSONObject();
				  jsonObject.put(String.valueOf(i),plan_id.get(i));
				  plan_id_J.add(jsonObject);
			  }
			  allData.put("number",plan_id.size());
			  if(plan_id!=null) {
				  for(int i=0;i<plan_id.size();i++) {
					  ShopPlan shopPlan  = pushShopPlanService
							  .findShopPlanAndProcess(plan_id.get(i));
					  if(shopPlan!=null) {
							  String material_no = shopPlan.getMaterial_no();
							  String batch_no = shopPlan.getBatch_no();
							  String shop_name = shopPlan.getShop_name();
							  String plan_no = shopPlan.getPlan_no();
							  String client_material_no = shopPlan.getClient_material_no();
							  String plan_num = shopPlan.getPlan_num();
							  JSONObject message = new JSONObject();
							  List<Task> tasks = shopPlan.getTasks();
							  try {
								  message.put("type", "plan");
								  message.put("material_no",material_no);
								  message.put("batch_no",batch_no);
								  message.put("shop_name",shop_name);
								  message.put("plao_no",plan_no);
								  message.put("client_material_no",client_material_no);
								  message.put("plan_num", plan_num);
								  JSONArray tasks_J = JSONArray.fromObject(tasks);
								  message.put("details",tasks_J);
								  shop_plan_J.add(message);
							  }catch(Exception e) {
								  e.printStackTrace();
							  }
					  }
				  }
			  }
			  if(shop_plan_J.size() == plan_id_J.size()) {
				  List<String> gongduanzhang = pushShopPlanService.findGongDuanZhang(new DepartmentVo());
				  if(gongduanzhang.size()!=0) {
					  allData.put("user",gongduanzhang);
				  }
				  allData.put("plan_id",plan_id_J);
				  allData.put("shop_plan", shop_plan_J);
				  response.setContentType("text/html;charset=utf-8");
				  PrintWriter print = response.getWriter();
				  print.write(allData.toString());
				  print.flush();
				  print.close();
			  }else {
				  throw new Exception();
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	}
	
	@RequestMapping("/pushWaixieMess")
	public void pushWaixieMess(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null) {
			json.append(line);
		}
		String jsonStr = json.toString();
		try {
			JSONObject data = JSONObject.fromObject(jsonStr);
			String shop_name = data.getString("shop_name");
			String username = data.getString("username");
			String tips = data.getString("tips");
			JSONObject mess = new JSONObject();
			mess.put("type","WaiXie");
			mess.put("shop_name",shop_name);
			mess.put("username",username);
			mess.put("tips", tips);
			messToSocket(mess.toString(), username);
			System.out.println("推送成功！");
			JSONObject is_ok = new JSONObject();
		    is_ok.put("is_ok", "1");
		    response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(is_ok.toString());
			print.flush();
			print.close();
		}catch (Exception e) {
			e.printStackTrace();
			JSONObject is_ok = new JSONObject();
			is_ok.put("is_ok", "0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(is_ok.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/updateShopPlan")
	public void updateShopPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader str = request.getReader();
		if((line = str.readLine())!=null) {
			json.append(line);
		}
		String jsonStr = json.toString();
		JSONArray jsonObject = JSONArray.fromObject(jsonStr);
		for(int i=0;i<jsonObject.size();i++) {
			JSONObject j = jsonObject.getJSONObject(i);
			int plan_id = j.getInt(String.valueOf(i));
			pushShopPlanService.updateNewestShopPlan(plan_id);
		}
	}
	
	public static void messToSocket(String mess,String username) {
		Collection<IoSession> sessions = null;
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
		}else {
			int a=0;
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(c[0].equals(username)) {
					s.write(mess);
					a++;
				}
			}
			if(a == 0) {
				WaiXieMessCache waiXieMessCache = new WaiXieMessCache();
				waiXieMessCache.setPush_people(username);
				waiXieMessCache.setMessage(mess);
				MessCacheManager.getInstance().insertWaiXieMessCache(waiXieMessCache);
			}
		}
	}
}
