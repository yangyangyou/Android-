package cn.itcast.ssm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.ssm.method.BindPortAccpector;
import cn.itcast.ssm.method.MessCacheManager;
import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.GetAndBackCailiao;
import cn.itcast.ssm.po.GetDetail;
import cn.itcast.ssm.po.GetDetailsVo;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.MaterialBatchNo;
import cn.itcast.ssm.service.MaterialService;
import cn.itcast.ssm.service.PushShopPlanService;
import cn.itcast.ssm.service.ShopPlanService;

@Controller
@RequestMapping("/material")

public class MaterialController{
    private String material_no;
    private String batch_no;
    private String shop_name;
    private List<MaterialBatchNo> list2;
	
	//private Map map = new HashMap();
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ShopPlanService shopPlanService;
	@Autowired
	private PushShopPlanService pushShopPlanService;
	
	@RequestMapping("/getMaterial")//
	public void getMaterial(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
	    material_no = request.getParameter("material_no");
		batch_no= request.getParameter("batch_no");
		shop_name= request.getParameter("shop_name");
		List<GetDetail> list =materialService.findMaterial(material_no, batch_no, shop_name);
	    List<Integer> get_material_id = materialService.findGet_material_id(material_no, batch_no, shop_name);
	    if(list.size()==0 || get_material_id.size()==0){
	    	JSONObject json=new JSONObject();
	    	JSONArray jsonCheck = JSONArray.fromObject(list);
	    	json.put("is_ok", "0");
	    	json.put("Material", jsonCheck);
	    	response.setContentType("test/html;charset=utf-8");
			PrintWriter Writer = response.getWriter();
			Writer.write(json.toString());
			Writer.flush();
			Writer.close();
	    }else{
			JSONObject json=new JSONObject();	
	        JSONArray jsonCheck = JSONArray.fromObject(list);
		    json.put("is_ok", "1");
		    json.put("get_material_id", get_material_id.get(0));
	        json.put("Material", jsonCheck);
			response.setContentType("test/html;charset=utf-8");
			PrintWriter Writer = response.getWriter();
			Writer.write(json.toString());
			Writer.flush();
			Writer.close();
	    }
	}
	
