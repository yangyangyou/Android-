package cn.itcast.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.po.MaterialNo;
import cn.itcast.ssm.po.Middleproduct;
import cn.itcast.ssm.po.ProductRecord;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Storehouse;
import cn.itcast.ssm.service.GetBanChengService;
import cn.itcast.ssm.service.TransitionService;

@Controller
@RequestMapping("/transition")

public class TransitionController {
	String shop_name;
	private String batchNo;
	private int priority;
	private int plan_num;
	//private Map map = new HashMap();
	@Autowired
	private TransitionService transitionService;
	 @Autowired
	private GetBanChengService getBanChengService;
	 
	@RequestMapping("/getProductionPlan")//查询生产计划表
	public void getProductionPlan(HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		String type=request.getParameter("type");
		List<ProductionPlan> list=transitionService.findProduction_plan();
		JSONArray jsonArray=JSONArray.fromObject(list);
		JSONObject object=new JSONObject();
		object.put("ProductionPlan", jsonArray);
		response.setContentType("test/html;charset=utf-8");
		PrintWriter writer=response.getWriter();
		writer.write(object.toString());
		writer.flush();
		writer.close();
	}
	 
	 
	@RequestMapping("/getPlan_num")//统计员查询剩余总量
	public void getPlan_num(HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		priority=Integer.parseInt(request.getParameter("priority"));
		shop_name= request.getParameter("shop_name");
		batchNo= request.getParameter("batch_no");
		String str=transitionService.findPlan_num(shop_name,batchNo,priority);
		System.out.println("+++++++++++++++++"+str);
		JSONObject json=new JSONObject();
		if(str!=null){
			json.put("Plan_num",str);
		}else{
			String str2=transitionService.findPlan_num2(shop_name, batchNo);
			System.out.println("111111111111111111"+str2);
			json.put("Plan_num",str2);
		}
		/**
		 * 测试获取MYsql数据库人员表*/
	/*	JSONArray jsonArray = new JSONArray();
		JSONObject json1=new JSONObject();
		JSONObject json2=new JSONObject();
		List<Person> list=transitionService.findPerson();
		System.out.println("66666666666666"+list.get(0).getPerson_name());
		json2.put("Person", list);
		if(str!=null){
			json1.put("Plan_num",str);
		}else{
			String str2=transitionService.findPlan_num2(shop_name, batchNo);
			System.out.println("111111111111111111"+str2);
			json1.put("Plan_num",str2);
		}
		jsonArray.add(json1);
		jsonArray.add(json2);
		*/
		response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(json.toString());
		Writer.flush();
		Writer.close();
	}

