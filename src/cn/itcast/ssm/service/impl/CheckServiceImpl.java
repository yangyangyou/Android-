package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.CheckMapper;
import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.CheckRecord;
import cn.itcast.ssm.po.DailyCheck;
import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.ShopPlan;
import cn.itcast.ssm.service.CheckService;

public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckMapper checkMapper;
	
	@Override
	public List<DailyCheck> findAssetList() throws Exception {
		return checkMapper.findAssetList();
	}

	@Override
	public List<CheckRecord> selectCheckList(CheckRecord checkRecord) throws Exception {
		return checkMapper.selectCheckList(checkRecord);
	}

	@Override
	public void insertCheckList(CheckRecord checkRecord) throws Exception {
		checkMapper.insertCheckList(checkRecord);
	}

	@Override
	public void updateCheckList_1(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_1(checkRecord);	
	}
	
	@Override
	public void updateCheckList_3(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_3(checkRecord);
	}

	@Override
	public void updateCheckList_4(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_4(checkRecord);
	}

	@Override
	public void updateCheckList_5(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_5(checkRecord);
	}

	@Override
	public void updateCheckList_6(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_6(checkRecord);
	}

	@Override
	public void updateCheckList_7(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_7(checkRecord);
	}

	@Override
	public void updateCheckList_8(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_8(checkRecord);
	}

	@Override
	public void updateCheckList_2(CheckRecord checkRecord) throws Exception {
		checkMapper.updateCheckList_2(checkRecord);
	}

	@Override
	public void insertDailyCheck(DailyCheck_2 dailyCheck_2) throws Exception {
		// TODO Auto-generated method stub
		checkMapper.insertDailyCheck(dailyCheck_2);
	}

	@Override
	public List<DailyCheck_2> findDailyCheck(DailyCheck_2 dailyCheck_2)
			throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findDailyCheck(dailyCheck_2);
	}

	@Override
	public List<ShopPlan> findPlanNo(DailyCheck_2 dailyCheck_2)
			throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findPlanNo(dailyCheck_2);
	}

	@Override
	public List<DailyCheck_2> findCheckId(String date, String operator)
			throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findCheckId(date, operator);
	}

	@Override
	public List<CheckRecord> findCheckRecord(Integer check_id) throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findCheckRecord(check_id);
	}

	@Override
	public List<Asset> findAsset(String shop_name) throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findAsset(shop_name);
	}

	@Override
	public List<DailyCheck_2> findCheck_id(String asset_no) throws Exception {
		// TODO Auto-generated method stub
		return checkMapper.findCheck_id(asset_no);
	}
}
