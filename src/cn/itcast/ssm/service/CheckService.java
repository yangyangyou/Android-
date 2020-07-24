package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.CheckRecord;
import cn.itcast.ssm.po.DailyCheck;
import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.ShopPlan;

public interface CheckService {
	   public List<DailyCheck> findAssetList() throws Exception;
	   public void updateCheckList_1(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_2(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_3(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_4(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_5(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_6(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_7(CheckRecord checkRecord) throws Exception;
	   public void updateCheckList_8(CheckRecord checkRecord) throws Exception;
	   public List<CheckRecord> selectCheckList(CheckRecord checkRecord) throws Exception;
	   public void insertCheckList(CheckRecord checkRecord) throws Exception;
	   public List<DailyCheck_2> findDailyCheck(DailyCheck_2 dailyCheck_2) throws Exception;
	   public void insertDailyCheck(DailyCheck_2 dailyCheck_2) throws Exception;
	   public List<ShopPlan> findPlanNo(DailyCheck_2 dailyCheck_2) throws Exception;
	   public List<DailyCheck_2> findCheckId(String date,String operator) throws Exception;
	   
	   public List<CheckRecord> findCheckRecord(Integer checkId) throws Exception;
	   public List<Asset> findAsset(String shop_name) throws Exception;
	   public List<DailyCheck_2> findCheck_id(String asset_no)throws Exception;
}
