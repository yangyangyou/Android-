package cn.itcast.ssm.po;

public class GetDetail {
   private int detail_id;
   private int get_material_id;
   private String batch_no; //半成品批次号
   private String unit; //单位
   private String material_num;//数量
   private String provider;//提供人--统计员
   private String acceptor;//接收人--领料人
   private String cailiao_mc;//材料名称
   private String cailiao_bh;//材料编号
   private String get_date;//领料日期
   private String remark;//备注
   private String material_batch_no;
   private int is_return;
public int getGet_material_id() {
	return get_material_id;
}
public void setGet_material_id(int get_material_id) {
	this.get_material_id = get_material_id;
}
public String getBatch_no() {
	return batch_no;
}
public void setBatch_no(String batch_no) {
	this.batch_no = batch_no;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getMaterial_num() {
	return material_num;
}
public void setMaterial_num(String material_num) {
	this.material_num = material_num;
}
public String getProvider() {
	return provider;
}
public void setProvider(String provider) {
	this.provider = provider;
}
public String getAcceptor() {
	return acceptor;
}
public void setAcceptor(String acceptor) {
	this.acceptor = acceptor;
}
public String getCailiao_mc() {
	return cailiao_mc;
}
public void setCailiao_mc(String cailiao_mc) {
	this.cailiao_mc = cailiao_mc;
}
public String getCailiao_bh() {
	return cailiao_bh;
}
public void setCailiao_bh(String cailiao_bh) {
	this.cailiao_bh = cailiao_bh;
}
public String getGet_date() {
	return get_date;
}
public void setGet_date(String get_date) {
	this.get_date = get_date;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public int getIs_return() {
	return is_return;
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
public void setIs_return(int is_return) {
	this.is_return = is_return;
}
public int getDetail_id() {
	return detail_id;
}
public void setDetail_id(int detail_id) {
	this.detail_id = detail_id;
}
public String getMaterial_batch_no() {
	return material_batch_no;
}
public void setMaterial_batch_no(String material_batch_no) {
	this.material_batch_no = material_batch_no;
}
}
