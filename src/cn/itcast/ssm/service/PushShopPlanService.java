package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.DepartmentVo;
import cn.itcast.ssm.po.ShopPlan;

public interface PushShopPlanService {	
	public List<Integer> findNewestShopPlan() throws Exception;
	public void updateNewestShopPlan(int plan_id) throws Exception;
	public ShopPlan findShopPlanAndProcess(int plan_id) throws Exception;
	public List<String> findGongDuanZhang(DepartmentVo departmentVo) throws Exception;
	public List<String> findFahuoAndBafahuoyoqian() throws Exception;
	public List<String> findCangKuGM() throws  Exception;
}
