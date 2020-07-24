package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.itcast.ssm.mapper.TransitionMapper;
import cn.itcast.ssm.po.MaterialNo;
import cn.itcast.ssm.po.ProductionPlan;
import cn.itcast.ssm.po.ShopDelivery;
import cn.itcast.ssm.service.TransitionService;


public class TransitionServiceImpl implements TransitionService {
	@Autowired
	public TransitionMapper transitionMapper;
	
	@Override
	public List<ProductionPlan> findProduction_plan() {
		// TODO Auto-generated method stub
		return transitionMapper.findProduction_plan();
	}
	
	@Override
	public String findPosition(String username) {
		// TODO Auto-generated method stub
		return transitionMapper.findPosition(username);
	}
	
	@Override
	public String findPerson_name(String number) {
	
		return transitionMapper.findPerson_name(number);
	}
	
	@Override
	public String findPlan_num(String shop_name,String batchNo,int priority) {
		  return transitionMapper.findPlan_num(shop_name,batchNo,priority);
	}

	@Override
	public String findPlan_num2(String shop_name, String batchNo) {
		
		return transitionMapper.findPlan_num2(shop_name,batchNo);
	}
	
	@Override
	public String findPlan_num3(String shop_name, String batchNo, int priority) {
		
		return transitionMapper.findPlan_num3(shop_name,batchNo,priority);
	}
	
	@Override
	public List<MaterialNo> findClient_material_no(String batchNo) {
	
		return transitionMapper.findClient_material_no(batchNo);
	}	
	
/*	@Override
	public String findPhynum(String rfid) {
		
		return transitionMapper.findPhynum(rfid);
	}*/
	
	@Override
	public String findDepartment(String username) { 
		return transitionMapper.findDepartment(username);
	}
	
	@Override
	public void updateDelivery_num(String planNo, String materialNo,
			String batchNo,String shop1,String tranDate,int priority) {
		     transitionMapper.updateDelivery_num(planNo, materialNo, batchNo, shop1,tranDate,priority);
	}
	
	@Override
	public void updateDelivery_num2(String planNo, String materialNo,
			String batchNo, String shop1,String tranDate) {
		transitionMapper.updateDelivery_num2(planNo, materialNo, batchNo,shop1,tranDate);
		
	}
	
	@Override
	public void updateDelivery_num3(String planNo, String materialNo,
			String batchNo, String shop1, String tranDate) {
		// TODO Auto-generated method stub
		transitionMapper.updateDelivery_num3(planNo, materialNo, batchNo,shop1,tranDate);
	}
	
	@Override
	public void insertTransition(String planNo, String clientMaterialNo,String materialNo,String batchNo,String shop1, String shop2,
			 String qualifiedNum, String unqualifiedNum,String tranDate,String provider,String acceptor,int priority) {
	    transitionMapper.insertTransition(planNo, clientMaterialNo,materialNo, batchNo, shop1,shop2,qualifiedNum, unqualifiedNum,tranDate, provider, acceptor,priority);
				
		
	}
	@Override
	public void insertOutTransition(String planNo, String clientMaterialNo,String materialNo,String batchNo,
			String reshop1, String reshop2,String tranDate, String provider, String acceptor,
			String qualifiedNum,int type) {
		transitionMapper.insertOutTransition(planNo, clientMaterialNo, materialNo, batchNo, reshop1,reshop2,tranDate, provider, acceptor, qualifiedNum,type);
	}

	@Override
	public Float findWeight(String materialNo) {
		
		return  transitionMapper.findWeight(materialNo);
	}

	@Override
	public List<ShopDelivery> findShop_name(String planNo, String clientMaterialNo,
			String materialNo) {
		
		return transitionMapper.findShop_name(planNo, clientMaterialNo, materialNo);
	}

	@Override
	public String findPlan_num4(String shop_name, String batchNo,int priority) {
		// TODO Auto-generated method stub
		return transitionMapper.findPlan_num4(shop_name, batchNo, priority);
	}

	@Override
	public List<MaterialNo> findClient_material_no2(String batchNo) {
		// TODO Auto-generated method stub
		return transitionMapper.findClient_material_no2(batchNo);
	}

	@Override
	public String findQualifed_num(String shop_name, String batchNo,
			int priority) {
		// TODO Auto-generated method stub
		return transitionMapper.findQualifed_num(shop_name, batchNo, priority);
	}

	

}
