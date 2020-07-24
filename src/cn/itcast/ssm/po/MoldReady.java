package cn.itcast.ssm.po;

public class MoldReady {
	private Integer id;
	private Integer plan_id;
	private String process_name;
	private String mold;
	private Integer is_ready;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}
	public String getProcess_name() {
		return process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public String getMold() {
		return mold;
	}
	public void setMold(String mold) {
		this.mold = mold;
	}
	public Integer getIs_ready() {
		return is_ready;
	}
	public void setIs_ready(Integer is_ready) {
		this.is_ready = is_ready;
	}
}
