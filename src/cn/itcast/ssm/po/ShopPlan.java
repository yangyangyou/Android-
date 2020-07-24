package cn.itcast.ssm.po;

import java.util.List;

public class ShopPlan {
   private int plan_id;//主键
   private String client;
   private String plan_no;//计划单号
   private String client_material_no;//图号
   private String material_no;//物料号
   private String product_spec;//产品规格
   private String batch_no;//批次号
   private String shop_name;//车间名称
   private String material_name;//产品名称
   private String plan_people;
   private String plan_date;
   private String plan_num;//计划生产数量
   private String online_date;
   private String produced_num;
   private String completed_date;
   private String process_sort;
   private String deliver_time;
   private Integer send_num;
   private int is_latest;
   private int is_new;
   private String rfid;
   
   //计划的工序和各个工序的操作工、模具
   private List<Task> tasks;
   
   private TrackCard trackCard;
   private Integer produce_num;

   
public Integer getSend_num() {
	return send_num;
}

public void setSend_num(Integer send_num) {
	this.send_num = send_num;
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

public void setClient(String clien) {
	this.client = clien;
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

public String getMaterial_name() {
	return material_name;
}

public void setMaterial_name(String material_name) {
	this.material_name = material_name;
}

public String getPlan_people() {
	return plan_people;
}

public void setPlan_people(String plan_people) {
	this.plan_people = plan_people;
}

public String getPlan_date() {
	return plan_date;
}

public void setPlan_date(String plan_date) {
	this.plan_date = plan_date;
}

public String getPlan_num() {
	return plan_num;
}

public void setPlan_num(String plan_num) {
	this.plan_num = plan_num;
}

public String getOnline_date() {
	return online_date;
}

public void setOnline_date(String online_date) {
	this.online_date = online_date;
}

public String getProduced_num() {
	return produced_num;
}

public void setProduced_num(String produced_num) {
	this.produced_num = produced_num;
}

public String getCompleted_date() {
	return completed_date;
}

public void setCompleted_date(String completed_date) {
	this.completed_date = completed_date;
}

public String getProcess_sort() {
	return process_sort;
}

public void setProcess_sort(String process_sort) {
	this.process_sort = process_sort;
}

public int getIs_latest() {
	return is_latest;
}

public void setIs_latest(int is_latest) {
	this.is_latest = is_latest;
}

public int getIs_new() {
	return is_new;
}

public void setIs_new(int is_new) {
	this.is_new = is_new;
}

public String getRfid() {
	return rfid;
}

public void setRfid(String rfid) {
	this.rfid = rfid;
}

public List<Task> getTasks() {
	return tasks;
}

public void setTasks(List<Task> tasks) {
	this.tasks = tasks;
}

public String getDeliver_time() {
	return deliver_time;
}

public void setDeliver_time(String deliver_time) {
	this.deliver_time = deliver_time;
}
public void jisuanProducedNum(){
	int sum=0;
	List<WorkCardNew> workCardNews = trackCard.getWork_cards();
	if(workCardNews.size()!=0){
		for(int i=0;i<workCardNews.size();i++){
			WorkCardNew workCardNew = workCardNews.get(i);
			sum += Integer.valueOf(workCardNew.getHege_num());
		}
	}
	produce_num = sum;
}

public TrackCard getTrackCard() {
	return trackCard;
}

public void setTrackCard(TrackCard trackCard) {
	this.trackCard = trackCard;
}

public Integer getProduce_num() {
	return produce_num;
}

public void setProduce_num(Integer produce_num) {
	this.produce_num = produce_num;
}

}