	@RequestMapping("/updateMaterial")//
	public void updateMaterial(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject = materialService.updateMaterial(request);
		}catch (Exception e) {
			jsonObject.put("is_ok", "0");
			e.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter print = response.getWriter();
			print.write(jsonObject.toString());
			print.flush();
			print.close();
		}
	}
	@RequestMapping("/findCailiao_bh")
	public void findCailiao_bh(HttpServletRequest request,HttpServletResponse response) throws IOException{
	request.setCharacterEncoding("UTF-8");
	String material_no = request.getParameter("client_material_no");
    String batch_no= request.getParameter("batch_no");	
    String cailiao_mc=request.getParameter("cailiao_mc");
    String cailiao_bh=materialService.findCailiao_bh(batch_no,material_no,cailiao_mc);
    if(cailiao_bh!=null){
    	JSONObject json=new JSONObject();
    	json.put("cailiao_bh", cailiao_bh);
    	response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(json.toString());
		Writer.flush();
		Writer.close();
    }else{
    	JSONObject json=new JSONObject();
    	json.put("cailiao_bh", "0");
    	response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(json.toString());
		Writer.flush();
		Writer.close();
     }	
	}
	
	@RequestMapping("/returnMaterial")//
	public void returnMaterial(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine()) != null){
			json.append(line);
		}
		try {
		String jsonStr = json.toString(); 
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		String material_no = jsonArray.getJSONObject(0).getString("client_material_no");
		String batch_no= jsonArray.getJSONObject(1).getString("batch_no");
		String shop_name= jsonArray.getJSONObject(2).getString("shop_name");
		String cailiao_mc=jsonArray.getJSONObject(5).getString("cailiao_mc");
		String cailiao_bh=jsonArray.getJSONObject(6).getString("cailiao_bh");
		String material_num=jsonArray.getJSONObject(7).getString("material_num");
		String provider= jsonArray.getJSONObject(3).getString("provider");
		String acceptor= jsonArray.getJSONObject(4).getString("acceptor");
		String remark= jsonArray.getJSONObject(8).getString("remark");
		String unit = jsonArray.getJSONObject(9).getString("unit");
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String get_date = time.format(new Date());//
	if(material_no!=null&&batch_no!=null&&shop_name!=null&&cailiao_bh!=null&&material_num!=null&&provider!=null&&acceptor!=null){
		List<Integer> get_material_id = materialService.findGet_material_id(material_no, batch_no, shop_name);
		if(get_material_id.size()!=0){
			materialService.insertGet_detail(get_material_id.get(0), cailiao_mc, cailiao_bh, material_num, provider, acceptor, get_date, remark,unit);
			List<String> cangku = pushShopPlanService.findCangKuGM();
			  JSONObject jsonObject2 = new JSONObject();
			  JSONObject lingliao_dan = new JSONObject();
			  lingliao_dan.put("batch_no", batch_no);
			  lingliao_dan.put("material_no", material_no);
			  lingliao_dan.put("shop_name", shop_name);
			  lingliao_dan.put("remark", remark);
			  lingliao_dan.put("acceptor", acceptor);
			  lingliao_dan.put("provider", provider);
			  lingliao_dan.put("date", get_date);
			  lingliao_dan.put("is_return", 1);
			  List<GetDetail> getDetails = new ArrayList<>();
			  GetDetail getDetail = new GetDetail();
			  getDetail.setAcceptor(acceptor);
			  getDetail.setProvider(provider);
			  getDetail.setCailiao_bh(cailiao_bh);
			  getDetail.setCailiao_mc(cailiao_mc);
			  getDetail.setMaterial_num(material_num);
			  getDetail.setUnit(unit);
			  getDetail.setMaterial_batch_no("——");
			  getDetails.add(getDetail);
			  lingliao_dan.put("getDetails", JSONArray.fromObject(getDetails));
			  jsonObject2.put("type", "get_or_back");
			  JSONArray jsonArray2 = new JSONArray();
			  jsonArray2.add(lingliao_dan);
			  jsonObject2.put("get_or_back", jsonArray2);
			  getMaterialmessToSocket(jsonObject2.toString(), cangku);
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
	@RequestMapping("/findMaterialBatch_no")//查询材料批次号
	public void findMaterialBatch_no(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		batch_no= request.getParameter("batch_no");
		if(batch_no!=null&&!batch_no.isEmpty()){
	    list2=materialService.findMaterialBatch_no(batch_no);
	    System.out.println("材料名称:"+list2.get(0).getCailiao_mc());
		JSONObject json=new JSONObject();	
        JSONArray jsonCheck = JSONArray.fromObject(list2);
        json.put("MaterialBatch_no", jsonCheck);
		response.setContentType("test/html;charset=utf-8");
		PrintWriter Writer = response.getWriter();
		Writer.write(json.toString());
		Writer.flush();
		Writer.close();
		}
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
	
	@RequestMapping("/findGetAndBack")//
	   public void findNoRecordChengpinIn(HttpServletRequest request,HttpServletResponse response)throws Exception{
		   JSONObject jsonObject = new JSONObject();
		   try{
			   List<GetMaterial> productRecords = materialService.findNoRecordGetMaterial();
			   List<GetDetailsVo> getDetailsVos = materialService.findNoRecordReturnMaterial();
			   List<GetAndBackCailiao> getAndBackCailiaos = new ArrayList<>();
			   if(productRecords.size()!=0 || getDetailsVos.size()!=0){
				   for(int i=0;i<productRecords.size();i++){
					   GetMaterial getMaterial = productRecords.get(i);
					   GetAndBackCailiao getAndBackCailiao = new GetAndBackCailiao();
					   getAndBackCailiao.setGet_material_id(getMaterial.getGet_material_id());
					   getAndBackCailiao.setDate(getMaterial.getGetDetails().get(0).getGet_date());
					   getAndBackCailiao.setBatch_no(getMaterial.getBatch_no());
					   getAndBackCailiao.setShop_name(getMaterial.getShop_name());
					   getAndBackCailiao.setMaterial_no(getMaterial.getMaterial_no());
					   getAndBackCailiao.setRemark(getMaterial.getGetDetails().get(0).getRemark());
					   getAndBackCailiao.setAcceptor(getMaterial.getGetDetails().get(0).getAcceptor());
					   getAndBackCailiao.setProvider(getMaterial.getGetDetails().get(0).getProvider());
					   getAndBackCailiao.setIs_return(0);
					   getAndBackCailiao.setGetDetails(getMaterial.getGetDetails());
					   getAndBackCailiaos.add(getAndBackCailiao);
				   }
				   for(int i=0;i<getDetailsVos.size();i++){
					   GetDetailsVo getDetailsVo = getDetailsVos.get(i);
					   GetAndBackCailiao getAndBackCailiao = new GetAndBackCailiao();
					   getAndBackCailiao.setDate(getDetailsVo.getGet_date());
					   getAndBackCailiao.setBatch_no(getDetailsVo.getBatch_no());
					   getAndBackCailiao.setShop_name(getDetailsVo.getShop_name());
					   getAndBackCailiao.setMaterial_no(getDetailsVo.getMaterial_no());
					   getAndBackCailiao.setRemark(getDetailsVo.getRemark());
					   getAndBackCailiao.setAcceptor(getDetailsVo.getAcceptor());
					   getAndBackCailiao.setProvider(getDetailsVo.getProvider());
					   getAndBackCailiao.setIs_return(1);
					   GetDetail getDetail = new GetDetail();
					   List<GetDetail> getDetails = new ArrayList<>();
					   getDetail.setDetail_id(getDetailsVo.getDetail_id());
					   getDetail.setCailiao_bh(getDetailsVo.getCailiao_bh());
					   getDetail.setCailiao_mc(getDetailsVo.getCailiao_mc());
					   getDetail.setMaterial_batch_no(getDetailsVo.getMaterial_batch_no());
					   getDetail.setMaterial_num(getDetailsVo.getMaterial_num());
					   getDetail.setUnit(getDetailsVo.getUnit());
					   getDetails.add(getDetail);
					   getAndBackCailiao.setGetDetails(getDetails);
					   getAndBackCailiaos.add(getAndBackCailiao);
				   } 
				   jsonObject.put("size",getAndBackCailiaos.size());
				   jsonObject.put("details", JSONArray.fromObject(getAndBackCailiaos));
			   }else{
				   jsonObject.put("size",0);
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
	   
	   @RequestMapping("/updateNoRecordGetMaterial")//
	   public void updateNoRecordGetMaterial(HttpServletRequest request,HttpServletResponse response)throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   JSONObject jsonObject = new JSONObject(); 
		   int record_id =Integer.parseInt(request.getParameter("get_material_no"));
		   try{
			   materialService.updateNoRecordGetMaterial(record_id);
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
	   
	   @RequestMapping("/updateNoRecordReturnMaterial")//
	   public void updateNoRecordReturnMaterial(HttpServletRequest request,HttpServletResponse response)throws Exception{
		   request.setCharacterEncoding("UTF-8");
		   JSONObject jsonObject = new JSONObject(); 
		   int record_id =Integer.parseInt(request.getParameter("detail_id"));
		   try{
			   materialService.updateNoRecordReturnMaterial(record_id);
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
