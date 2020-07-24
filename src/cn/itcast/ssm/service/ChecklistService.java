package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.Asset;
import cn.itcast.ssm.po.Checklist;
import cn.itcast.ssm.po.Content;
import cn.itcast.ssm.po.Person;
import cn.itcast.ssm.po.RFIdLabel;

public interface ChecklistService {
	public List<Person> findPerson();
	List<Checklist> findChecklist(String client_material_no,String batch_no,String shop_name);
	List<Asset>findCommon(String asset_name);
    List<String> findCheckContent_asset();
	List<Content> findContent(String asset_name,String asset_no);
	public String findWhatRfid(String rfid);
	public RFIdLabel findRfIdIsHere(String rfid);
	public void insertRfIdLabel(RFIdLabel rfIdLabel);
	public void updateRfIdLabel(RFIdLabel rfIdLabel);
	public List<Person> findUserAndPassword(Person person);
	public List<Person> findUser(String number);
}
