package cn.itcast.ssm.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MD5;
import cn.itcast.ssm.po.Checklist;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.RFIdLabel;
import cn.itcast.ssm.service.ChecklistService;
import cn.itcast.ssm.service.PushShopPlanService;

@Controller
@RequestMapping("/check")
public class ChecklistController {
	private BufferedReader reader;
/*	private ServerSocket server;
	private Socket socket;*/
	private static int port=9000; //为用户分配的端口号
/*	public static Map<String,UserOnline> online_user = new HashMap();
	public static Map<String,IoSession> user_socket = new HashMap(); */
	public static IoSession user_session = null;
	/*public static int ONLINE_NUMBER = 0;*/
	public static String username;
	@Autowired
	private ChecklistService checklistService;
	@Autowired
	private PushShopPlanService pushShopPlanService;
	
	@RequestMapping("/getCheck")
	public void getCheck(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		JSONObject jsonJ = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = jsonJ.getJSONArray("allData");
		String material_no = jsonArray.getJSONObject(0).getString("material_no");
		String batch_no = jsonArray.getJSONObject(1).getString("batch_no");
		String shop_name = jsonArray.getJSONObject(2).getString("shop_name");
		List<Checklist> list=checklistService.findChecklist(material_no,batch_no,shop_name);
		JSONArray song = new JSONArray();
		List<Person> list4=checklistService.findPerson();
		JSONArray person = new JSONArray();
		JSONObject allData = new JSONObject();
		try {
			song=JSONArray.fromObject(list);
			person=JSONArray.fromObject(list4);
			allData.put("allData",song);
			allData.put("Person",person);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(allData.toString());
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/findRfid")
	public void findRfid(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		JSONObject rfid_J = JSONObject.fromObject(jsonStr);
		String rfid = rfid_J.getString("rfid");
		String fact_no = checklistService.findWhatRfid(rfid);
		JSONObject fact_no_J = new JSONObject();
		try {
			fact_no_J.put("fact_no",fact_no);
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(fact_no_J.toString());
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(fact_no_J.toString());
			print.flush();
			print.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping("/findRfidWhat")
	public void findRfidWhat(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		JSONObject rfid_J = JSONObject.fromObject(jsonStr);
		String rfid = rfid_J.getString("rfid");
		RFIdLabel rfIdLabel = checklistService.findRfIdIsHere(rfid);
		JSONObject allData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(rfIdLabel==null) {
			JSONObject is_here = new JSONObject();
			is_here.put("is_here","0");
			jsonArray.add(is_here);
			allData.put("allData",jsonArray);
			try {
				System.out.println(allData.toString());
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(allData.toString());
				print.flush();
				print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else {
			JSONObject type = new JSONObject();
			JSONObject phynum = new JSONObject();
			JSONObject time = new JSONObject();
			type.put("type",rfIdLabel.getType());
			phynum.put("phynum", rfIdLabel.getPhynum());
			time.put("time",rfIdLabel.getTime());
			jsonArray.add(type);
			jsonArray.add(phynum);
			jsonArray.add(time);
			allData.put("allData",jsonArray);
			try {
				System.out.println(allData.toString());
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(allData.toString());
				print.flush();
				print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	@RequestMapping("/saveRfId")
	public void saveRfId(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		try {
		JSONObject allData = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = allData.getJSONArray("allData");
		RFIdLabel rfIdLabel = new RFIdLabel();
		rfIdLabel.setPhynum(jsonArray.getJSONObject(1).getString("phy_num"));
		rfIdLabel.setRfid(jsonArray.getJSONObject(2).getString("rfid"));
		rfIdLabel.setType(jsonArray.getJSONObject(3).getString("type"));
		rfIdLabel.setTime(String.format("%tF",new Date())+" "+String.format("%tR",new Date()));
		if(checklistService.findRfIdIsHere(rfIdLabel.getRfid())==null){
			checklistService.insertRfIdLabel(rfIdLabel);
		}else{
			checklistService.updateRfIdLabel(rfIdLabel);
		}
		    JSONObject is_ok = new JSONObject();
		    is_ok.put("is_ok", "1");
		    response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(is_ok.toString());
			print.flush();
			print.close();
		}catch (Exception e) {
			// TODO: handle exception
			JSONObject is_ok = new JSONObject();
			   is_ok.put("is_ok", "0");
			   response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(is_ok.toString());
				print.flush();
				print.close();
		}
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//System.out.println(pushShopPlanService.findNewestShopPlan());
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		try {
		JSONObject allData = JSONObject.fromObject(jsonStr);
		JSONArray jsonArray = allData.getJSONArray("user");
		JSONObject allData_back = new JSONObject();
		JSONArray jsArray_back = new JSONArray();
		//获取登录设备的ip作为在线Map的value
		String user_ip = request.getRemoteAddr();
		//获取登录者的用户名，作为在线Map的key（用户名唯一）
		username = jsonArray.getJSONObject(1).getString("username");
		int i = Integer.valueOf(jsonArray.getJSONObject(0).getString("is_login"));
		switch(i) {
		   case 0:
			   List<Person> list = checklistService.findUser
			                (jsonArray.getJSONObject(1).getString("username"));
			   if(list.isEmpty()) {
				   try {
					  JSONObject is_sucess = new JSONObject();
					  JSONObject reason = new JSONObject();
					  is_sucess.put("is_success","0");
					  reason.put("reason","1"); //1是不存在该用户
					  jsArray_back.add(is_sucess);
					  jsArray_back.add(reason);
				   }catch (Exception e) {
					   e.printStackTrace();
				   }
				   try {
					   allData_back.put("allData",jsArray_back);
				   }catch(Exception e) {
					   e.printStackTrace();
				   }
				   response.setContentType("text/html;charset=utf-8");
					PrintWriter print = response.getWriter();
					print.write(allData_back.toString());
					print.flush();
					print.close();
			   }else {
				   Person person = new Person();
				   person.setNumber(jsonArray.getJSONObject(1).getString("username"));
				   //person.setPassword(jsonArray.getJSONObject(2).getString("password"));
				   String password=jsonArray.getJSONObject(2).getString("password");
				   MD5 md5 =new MD5();
				   String re_md5 = md5.getPassword(password);
				   person.setPassword(re_md5);
				   List<Person> list2 = checklistService.findUserAndPassword(person);
				   if(list2.isEmpty()) {
					   try {
							  JSONObject is_sucess = new JSONObject();
							  JSONObject reason = new JSONObject();
							  is_sucess.put("is_success","0");
							  reason.put("reason","2"); //2是密码错误
							  jsArray_back.add(is_sucess);
							  jsArray_back.add(reason);
						   }catch (Exception e) {
							   e.printStackTrace();
						   }
						   try {
							   allData_back.put("allData",jsArray_back);
						   }catch(Exception e) {
							   e.printStackTrace();
						   }
						   response.setContentType("text/html;charset=utf-8");
							PrintWriter print = response.getWriter();
							print.write(allData_back.toString());
							print.flush();
							print.close();
				   }else {
					   try {
					  /* //在一个用户登陆后，将其添加至在线Map中
					   UserOnline userOnline = new UserOnline(user_ip, 0);
					   online_user.put(username, userOnline);
					   Set<String> s = online_user.keySet();
					   ONLINE_NUMBER = s.size();*/
					   Collection<IoSession> sessions = null;
					   for(int t=0;t<BindPortAccpector.ioSession.size();t++) {
							if(BindPortAccpector.ioSession.get(t).isConnected()) {
								sessions = BindPortAccpector.ioSession.get(t).getService().getManagedSessions().values();
								break;
							}
					   }
					   if(sessions == null){
						   JSONObject is_success = new JSONObject();
						   JSONObject Person = new JSONObject();
						   //在登录后，将数据库中Person信息提交到客户端
						   List<Person> list3=checklistService.findPerson();
						   String position = list2.get(0).getPosition();
						   List<Person> one = checklistService.findUser(username);
						   Person.put("Person", list3);
						   is_success.put("is_success","1");
						   jsArray_back.add(is_success);
						   jsArray_back.add(Person);
						   allData_back.put("allData",jsArray_back);
						   if(position != null) {
							   allData_back.put("position", position);
							   allData_back.put("name", one.get(0).getPerson_name());
							   allData_back.put("shop_name", one.get(0).getDepartment());
						   }else {
							   throw new Exception();
						   }
						   response.setContentType("text/html;charset=utf-8");
							PrintWriter print = response.getWriter();
							print.write(allData_back.toString());
							print.flush();
							print.close();
					   }else{
						   int is_successful = 1;
						   for(IoSession s : sessions) {
								String c[] = (String[]) s.getAttribute("details");
								if(c[0].equals(username)) {
									is_successful = 0;
									break;
								}
							}
						   if(is_successful == 1){
							   JSONObject is_success = new JSONObject();
							   JSONObject Person = new JSONObject();
							   //在登录后，将数据库中Person信息提交到客户端
							   List<Person> list3=checklistService.findPerson();
							   String position = list2.get(0).getPosition();
							   List<Person> one = checklistService.findUser(username);
							   Person.put("Person", list3);
							   is_success.put("is_success","1");
							   jsArray_back.add(is_success);
							   jsArray_back.add(Person);
							   allData_back.put("allData",jsArray_back);
							   if(position != null) {
								   allData_back.put("position", position);
								   allData_back.put("name", one.get(0).getPerson_name());
								   allData_back.put("shop_name", one.get(0).getDepartment());
							   }else {
								   throw new Exception();
							   }
							   response.setContentType("text/html;charset=utf-8");
								PrintWriter print = response.getWriter();
								print.write(allData_back.toString());
								print.flush();
								print.close();
						   }else{
							   JSONObject is_sucess = new JSONObject();
								  JSONObject reason = new JSONObject();
								  is_sucess.put("is_success","0");
								  reason.put("reason","3"); //3是已经在线
								  jsArray_back.add(is_sucess);
								  jsArray_back.add(reason);
								  allData_back.put("allData",jsArray_back);
								  response.setContentType("text/html;charset=utf-8");
								  PrintWriter print = response.getWriter();
								  print.write(allData_back.toString());
								  print.flush();
								  print.close();
						   }
					   }
					   }catch(Exception e) {
						   e.printStackTrace();
					   }
				   }
			   }
			   break;
		   case 1:
			   break;
		   default:
			   break;
		}
	    }catch(Exception e) {
		e.printStackTrace();
	    }
	}	
}
//网页端测试 "http://192.168.10.7:8080/ssm/check/getCheck.action?client_material_no=1&batch_no=123&shop_name=车间1

  