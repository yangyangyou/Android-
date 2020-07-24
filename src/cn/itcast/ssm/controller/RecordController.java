package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.Record;
import cn.itcast.ssm.service.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {

	@Autowired
	private RecordService recordService;
	@RequestMapping("/getRecord")
	@ResponseBody
	public void getRecord(@RequestBody List<Record> record,HttpServletResponse response) throws Exception{
		try{
			JSONObject result = recordService.getRecord(record);
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(result.toString());
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
			JSONObject msg = new JSONObject();
			msg.put("is_ok","0");
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(msg.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/getProcess")
	@ResponseBody
	public void getProcess(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String material_no = new String();
		String batch_no = new String();
		String shopName = new String();
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    try {
 	    	JSONObject allData = JSONObject.fromObject(jsonStr);
 	    	JSONArray jsonArray = allData.getJSONArray("allData");
 	    	material_no = jsonArray.getJSONObject(0).getString("material_no");
 	    	batch_no = jsonArray.getJSONObject(1).getString("batch_no");
 	    	shopName = jsonArray.getJSONObject(2).getString("shop_name");
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    }
		//String shopName = new String(shop_name.getBytes("ISO-8859-1"),"utf-8");
		JSONObject result = recordService.getProcess(material_no, batch_no, shopName);
		System.out.println(result.toString());
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter print = response.getWriter();
		print.write(result.toString());
		print.flush();
		print.close();
	}
	
	/*@RequestMapping("/getOperator")
	@ResponseBody
	public JSONObject getOperator(String material_no, String batch_no, String shop_name, String process_name) throws Exception{
		String shopName = new String(shop_name.getBytes("ISO-8859-1"),"utf-8");
		String processName = new String(process_name.getBytes("ISO-8859-1"),"utf-8");
		JSONObject result = recordService.getOperator(material_no, batch_no, shopName, processName);
		return result;
	}*/
	
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
		String fact_no = recordService.findWhatRfid(rfid);
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
	
	@RequestMapping("/getProcessByBatch")
	@ResponseBody
	public void getProcessByBatch(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String batch_no = new String();
		String shopName = new String();
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    try {
 	    	JSONObject allData = JSONObject.fromObject(jsonStr);
 	    	JSONArray jsonArray = allData.getJSONArray("all_Data");
 	    	batch_no = jsonArray.getJSONObject(0).getString("batch_no");
 	    	shopName = jsonArray.getJSONObject(1).getString("shop_name");
 	    }catch(Exception e){
 	    	e.printStackTrace();
 	    }
		//String shopName = new String(shop_name.getBytes("ISO-8859-1"),"utf-8");
		JSONObject result = recordService.getProcessByBatch(batch_no, shopName);
		System.out.println(result.toString());
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter print = response.getWriter();
		print.write(result.toString());
		print.flush();
		print.close();
	}
	
	@RequestMapping("/returnRecord")
	@ResponseBody
	public void returnRecord(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*String shopName = new String(shop_name.getBytes("ISO-8859-1"),"utf-8");
		String pro = new String(process.getBytes("ISO-8859-1"),"utf-8");*/
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String s = null;
		BufferedReader bufferedReader = request.getReader();
		if((s = bufferedReader.readLine())!=null) {
			json.append(s);
		}
		String jsonStr = json.toString();
		try {
			JSONObject data = JSONObject.fromObject(jsonStr);
			String material_no = data.getString("material_no");
			String batch_no = data.getString("batch_no");
			String shopName = data.getString("shop_name");
			String pro = data.getString("process");
			JSONObject result = recordService.returnRecord(material_no, batch_no, shopName, pro);
			System.out.println(result.toString());
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(result.toString());
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/reRecord")
	@ResponseBody
	public void reRecord(@RequestBody List<Record> record,HttpServletResponse response) throws Exception{
		try {
			JSONObject result = recordService.updateRecord(record);
			System.out.println(result.toString());
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(result.toString());
			print.flush();
			print.close();
		}catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("is_ok","0");
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(result.toString());
			print.flush();
			print.close();
		}
	}
}
