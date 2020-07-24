package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.TvFindMapper;
import cn.itcast.ssm.po.FinishNum;
import cn.itcast.ssm.po.HegelvNum;
import cn.itcast.ssm.po.PlanNum;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ProductionPlan2;
import cn.itcast.ssm.po.ProductionPlanVo;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.ShopTransition;
import cn.itcast.ssm.service.TvFindService;

public class TvFindServiceImpl implements TvFindService {
	@Autowired
	private TvFindMapper tvFindMapper;
	
	
	@Override
	public List<ProductionPlan> findProductionPlan() throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findProductionPlan();
	}

	@Override
	public List<ProductionPlan2> findProductionPlan2() throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findProductionPlan2();
	}

	@Override
	public List<ProductionPlan> findProductionPlan3(String shop_name,
			String shop_name_2) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findProductionPlan3(shop_name, shop_name_2);
	}
	
	@Override
	public List<ProductionPlan> findAllOrder(String start_date, String end_date)
			throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findAllOrder(start_date, end_date);
	}

	@Override
	public List<ProductionPlan> findNoFinishOrder(String start_date,
			String end_date) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findNoFinishOrder(start_date, end_date);
	}

	@Override
	public List<ShopPlan> findOneShopBatch(String shop_name, String start_date,
			String end_date) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findOneShopBatch(shop_name, start_date, end_date);
	}

	@Override
	public List<ShopTransition> findTransitionNum(String batch_no,
			String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findTransitionNum(batch_no, shop_name);
	}

	@Override
	public FinishNum findFinishNum(String start_date, String end_date)
			throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findFinishNum(start_date, end_date);
	}

	@Override
	public List<ProductionPlan> findOrderHegelv(String start_date,
			String end_date) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findOrderHegelv(start_date, end_date);
	}

	@Override
	public PlanNum findCipinNum(String plan_no) throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findCipinNum(plan_no);
	}

	@Override
	public HegelvNum findHegelvNum(String start_date, String end_date)
			throws Exception {
		// TODO Auto-generated method stub
		return tvFindMapper.findHegelvNum(start_date, end_date);
	}
	
}
