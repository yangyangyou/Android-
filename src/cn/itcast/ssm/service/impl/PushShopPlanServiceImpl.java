package cn.itcast.ssm.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.PushShopPlanMapper;
import org.apache.ibatis.io.Resources;

import cn.itcast.ssm.po.DepartmentVo;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.service.PushShopPlanService;

public class PushShopPlanServiceImpl implements PushShopPlanService {
	
/*	private SqlSessionFactory SqlSessionFactory;
*/	
	@Autowired
	private PushShopPlanMapper pushShopPlanMapper;
	
	@Override
	public List<Integer> findNewestShopPlan() throws Exception {
		// TODO Auto-generated method stub
		/*InputStream inputStream = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
		SqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = SqlSessionFactory.openSession();
		pushShopPlanMapper = sqlSession.getMapper(PushShopPlanMapper.class);*/
		return pushShopPlanMapper.findNewestShopPlan();
	}

	@Override
	public void updateNewestShopPlan(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		pushShopPlanMapper.updateNewestShopPlan(plan_id);
	}

	@Override
	public ShopPlan findShopPlanAndProcess(int plan_id) throws Exception {
		// TODO Auto-generated method stub
		return pushShopPlanMapper.findShopPlanAndProcess(plan_id);
	}

	@Override
	public List<String> findGongDuanZhang(DepartmentVo departmentVo)
			throws Exception {
		// TODO Auto-generated method stub
		return pushShopPlanMapper.findGongDuanZhang(departmentVo);
	}

	@Override
	public List<String> findFahuoAndBafahuoyoqian() throws Exception {
		// TODO Auto-generated method stub
		return pushShopPlanMapper.findFahuoAndBafahuoyoqian();
	}

	@Override
	public List<String> findCangKuGM() throws Exception {
		// TODO Auto-generated method stub
		return pushShopPlanMapper.findCangKuGM();
	}

}
