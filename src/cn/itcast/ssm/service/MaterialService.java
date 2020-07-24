package cn.itcast.ssm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import net.sf.json.JSONObject;

import cn.itcast.ssm.po.GetDetail;
import cn.itcast.ssm.po.GetDetailsVo;
import cn.itcast.ssm.po.GetMaterial;
import cn.itcast.ssm.po.Material;
import cn.itcast.ssm.po.MaterialBatchNo;

public interface MaterialService {
	public String findCailiao_bh(@Param(value="batch_no")String batch_no,@Param(value="material_no")String material_no,@Param(value="cailiao_mc")String cailiao_mc);
	public List<MaterialBatchNo>findMaterialBatch_no(String batch_no);
	public List<GetDetail> findMaterial(String material_no,String batch_no,String shop_name);
	public void updateGet_detail(GetDetail getDetail);
	public void insertGet_detail(Integer get_material_id,String cailiao_mc,
			  String cailiao_bh,String material_num, String provider,String acceptor, String get_date,String remark,String unit);
    public List<Integer> findGet_material_id(String material_no,String batch_no,String shop_name);
    public JSONObject updateMaterial(HttpServletRequest request) throws Exception;
    public List<GetMaterial> findNoRecordGetMaterial() throws Exception;
	public void updateNoRecordGetMaterial(int get_material_id) throws Exception;
	public List<GetDetailsVo> findNoRecordReturnMaterial() throws Exception;
	public void updateNoRecordReturnMaterial(int detail_id) throws Exception;
} 
