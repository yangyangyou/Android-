package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.AssetStatus;
import cn.itcast.ssm.po.CheckRecord;
import cn.itcast.ssm.po.DailyCheck;
import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.service.CheckService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/check")
public class CheckController {
	private DailyCheck checkDaily;
	private String asset_xh;
	private String check_selecting;
	private String check_content;
	private String asset_no;
	private int check_id;
	private CheckRecord checkRecord;
	private CheckRecord checkRecords;
	//注入service
	@Autowired
	private CheckService checkService;


	@RequestMapping("/queryAsset")
	public void queryDevice(HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<DailyCheck> checkList = checkService.findAssetList();
		JSONObject allData = new JSONObject();
		JSONArray sing_one = new JSONArray();
		JSONArray sing_all = new JSONArray();
		for(int i=0;i<checkList.size();i++) {
			StringBuilder sb = new StringBuilder("");
			checkDaily=checkList.get(i);
			this.check_id = checkDaily.getCheck_id();
			this.asset_no = checkDaily.getAsset_no();
			for(int j=0;j<checkDaily.getCheckSelecting().size();j++) {
				if(j!=checkDaily.getCheckSelecting().size()-1) {
				sb.append(checkDaily.getCheckSelecting().get(j).getCheck_selecting()+",");
				}else {
					sb.append(checkDaily.getCheckSelecting().get(j).getCheck_selecting());	
				}
			}
			this.check_selecting =sb.toString();
			if(checkDaily.getCheckRecord().getCheck_content()==null || 
					checkDaily.getCheckRecord().getCheck_content()=="") {
				this.check_content="无";
			}else {
			  this.check_content = checkDaily.getCheckRecord().getCheck_content();
			}
			this.asset_xh = checkDaily.getAsset_xh();
			JSONObject allData_one = new JSONObject();
			JSONObject asset_xh  = new JSONObject();
			JSONObject check_selecting  = new JSONObject();
			JSONObject check_content  = new JSONObject();
			JSONObject asset_no = new JSONObject();
			JSONObject check_id = new JSONObject();
			try {
				/*asset_xh.clear();
				check_selecting.clear();
				check_content.clear();
				asset_no.clear();
				check_id.clear();*/
	            asset_xh.put("设备型号", this.asset_xh);
	            check_selecting.put("点检项目", this.check_selecting);
	            check_content.put("点检内容", this.check_content);
	            asset_no.put("设备编号",this.asset_no);
	            check_id.put("点检编号", this.check_id);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			sing_one.clear();
			sing_one.add(asset_xh);
			sing_one.add(asset_no);
			sing_one.add(check_content);
			sing_one.add(check_id);
			sing_one.add(check_selecting);
			try {
				allData_one.put("设备"+i,sing_one);
				sing_all.add(allData_one);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			allData.put("allAsset",sing_all);
		}catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@RequestMapping("/updateCheck")
	public void updateCheck(HttpServletRequest request,HttpServletResponse response) throws Exception {
	    checkRecord = new CheckRecord();
	    request.setCharacterEncoding("UTF-8");
	    StringBuffer json = new StringBuffer();
		String line = null;
		Date date = new Date();
	    String time = String.format("%tR",date);
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		try {
		String jsonStr = json.toString(); 
		JSONObject all_Date = JSONObject.fromObject(jsonStr);
		DailyCheck_2 dailyCheck_2 = new DailyCheck_2();
		String[] tips = all_Date.getString("check_remarks").split(" ");
		String[] results = all_Date.getString("check_results").split(" ");
		JSONArray content = all_Date.getJSONArray("check_content");
		int content_length =  content.size();
		String[] content_str = new String[content_length];
		for(int j=0;j<content_length;j++){
			content_str[j]=content.getJSONObject(j).getString(String.valueOf(j));
		}
		if(content_str.length==results.length && results.length==tips.length){
			dailyCheck_2.setAsset_no(all_Date.getString("asset_no"));
			dailyCheck_2.setAsset_name(all_Date.getString("asset_name"));
			dailyCheck_2.setOperator(all_Date.getString("operator"));
			dailyCheck_2.setError_date(String.format("%tF",date));
			/*List<ShopPlan> list_plan = checkService.findPlanNo(dailyCheck_2);
			if(list_plan.size()!=0){
				dailyCheck_2.setPlan_no(list_plan.get(0).getPlan_no());
				dailyCheck_2.setClient_material_no(list_plan.get(0).getClient_material_no());
			}*/
			List<DailyCheck_2> list = checkService.findDailyCheck(dailyCheck_2);
			if(list.size()==0){
			checkService.insertDailyCheck(dailyCheck_2);
			check_id = dailyCheck_2.getCheck_id();
			}else{
				check_id = list.get(0).getCheck_id(); 
			}
			for(int i=0;i<content_str.length;i++){
		checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
		checkRecord.setCheck_id(check_id);
		checkRecord.setCheck_content(content_str[i]);
		checkRecord.setRemarks_1(tips[i]);
		checkRecord.setResults_1(results[i]);
		/*for(int i = 0;i<song.size();i++) {
			switch (i) {
			case 0:
				checkRecord.setTime_1(song.getJSONObject(i).getString("check_time")+"&"+time);
				break;
			case 1:
				checkRecord.setCheck_id(song.getJSONObject(i).getInt("check_id"));
				break;
			case 2:
				checkRecord.setRemarks_1(song.getJSONObject(i).getString("check_remarks"));
				break;
			case 3:
				checkRecord.setResults_1(song.getJSONObject(i).getString("check_results"));
				break;
			default:
				break;
			}
		}*/
	    int day_time = 10000;
	    int record_id=0;
		List<CheckRecord> checkRecords = checkService.selectCheckList(checkRecord);
		if(checkRecords.isEmpty()) {
			checkService.insertCheckList(checkRecord);
		}else {
		int long_CheckRecords = checkRecords.size();
		String[] check_time = new String[long_CheckRecords];
		for(int k=0;k<long_CheckRecords;k++) {
			check_time[k]=checkRecords.get(k).getTime_1();
		}
		for(int k=0;k<check_time.length;k++) {
			String[] s1 = checkRecord.getTime_1().split("&");
			String[] s2 = check_time[k].split("&");
			if(s1[0].equals(s2[0])) {
				day_time = k;
				break;
			}
		}
		if(day_time==10000) {
			checkService.insertCheckList(checkRecord);
		}else {
		record_id = checkRecords.get(day_time).getRecord_id();
		checkRecord.setRecord_id(record_id);
		int a = checkRecords.get(day_time).getWhereResults();
	    switch(a) {
			case 1:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_1(checkRecord);
				break;
			case 2:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_2(checkRecord);
				break;
			case 3:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_3(checkRecord);
				break;
			case 4:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_4(checkRecord);
				break;
			case 5:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_5(checkRecord);
				break;
			case 6:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_6(checkRecord);
				break;
			case 7:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_7(checkRecord);
				break;
			case 8:
				checkRecord.setTime_1(all_Date.getString("check_time")+"&"+time);
				checkService.updateCheckList_8(checkRecord);
				break;
            default:
            	break;
			}
		}
		}
		System.out.println("点检更新完成");
		JSONObject is_success = new JSONObject();
		is_success.put("is_success","1");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(is_success.toString());
		print.flush();
		print.close();
			}
		}else{
			JSONObject is_success = new JSONObject();
			is_success.put("is_success","2");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(is_success.toString());
			print.flush();
			print.close();
		}
		}catch(Exception e) {
			e.printStackTrace();
			JSONObject is_success = new JSONObject();
			is_success.put("is_success","0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(is_success.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/findDianjian")
	public void findDianjian(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine())!=null) {
			json.append(line);
		}
		String jsonStr = json.toString();
		JSONObject allData = JSONObject.fromObject(jsonStr);
		JSONObject responseDate = new JSONObject();
		JSONObject daily_check_j = new JSONObject();
		JSONObject check_record_j = new JSONObject();
		try{
			String date = allData.getString("date");
			String operator = allData.getString("operator");
			List<DailyCheck_2> check_id_list = checkService.findCheckId(date, operator);
			if(check_id_list!=null && check_id_list.size()!=0){
				for(int i=0;i<check_id_list.size();i++){
					DailyCheck_2 dailyCheck_2 = check_id_list.get(i);
					daily_check_j.put(String.valueOf(i),dailyCheck_2.getAsset_name()+
							"("+dailyCheck_2.getAsset_no()+")");
					List<CheckRecord> checkRecords = checkService.findCheckRecord(dailyCheck_2.getCheck_id());
					CheckRecord checkRecord = checkRecords.get(i);
					List<String> list = new ArrayList<>();
					list.add(checkRecord.getTime_1());
			    	list.add(checkRecord.getTime_2());
			    	list.add(checkRecord.getTime_3());
			    	list.add(checkRecord.getTime_4());
			    	list.add(checkRecord.getTime_5());
			    	list.add(checkRecord.getTime_6());
			    	list.add(checkRecord.getTime_7());
			    	list.add(checkRecord.getTime_8());
			    	list.removeAll(Collections.singleton(null));
			    	JSONObject jsonObject = new JSONObject();
			    	for(int j=0;j<list.size();j++){
			    		jsonObject.put(String.valueOf(j),list.get(j));
			    	}
			    	check_record_j.put(String.valueOf(i),jsonObject);
				}
				responseDate.put("daily_check", daily_check_j);
				responseDate.put("check_record", check_record_j);
				responseDate.put("is_success", "1");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(responseDate.toString());
				print.flush();
				print.close();
			}else{
				responseDate.put("is_success", "-1");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(responseDate.toString());
				print.flush();
				print.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			responseDate.put("is_success", "0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(responseDate.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/assetDianjian")//工段长查看设备点检记录
	public void assetDianjian(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String shop_name=request.getParameter("shop_name");
		List<Asset> assets=checkService.findAsset(shop_name);
		List<AssetStatus> assetStatus=new ArrayList<AssetStatus>();
	    for (int i = 0; i < assets.size(); i++) {
			String Asset_no=assets.get(i).getAsset_no();
			String Asset_name=assets.get(i).getAsset_name();
			String asset_type=assets.get(i).getAsset_type();
			AssetStatus aStatus=new AssetStatus();
			aStatus.setAsset_name(Asset_name);
			aStatus.setAsset_no(Asset_no);
			aStatus.setAsset_type(asset_type);
			List<DailyCheck_2> dailyCheck_2=checkService.findCheck_id(Asset_no);
			if (dailyCheck_2==null){
				aStatus.setStatus("未点检");
				aStatus.setPerson("无");
			}else{
				int checkId=dailyCheck_2.get(0).getCheck_id();
				List<CheckRecord> checkRecords=checkService.findCheckRecord(checkId);
				if (checkRecords==null) {
					aStatus.setStatus("未点检");
					aStatus.setPerson("无");
				}else{
					for (int j = 0; j < checkRecords.size(); j++){
					    if((checkRecords.get(j).getResults_1())!=null && checkRecords.get(j).getResults_1().equals("异常")){
					    	aStatus.setStatus("异常"); 
					    }else if ((checkRecords.get(j).getResults_2())!=null && checkRecords.get(j).getResults_2().equals("异常")){     
					    	aStatus.setStatus("异常"); 
						}else if ((checkRecords.get(j).getResults_3())!=null && checkRecords.get(j).getResults_3().equals("异常")){     
							aStatus.setStatus("异常"); 
					    }else if ((checkRecords.get(j).getResults_4())!=null && checkRecords.get(j).getResults_4().equals("异常")){     
					    	aStatus.setStatus("异常"); 
						}else if ((checkRecords.get(j).getResults_5())!=null && checkRecords.get(j).getResults_5().equals("异常")){     
							aStatus.setStatus("异常"); 
						}else if ((checkRecords.get(j).getResults_6())!=null && checkRecords.get(j).getResults_6().equals("异常")){     
							aStatus.setStatus("异常"); 
						}else if ((checkRecords.get(j).getResults_7())!=null && checkRecords.get(j).getResults_7().equals("异常")){     
							aStatus.setStatus("异常"); 
						}else if ((checkRecords.get(j).getResults_8())!=null && checkRecords.get(j).getResults_8().equals("异常")){     
							aStatus.setStatus("异常"); 
						}
					 }
					if(aStatus.getStatus()==null){
						 aStatus.setStatus("正常");
					}
					aStatus.setPerson(dailyCheck_2.get(0).getOperator());
					
				  }
			   }
			 assetStatus.add(aStatus);
			}
	        JSONObject json=new JSONObject();
	        JSONArray jsonArray=JSONArray.fromObject(assetStatus);
	        json.put("assetStatus", jsonArray);
	        response.setContentType("test/html;charset=utf-8");
			PrintWriter Writer = response.getWriter();
			Writer.write(json.toString());
			Writer.flush();
			Writer.close();        
	
		}
		
	}
