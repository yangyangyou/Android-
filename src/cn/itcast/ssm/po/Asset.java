package cn.itcast.ssm.po;

public class Asset {
	private String asset_name;
	private String asset_no;
	private String  is_common;
	private String asset_type;
	
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
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
	public String getIs_common() {
		return is_common;
	}
	public void setIs_common(String is_common) {
		this.is_common = is_common;
	}
	
	

}
