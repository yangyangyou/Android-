package cn.itcast.ssm.po;

import java.util.List;

public class OperatorVo {
	private int card_id;
	private String shop_name;
	private String asset;
	private String process_name;
	private List<String> operator;

	public List<String> getOperator() {
		return operator;
	}

	public void setOperator(List<String> operator) {
		this.operator = operator;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	
}
