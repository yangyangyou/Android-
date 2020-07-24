package cn.itcast.ssm.po;

public class ShopDelivery {
	private int shop_delivery_id;
	private int plan_id;
	private String shop_name;
	private String delivery_num;
	private String plan_finish_date;
	private String delivert_date;
	private String send_num;
	private String send_date;
	private String delay_reason;
	private int priority;
	
	public int getShop_delivery_id() {
		return shop_delivery_id;
	}
	public void setShop_delivery_id(int shop_delivery_id) {
		this.shop_delivery_id = shop_delivery_id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getDelivery_num() {
		return delivery_num;
	}
	public void setDelivery_num(String delivery_num) {
		this.delivery_num = delivery_num;
	}
	public String getPlan_finish_date() {
		return plan_finish_date;
	}
	public void setPlan_finish_date(String plan_finish_date) {
		this.plan_finish_date = plan_finish_date;
	}
	public String getDelivert_date() {
		return delivert_date;
	}
	public void setDelivert_date(String delivert_date) {
		this.delivert_date = delivert_date;
	}
	public String getSend_num() {
		return send_num;
	}
	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getDelay_reason() {
		return delay_reason;
	}
	public void setDelay_reason(String delay_reason) {
		this.delay_reason = delay_reason;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
