package cn.itcast.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.MaterialNo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;


public interface TransitionMapper {
	
	public List<ProductionPlan> findProduction_plan();//查询生产计划表
	
	public List<ShopDelivery> findShop_name(@Param(value="planNo")String planNo, @Param(value="clientMaterialNo")String clientMaterialNo,
			                   @Param(value="materialNo")String materialNo);

    public String findPosition(@Param(value="username")String username);//查找username的职位
	
	public Float  findWeight(@Param(value="materialNo") String materialNo);//在BOM表查找产品净重
	
    public String findPerson_name(@Param(value="number")String number);//根据编号查找姓名
    
    public String findQualifed_num(String shop_name,String batchNo,int priority);//查询每个工段交接时合格品数量
	
	public String findPlan_num(String shop_name,String batchNo,int priority);//没有返工，查找剩余生产总量
	
	public String findPlan_num4(String shop_name,String batchNo,int priority);
	
	public String findPlan_num2(String shop_name,String batchNo);//首次查找计划生产总量
	
	public String findPlan_num3(String shop_name,String batchNo,int priority);//工段长查询剩余生产总量
	
	public List<MaterialNo> findClient_material_no(@Param(value="batchNo")String batchNo);//查询计划单号，客户物料号，物料号
	
	public List<MaterialNo> findClient_material_no2(String batchNo);
	
	//public String findPhynum(@Param(value="rfid")String rfid);//查找标签所对应的实际标号
	
	public String findDepartment(@Param(value="username")String username);//查找username所在的部门
	
	public void updateDelivery_num(String planNo,String materialNo,String batchNo,String shop1,String tranDate,int priority);//更新交付数量
	
	public void updateDelivery_num2(String planNo,String materialNo,String batchNo,String shop1,String tranDate);
	
	public void updateDelivery_num3(String planNo,String materialNo,String batchNo,String shop1,String tranDate);
	
	public void insertTransition(String planNo, String clientMaterialNo,String materialNo,String batchNo,String shop1,String shop2,
			String qualifiedNum,String unqualifiedNum,String tranDate,String provider,String acceptor,int priority);
	
	public void insertOutTransition(String planNo,String clientMaterialNo,String materialNo, String batchNo,String reshop1,String reshop2,
			  String tranDate,String provider, String acceptor,String qualifiedNum,int type);//外协提交
	
}
