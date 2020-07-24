package cn.itcast.ssm.po;

import java.util.List;

public class GetAndBackCailiao {
	private int get_material_id;
	private String date;
	private String batch_no;
	private String shop_name;
	private String material_no;
	private String remark;
	private String acceptor;
	private String provider;
	private int is_return;
	private List<GetDetail> getDetails;
	
	
	public int getGet_material_id() {
		return get_material_id;
	}
	public void setGet_material_id(int get_material_id) {
		this.get_material_id = get_material_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getIs_return() {
		return is_return;
	}
	public void setIs_return(int is_return) {
		this.is_return = is_return;
	}
	public List<GetDetail> getGetDetails() {
		return getDetails;
	}
	public void setGetDetails(List<GetDetail> getDetails) {
		this.getDetails = getDetails;
	}
}
