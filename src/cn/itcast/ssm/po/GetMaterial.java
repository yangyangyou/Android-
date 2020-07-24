package cn.itcast.ssm.po;

import java.util.List;

public class GetMaterial {
    private int  get_material_id;
    private String client;//客户
    private String plan_no;//计划单号
    private String material_name; //产品名称
    private String client_material_no; //客户物料号
    private String material_no; //母件编码
    private String product_spec; //产品规格
    private String batch_no; //批次号
    private String shop_name; //车间名称
    private String apply_people; //申请人
    private String apply_date; //申请日期
    private String approver; //审批人
    private String approver_date; //审批日期
    private int is_approval; //是否已审批
    private int is_reapply; //是否补领
    private int is_pushed;//是否推送
    
    private List<GetDetail> getDetails;
    
	public int getGet_material_id() {
		return get_material_id;
	}
	public void setGet_material_id(int get_material_id) {
		this.get_material_id = get_material_id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public String getClient_material_no() {
		return client_material_no;
	}
	public void setClient_material_no(String client_material_no) {
		this.client_material_no = client_material_no;
	}
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getProduct_spec() {
		return product_spec;
	}
	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getApply_people() {
		return apply_people;
	}
	public void setApply_people(String apply_people) {
		this.apply_people = apply_people;
	}
	public String getApply_date() {
		return apply_date;
	}
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprover_date() {
		return approver_date;
	}
	public void setApprover_date(String approver_date) {
		this.approver_date = approver_date;
	}
	public int getIs_approval() {
		return is_approval;
	}
	public void setIs_approval(int is_approval) {
		this.is_approval = is_approval;
	}
	public int getIs_reapply() {
		return is_reapply;
	}
	public void setIs_reapply(int is_reapply) {
		this.is_reapply = is_reapply;
	}
	public int getIs_pushed() {
		return is_pushed;
	}
	public void setIs_pushed(int is_pushed) {
		this.is_pushed = is_pushed;
	}
	public List<GetDetail> getGetDetails() {
		return getDetails;
	}
	public void setGetDetails(List<GetDetail> getDetails) {
		this.getDetails = getDetails;
	}
}
