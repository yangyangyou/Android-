package cn.itcast.ssm.po;

public class AssetStatus {
	private String asset_name;
	private String asset_no;
	private String asset_type;
	private String status;
	private String person;
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getAsset_no() {
		return asset_no;
	}
	public void setAsset_no(String asset_no){
		this.asset_no = asset_no;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
}
