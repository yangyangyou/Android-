package cn.itcast.ssm.po;

public class HeightGauge {
	private Integer id;
	private String gauge_no;
	private String product_specification;
	private String tolerance_range;
	private String num;
	private String declaration_date;
	private String purpose;
	private String completion_date;
	private String collar_workers;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGauge_no() {
		return gauge_no;
	}
	public void setGauge_no(String gauge_no) {
		this.gauge_no = gauge_no;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getTolerance_range() {
		return tolerance_range;
	}
	public void setTolerance_range(String tolerance_range) {
		this.tolerance_range = tolerance_range;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDeclaration_date() {
		return declaration_date;
	}
	public void setDeclaration_date(String declaration_date) {
		this.declaration_date = declaration_date;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getCompletion_date() {
		return completion_date;
	}
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}
	public String getCollar_workers() {
		return collar_workers;
	}
	public void setCollar_workers(String collar_workers) {
		this.collar_workers = collar_workers;
	}
}
