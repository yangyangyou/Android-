package cn.itcast.ssm.po;

import java.util.HashMap;

public class CangkuMessCache {
	//该消息需要推送的人员Map
		private HashMap<String,String> push_people;
			
		//该消息的具体内容
		private String message;

		public HashMap<String, String> getPush_people() {
			return push_people;
		}

		public void setPush_people(HashMap<String, String> push_people) {
			this.push_people = push_people;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
}
