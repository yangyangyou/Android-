package cn.itcast.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.FinishNum;
import cn.itcast.ssm.po.HegelvNum;
import cn.itcast.ssm.po.PlanNum;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ProductionPlan2;
import cn.itcast.ssm.po.ProductionPlanVo;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.ShopTransition;

public interface TvFindService {
	public List<ProductionPlan> findAllOrder(String start_date,String end_date) throws Exception;
	public List<ProductionPlan> findNoFinishOrder(String start_date,String end_date) throws Exception;
	public List<ProductionPlan> findProductionPlan() throws Exception;   
	public List<ProductionPlan2> findProductionPlan2() throws Exception;
	public List<ProductionPlan> findProductionPlan3(String shop_name,String shop_name_2)throws Exception;
	public FinishNum findFinishNum(String start_date,String end_date) throws Exception;
	public List<ShopPlan> findOneShopBatch(String shop_name,String start_date,String end_date) throws Exception;
	public List<ShopTransition> findTransitionNum(String batch_no,String shop_name) throws Exception;
	public List<ProductionPlan> findOrderHegelv(String start_date,String end_date) throws Exception;
	public PlanNum findCipinNum(String plan_no) throws Exception;
	public HegelvNum findHegelvNum(String start_date,String end_date) throws Exception;
}


