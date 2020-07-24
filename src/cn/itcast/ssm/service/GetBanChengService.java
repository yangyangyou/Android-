package cn.itcast.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.Middleproduct;
import cn.itcast.ssm.po.ProductRecord;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Storehouse;

public interface GetBanChengService {
	
	public List<Middleproduct> findMiddleProduct(@Param(value="material_no")String material_no) throws Exception;
	
	public void updateAddNum(@Param(value="product_num")String product_num,@Param(value="material_no")String material_no) throws Exception;
	  
	public void updateSubNum(@Param(value="product_num")String product_num,@Param(value="material_no")String material_no) throws Exception;
	
	public String findProductNum(@Param(value="batch_no") String batch_no,@Param(value="plan_no") String plan_no )throws Exception;
	
	public List<ShopPlan> findFromShopPlan(String batch_no) throws Exception;
	
	public List<ShopPlan> findFromShopPlan2(String batch_no) throws Exception;
	
	public void insertProductRecord(ProductRecord productRecord) throws Exception;
	
	public List<Storehouse>findStorehouse(String batch_no) throws Exception;
	
	public void insertStorehouse(Storehouse storehouse) throws Exception;
	
	public void updatematerialNum(String product_num,String batch_no) throws Exception;
	  
	public void updatematerialNum2(String product_num,String batch_no) throws Exception;
	
	public String findPlanNum(String batch_no)throws Exception;
	  
	public String findQualifed_num(String shop_name,String batch_no,int priority)throws Exception;
	  
	public String findUnqualifed_num(@Param(value="batch_no") String batch_no)throws Exception;
	
	public void updateBanAbnormal(String batch_no,int is_normal)throws Exception;
	
	public void updateChenAbnormal(String batch_no,int is_normal)throws Exception;
	
	public void updateAllNumOneBatch(String plan_no,String client_material_no,String material_no,String batch_no,Integer all_num) throws Exception;
	
	public List<ProductRecord> findNoRecordChengpinIn() throws Exception;
	  
	public void updateChengpinInRecord(int record_id) throws Exception;
}
