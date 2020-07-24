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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.ssm.po.FinishNum;
import cn.itcast.ssm.po.HegelvNum;
import cn.itcast.ssm.po.PlanNum;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ProductionPlan2;
import cn.itcast.ssm.po.ProductionPlanVo;
import cn.itcast.ssm.po.SecondaryMaterials;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.ShopTransition;
import cn.itcast.ssm.service.TvFindService;

@Controller
@RequestMapping("/tv")
public class TvFindController {
	@Autowired
	private TvFindService tvFindService;
	
	@RequestMapping("/findAllOrder")
	@ResponseBody
	public void findAllOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
			   String start_date = allData.getString("start_date");
			   String end_date = allData.getString("end_date");
			   /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			   String date = simpleDateFormat.format(new Date());*/
			   Integer position = allData.getInt("position");
			   JSONArray jsonArray = new JSONArray();
			   switch(position){
			   		case 0:
			   			List<ProductionPlan> plans_all = tvFindService.findAllOrder(start_date,end_date);
			   			for(int i=0;i<plans_all.size();i++){
							plans_all.get(i).jisuanFinishNum();
							plans_all.get(i).setProduction_records(null);
						}
			   			jsonArray = JSONArray.fromObject(plans_all);
			   			break;
			   		case 1:
			   			List<ProductionPlan> plans_no_finish = tvFindService.findNoFinishOrder(start_date,end_date);
			   			for(int i=0;i<plans_no_finish.size();i++){
							plans_no_finish.get(i).jisuanFinishNum();
							plans_no_finish.get(i).setProduction_records(null);
						}
			   			jsonArray = JSONArray.fromObject(plans_no_finish);
			   			break;
			   		case 2:
			   			List<ShopPlan> chongya_plan = tvFindService.findOneShopBatch("冲压工段", start_date, end_date);
			   			for(int i=0;i<chongya_plan.size();i++){
			   				ShopPlan shopPlan = chongya_plan.get(i);
			   				shopPlan.jisuanProducedNum();
			   				shopPlan.setTrackCard(null);
			   				int sum = 0;
			   				List<ShopTransition> shopTransitions = tvFindService.
			   						findTransitionNum(shopPlan.getBatch_no(),"冲压工段");
			   				if(shopTransitions.size()!=0){
			   					for(int j=0;j<shopTransitions.size();j++){
			   						sum += Integer.valueOf(shopTransitions.get(0).getQualifiedNum());
			   					}
			   				}
			   				shopPlan.setSend_num(sum);
			   			}
			   			jsonArray = JSONArray.fromObject(chongya_plan);
			   			break;
			   		case 3:
			   			List<ShopPlan> yibiao_plan = tvFindService.findOneShopBatch("仪表工段", start_date, end_date);
			   			for(int i=0;i<yibiao_plan.size();i++){
			   				ShopPlan shopPlan = yibiao_plan.get(i);
			   				shopPlan.jisuanProducedNum();
			   				shopPlan.setTrackCard(null);
			   				int sum = 0;
			   				List<ShopTransition> shopTransitions = tvFindService.
			   						findTransitionNum(shopPlan.getBatch_no(),"仪表工段");
			   				if(shopTransitions.size()!=0){
			   					for(int j=0;j<shopTransitions.size();j++){
			   						sum += Integer.valueOf(shopTransitions.get(0).getQualifiedNum());
			   					}
			   				}
			   				shopPlan.setSend_num(sum);
			   			}
			   			jsonArray = JSONArray.fromObject(yibiao_plan);
			   			break;
			   		case 4:
			   			List<ShopPlan> hanjie_plan = tvFindService.findOneShopBatch("焊接工段", start_date, end_date);
			   			for(int i=0;i<hanjie_plan.size();i++){
			   				ShopPlan shopPlan = hanjie_plan.get(i);
			   				shopPlan.jisuanProducedNum();
			   				shopPlan.setTrackCard(null);
			   				int sum = 0;
			   				List<ShopTransition> shopTransitions = tvFindService.findTransitionNum(shopPlan.getBatch_no(),"焊接工段");
			   				if(shopTransitions.size()!=0){
			   					for(int j=0;j<shopTransitions.size();j++){
			   						sum += Integer.valueOf(shopTransitions.get(0).getQualifiedNum());
			   					}
			   				}
			   				shopPlan.setSend_num(sum);
			   			}
			   			jsonArray = JSONArray.fromObject(hanjie_plan);
			   			break;
			   		case 5:
			   			List<ShopPlan> houdao_plan = tvFindService.findOneShopBatch("后道工段", start_date, end_date);
			   			for(int i=0;i<houdao_plan.size();i++){
			   				ShopPlan shopPlan = houdao_plan.get(i);
			   				shopPlan.jisuanProducedNum();
			   				shopPlan.setTrackCard(null);
			   				int sum = 0;
			   				List<ShopTransition> shopTransitions = tvFindService.
			   						findTransitionNum(shopPlan.getBatch_no(),"后道工段");
			   				if(shopTransitions.size()!=0){
			   					for(int j=0;j<shopTransitions.size();j++){
			   						sum += Integer.valueOf(shopTransitions.get(0).getQualifiedNum());
			   					}
			   				}
			   				shopPlan.setSend_num(sum);
			   			}
			   			jsonArray = JSONArray.fromObject(houdao_plan);
			   			break;
			   		case 6:
			   			FinishNum finishNum = tvFindService.findFinishNum(start_date, end_date);
			   			JSONObject jsonObject = new JSONObject();
			   			jsonObject = JSONObject.fromObject(finishNum);
			   			jsonArray.add(jsonObject);
			   			break;
			   		case 7:
			   			List<ProductionPlan> hegelv_all = tvFindService.findOrderHegelv(start_date, end_date);
			   			for(int i=0;i<hegelv_all.size();i++){
			   				ProductionPlan productionPlan = hegelv_all.get(i);
			   				PlanNum num = tvFindService.findCipinNum(productionPlan.getPlan_no());
			   				if(num!=null){
			   					productionPlan.setGongfei_num(num.getGongfei_num());
			   					productionPlan.setLiaofei_num(num.getLiaofei_num());
			   					productionPlan.setCipin_num(num.getGongfei_num()+num.getLiaofei_num());
			   				}
			   			}
			   			jsonArray = JSONArray.fromObject(hegelv_all);
			   			break;
			   		case 8:
			   			HegelvNum hegelvNum = tvFindService.findHegelvNum(start_date, end_date);
			   			JSONObject json_s = new JSONObject();
			   			json_s = JSONObject.fromObject(hegelvNum);
			   			jsonArray.add(json_s);
			   			break;
			   		default:
			   			break;
			   }
			   /*List<ProductionPlan> plans_all = tvFindService.findAllOrder(start_date,end_date);
			   List<ProductionPlan> plans_no_finish = tvFindService.findNoFinishOrder(start_date,end_date);
			   List<ProductionPlan> chongya_plan = tvFindService.findProductionPlan3("冲压工段","%冲压工段%");
			   List<ProductionPlan> yibiao_plan = tvFindService.findProductionPlan3("仪表工段","%仪表工段%");
			   List<ProductionPlan> hanjie_plan = tvFindService.findProductionPlan3("焊接工段","%焊接工段%");
			   List<ProductionPlan> houdao_plan = tvFindService.findProductionPlan3("后道工段","%后道工段%");
			   for(int i=0;i<plans_all.size();i++){
				   plans_all.get(i).jisuanFinishNum();
				   plans_all.get(i).setProduction_records(null);
			   }
			   for(int i=0;i<plans_no_finish.size();i++){
				   plans_no_finish.get(i).jisuanFinishNum();
				   plans_no_finish.get(i).setProduction_records(null);
			   }*/
			   /*JSONArray all_jsonArray = JSONArray.fromObject(plans_all);
			   JSONArray no_finish_jsonArray = JSONArray.fromObject(plans_no_finish);
			   JSONArray chongya_jsonArray = JSONArray.fromObject(chongya_plan);
			   JSONArray yibiao_jsonArray = JSONArray.fromObject(yibiao_plan);
			   JSONArray hanjie_jsonArray = JSONArray.fromObject(hanjie_plan);
			   JSONArray houdao_jsonArray = JSONArray.fromObject(houdao_plan);*/
			   SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			   /*FinishNum finishNum = tvFindService.findFinishNum();
			   JSONObject jsonObject = JSONObject.fromObject(finishNum);
			   jsonObject.put("chongya", chongya_jsonArray.size());
			   jsonObject.put("yibiao", yibiao_jsonArray.size());
			   jsonObject.put("hanjie", hanjie_jsonArray.size());
			   jsonObject.put("houdao", houdao_jsonArray.size());
			   jsonObject.put("all", no_finish_jsonArray.size());
			   responseData.put("all", all_jsonArray);
			   responseData.put("no_finish", no_finish_jsonArray);
			   responseData.put("chongya", chongya_jsonArray);
			   responseData.put("yibiao", yibiao_jsonArray);
			   responseData.put("hanjie", hanjie_jsonArray);
			   responseData.put("houdao", houdao_jsonArray);
			   responseData.put("num", jsonObject);*/
			   responseData.put("detail", jsonArray);
			   System.out.println(jsonArray.toString());
			   responseData.put("date", simpleDateFormat2.format(new Date()));
			   response.setContentType("text/html;charset=utf-8");
		   	   PrintWriter print = response.getWriter();
		   	   print.write(responseData.toString());
		   	   print.flush();
		   	   print.close();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	}
	/*
	 @RequestMapping("/queryProductionPlan")//查询  当前月份，工厂所有订单完成情况，按时间顺序列表
	   public void queryProductionPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   List<ProductionPlan> list = tvFindService.findProductionPlan();
		   JSONArray jsonArray=JSONArray.fromObject(list);
		   JSONObject object=new JSONObject();
		   object.put("ProductionPlan", jsonArray);
			response.setContentType("test/html;charset=utf-8");
			PrintWriter writer=response.getWriter();
			writer.write(object.toString());
			writer.flush();
			writer.close();
		   
	   }
	   
	   @RequestMapping("/queryProductionPlan2")//查询  所有月份,未完成订单的完成情况，按时间顺序列表
	   public void queryProductionPlan2(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   List<ProductionPlan2> list = tvFindService.findProductionPlan2();
		   JSONArray jsonArray=JSONArray.fromObject(list);
		   JSONObject object=new JSONObject();
		   object.put("ProductionPlan2", jsonArray);
			response.setContentType("test/html;charset=utf-8");
			PrintWriter writer=response.getWriter();
			writer.write(object.toString());
			writer.flush();
			writer.close();

	   }
	   
	   @RequestMapping("/queryProductionPlan3")//查询  以车间为统计单位，显示当前该车间所有未完成订单生产完成情况
	   public void queryProductionPlan3(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   String shop_name=request.getParameter("shop_name");
		   List<ProductionPlan2> list = tvFindService.findProductionPlan3(shop_name);
		   JSONArray jsonArray=JSONArray.fromObject(list);
		   JSONObject object=new JSONObject();
		   object.put("ProductionPlan3", jsonArray);
			response.setContentType("test/html;charset=utf-8");
			PrintWriter writer=response.getWriter();
			writer.write(object.toString());
			writer.flush();
			writer.close();
	   }*/
}
