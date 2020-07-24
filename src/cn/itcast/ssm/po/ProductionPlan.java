package cn.itcast.ssm.po;

import java.util.List;

public class ProductionPlan {
	private int plan_id;
	private String client;
	private String order_date;
	private String order_no;
	private String plan_no;
	private String client_material_no;
	private String material_no;
	private String product_spec;
	private String product_name;
	private String plan_num;
	private String start_num;
	private String unproduct_num;
	private String product_num;
	private String material_num;
	private String sort;
	private int is_monthly_plan;
	private int is_new;
	private int is_product;
	private String remark;
	private String make_people;
	private String make_time;
	private String rfid;
	private Integer finish_num;
	private String deliver_time;
	
	private Integer cipin_num;
	private Integer gongfei_num;
	private Integer liaofei_num;
	
	
	public Integer getCipin_num() {
		return cipin_num;
	}
	public void setCipin_num(Integer cipin_num) {
		this.cipin_num = cipin_num;
	}
	public Integer getGongfei_num() {
		return gongfei_num;
	}
	public void setGongfei_num(Integer gongfei_num) {
		this.gongfei_num = gongfei_num;
	}
	public Integer getLiaofei_num() {
		return liaofei_num;
	}
	public void setLiaofei_num(Integer liaofei_num) {
		this.liaofei_num = liaofei_num;
	}
	private List<ProductRecord> production_records;
	
	private List<ShopDelivery> shop_deliverys;
	
	private ShopDelivery shop_delivery;
	
	
	public List<ShopDelivery> getShop_deliverys() {
		return shop_deliverys;
	}
	public void setShop_deliverys(List<ShopDelivery> shop_deliverys) {
		this.shop_deliverys = shop_deliverys;
	}
	public ShopDelivery getShop_delivery() {
		return shop_delivery;
	}
	public void setShop_delivery(ShopDelivery shop_delivery) {
		this.shop_delivery = shop_delivery;
	}
	public List<ProductRecord> getProduction_records() {
		return production_records;
	}
	public void setProduction_records(List<ProductRecord> production_records) {
		this.production_records = production_records;
	}
	
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public String getProduct_spec() {
		return product_spec;
	}
	public void setProduct_spec(String product_spec) {
		this.product_spec = product_spec;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getPlan_num() {
		return plan_num;
	}
	public void setPlan_num(String plan_num) {
		this.plan_num = plan_num;
	}
	public String getStart_num() {
		return start_num;
	}
	public void setStart_num(String start_num) {
		this.start_num = start_num;
	}
	public String getUnproduct_num() {
		return unproduct_num;
	}
	public void setUnproduct_num(String unproduct_num) {
		this.unproduct_num = unproduct_num;
	}
	public String getProduct_num() {
		return product_num;
	}
	public void setProduct_num(String product_num) {
		this.product_num = product_num;
	}
	public String getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(String material_num) {
		this.material_num = material_num;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getIs_monthly_plan() {
		return is_monthly_plan;
	}
	public void setIs_monthly_plan(int is_monthly_plan) {
		this.is_monthly_plan = is_monthly_plan;
	}
	public int getIs_new() {
		return is_new;
	}
	public void setIs_new(int is_new) {
		this.is_new = is_new;
	}
	public int getIs_product() {
		return is_product;
	}
	public void setIs_product(int is_product) {
		this.is_product = is_product;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMake_people() {
		return make_people;
	}
	public void setMake_people(String make_people) {
		this.make_people = make_people;
	}
	public String getMake_time() {
		return make_time;
	}
	public void setMake_time(String make_time) {
		this.make_time = make_time;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public Integer getFinish_num() {
		return finish_num;
	}
	public void setFinish_num(Integer finish_num) {
		this.finish_num = finish_num;
	}
	public void jisuanFinishNum(){
		Integer sum = 0;
		for(int i=0;i<this.production_records.size();i++){
			sum += Integer.parseInt(this.production_records.get(i).getProductNum());
		}
		this.finish_num = sum;
	}
	public String getDeliver_time() {
		return deliver_time;
	}
	public void setDeliver_time(String deliver_time) {
		this.deliver_time = deliver_time;
	}
}
