package cn.itcast.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.Record;

import net.sf.json.JSONObject;

public interface RecordService {
	public JSONObject getRecord(List<Record> record) throws Exception;
	public JSONObject getProcess(String material_no, String batch_no, String shop_name) throws Exception;
	public JSONObject updateRecord(List<Record> record)throws Exception;
	public JSONObject returnRecord(String material_no, String batch_no, String shop_name, String process) throws Exception;
	//public JSONObject getOperator(String material_no, String batch_no, String shop_name, String process_name) throws Exception;
	public String findWhatRfid(String rfid) throws Exception;	
	public JSONObject getProcessByBatch(String batch_no, String shop_name) throws Exception;
}
