package cn.itcast.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.ssm.po.Cipin;
import cn.itcast.ssm.po.Task;
import cn.itcast.ssm.po.WorkCard;
import cn.itcast.ssm.po.WorkCardTrack;

public interface RecordMapper {
	public void insertRecord(int card_id, String shop_name, String process_name,
			String operator, String return_num, String gf_num, String cipin_num, 
			String date, String reason) throws Exception;
	
	public List<Task> findAsset(String material_no, String batch_no, String shop_name, String process_name) throws Exception;
	//查询回显操作工生产合格品等数据
	public WorkCard findRecord(String material_no, String batch_no, String shop_name, String process, String operator) throws Exception;
	//查询回显次品种类及数量
	public List<Cipin> findCipin(String shop_name, String process, String operator)throws Exception;
	//更新workcard中记录
	public void updateRecord(WorkCardTrack workCardTrack)throws Exception;
	//更新次品记录
	public void updateCipin(String shop_name, String process, String operator, String cipin_species,String cipin_num)throws Exception;
	//查询回显数据中该工序员工姓名（个数）
	public String findName(String material_no, String batch_no, String shop_name,String process)throws Exception;
	
	public void insertTrack(WorkCard workCard) throws Exception;
	
	public void insertCipin(@Param(value = "track_id")int track_id, @Param(value = "cipin_type")String cipin_type, @Param(value = "cipin_species")String cipin_species, 
			@Param(value = "cipin_num")String cipin_num)throws Exception;
	
	public List<Task> findProcess(String material_no, String batch_no, String shop_name) throws Exception;
	
	public List<Task> findProcessByBatch(String batch_no, String shop_name) throws Exception;
	
	public List<Task> findOperator(String material_no, String batch_no, String shop_name) throws Exception;
	
	public String findWhatRfid(String rfid) throws Exception;	
	
	public String findPrice(@Param(value = "material_no")String material_no, @Param(value = "shop_name")String shop_name, @Param(value = "process")String process) throws Exception;
	
	public int getCardID(String material_no,String batch_no) throws Exception;
}
