package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.MaterialNo;
import cn.itcast.ssm.po.Middleproduct;
import cn.itcast.ssm.po.ProductRecord;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Storehouse;
import cn.itcast.ssm.service.GetBanChengService;
import cn.itcast.ssm.service.PushShopPlanService;
import cn.itcast.ssm.service.TransitionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/getBanCheng")
public class GetBanChengController {
   private ProductRecord productRecord;
   private Storehouse storehouse;
   private int plan_num;
   @Autowired
   private GetBanChengService getBanChengService;
   @Autowired
   private TransitionService transitionService;
   @Autowired
   private PushShopPlanService pushShopPlanService;
   
   @RequestMapping("/BanChengDetail")// 领取/退还半成品 
   public void BanChengDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   productRecord=new ProductRecord();
	   storehouse=new Storehouse();
	   Date date = new Date();
	   String date_str = String.format("%tF",date);
	   request.setCharacterEncoding("UTF-8");
	   StringBuffer json = new StringBuffer();
	   String line = null;
	   BufferedReader reader = request.getReader();
	   while((line = reader.readLine())!=null) {
		   json.append(line);
	   }
	   String jsonStr = json.toString();
	   String qualified_weight=null;
	   try {
	   JSONObject allData = JSONObject.fromObject(jsonStr);
	   JSONArray details = allData.getJSONArray("allData");
	   //getMaterial.setShop_name(details.getJSONObject(0).getString("shop_name"));
	   productRecord.setBatchNo1(details.getJSONObject(1).getString("zuhejian_pici"));
	   productRecord.setBatchNo2(details.getJSONObject(2).getString("bancheng_pici"));
	   storehouse.setBatchNo(details.getJSONObject(2).getString("bancheng_pici"));
	   
	   productRecord.setProductNum(details.getJSONObject(3).getString("number"));
	   productRecord.setWeight(qualified_weight);
	   storehouse.setMaterialNum(details.getJSONObject(3).getString("number"));
	   productRecord.setUnit(details.getJSONObject(4).getString("unit"));
	   storehouse.setUnit(details.getJSONObject(4).getString("unit"));
	   
	   productRecord.setProvider(details.getJSONObject(5).getString("lingliaoren"));
	   productRecord.setAcceptor(details.getJSONObject(6).getString("tongjiyuan"));
	   String linliaoren=details.getJSONObject(5).getString("lingliaoren");
	   System.out.println("领料人111111"+linliaoren);
	   String tongjiyuan = details.getJSONObject(6).getString("tongjiyuan");
	   String shop2=transitionService.findDepartment(tongjiyuan);;
	   /*if(linliaoren.equals("")){
		   shop2="发货中";
	   }else{
		   shop2=transitionService.findDepartment(tongjiyuan);
	   }*/
	   String shop1=transitionService.findDepartment(productRecord.getProvider()); //
       productRecord.setShop1(shop1);
	   productRecord.setShop2(shop2); 
	   productRecord.setTransDate(date_str);
	   productRecord.setRemark(details.getJSONObject(8).getString("remark"));
	   int type=Integer.parseInt(details.getJSONObject(7).getString("is_back"));
	   productRecord.setIsReturn(type);//0是出库，1是入库
	   int is_cp=Integer.parseInt(details.getJSONObject(9).getString("is_cp"));
	   storehouse.setIsCp(is_cp);//0是半成品，1是成品
	   String batch_no;
	   if(is_cp==1){
		    batch_no=productRecord.getBatchNo1();
	   }else{
		    batch_no=productRecord.getBatchNo2();
	   }
	   String product_num=productRecord.getProductNum();
	   List<ShopPlan> shopPlanList = getBanChengService.findFromShopPlan2(batch_no);
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
		   List<Storehouse> list=getBanChengService.findStorehouse(batch_no);
		   if(list.size()==0){
			   getBanChengService.insertStorehouse(storehouse);
		   }else{
			   if(type==1){	//0是出库，1是入库     
				getBanChengService.updatematerialNum(product_num, batch_no);	
				
			   }else if(type==0){
				getBanChengService.updatematerialNum2(product_num, batch_no);  
			   }
		   }
		   String material_no=productRecord.getMaterialNo();
		   List<Middleproduct> list1=getBanChengService.findMiddleProduct(material_no);
		   if(list1.size()!=0){
			   if(type==1){	//0是出库，1是入库     
				   getBanChengService.updateAddNum(product_num, material_no); 
			   } else if(type==0){
				   getBanChengService.updateSubNum(product_num, material_no);
			   }
		   }
			   JSONObject is_ok = new JSONObject();
			   is_ok.put("is_ok", "1");
			   response.setContentType("text/html;charset=utf-8");
				PrintWriter print = response.getWriter();
				print.write(is_ok.toString());
				print.flush();
				print.close();
	   }else{
		   JSONObject is_ok = new JSONObject();
		   is_ok.put("is_ok", "0");
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(is_ok.toString());
		   print.flush();
		   print.close();
	   }
	   }catch(Exception e) {
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
   
   @RequestMapping("/BanChengIn")//半/成品入库
   public void BanChengIn(HttpServletRequest request,HttpServletResponse response)throws Exception{
	    request.setCharacterEncoding("UTF-8");
	    int type=Integer.parseInt(request.getParameter("type"));
	    int priority=Integer.parseInt(request.getParameter("priority"));
		String shop_name= request.getParameter("shop_name");
		String batchNo= request.getParameter("batch_no");
		String qualifiedNum = request.getParameter("qualified_num");
		String unqualifiedNum= request.getParameter("unqualified_num");
		String provider = request.getParameter("provider");
		String acceptor = request.getParameter("acceptor");
		int is_cp=Integer.parseInt(request.getParameter("is_cp"));
		System.out.println("0为半成品,1为成品"+is_cp);
		System.out.println("提供人，接收人"+provider+"...."+acceptor);
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String tranDate = time.format(new Date());//获取当天日期
		int AllunqualifiedNum;
		String qualified_weight=null;
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
		transitionService.insertTransition(planNo, clientMaterialNo,materialNo, batchNo,shop1,shop2,qualifiedNum, unqualifiedNum, tranDate, provider, acceptor,priority);
		transitionService.updateDelivery_num(planNo, materialNo, batchNo,shop_name,tranDate,priority);//更新交付数量
		AllunqualifiedNum=Integer.parseInt(getBanChengService.findUnqualifed_num(batchNo));
		AllqualifiedNum=Integer.parseInt(getBanChengService.findQualifed_num(shop_name, batchNo, priority));
		all_num=AllqualifiedNum+AllunqualifiedNum;
		System.out.println("查询计划数量..."+plan_num+"总的交接数量..."+all_num);
		ProductRecord productRecord = new ProductRecord();
		Storehouse storehouse = new Storehouse();
		     if(is_cp==1){
		    	 productRecord.setBatchNo1(batchNo);
		    	 productRecord.setBatchNo2("");
		    	 productRecord.setRemark("成品入库");
		     }else {
		    	 qualified_weight = request.getParameter("qualified_weight");
		    	 productRecord.setBatchNo1("");
		    	 productRecord.setBatchNo2(batchNo);
		    	 productRecord.setRemark("半成品入库");
			}
			  storehouse.setBatchNo(batchNo);
			  productRecord.setProductNum(qualifiedNum);
			  productRecord.setWeight(qualified_weight);
			  storehouse.setMaterialNum(qualifiedNum);
			  productRecord.setUnit("只");
			  storehouse.setUnit("只");
			  productRecord.setProvider(provider);
			  productRecord.setShop1(shop1);
			  productRecord.setAcceptor(acceptor);
			  productRecord.setShop2(shop2); 
			  productRecord.setTransDate(tranDate);
			  productRecord.setIsReturn(1);//0是出库，1是入库
			  storehouse.setIsCp(is_cp);//0是半成品，1是成品
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
				  List<String> cangku = pushShopPlanService.findCangKuGM();
				  JSONObject message = new JSONObject();
				  message.put("type", "chengpin_in");
				  JSONArray jsonArray = new JSONArray();
				  jsonArray.add(JSONObject.fromObject(productRecord));
				  message.put("chengpin_in", jsonArray);
				  getMaterialmessToSocket(message.toString(), cangku);
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
				  
				   String material_no=productRecord.getMaterialNo();
				   List<Middleproduct> list1=getBanChengService.findMiddleProduct(material_no);
				   if(list1.size()!=0){
					   getBanChengService.updateAddNum(qualifiedNum, material_no);
				   }
		       }
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
   
   @RequestMapping("/Change")//
   public void Change(HttpServletRequest request,HttpServletResponse response)throws Exception{
	   request.setCharacterEncoding("UTF-8");
	   String batch_no=request.getParameter("batch_no");
	   int is_cp=Integer.parseInt(request.getParameter("is_cp"));
	   int is_normal=Integer.parseInt(request.getParameter("is_normal"));
	   if (is_cp==0) {
		   getBanChengService.updateBanAbnormal(batch_no, is_normal); 
	  }else {
		  getBanChengService.updateChenAbnormal(batch_no, is_normal);
	}
	   JSONObject is_ok = new JSONObject();
	   is_ok.put("is_ok", "1");
	   response.setContentType("text/html;charset=utf-8");
	   PrintWriter print = response.getWriter();
	   print.write(is_ok.toString());
	   print.flush();
	   print.close();
   }
 
   public static void getMaterialmessToSocket(String string,List<String> user) throws Exception {
		Collection<IoSession> sessions = null;
		HashMap<String,String> map = new HashMap<>();
		if(user.size()!=0) {
			for(int i=0;i<user.size();i++) {
				map.put(user.get(i),"1");
			}
		}
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
			CangkuMessCache planMessCache = new CangkuMessCache();
			planMessCache.setMessage(string);
			planMessCache.setPush_people(map);
			MessCacheManager.getInstance().insertCangkuMessCache(planMessCache);
		}else {
			int a = 0;
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(map.get(c[0])!=null) {
					s.write(string);
					map.remove(c[0]);
					a++;
				}
			}
			if(a != user.size()) {
				CangkuMessCache planMessCache = new CangkuMessCache();
				planMessCache.setMessage(string);
				planMessCache.setPush_people(map);
				MessCacheManager.getInstance().insertCangkuMessCache(planMessCache);
			}
		}
	}
   
   @RequestMapping("/findNoRecordChengpinIn")//
   public void findNoRecordChengpinIn(HttpServletRequest request,HttpServletResponse response)throws Exception{
	   JSONObject jsonObject = new JSONObject();
	   try{
		   List<ProductRecord> productRecords = getBanChengService.findNoRecordChengpinIn();
		   if(productRecords.size()==0){
			   jsonObject.put("size", 0);
		   }else{
			   jsonObject.put("size",productRecords.size());
			   jsonObject.put("details", JSONArray.fromObject(productRecords));
		   }
	   }catch (Exception e) {
		   e.printStackTrace();
		   jsonObject.put("size", -1);
	   }finally{
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter print = response.getWriter();
		   print.write(jsonObject.toString());
		   print.flush();
		   print.close();
	   }
   }
   
   @RequestMapping("/updateNoRecordChengpinIn")//
   public void updateNoRecordChengpinIn(HttpServletRequest request,HttpServletResponse response)throws Exception{
	   request.setCharacterEncoding("UTF-8");
	   JSONObject jsonObject = new JSONObject(); 
	   int record_id =Integer.parseInt(request.getParameter("record_id"));
	   try{
		   getBanChengService.updateChengpinInRecord(record_id);
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
