package cn.itcast.ssm.po;

import java.util.List;

public class MoldUpdateVo {
	private String mold;
	private List<Integer> task_id;
	public String getMold() {
		return mold;
	}
	public void setMold(String mold) {
		this.mold = mold;
	}
	public List<Integer> getTask_id() {
		return task_id;
	}
	public void setTask_id(List<Integer> task_id) {
		this.task_id = task_id;
	}
	
}	
