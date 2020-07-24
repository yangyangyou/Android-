package cn.itcast.ssm.po;

public class Message {
	private String batch_no;
	private String asset_no;
	private String shop_name;
	private String process_name;
	private String operator;
	private String error_content;
	private String send_person;
	private String error_date;
	private int is_latest;
	
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getAsset_no() {
		return asset_no;
	}
	public void setAsset_no(String asset_no) {
		this.asset_no = asset_no;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
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
	public int getIs_latest() {
		return is_latest;
	}
	public void setIs_latest(int is_latest) {
		this.is_latest = is_latest;
	}

}
