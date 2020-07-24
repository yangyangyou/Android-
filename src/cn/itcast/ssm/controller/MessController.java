package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.service.MessService;

@Controller
@RequestMapping("/mess")
public class MessController {
	@Autowired
	private MessService messService;
	@RequestMapping("/receiveMess")
	@ResponseBody
	public JSONObject receiveMess(@RequestBody List<Message> mess) throws Exception{
		JSONObject result = messService.receiveMess(mess);
		return result;		
	}

	@RequestMapping("/getMess")
	@ResponseBody
	public JSONObject getMess(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
 	    StringBuffer json = new StringBuffer();
 	    String line = null;
 	    int position = 10;
 	    BufferedReader reader = request.getReader();
 	    while((line = reader.readLine())!=null) {
 		   json.append(line);
 	    }
 	    String jsonStr = json.toString();
 	    try {
 	    	JSONObject position_J = JSONObject.fromObject(jsonStr);
 	    	position = Integer.valueOf(position_J.getString("position"));
 	    }catch(Exception e) {
 	    	e.printStackTrace();
 	    }
 	   JSONObject result = messService.getMess(position);
		return result;
    }
	
	@RequestMapping("/updateState")
	public void updateState(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		String person=request.getParameter("person");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = time.format(new Date());//获取当天日期
		String batch_no = request.getParameter("batch_no");
		String asset_no= request.getParameter("asset_no");
		messService.updateState(person,tranDate,batch_no, asset_no);
		JSONObject object = new JSONObject();
		object.put("is_ok","1");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(object.toString());
		print.flush();
		print.close(); 
	}
	
	@RequestMapping("/getDailyCheck66")
	public void getDailyCheck66(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
	    StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		JSONObject all_Date = JSONObject.fromObject(jsonStr);
		try {
			Integer is_mold = Integer.valueOf(all_Date.getString("is_mold"));
			String shop_name = all_Date.getString("shop_name");
			List<DailyCheck_2> list =messService.getdailyCheck(is_mold,shop_name);
			JSONObject object=new JSONObject();
			JSONArray jsonArray=JSONArray.fromObject(list);
			object.put("daily_check",jsonArray);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
