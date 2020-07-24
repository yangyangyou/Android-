package cn.itcast.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.Middleproduct;
import cn.itcast.ssm.po.ProductRecord;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Storehouse;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.po.WorkCardNew;

public interface GetBanChengMapper {
	
  public List<Middleproduct> findMiddleProduct(@Param(value="material_no")String material_no) throws Exception;
	
  public void updateAddNum(@Param(value="product_num")String product_num,@Param(value="material_no")String material_no) throws Exception;
  
  public void updateSubNum(@Param(value="product_num")String product_num,@Param(value="material_no")String material_no) throws Exception;
  
  public String findProductNum(@Param(value="batch_no") String batch_no,@Param(value="plan_no") String plan_no )throws Exception;
	
  public List<ShopPlan> findFromShopPlan(@Param(value="batch_no")String batch_no) throws Exception;
  
  public List<ShopPlan> findFromShopPlan2(@Param(value="batch_no")String batch_no) throws Exception;
  
  public void insertProductRecord( ProductRecord productRecord) throws Exception;
  
  public List<Storehouse>findStorehouse(@Param(value="batch_no")String batch_no) throws Exception;
  
  public void insertStorehouse(Storehouse storehouse) throws Exception;
  
  public void updatematerialNum(@Param(value="product_num")String product_num,@Param(value="batch_no")String batch_no) throws Exception;
  
  public void updatematerialNum2(@Param(value="product_num")String product_num,@Param(value="batch_no")String batch_no) throws Exception;
  
  public String findPlanNum(String batch_no)throws Exception;
  
  public String findQualifed_num(String shop_name,String batch_no,int priority)throws Exception;
  
  public String findUnqualifed_num(@Param(value="batch_no") String batch_no)throws Exception;
  
  public void updateBanAbnormal(@Param(value="batch_no")String batch_no,@Param(value="is_normal") int is_normal)throws Exception;
  
  public void updateChenAbnormal(@Param(value="batch_no")String batch_no,@Param(value="is_normal") int is_normal)throws Exception;
  
  public List<WorkCardNew> findOneBatchAllWork(String plan_no,String batch_no,String client_material_no,String material_no) throws Exception;
  
  public void updateOneBatchAllNum(List<WorkCardNew> workCardNews) throws Exception;
  
  public List<ProductRecord> findNoRecordChengpinIn() throws Exception;
  
  public void updateChengpinInRecord(int record_id) throws Exception;
}
