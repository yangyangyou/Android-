package cn.itcast.ssm.po;

public class SpecialGauge {
	   private Integer id;
	   private String inspection_production;
	   private String type;
	   private String gauge_name;
	   private String gauge_no;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInspection_production() {
		return inspection_production;
	}
	public void setInspection_production(String inspection_production) {
		this.inspection_production = inspection_production;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGauge_name() {
		return gauge_name;
	}
	public void setGauge_name(String gauge_name) {
		this.gauge_name = gauge_name;
	}
	public String getGauge_no() {
		return gauge_no;
	}
	public void setGauge_no(String gauge_no) {
		this.gauge_no = gauge_no;
	}
}