	@RequestMapping("/getPlan_num2")//工段长查询剩余总量
	public void getPlan_num2(HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		String str;
		priority=Integer.parseInt(request.getParameter("priority"));
		shop_name= request.getParameter("shop_name");
		batchNo= request.getParameter("batch_no");
		String str3=transitionService.findPlan_num4(shop_name, batchNo, priority);
	   if(str3==null){
		 str=transitionService.findPlan_num(shop_name, batchNo, priority);
	   }else{
		 str=transitionService.findPlan_num3(shop_name,batchNo,priority);
	   }
		System.out.println("+++++++++++++++++"+str);
		JSONObject json=new JSONObject();
		if(str!=null){
			json.put("Plan_num",str);
		}else{
			String str2=transitionService.findPlan_num2(shop_name, batchNo);
			System.out.println("111111111111111111"+str2);
			json.put("Plan_num",str2);
		}
		response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(json.toString());
		Writer.flush();
		Writer.close();
	}

	
	@RequestMapping("/insertTransition")//工段长交接：车间>车间，车间>统计员
	public void insertTransition(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		int priority=Integer.parseInt(request.getParameter("priority"));
		String shop_name= request.getParameter("shop_name");
		String batchNo= request.getParameter("batch_no");
		String qualifiedNum = request.getParameter("qualified_num");
		String unqualifiedNum= request.getParameter("unqualified_num");
		String provider = request.getParameter("provider");
		String acceptor = request.getParameter("acceptor");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = time.format(new Date());//获取当天日期
	 if(!(provider.isEmpty())&&!(acceptor.isEmpty())){
		String shop1=transitionService.findDepartment(provider);
		String shop2=transitionService.findDepartment(acceptor);
		System.out.println("所在部门========"+shop1);
		List<MaterialNo> list=transitionService.findClient_material_no(batchNo);//
		String planNo=list.get(0).getPlan_no();
		String clientMaterialNo=list.get(0).getClient_material_no();
		String materialNo=list.get(0).getMaterial_no();
		System.out.println("计划单号"+planNo+"客户物料号"+clientMaterialNo+"物料号"+materialNo);
		transitionService.insertTransition(planNo, clientMaterialNo,materialNo, batchNo,shop1,shop2,qualifiedNum, unqualifiedNum, tranDate, provider, acceptor,priority);
		transitionService.updateDelivery_num(planNo, materialNo, batchNo,shop_name,tranDate,priority);
		JSONObject object = new JSONObject();
			object.put("is_ok","1");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close(); 
		}else{
			JSONObject object = new JSONObject();
			object.put("is_ok","0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/BanChenTransition")//半成品交接：半成品车间>成品车间，包含：交接记录，半成品领取记录，半成品入库记录,交付数量记录，仓库记录
	public void BanChenTransition(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		int type=Integer.parseInt(request.getParameter("type"));
		int priority=Integer.parseInt(request.getParameter("priority"));
		String shop_name= request.getParameter("shop_name");
		String batchNo= request.getParameter("batch_no");
		String chenbatchNo=request.getParameter("chenbatch_no");
		String qualifiedNum = request.getParameter("qualified_num");
		String unqualifiedNum= request.getParameter("unqualified_num");
		String provider = request.getParameter("provider");
		String acceptor = request.getParameter("acceptor");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = time.format(new Date());//获取当天日期
		int AllunqualifiedNum;
		int AllqualifiedNum;
		int all_num;
	    plan_num=Integer.parseInt(getBanChengService.findPlanNum(batchNo));
	 if(!(provider.isEmpty())&&!(acceptor.isEmpty())){
		String shop1=transitionService.findDepartment(provider);
		String shop2=transitionService.findDepartment(acceptor);
		System.out.println("所在部门========"+shop1);
		List<MaterialNo> list=transitionService.findClient_material_no(batchNo);//
		String planNo=list.get(0).getPlan_no();
		String clientMaterialNo=list.get(0).getClient_material_no();
		String materialNo=list.get(0).getMaterial_no();
		System.out.println("计划单号"+planNo+"客户物料号"+clientMaterialNo+"物料号"+materialNo);
		
		List<ShopDelivery> list2=transitionService.findShop_name(planNo, clientMaterialNo, materialNo);
		String shopName=list2.get(0).getShop_name();
		int Priority=list2.get(0).getPriority();
		ProductRecord productRecord = new ProductRecord();
		Storehouse storehouse = new Storehouse();
	    /*--------------自动完成      半成品入库-------------------------*/
		if(shop_name.equals(shopName) && shop1.equals(shopName) && (priority==Priority)){
			transitionService.insertTransition(planNo, clientMaterialNo,materialNo, batchNo,shop1,shop2,qualifiedNum, unqualifiedNum, tranDate, provider, acceptor,priority);
			transitionService.updateDelivery_num(planNo, materialNo, batchNo,shop_name,tranDate,priority);
			String qualified_weight=null;
			AllunqualifiedNum=Integer.parseInt(getBanChengService.findUnqualifed_num(batchNo));
			AllqualifiedNum=Integer.parseInt(getBanChengService.findQualifed_num(shop_name, batchNo, priority));
			all_num=AllqualifiedNum+AllunqualifiedNum;
			 productRecord.setBatchNo1("");
	    	 productRecord.setBatchNo2(batchNo);
	    	 productRecord.setRemark("半成品入库");
	    	 storehouse.setBatchNo(batchNo);
			 productRecord.setProductNum(qualifiedNum);
			 productRecord.setWeight(qualified_weight);
			 storehouse.setMaterialNum(qualifiedNum);
			 productRecord.setUnit("个");
			 storehouse.setUnit("个");
			 productRecord.setProvider(provider);
			 productRecord.setShop1(shop1);
			 productRecord.setAcceptor(acceptor);
			 productRecord.setShop2(shop2); 
			 productRecord.setTransDate(tranDate);
			 productRecord.setIsReturn(1);//0是出库，1是入库
			 storehouse.setIsCp(0);//0是半成品，1是成品
			 List<ShopPlan> shopPlanList = getBanChengService.findFromShopPlan(batchNo);
		  if(shopPlanList.size()!=0 ){
			  productRecord.setPlanNo(shopPlanList.get(0).getPlan_no());
			  storehouse.setPlanNo(shopPlanList.get(0).getPlan_no());
			  productRecord.setClientMaterialNo(shopPlanList.get(0).getClient_material_no());
			  storehouse.setClientMaterialNo(shopPlanList.get(0).getClient_material_no());
			  productRecord.setMaterialNo(shopPlanList.get(0).getMaterial_no());
			  storehouse.setMaterialNo(shopPlanList.get(0).getMaterial_no());
			  productRecord.setProductMc(shopPlanList.get(0).getMaterial_name());
			  storehouse.setMaterialName(shopPlanList.get(0).getMaterial_name());
			  storehouse.setMaterialSpec(shopPlanList.get(0).getProduct_spec());
			  getBanChengService.insertProductRecord(productRecord);
			  if(type==1){
					Integer num =Integer.valueOf(getBanChengService.findProductNum(batchNo, planNo));
					getBanChengService.updateAllNumOneBatch(planNo, clientMaterialNo, materialNo, batchNo, num);
				}
			  List<Storehouse> list3=getBanChengService.findStorehouse(batchNo);
			   if(list3.size()==0){
				   getBanChengService.insertStorehouse(storehouse);
			    }else{
				   getBanChengService.updatematerialNum(qualifiedNum, batchNo);	
			   }
	       }
		 /*--------------自动完成  领取半成品-------------------------*/
		  productRecord.setBatchNo1(chenbatchNo);
		  productRecord.setRemark("领取组合件");
		  productRecord.setIsReturn(0);//0是出库，1是入库
		  List<ShopPlan> shopPlanList1 = getBanChengService.findFromShopPlan(chenbatchNo);
		  productRecord.setPlanNo(shopPlanList1.get(0).getPlan_no());
		  storehouse.setPlanNo(shopPlanList1.get(0).getPlan_no());
		  productRecord.setClientMaterialNo(shopPlanList1.get(0).getClient_material_no());
		  storehouse.setClientMaterialNo(shopPlanList1.get(0).getClient_material_no());
		  productRecord.setMaterialNo(shopPlanList1.get(0).getMaterial_no());
		  storehouse.setMaterialNo(shopPlanList1.get(0).getMaterial_no());
		  productRecord.setProductMc(shopPlanList1.get(0).getMaterial_name());
		  storehouse.setMaterialName(shopPlanList1.get(0).getMaterial_name());
		  storehouse.setMaterialSpec(shopPlanList1.get(0).getProduct_spec());
		  getBanChengService.insertProductRecord(productRecord);
		  String product_num=productRecord.getProductNum(); 
		  getBanChengService.updatematerialNum2(product_num, batchNo);
		  
		  JSONObject planNum=new JSONObject();
		  JSONObject AllNum=new JSONObject();
		  JSONObject object = new JSONObject();
		  JSONArray jsonArray=new JSONArray();
			object.put("is_ok","1");
			planNum.put("plan_num", plan_num);
			AllNum.put("all_num", all_num);
			jsonArray.add(object);
			jsonArray.add(planNum);
			jsonArray.add(AllNum);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(jsonArray.toString());
			print.flush();
			print.close(); 
		}else{
			JSONArray jsonArray=new JSONArray();
    	    JSONObject planNum=new JSONObject();
    	    JSONObject AllNum=new JSONObject();
		    JSONObject object = new JSONObject();
			object.put("is_ok","2");
			planNum.put("plan_num", plan_num);
			AllNum.put("all_num", plan_num);
			jsonArray.add(object);
			jsonArray.add(planNum);
			jsonArray.add(AllNum);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(jsonArray.toString());
			print.flush();
			print.close(); 
		  }
		
		   }else{
			   JSONArray jsonArray=new JSONArray();
	    	   JSONObject planNum=new JSONObject();
	    	   JSONObject AllNum=new JSONObject();
			   JSONObject object = new JSONObject();
				object.put("is_ok","0");
				planNum.put("plan_num", plan_num);
				AllNum.put("all_num", plan_num);
				jsonArray.add(object);
				jsonArray.add(planNum);
				jsonArray.add(AllNum);
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(jsonArray.toString());
				print.flush();
				print.close();
		}
	}
	
	@RequestMapping("/findnum")//统计员交接:获取净重
	public void findnum(HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		String batchNo= request.getParameter("batch_no");
		List<MaterialNo> list=transitionService.findClient_material_no(batchNo);
		String materialNo=list.get(0).getMaterial_no();
		float weight=transitionService.findWeight(materialNo);
		DecimalFormat fnum = new  DecimalFormat("##0.00");  
		String wString=fnum.format(weight);     
		//String wString= String.valueOf(weight);
		JSONObject object=new JSONObject();
		object.put("weight", wString);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter=response.getWriter();
		printWriter.write(object.toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/insertTransition2")//统计员交接：统计员>车间
	public void insertTransition2(HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		String batchNo= request.getParameter("batch_no");
		String qualifiedNum = request.getParameter("qualified_num");
		String unqualifiedNum= request.getParameter("unqualified_num");
		String provider =request.getParameter("provider");
		String acceptor = request.getParameter("acceptor");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = time.format(new Date());//获取当天日期
		if(!(provider.isEmpty())&&!(acceptor.isEmpty())){
		String shop1=transitionService.findDepartment(provider);
		String shop2=transitionService.findDepartment(acceptor);
		List<MaterialNo> list=transitionService.findClient_material_no(batchNo);
		String planNo=list.get(0).getPlan_no();
		String clientMaterialNo=list.get(0).getClient_material_no();
		String materialNo=list.get(0).getMaterial_no();
		int priority=1;
		transitionService.insertTransition(planNo, clientMaterialNo,materialNo, batchNo,shop1,shop2,qualifiedNum, unqualifiedNum, tranDate, provider, acceptor,priority);
		 JSONObject object = new JSONObject();
			object.put("is_ok","1");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close(); 
		}else{
			JSONObject object = new JSONObject();
			object.put("is_ok","0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print = response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close();
		}
	}
	
	@RequestMapping("/insertOutTransition")//外协交接:部分外协和整体外协，部分外协不更新，在班组交接里更新；整体外协根据type=2更新
	public void insertOutTransition(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		String batchNo= request.getParameter("batch_no");
		String qualifiedNum = request.getParameter("qualified_num");
		String person = request.getParameter("provider");
		String person2=request.getParameter("acceptor");
		String is_pro = request.getParameter("is_pro");
		int is_out=Integer.parseInt(request.getParameter("is_out"));
		int is_cp=Integer.parseInt(request.getParameter("is_cp"));
		String acceptor=null;
		String provider=null;
		String shop1 = null;
		String shop2 = null;
		String planNo=null;
		String clientMaterialNo=null;
		String materialNo=null;
		String qualified_weight=null;
		List<MaterialNo> list = new ArrayList<MaterialNo>();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate2 = request.getParameter("tran_date");
		Date tranDate3 = time.parse(tranDate2);
		String tranDate = time.format(tranDate3);
	
		if(!(person.isEmpty())){
			if(is_pro.equals("fahuo")){
				 provider=person;
				 acceptor=person2;
				 shop1 = transitionService.findDepartment(person);
				 shop2 ="外协中";
			}else if(is_pro.equals("shouhuo")) {
				 acceptor=person;
				 provider=person2;
				 shop1="外协";
				 shop2=transitionService.findDepartment(person);
			}			
			ProductRecord productRecord = new ProductRecord();
			Storehouse storehouse = new Storehouse();
		if(is_out==1){
			 /*--------------原材料外协，交接自动入库-------------------------*/
			 qualified_weight = request.getParameter("qualified_weight");
			 list=transitionService.findClient_material_no2(batchNo);
			 planNo=list.get(0).getPlan_no();
			 clientMaterialNo=list.get(0).getClient_material_no();
			 materialNo=list.get(0).getMaterial_no();
			 transitionService.insertOutTransition(planNo,clientMaterialNo,materialNo, batchNo,shop1,shop2,provider,acceptor,tranDate,qualifiedNum,type);
			 if(is_cp==1){
				 productRecord.setBatchNo1(batchNo);
			 }else{
				 productRecord.setBatchNo2(batchNo);
			 }
	    	 productRecord.setRemark("原材料外协入库");
	    	 storehouse.setBatchNo(batchNo);
			 productRecord.setProductNum(qualifiedNum);
			 storehouse.setMaterialNum(qualifiedNum);
			 productRecord.setWeight(qualified_weight);
			 productRecord.setUnit("个");
			 storehouse.setUnit("个");
			 productRecord.setProvider(provider);
			 productRecord.setShop1(shop1);
			 productRecord.setAcceptor(acceptor);
			 productRecord.setShop2(shop2); 
			 productRecord.setTransDate(tranDate);
			 productRecord.setIsReturn(1);//0是出库，1是入库
			 storehouse.setIsCp(0);//0是半成品，1是成品 
			 productRecord.setPlanNo(list.get(0).getPlan_no());
			 storehouse.setPlanNo(list.get(0).getPlan_no());
			 productRecord.setClientMaterialNo(list.get(0).getClient_material_no());
			 storehouse.setClientMaterialNo(list.get(0).getClient_material_no());
			 productRecord.setMaterialNo(list.get(0).getMaterial_no());
			 storehouse.setMaterialNo(list.get(0).getMaterial_no());
			 productRecord.setProductMc(list.get(0).getMaterial_name());
			 storehouse.setMaterialName(list.get(0).getMaterial_name());
			 storehouse.setMaterialSpec(list.get(0).getProduct_spec());
			 getBanChengService.insertProductRecord(productRecord);
			 List<Storehouse> list3=getBanChengService.findStorehouse(batchNo);
			 if(list3.size()==0){
				   getBanChengService.insertStorehouse(storehouse);
			   }else{
				   getBanChengService.updatematerialNum(qualifiedNum, batchNo);	
			   }
			 
			   String material_no=productRecord.getMaterialNo();
			   List<Middleproduct> list1=getBanChengService.findMiddleProduct(material_no);
			   if(list1.size()!=0){
				   getBanChengService.updateAddNum(qualifiedNum, material_no);
			   } 
			
			}else{
		     list=transitionService.findClient_material_no(batchNo);
		     planNo=list.get(0).getPlan_no();
			 clientMaterialNo=list.get(0).getClient_material_no();
			 materialNo=list.get(0).getMaterial_no();
			 transitionService.insertOutTransition(planNo,clientMaterialNo,materialNo, batchNo,shop1,shop2,provider,acceptor,tranDate,qualifiedNum,type);
			}
		
		  /*  String planNo=list.get(0).getPlan_no();
		    String clientMaterialNo=list.get(0).getClient_material_no();
		    String materialNo=list.get(0).getMaterial_no();
		    transitionService.insertOutTransition(planNo,clientMaterialNo,materialNo, batchNo,shop1,shop2,provider,acceptor,tranDate,qualifiedNum,type);*/
		    if((shop1.equals("外协"))){
		    	if(type==2){
		    		transitionService.updateDelivery_num2(planNo, materialNo, batchNo,shop1,tranDate);	
		    	}else if(type==1){
		    		transitionService.updateDelivery_num3(planNo, materialNo, batchNo,shop1,tranDate);
		    	}
		    }else{
				 System.out.println("部分外协/整体外协发货时不更新");
			 }	
			 JSONObject object = new JSONObject();
				object.put("is_ok","1");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(object.toString());
				print.flush();
				print.close(); 		
		}else{
			JSONObject object = new JSONObject();
			object.put("is_ok","0");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter print=response.getWriter();
			print.write(object.toString());
			print.flush();
			print.close();
		}
	}
}


	

