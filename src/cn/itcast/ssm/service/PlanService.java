package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.ShopPlanVo;
import cn.itcast.ssm.po.Task;

public interface PlanService {
	 public int findPlanId(ShopPlanVo shopPlanVo) throws Exception;
	 public List<Task> findProcess(int id) throws Exception;
}
