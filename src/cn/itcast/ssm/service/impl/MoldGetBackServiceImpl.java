package cn.itcast.ssm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.itcast.ssm.mapper.MoldGetBackMapper;
import cn.itcast.ssm.po.MoldOfAsset;
import cn.itcast.ssm.po.MoldRecord;
import cn.itcast.ssm.po.MoldUpdateVo;
import cn.itcast.ssm.po.MoldVo;
import cn.itcast.ssm.po.Task_New;
import cn.itcast.ssm.service.MoldGetBackService;

public class MoldGetBackServiceImpl implements MoldGetBackService {

	@Autowired
	private MoldGetBackMapper moldGetBackMapper;
	@Override
	public void GetBackMold(MoldRecord moldRecord) throws Exception {
	    moldGetBackMapper.GetBackMold(moldRecord);
	}
	@Override
	public String findMoldName(String mold_no) throws Exception {
		return moldGetBackMapper.findMoldName(mold_no);
	}
	@Override
	public JSONObject findBindingMold(JSONObject requestData) throws Exception {
		// TODO Auto-generated method stub
		JSONObject reponseData = new JSONObject();
		try{
			//String batch_no = requestData.getString("batch_no");
		    String asset_no = requestData.getString("asset_no");
		    String mold_no = requestData.getString("mold_no");
		    MoldOfAsset moldOfAsset = moldGetBackMapper.findIsMold(asset_no);
		    if(moldOfAsset!=null){
		    	if(moldOfAsset.getIs_mold()==0){
		    		moldGetBackMapper.updateAssetMold(mold_no, asset_no);
		    		reponseData.put("is_ok", "1");
			    	return reponseData;
		    	}else{
		    		reponseData.put("is_ok", "0");
		    		reponseData.put("reason", 1);
		    		reponseData.put("mold", moldOfAsset.getMold());
		    		return reponseData;
		    	}
		    }else{
		    	MoldOfAsset mold = new MoldOfAsset();
		    	mold.setAsset_no(asset_no);
		    	mold.setMold(mold_no);
		    	mold.setIs_mold(1);
		    	moldGetBackMapper.insertAssetMold(mold);
		    	reponseData.put("is_ok", "1");
		    	return reponseData;
		    }
		    /*List<Integer> plan_id = moldGetBackMapper.findPlanId(batch_no);
		    if(plan_id.size()!=0){
		    	MoldVo moldVo = new MoldVo();
		    	moldVo.setAsset_no(asset_no);
		    	moldVo.setPlan_id(plan_id);
		    	List<Task_New> task_News = moldGetBackMapper.findBindingMold(moldVo);
		    	if (task_News.size()!=0) {
					List<String> molds = new ArrayList<>();
					List<Integer> task_ids = new ArrayList<>();
					for(int i=0;i<task_News.size();i++){
						molds.add(task_News.get(i).getMold());
						task_ids.add(task_News.get(i).getTask_id());
					}
					
					//去重
					HashSet<String> set = new HashSet<String>(molds.size());
					List<String> result = new ArrayList<>();
					for(String s : molds){
						if(set.add(s)){
							result.add(s);
						}
					}
					molds.clear();
					molds.addAll(result);
					
					String mold = molds.get(0);
					if(mold==null && mold.equals("")){
						MoldUpdateVo moldUpdateVo = new MoldUpdateVo();
						moldUpdateVo.setMold(mold_no);
						moldUpdateVo.setTask_id(task_ids);
						moldGetBackMapper.updateMold(moldUpdateVo);
						reponseData.put("is_ok", "1");
						return reponseData;
					}else{
						reponseData.put("is_ok", "0");
						reponseData.put("reason",3);
						reponseData.put("mold",mold);//设备已有模具
						return reponseData;
					}
				}else{
					reponseData.put("is_ok", "0");
			    	reponseData.put("reason",2); //找不到设备
			    	return reponseData;
				}
		    }else{
		    	reponseData.put("is_ok", "0");
		    	reponseData.put("reason",1); //找不到批次
		    	return reponseData;
		    }*/
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			reponseData.put("is_ok", "0");
	    	reponseData.put("reason",0); //报错
	    	return reponseData;
		}
	}
	@Override
	public JSONObject updateBindingMold(JSONObject requestData)
			throws Exception {
		JSONObject responseData = new JSONObject();
		try{
			String asset_no = requestData.getString("asset_no");
		    String mold_no = requestData.getString("mold_no");
		    moldGetBackMapper.updateAssetMold(mold_no, asset_no);
		    responseData.put("is_ok", "1");
	    	return responseData;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			responseData.put("is_ok", "0");
	    	return responseData;
		}
	}
	@Override
	public JSONObject findMold(JSONObject requestData)
			throws Exception {
		// TODO Auto-generated method stub
		JSONObject responseData = new JSONObject();
		try{
			String asset = requestData.getString("asset_no");
			MoldOfAsset moldOfAsset = moldGetBackMapper.findIsMold(asset);
			if(moldOfAsset==null){
				responseData.put("mold","");
		    	return responseData;
			}else{
				if(moldOfAsset.getIs_mold() == 1){
					responseData.put("mold",moldOfAsset.getMold());
					return responseData;
				}else{
					responseData.put("mold","");
			    	return responseData;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			responseData.put("mold","0");
	    	return responseData;
		}
	}
	@Override
	public JSONObject deleteMold(JSONObject requestData) throws Exception {
		// TODO Auto-generated method stub
		JSONObject responseData = new JSONObject();
		try{
			String asset = requestData.getString("asset_no");
			moldGetBackMapper.deleteAssetMold(asset);
			responseData.put("is_ok","1");
	    	return responseData;
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			responseData.put("is_ok","0");
	    	return responseData;
		}
	}
}
