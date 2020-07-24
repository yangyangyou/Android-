package cn.itcast.ssm.po;

public class SecondaryMaterials {
	//private Integer materials_id;
	private String sec_materials_name;
	private String type;
	private String unit;
	private String number;
	private String product_no;
	private String product_describe;
	
	
	public String getProduct_describe() {
		return product_describe;
	}
	public void setProduct_describe(String product_describe) {
		this.product_describe = product_describe;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSec_materials_name() {
		return sec_materials_name;
	}
	public void setSec_materials_name(String sec_materials_name) {
		this.sec_materials_name = sec_materials_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
