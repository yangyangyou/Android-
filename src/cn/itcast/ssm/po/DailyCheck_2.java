package cn.itcast.ssm.po;

public class DailyCheck_2 {
    private int check_id;
    private String plan_no;
    private String client_material_no;
    private String material_no;
    private String batch_no;
    private String shop_name;
    private String error_date;
    private String process_name;
    private String asset_name;
    private String asset_no;
    private String operator;
    private int is_rijian;
    private String error_content;
    private String send_person;
    
    public String getError_content() {
		return error_content;
	}
	public void setError_content(String error_content) {
		this.error_content = error_content;
	}
	public String getSend_person() {
		return send_person;
	}
	public void setSend_person(String send_person) {
		this.send_person = send_person;
	}
    
	public String getError_date() {
		return error_date;
	}
	public void setError_date(String error_date) {
		this.error_date = error_date;
	}
	public int getCheck_id() {
		return check_id;
	}
	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
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
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getAsset_no() {
		return asset_no;
	}
	public void setAsset_no(String asset_no) {
		this.asset_no = asset_no;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getIs_rijian() {
		return is_rijian;
	}
	public void setIs_rijian(int is_rijian) {
		this.is_rijian = is_rijian;
	}
    
}
