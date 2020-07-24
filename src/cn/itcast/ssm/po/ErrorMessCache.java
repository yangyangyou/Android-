package cn.itcast.ssm.po;

import java.util.HashMap;
import java.util.List;

/**
 * 单个故障信息的缓存类
* <p>Title: ErrorMessCache</p>
* <p>Description: </p>
* @author 胡来刚
* @date 2018年6月8日 下午3:40:04
 */
public class ErrorMessCache {
	//故障信息在数据库中的主键
	private int check_id;
	
	//该消息需要推送的人员Map
	private HashMap<String,String> push_people;
	
	//该消息的具体内容
	private List<Message> message;

	private String json_message;
	
	
	public String getJson_message() {
		return json_message;
	}

	public void setJson_message(String json_message) {
		this.json_message = json_message;
	}

	public int getCheck_id() {
		return check_id;
	}

	public void setCheck_id(int check_id) {
		this.check_id = check_id;
	}
	


	public HashMap<String, String> getPush_people() {
		return push_people;
	}

	public void setPush_people(HashMap<String, String> push_people) {
		this.push_people = push_people;
	}

	public List<Message> getMessage() {
		return message;
	}
	
	public void setMessage(List<Message> message) {
		this.message = message;
	}
}
