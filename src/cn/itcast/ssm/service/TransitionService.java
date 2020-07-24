package cn.itcast.ssm.service;

import java.util.List;
import cn.itcast.ssm.po.MaterialNo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;

public interface TransitionService {
	
	public List<ProductionPlan> findProduction_plan();

	public List<ShopDelivery> findShop_name(String planNo, String clientMaterialNo,String materialNo);

    public String findPosition(String username);//查找员工职位
	
	public Float findWeight(String materialNo);
	
    public String findPerson_name(String number);
    
    public String findQualifed_num(String shop_name,String batchNo,int priority);
	
	public String findPlan_num(String shop_name,String batchNo,int priority);
	
	public String findPlan_num2(String shop_name,String batchNo);
	
	public String findPlan_num4(String shop_name,String batchNo,int priority);
	
	public String findPlan_num3(String shop_name,String batchNo,int priority);
	
	public List<MaterialNo> findClient_material_no(String batchNo);
	
	public List<MaterialNo> findClient_material_no2(String batchNo);
	
	//public String findPhynum(String rfid);
	
	public String findDepartment(String username);
	
	public void updateDelivery_num(String planNo,String materialNo,String batchNo,String shop1,String tranDate,int priority);
	
	public void updateDelivery_num2(String planNo,String materialNo,String batchNo,String shop1,String tranDate);
	
	public void updateDelivery_num3(String planNo,String materialNo,String batchNo,String shop1,String tranDate);
	
	public void insertTransition(String planNo,String clientMaterialNo,String materialNo,String batchNo,String shop1,String shop2, 
			    String qualifiedNum, String unqualifiedNum,String tranDate,String provider, String acceptor,int priority);
	
	public void insertOutTransition(String planNo,String clientMaterialNo, String materialNo,String batchNo,String reshop1,String reshop2,
			  String tranDate,String provider, String acceptor,String qualifiedNum,int type);
}
