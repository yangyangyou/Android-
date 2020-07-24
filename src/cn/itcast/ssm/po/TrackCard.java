package cn.itcast.ssm.po;

import java.util.List;

public class TrackCard {
	private Integer card_id;
	private String client_material_no;
	private String batch_no;
	private List<WorkCardNew> work_cards;
	public Integer getCard_id() {
		return card_id;
	}
	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public List<WorkCardNew> getWork_cards() {
		return work_cards;
	}
	public void setWork_cards(List<WorkCardNew> work_cards) {
		this.work_cards = work_cards;
	}
	public String getClient_material_no() {
		return client_material_no;
	}
	public void setClient_material_no(String client_material_no) {
		this.client_material_no = client_material_no;
	}
	
}
