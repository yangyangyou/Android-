package cn.itcast.ssm.po;

public class ProcessTransition {
	private Integer transition_id;
	private String plan_no;
	private String client_material_no;
	private String material_no;
	private String batch_no;
	private String shop_name;
	private String provider;
	private String process1;
	private String acceptor;
	private String process2;
	private String tran_date;
	private String qualified_num;
	private String unqualified_num;
	public Integer getTransition_id() {
		return transition_id;
	}
	public void setTransition_id(Integer transition_id) {
		this.transition_id = transition_id;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getClient_material_no() {
		return client_material_no;
	}
	public void setClient_material_no(String client_material_no) {
		this.client_material_no = client_material_no;
	}
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
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
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProcess1() {
		return process1;
	}
	public void setProcess1(String process1) {
		this.process1 = process1;
	}
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getProcess2() {
		return process2;
	}
	public void setProcess2(String process2) {
		this.process2 = process2;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public String getQualified_num() {
		return qualified_num;
	}
	public void setQualified_num(String qualified_num) {
		this.qualified_num = qualified_num;
	}
	public String getUnqualified_num() {
		return unqualified_num;
	}
	public void setUnqualified_num(String unqualified_num) {
		this.unqualified_num = unqualified_num;
	}
}
