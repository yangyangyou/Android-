package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.MoldRecord;
import cn.itcast.ssm.service.MoldGetBackService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/mold")
public class MoldGetBackController {
    private MoldRecord moldRecord;
    
    //注入Service
    @Autowired
    private MoldGetBackService moldGetBackService;
    
    @RequestMapping("/getbackMold")
    public void moldGetBack(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	moldRecord = new MoldRecord();
    	Date date = new Date();
    	String date_1 = String.format("%tF",date);
    	String date_str = String.format("%tF",date);
 	    request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    try {
 	    	JSONObject allData = JSONObject.fromObject(jsonStr);
 	    	JSONArray record = allData.getJSONArray("allData");
 	    	moldRecord.setBatch_no(record.getJSONObject(0).getString("batch_no"));
 	    	moldRecord.setMaterial_no(record.getJSONObject(1).getString("client_material_no"));
 	    	moldRecord.setMold_no(record.getJSONObject(2).getString("mold_no"));
 	    	moldRecord.setNum(record.getJSONObject(3).getString("moju_num"));
 	    	moldRecord.setMold_name(moldGetBackService.findMoldName(record.getJSONObject(2).getString("mold_no")));
 	    	moldRecord.setAcceptor(record.getJSONObject(4).getString("acceptor"));
 	    	moldRecord.setProvider(record.getJSONObject(5).getString("provider"));
 	    	int get_back = Integer.valueOf(record.getJSONObject(6).getString("get_or_back"));
 	    	moldRecord.setRemark(record.getJSONObject(7).getString("remark"));
 	    	if(get_back==0) {
 	    		moldRecord.setOut_date(date_1);
 	    		moldRecord.setIn_date("——");
 	    	}if(get_back==1) {
 	    		moldRecord.setOut_date("——");
 	    		moldRecord.setIn_date(date_1);
 	    	}else {
 	    		System.out.println("数据传输错误");
 	    	}
 	    	String mold_name = moldGetBackService.findMoldName(record.getJSONObject(2).getString("mold_no"));
 	    	moldRecord.setMold_name(mold_name);
 	    	moldGetBackService.GetBackMold(moldRecord);
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
    
    @RequestMapping("/bindingMold")
    public void bindingMold(HttpServletRequest request,HttpServletResponse response) throws Exception{
 	    request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    JSONObject allData = JSONObject.fromObject(jsonStr);
 	    JSONObject responseData = moldGetBackService.findBindingMold(allData);
 	    response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(responseData.toString());
		print.flush();
		print.close();
    }
    
    @RequestMapping("/changeMold")
    public void changeMold(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    JSONObject allData = JSONObject.fromObject(jsonStr);
 	    JSONObject responseData = moldGetBackService.updateBindingMold(allData);
 	    response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(responseData.toString());
		print.flush();
		print.close();
    }
    
    @RequestMapping("/scanMold")
    public void scanMold(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    JSONObject allData = JSONObject.fromObject(jsonStr);
 	    JSONObject responseData = moldGetBackService.findMold(allData);
 	    response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(responseData.toString());
		print.flush();
		print.close();
    }
    
    @RequestMapping("/deleteMold")
    public void deleteMold(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    JSONObject allData = JSONObject.fromObject(jsonStr);
 	    JSONObject responseData = moldGetBackService.deleteMold(allData);
 	    response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(responseData.toString());
		print.flush();
		print.close();
    }
}
