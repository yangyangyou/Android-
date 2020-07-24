package cn.itcast.ssm.po;

import java.util.List;

public class MoldVo {
	private String asset_no;
	private List<Integer> plan_id;
	
	public String getAsset_no() {
		return asset_no;
	}
	public void setAsset_no(String asset_no) {
		this.asset_no = asset_no;
	}
	public List<Integer> getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(List<Integer> plan_id) {
		this.plan_id = plan_id;
	}
}
