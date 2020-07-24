package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.ChecklistMapper;
import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.Checklist;
import cn.itcast.ssm.po.Content;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.RFIdLabel;
import cn.itcast.ssm.service.ChecklistService;

public class ChecklistServiceImpl implements ChecklistService {
	@Autowired
	
	public ChecklistMapper checklistMapper;
	
	@Override
	public List<Person> findPerson() {
		
		return checklistMapper.findPerson();
	}
	
	@Override
	public List<Checklist> findChecklist(String client_material_no,
			String batch_no,String shop_name) {
		
		return checklistMapper.findChecklist(client_material_no,batch_no,shop_name);
	}

	@Override
	public List<Content> findContent( String asset_name,String asset_no) {
	
		return checklistMapper.findContent(asset_name,asset_no);
	}

	@Override
	public List<Asset> findCommon(String asset_name) {
		
		return checklistMapper.findCommon(asset_name);
	}

	@Override
	public String findWhatRfid(String rfid) {
		return checklistMapper.findWhatRfid(rfid);
	}

	@Override
	public RFIdLabel findRfIdIsHere(String rfid) {
		// TODO Auto-generated method stub
		return checklistMapper.findRfIdIsHere(rfid);
	}

	@Override
	public void insertRfIdLabel(RFIdLabel rfIdLabel) {
		checklistMapper.insertRfIdLabel(rfIdLabel);
		
	}

	@Override
	public void updateRfIdLabel(RFIdLabel rfIdLabel) {
		// TODO Auto-generated method stub
		checklistMapper.updateRfIdLabel(rfIdLabel);
	}

	@Override
	public List<Person> findUserAndPassword(Person person) {
		// TODO Auto-generated method stub
		return checklistMapper.findUserAndPassword(person);
	}

	@Override
	public List<Person> findUser(String number) {
		// TODO Auto-generated method stub
		return checklistMapper.findUser(number);
	}

	@Override
	public List<String> findCheckContent_asset() {
		 
		return checklistMapper.findCheckContent_asset();
	}


}
