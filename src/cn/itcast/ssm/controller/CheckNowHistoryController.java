package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.Check;
import cn.itcast.ssm.po.CheckRecord;
import cn.itcast.ssm.service.CheckNowHistoryService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/check_1")
public class CheckNowHistoryController {
	private List<String> list;

	@Autowired
	private CheckNowHistoryService checkService;
	
	@RequestMapping("/querySomething")
	public void querySomething(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		list = new ArrayList();
		Check check = new Check();
	    StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		String jsonStr = json.toString(); 
		try {
		JSONObject all_Date = JSONObject.fromObject(jsonStr);
		JSONArray song = all_Date.getJSONArray("allData");
		int i = song.getJSONObject(0).getInt("type");
		switch(i) {
		    case 2:
		    	list.clear();
		    	list.add("2");
		    	check.setShop_name(song.getJSONObject(1).getString("shop_name"));
		    	check.setBatch_no(song.getJSONObject(2).getString("wuliaohao"));
		    	list.addAll(checkService.findGongxu(check));
		    	break;
		    case 3:
		    	list.clear();
		    	list.add("3");
		    	check.setShop_name(song.getJSONObject(1).getString("shop_name"));
		    	check.setBatch_no(song.getJSONObject(2).getString("wuliaohao"));
		    	check.setProcess_name(song.getJSONObject(3).getString("gongxu_name"));
		    	list.addAll(checkService.findSheBei(check));
		    	break;
		    case 4:
		    	list.clear();
		    	list.add("4");
		    	check.setShop_name(song.getJSONObject(1).getString("shop_name"));
		    	check.setBatch_no(song.getJSONObject(2).getString("wuliaohao"));
		    	check.setProcess_name(song.getJSONObject(3).getString("gongxu_name"));
		    	check.setAsset(song.getJSONObject(4).getString("shebei_name"));
		    	list.addAll(checkService.findWorker(check));
		    	break;
		    case 5:
		    	list.clear();
		    	list.add("5");
		    	check.setShop_name(song.getJSONObject(1).getString("shop_name"));
		    	check.setBatch_no(song.getJSONObject(2).getString("wuliaohao"));
		    	check.setProcess_name(song.getJSONObject(3).getString("gongxu_name"));
		    	check.setAsset(song.getJSONObject(4).getString("shebei_name"));
		    	check.setOperator(song.getJSONObject(5).getString("work_name"));
		    	String now_data = String.format("%tF",new Date());
		    	String now_time = String.format("%tR",new Date());
		    	List<String> check_time = checkService.findCheck(check);
		    	if(check_time.size()!=0){
		    		String[] lastest = check_time.get(0).split("&");
			    	int lasest_int=0;
			    	for(int j=0;j<check_time.size();j++) {
			    		String[] check_str = check_time.get(j).split("&");
			    		if(check_str[0].compareTo(lastest[0])>0) {
			    			lastest[0]=check_str[0];
			    			lasest_int =j;
			    		}
			    	}
			    	String[] time_day = check_time.get(lasest_int).split("&");
			    	if(time_day[0].equals(now_data)) {
			    			list.add(time_day[0]+"  "+time_day[1]);
			    			list.add(now_data+"  "+now_time);
			    			if(time_day[1].compareTo(now_time)<=0) {
			    				list.add("已点检");
			    			}else {
			    				list.add("未点检");
			    			}
			    			break;
			    		}else {
			    			list.add(time_day[0]+"  "+time_day[1]);
			    			list.add(now_data+"  "+now_time);
			    			list.add("未点检");
			    		}
		    	}else{
		    		list.add("未查询到点检记录！");
		    	}
		    	break;
		    case 6:
		    	list.clear();
		    	list.add("6");
		    	int where=-1;
		    	check.setShop_name(song.getJSONObject(1).getString("shop_name"));
		    	check.setBatch_no(song.getJSONObject(2).getString("wuliaohao"));
		    	check.setProcess_name(song.getJSONObject(3).getString("gongxu_name"));
		    	check.setAsset(song.getJSONObject(4).getString("shebei_name"));
		    	check.setOperator(song.getJSONObject(5).getString("work_name"));
		    	String history_day = song.getJSONObject(6).getString("day");
		    	List<CheckRecord> check_record = checkService.findCheckMuch(check);
		    	List<String> check_time_1 = new ArrayList<>();
		    	for(int j =0;j<check_record.size();j++) {
		    		check_time_1.add(check_record.get(j).getTime_1());
		    	}
		    	List<String> histroy_date = new ArrayList<>();
		    	for(int j=0;j<check_time_1.size();j++) {
		    		String[] s = check_time_1.get(j).split("&");
		    		histroy_date.add(s[0]);
		    	}
		    	if(!(histroy_date.isEmpty())) {
		    		for(int j=0;j<histroy_date.size();j++) {
		    			if(histroy_date.get(j).equals(history_day)) {
		    				where=j;
		    				break;
		    			}
		    		}
		    	}
		    	int record_id = check_record.get(where).getRecord_id();
		    	CheckRecord check_history = checkService.findCheckOne(record_id);
		    	list.add(check_history.getTime_1());
		    	list.add(check_history.getTime_2());
		    	list.add(check_history.getTime_3());
		    	list.add(check_history.getTime_4());
		    	list.add(check_history.getTime_5());
		    	list.add(check_history.getTime_6());
		    	list.add(check_history.getTime_7());
		    	list.add(check_history.getTime_8());
		    	list.removeAll(Collections.singleton(null)); 
		    default:
		    	break;
		}
		JSONObject allData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int j=0;j<list.size();j++) {
			JSONObject jsonObject = new JSONObject();
			if(j==0) {
				jsonObject.put("is_ok","1");
				jsonArray.add(jsonObject);
				jsonObject.clear();
				jsonObject.put("type", list.get(j));
				jsonArray.add(jsonObject);
			}else {
				jsonObject.put(String.valueOf(j),list.get(j));
				jsonArray.add(jsonObject);			
			}
		} 
		allData.put("allData", jsonArray);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(allData.toString());
		print.flush();
		print.close();
	  }catch(Exception e) {
		  e.printStackTrace();
		  JSONObject allData = new JSONObject();
		  JSONArray jsonArray = new JSONArray();
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put("is_ok", "0");
		  jsonArray.add(jsonObject);
		  allData.put("allData", jsonArray);
		  response.setContentType("text/html;charset=utf-8");
		  PrintWriter print = response.getWriter();
		  print.write(allData.toString());
		  print.flush();
		  print.close();
	  }
	}
}
