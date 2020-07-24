package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.ProcessQueryMapper;
import cn.itcast.ssm.po.ShopPlanVo;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.service.PlanService;

public class PlanServiceImpl implements PlanService {
    @Autowired
    private ProcessQueryMapper processQueryMapper;
    
	@Override
	public int findPlanId(ShopPlanVo shopPlanVo) throws Exception {
		return processQueryMapper.findPlanId(shopPlanVo);
	}

	@Override
	public List<Task> findProcess(int id) throws Exception {
		return processQueryMapper.findProcess(id);
	}

}
