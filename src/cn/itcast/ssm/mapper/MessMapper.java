package cn.itcast.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import cn.itcast.ssm.po.DailyCheck_2;
import cn.itcast.ssm.po.Message;

public interface MessMapper {
	public void updateState(String person,String tranDate,String batch_no,String asset_no);
	public List<DailyCheck_2>  getdailyCheck(@Param(value="is_mold") int is_mold);
	public List<DailyCheck_2> getMyDailyCheck(String shop_name);
	public List<Message> getMess() throws Exception;
	public void updateMess(String batch_no,String asset_no,String shop_name,String process_name,String operator,String send_person )throws Exception;
	public void updateAssetState(String batch_no,String asset_no,String shop_name,
			String process_name,String operator);
	public void updateMoldState(String batch_no,String asset_no,String shop_name,
            String process_name,String operator) throws Exception;
	public List<String> findMujuWeiXiu() throws Exception;
	public List<String> findShebeiWeiXiu() throws Exception;
}
