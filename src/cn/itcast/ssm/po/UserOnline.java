package cn.itcast.ssm.po;

public class UserOnline {
  String ip; //在线用户的IP
  int is_online; //每20s检查一次是否为1，不为1时视为离线
  public UserOnline(String ip,int is_online) {
	  this.ip = ip;
	  this.is_online = is_online;
  }
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public int getIs_online() {
	return is_online;
}
public void setIs_online(int is_online) {
	this.is_online = is_online;
}
  
}
