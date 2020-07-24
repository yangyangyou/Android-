package cn.itcast.ssm.service;

import java.util.List;

import net.sf.json.JSONObject;
import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.Message;
public interface MessService {
	public List<DailyCheck_2> getdailyCheck( int is_mold,String shop_name);
	public void updateState(String person,String tranDate,String batch_no,String asset_no);
	public JSONObject receiveMess(List<Message> mess) throws Exception;
	public JSONObject getMess(int position) throws Exception;
	public List<String> findMujuWeiXiu() throws Exception;
	public List<String> findShebeiWeiXiu() throws Exception;
}
