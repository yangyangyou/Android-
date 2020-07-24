package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.GetSecMaterials;
import cn.itcast.ssm.po.SecondaryMaterials;
import cn.itcast.ssm.service.FuliaoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/fuliao")
public class FuliaoController {
	@Autowired
	private FuliaoService fuliaoService;
	
	@RequestMapping("/findFuliao")
	@ResponseBody
	public void findFuliao(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = new JSONObject();
		   try {
			   JSONObject allData = JSONObject.fromObject(jsonStr);
			   String fuliao_name = allData.getString("fuliao_name");
			   List<SecondaryMaterials> fuliao_list = fuliaoService.findFuliaoDetails("%"+fuliao_name+"%");
			   if(fuliao_list.size()!=0) {
				   JSONArray jsonArray = JSONArray.fromObject(fuliao_list);
				   responseData.put("is_success", "1");
				   responseData.put("all", jsonArray);
				   response.setContentType("text/html;charset=utf-8");
		   		   PrintWriter print = response.getWriter();
		   		   print.write(responseData.toString());
		   		   System.out.println(responseData.toString());
		   		   print.flush();
		   		   print.close();
			   }else {
				   responseData.put("is_success", "0");
				   response.setContentType("text/html;charset=utf-8");
		   		   PrintWriter print = response.getWriter();
		   		   print.write(responseData.toString());
		   		   System.out.println(responseData.toString());
		   		   print.flush();
		   		   print.close();  
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
			   responseData.put("is_success", "-1");
			   response.setContentType("text/html;charset=utf-8");
	   		   PrintWriter print = response.getWriter();
	   		   print.write(responseData.toString());
	   		   System.out.println(responseData.toString());
	   		   print.flush();
	   		   print.close();
		   }
	}
	
	@RequestMapping("/acceptFuliao")
	@ResponseBody
	public void acceptFuliao(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = fuliaoService.insertFuliao(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/findNoFuliao")
	@ResponseBody
	public void findNoFuliao(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = fuliaoService.findNoGetFuliao(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/getFuliao")
	@ResponseBody
	public void getFuliao(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = fuliaoService.updateFuliao(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/returnFuliao")
	@ResponseBody
	public void returnFuliao(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = fuliaoService.insertBackFuliao(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/findJianju")
	@ResponseBody
	public void findJianju(HttpServletRequest request,HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject responseData = fuliaoService.findGauge(jsonStr);
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(responseData.toString());
		   print.flush();
		   print.close();
	}
	
	@RequestMapping("/findNoGetFuliao")
	@ResponseBody
	public void findNoGetFuliao(HttpServletResponse response) throws Exception {
		   JSONObject responseData = new JSONObject();
		   JSONArray task = new JSONArray();
		   List<GetSecMaterials> getSecMaterials = fuliaoService.findNoGetSecMaterial();
		   if(getSecMaterials.size()!=0) {
			   responseData.put("size","1");
			   JSONArray jsonArray = JSONArray.fromObject(getSecMaterials);
			   responseData.put("approve_sec_material", jsonArray);
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
	
	@RequestMapping("/findNoRecordFuliao")
	@ResponseBody
	public void findNoRecordFuliao(HttpServletResponse response) throws Exception {
		   JSONObject responseData = new JSONObject();
		   JSONArray task = new JSONArray();
		   List<GetSecMaterials> getSecMaterials = fuliaoService.findNoRecordGetSec();
		   if(getSecMaterials.size()!=0) {
			   responseData.put("size","1");
			   for(int i=0;i<getSecMaterials.size();i++){
				   GetSecMaterials getSecMaterial = getSecMaterials.get(i);
				   getSecMaterial.setApply_data(getSecMaterial.getGet_sec_details().get(0).getTime());
				   getSecMaterial.setRemark(getSecMaterial.getGet_sec_details().get(0).getRemark());
			   }
			   JSONArray jsonArray = JSONArray.fromObject(getSecMaterials);
			   responseData.put("sec_material_get_back", jsonArray);
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
	
	@RequestMapping("/updateNoRecordFuliao")//
	   public void updateNoRecordFuliao(HttpServletRequest request,HttpServletResponse response)throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   JSONObject jsonObject = new JSONObject(); 
		   int get_sec_material_id =Integer.parseInt(request.getParameter("get_sec_material_id"));
		   try{
			   fuliaoService.updateNoRecordGetSec(get_sec_material_id);
			   jsonObject.put("is_ok", 1);
		   }catch (Exception e) {
			   e.printStackTrace();
			   jsonObject.put("is_ok", 0);
		   }finally{
			   response.setContentType("text/html;charset=utf-8");
			   PrintWriter print = response.getWriter();
			   print.write(jsonObject.toString());
			   print.flush();
			   print.close();
		   }
	   }
}
