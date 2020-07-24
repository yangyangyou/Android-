package cn.itcast.ssm.po;

import java.util.List;

public class GetSecMaterials {
	private Integer get_materials_id;
	private String shop_name;
	private String acceptor;
	private String provider;
	private String applyer;
	private String apply_data;
	private String approver;
	private String approver_data;
	private Integer is_approve;
	private Integer is_apply;
	private Integer is_pushed;
	private Integer is_recorded;
	private Integer is_return;
	private String remark;
	
	private List<GetSecDetail> get_sec_details;
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIs_return() {
		return is_return;
	}
	public void setIs_return(Integer is_return) {
		this.is_return = is_return;
	}
	public Integer getIs_pushed() {
		return is_pushed;
	}
	public void setIs_pushed(Integer is_pushed) {
		this.is_pushed = is_pushed;
	}
	public Integer getIs_recorded() {
		return is_recorded;
	}
	public void setIs_recorded(Integer is_recorded) {
		this.is_recorded = is_recorded;
	}
	public Integer getGet_materials_id() {
		return get_materials_id;
	}
	public void setGet_materials_id(Integer get_materials_id) {
		this.get_materials_id = get_materials_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	public String getApply_data() {
		return apply_data;
	}
	public void setApply_data(String apply_data) {
		this.apply_data = apply_data;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprover_data() {
		return approver_data;
	}
	public void setApprover_data(String approver_data) {
		this.approver_data = approver_data;
	}
	public Integer getIs_approve() {
		return is_approve;
	}
	public void setIs_approve(Integer is_approve) {
		this.is_approve = is_approve;
	}
	public Integer getIs_apply() {
		return is_apply;
	}
	public void setIs_apply(Integer is_apply) {
		this.is_apply = is_apply;
	}
	public List<GetSecDetail> getGet_sec_details() {
		return get_sec_details;
	}
	public void setGet_sec_details(List<GetSecDetail> get_sec_details) {
		this.get_sec_details = get_sec_details;
	}	
}
