package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.po.GetSecDetail;
import cn.itcast.ssm.po.GetSecDetailVo;
import cn.itcast.ssm.po.GetSecMaterials;
import cn.itcast.ssm.po.HeightGauge;
import cn.itcast.ssm.po.SecondaryMaterials;
import cn.itcast.ssm.po.SpecialGauge;

public interface FuliaoMapper {
	public List<SecondaryMaterials> findFuliaoDetails(String name) throws Exception;
	public void insertFuliao(GetSecMaterials getSecMaterials) throws Exception;
	public void insertFuliaoDetail(List<GetSecDetail> getSecDetails) throws Exception;
	public List<GetSecMaterials> findNoGetFuliao(String shop_name,String applyer) throws Exception;
	public void updateGetSecMaterial(GetSecMaterials getSecMaterials) throws Exception;
	public void updateGetSecDetail(List<GetSecDetail> getSecDetails) throws Exception;
	public void insertBackFuliao(List<GetSecDetail> getSecDetails) throws Exception;
	public List<SpecialGauge> findSpecialGauge(String inspection_production,String gauge_name) throws Exception;
	public List<HeightGauge> findHeightGauge(String product_specification) throws Exception;
	public List<GetSecMaterials> findNoPushSecMaterial() throws Exception;
	public void updateNoPushSecMaterial(int get_sec_material_id) throws Exception;
	public List<GetSecMaterials> findNoGetSecMaterial() throws Exception;
	public void updateNoGetSecMaterial(int get_sec_material_id) throws Exception;
	public List<GetSecMaterials> findNoRecordGetSec() throws Exception;
	public void updateNoRecordGetSec(int get_sec_material_id) throws Exception;
	public List<GetSecDetail> findNoRecordReturnSecMaterial() throws Exception;
	public void updateNoRecordReturnSec(int detail_id) throws Exception;
	public List<GetSecDetail> findGetSecDetailById(int get_material_id) throws Exception;
}
