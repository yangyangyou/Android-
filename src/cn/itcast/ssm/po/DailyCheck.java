package cn.itcast.ssm.po;

import java.util.List;

public class DailyCheck {
   private int check_id; //点检号
   private String asset_no; //设备号
   private String asset_xh; //设备型号
   
   private CheckRecord checkRecord;
   private List<CheckSelecting> checkSelecting;
   
public int getCheck_id() {
	return check_id;
}
public void setCheck_id(int check_id) {
	this.check_id = check_id;
}
public String getAsset_no() {
	return asset_no;
}
public void setAsset_no(String asset_no) {
	this.asset_no = asset_no;
}
public String getAsset_xh() {
	return asset_xh;
}
public void setAsset_xh(String asset_xh) {
	this.asset_xh = asset_xh;
}
public CheckRecord getCheckRecord() {
	return checkRecord;
}
public void setCheckRecord(CheckRecord checkRecord) {
	this.checkRecord = checkRecord;
}
public List<CheckSelecting> getCheckSelecting() {
	return checkSelecting;
}
public void setCheckSelecting(List<CheckSelecting> checkSelecting) {
	this.checkSelecting = checkSelecting;
}  

}
