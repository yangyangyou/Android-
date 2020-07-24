package cn.itcast.ssm.po;

public class GetSecDetail {
	private Integer detail_id;
	private Integer get_materials_id;
	private String reshop_name;
	private String sec_material_name;
	private String sec_material_no;
	private String type;
	private String num;
	private String unit;
	private String returner;
	private String receiver;
	private String time;
	private String remark;
	private Integer is_return;
	private Integer is_recorded;
	
	
	public Integer getIs_recorded() {
		return is_recorded;
	}
	public void setIs_recorded(Integer is_recorded) {
		this.is_recorded = is_recorded;
	}
	public String getSec_material_no() {
		return sec_material_no;
	}
	public void setSec_material_no(String sec_material_no) {
		this.sec_material_no = sec_material_no;
	}
	public Integer getGet_materials_id() {
		return get_materials_id;
	}
	public void setGet_materials_id(Integer get_materials_id) {
		this.get_materials_id = get_materials_id;
	}
	public String getReshop_name() {
		return reshop_name;
	}
	public void setReshop_name(String reshop_name) {
		this.reshop_name = reshop_name;
	}
	public String getSec_material_name() {
		return sec_material_name;
	}
	public void setSec_material_name(String sec_material_name) {
		this.sec_material_name = sec_material_name;
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
	public String getReturner() {
		return returner;
	}
	public void setReturner(String returner) {
		this.returner = returner;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Integer getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Integer detail_id) {
		this.detail_id = detail_id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
}
