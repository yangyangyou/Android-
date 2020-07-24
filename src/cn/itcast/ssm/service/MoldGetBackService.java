package cn.itcast.ssm.service;

import net.sf.json.JSONObject;
import cn.itcast.ssm.po.MoldRecord;

public interface MoldGetBackService {
	public void GetBackMold(MoldRecord moldRecord) throws Exception;
	public String findMoldName(String mold_no) throws Exception;
	public JSONObject findBindingMold(JSONObject requestData) throws Exception;
	public JSONObject updateBindingMold(JSONObject requestData) throws Exception;
	public JSONObject findMold(JSONObject requestData) throws Exception;
	public JSONObject deleteMold(JSONObject requestData) throws Exception;
}
