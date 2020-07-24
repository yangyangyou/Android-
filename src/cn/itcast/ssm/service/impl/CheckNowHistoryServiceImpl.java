package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.CheckNowHistoryMapper;
import cn.itcast.ssm.po.Check;
import cn.itcast.ssm.po.CheckRecord;
import cn.itcast.ssm.service.CheckNowHistoryService;

public class CheckNowHistoryServiceImpl implements CheckNowHistoryService {

	@Autowired
	private CheckNowHistoryMapper checkNowHistoryMapper;
	@Override
	public List<String> findGongxu(Check check) throws Exception {
		return checkNowHistoryMapper.findGongxu(check);
	}

	@Override
	public List<String> findSheBei(Check check) throws Exception {
		// TODO Auto-generated method stub
		return checkNowHistoryMapper.findSheBei(check);
	}

	@Override
	public List<String> findWorker(Check check) throws Exception {
		// TODO Auto-generated method stub
		return checkNowHistoryMapper.findWorker(check);
	}

	@Override
	public List<String> findCheck(Check check) throws Exception {
		// TODO Auto-generated method stub
		return checkNowHistoryMapper.findCheck(check);
	}

	@Override
	public List<CheckRecord> findCheckMuch(Check check) throws Exception {
		// TODO Auto-generated method stub
		return checkNowHistoryMapper.findCheckMuch(check);
	}

	@Override
	public CheckRecord findCheckOne(int check_id) throws Exception {
		// TODO Auto-generated method stub
		return checkNowHistoryMapper.findCheckOne(check_id);
	}

}
