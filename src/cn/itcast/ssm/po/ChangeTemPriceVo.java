package cn.itcast.ssm.po;

import java.util.List;

public class ChangeTemPriceVo {
	public String material_no;
	public String batch_no;
	public String plan_no;
	public List<WorkCardNew> workCards;
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
	public List<WorkCardNew> getWorkCards() {
		return workCards;
	}
	public void setWorkCards(List<WorkCardNew> workCards) {
		this.workCards = workCards;
	}
}
