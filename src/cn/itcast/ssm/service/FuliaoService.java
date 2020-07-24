package cn.itcast.ssm.service;

import java.util.List;

import net.sf.json.JSONObject;

import cn.itcast.ssm.po.GetSecMaterials;
import cn.itcast.ssm.po.SecondaryMaterials;

public interface FuliaoService {
	public List<SecondaryMaterials> findFuliaoDetails(String name) throws Exception;
	public JSONObject insertFuliao(String jsonStr) throws Exception;
	public JSONObject findNoGetFuliao(String jsonStr) throws Exception;
	public JSONObject updateFuliao(String jsonStr) throws Exception;
	public JSONObject insertBackFuliao(String jsonStr) throws Exception;
	public JSONObject findGauge(String jsonStr) throws Exception;
	public List<GetSecMaterials> findNoPushSecMaterial() throws Exception;
	public void updateNoPushSecMaterial(int get_sec_materials_id) throws Exception;
	public List<GetSecMaterials> findNoGetSecMaterial() throws Exception;
	public void updateNoGetSecMaterial(int get_sec_material_id) throws Exception;
	public List<GetSecMaterials> findNoRecordGetSec() throws Exception;
	public void updateNoRecordGetSec(int get_sec_material_id) throws Exception;
}
