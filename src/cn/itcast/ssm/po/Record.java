package cn.itcast.ssm.po;

public class Record {
	public String material_no;
	public String batch;
	public String workshop;
	public String process;
	public String assetState;
	public String moldState;
	public String staff;
	public String part;
	public String num;		
	
	
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getWorkshop() {
		return workshop;
	}
	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getAssetState() {
		return assetState;
	}
	public void setAssetState(String assetState) {
		this.assetState = assetState;
	}
	public String getMoldState() {
		return moldState;
	}
	public void setMoldState(String moldState) {
		this.moldState = moldState;
	}		
}
