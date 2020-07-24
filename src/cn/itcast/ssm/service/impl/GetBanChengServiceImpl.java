package cn.itcast.ssm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sun.jna.platform.win32.WinDef.WPARAM;

import cn.itcast.ssm.mapper.GetBanChengMapper;
import cn.itcast.ssm.po.Middleproduct;
import cn.itcast.ssm.po.ProductRecord;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.po.Storehouse;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.po.WorkCardNew;
import cn.itcast.ssm.service.GetBanChengService;

public class GetBanChengServiceImpl implements GetBanChengService {
    
	@Autowired
    private GetBanChengMapper getBanChengMapper;
	
	
	@Override
	public List<ShopPlan> findFromShopPlan2(String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findFromShopPlan(batch_no);
	}

	@Override
	public List<ShopPlan> findFromShopPlan(String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findFromShopPlan(batch_no);
	}

	@Override
	public void insertProductRecord(ProductRecord productRecord) throws Exception {
	
		getBanChengMapper.insertProductRecord(productRecord);
	}

	@Override
	public List<Storehouse> findStorehouse(String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findStorehouse(batch_no);
	}

	@Override
	public void insertStorehouse(Storehouse storehouse) throws Exception {
		
		getBanChengMapper.insertStorehouse(storehouse);
	}

	@Override
	public void updatematerialNum(String product_num, String batch_no)
			throws Exception {
		// TODO Auto-generated method stub
		getBanChengMapper.updatematerialNum(product_num, batch_no);
		
	}

	@Override
	public void updatematerialNum2(String product_num, String batch_no)
			throws Exception {
		// TODO Auto-generated method stub
		getBanChengMapper.updatematerialNum2(product_num, batch_no);
	}

	@Override
	public String findPlanNum(String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findPlanNum(batch_no);
	}

	@Override
	public String findQualifed_num(String shop_name, String batch_no,
			int priority) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findQualifed_num(shop_name, batch_no, priority);
	}

	@Override
	public String findUnqualifed_num(String batch_no) throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findUnqualifed_num(batch_no);
	}

	@Override
	public void updateBanAbnormal(String batch_no, int is_normal)
			throws Exception {
		getBanChengMapper.updateBanAbnormal(batch_no, is_normal);
		
	}

	@Override
	public void updateChenAbnormal(String batch_no, int is_normal)
			throws Exception {
		getBanChengMapper.updateChenAbnormal(batch_no, is_normal);
		
	}
	
	@Override
	public void updateAllNumOneBatch(String plan_no, String client_material_no,
			String material_no, String batch_no, Integer all_num)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,Integer> one_shop_all_num = new HashMap<>(); //存放每一个工段总数的map
		Map<String,List<WorkCardNew>> one_shop_work_card = new HashMap<>(); //按照工段将workcard分开
		//最终的WorkCardList 用于修改全部数据
		List<WorkCardNew> workCardNews_all = new ArrayList<>();
		try{
			List<WorkCardNew> workCardNews = getBanChengMapper.findOneBatchAllWork(plan_no, batch_no, client_material_no, material_no);
			if(workCardNews.size()!=0){
				for(int i=0;i<workCardNews.size();i++){
					WorkCardNew workCardNew = workCardNews.get(i);
					if(one_shop_work_card.get(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name())==null){
						List<WorkCardNew> wList = new ArrayList<>();
						wList.add(workCardNew);
						one_shop_work_card.put(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name(), wList);
					}else{
						List<WorkCardNew> wList = one_shop_work_card.get(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name());
						wList.add(workCardNew);
						one_shop_work_card.put(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name(), wList);
					}
					if(one_shop_all_num.get(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name())==null){
						Integer num = Integer.valueOf(workCardNew.getHege_num());
						one_shop_all_num.put(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name(), num);
					}else{
						Integer num = Integer.valueOf(workCardNew.getHege_num())+one_shop_all_num.get(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name());
						one_shop_all_num.put(workCardNew.getShop_name()+"-"+workCardNew.getProcess_name(), num);
					}
				}
				for(Map.Entry<String, List<WorkCardNew>> entry:one_shop_work_card.entrySet()){
					Integer num = one_shop_all_num.get(entry.getKey()); //一个工序的总数
					Integer cha_num = num - all_num; //和最后入库数的差值
					List<WorkCardNew> news = entry.getValue();
					for(int i=0;i<news.size();i++){
						WorkCardNew wNew = news.get(i);
						Integer now_hege = Integer.valueOf(wNew.getHege_num());
						float bili = now_hege/(float)num;
						int end_hege = (int)(now_hege - (bili*cha_num));
						int end_total = end_hege + Integer.valueOf(wNew.getTotal_cipin_num());
						wNew.setHege_num(String.valueOf(end_hege));
						wNew.setTotal_num(String.valueOf(end_total));
						workCardNews_all.add(wNew);
					}
				}
				if(workCardNews_all.size() == workCardNews.size()){
					getBanChengMapper.updateOneBatchAllNum(workCardNews_all);
				}else{
					throw new Exception();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@Override
	public String findProductNum(String batch_no, String plan_no)
			throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findProductNum(batch_no, plan_no);
	}

	@Override
	public void updateAddNum(String product_num, String material_no)
			throws Exception {
		// TODO Auto-generated method stub
		getBanChengMapper.updateAddNum(product_num,material_no);
	}

	@Override
	public void updateSubNum(String product_num, String material_no)
			throws Exception {
		// TODO Auto-generated method stub
		getBanChengMapper.updateSubNum(product_num,material_no);
	}

	@Override
	public List<Middleproduct> findMiddleProduct(String material_no)
			throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findMiddleProduct(material_no);
	}

	@Override
	public List<ProductRecord> findNoRecordChengpinIn() throws Exception {
		// TODO Auto-generated method stub
		return getBanChengMapper.findNoRecordChengpinIn();
	}

	@Override
	public void updateChengpinInRecord(int record_id) throws Exception {
		// TODO Auto-generated method stub
		getBanChengMapper.updateChengpinInRecord(record_id);
	}
}
