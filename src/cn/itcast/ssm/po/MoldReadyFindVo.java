package cn.itcast.ssm.po;

import java.util.List;

public class MoldReadyFindVo {
	private Integer plan_id;
	private String material_no;
	private String batch_no;
	private String plan_no;
	private String shop_name;
	
	private List<MoldReady> molds;

	
	public Integer getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}

	public String getMaterial_no() {
		return material_no;
	}

	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getPlan_no() {
		return plan_no;
	}

	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public List<MoldReady> getMolds() {
		return molds;
	}

	public void setMolds(List<MoldReady> molds) {
		this.molds = molds;
	}
}
