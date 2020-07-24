package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.Content;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.service.ChecklistService;
import cn.itcast.ssm.service.ShopPlanService;

@Controller
@RequestMapping("/content")
public class ContentController {
	private Map map = new HashMap();
	@Autowired
	private ChecklistService checklistService; 
	
	@Autowired
	private ShopPlanService shopPlanService;
	@RequestMapping("/getPersonname")
	public void getPersonname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		  String jsonStr = json.toString();
		JSONObject personNumber = JSONObject.fromObject(jsonStr);   
		String number= personNumber.getString("number");
	    List<Person> list=checklistService.findUser(number);
	    String person_name=list.get(0).getPerson_name();
	    JSONObject object = new JSONObject();
		object.put("person_name",person_name);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		print.write(object.toString());
		print.flush();
		print.close();
		
	}
	
	@RequestMapping("/getContent")
	public void getContent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 request.setCharacterEncoding("UTF-8");
		   StringBuffer json = new StringBuffer();
		   String line = null;
		   BufferedReader reader = request.getReader();
		   while((line = reader.readLine())!=null) {
			   json.append(line);
		   }
		   String jsonStr = json.toString();
		   JSONObject content = JSONObject.fromObject(jsonStr);
		   //String a=content.getString("asser_name");
		   String asset_no = content.getString("asset_no");
		   String asset = new String();
		   String asset_name = new String();
		   JSONObject allData = new JSONObject();
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
				asset_name=ass.getAsset_name();
			    List<String> b=checklistService.findCheckContent_asset();
				for (int i=0;i<b.size();i++){
					if(asset_name.contains(b.get(i))){
						 List<Content> contents = checklistService.findContent(b.get(i), asset_no);
						if(contents.size()!=0){
							allData.put("is_dianjian", 1);
							JSONArray cObject = JSONArray.fromObject(contents);
							allData.put("Content", cObject);
						}else{
							allData.put("is_dianjian",0);
						}   
					   
					}
				}
				allData.put("asset_name", asset);
			}else{
				allData.put("asset_name", "没有该设备信息");
			}
			allData.put("asset_no", asset_no);
			response.setContentType("text/html;charset=utf-8");
   			PrintWriter print = response.getWriter();
   			print.write(allData.toString());
   			print.flush();
   			print.close();
	}
}
//网页端测试 http://192.168.10.7:8080/ssm/content/getContent.action？asset_name=压力机
