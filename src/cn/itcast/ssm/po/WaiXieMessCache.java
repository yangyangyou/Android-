package cn.itcast.ssm.po;

public class WaiXieMessCache {
	
	//外协推送的目标人
	private String push_people;
	
	//外协推送的内容
	private String message;

	public String getPush_people() {
		return push_people;
	}

	public void setPush_people(String push_people) {
		this.push_people = push_people;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
