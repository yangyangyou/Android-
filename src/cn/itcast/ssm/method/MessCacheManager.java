package cn.itcast.ssm.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.mina.core.session.IoSession;

import cn.itcast.ssm.po.CangkuMessCache;
import cn.itcast.ssm.po.ErrorMessCache;
import cn.itcast.ssm.po.Message;
import cn.itcast.ssm.po.PlanMessCache;
import cn.itcast.ssm.po.WaiXieMessCache;
import net.sf.json.JSONObject;

public class MessCacheManager {
	private static MessCacheManager messCacheManager = null;
	
	// 故障消息缓存List
	private List<ErrorMessCache> errorMessList = new LinkedList<>();
	
	// 计划消息缓存List
	private List<PlanMessCache> planMessList = new LinkedList<>();
	
	// 外协消息缓存List
	private List<WaiXieMessCache> waixieMessList = new LinkedList<>();
	
	//仓库管理员推送的消息缓存List
	private List<CangkuMessCache> cangkuMessList = new LinkedList<>();
	
	// 加锁单例的标准写法
	public static MessCacheManager getInstance() {
		if(messCacheManager == null) {
			synchronized (MessCacheManager.class) {
				if(messCacheManager == null) {
				    messCacheManager = new MessCacheManager();
				}
			}
		}
		return messCacheManager;
	}
	
	//获取故障消息缓存
	public List<ErrorMessCache> getErrorMessCache(){
		return errorMessList;
	}
	
	//获取计划消息缓存
	public List<PlanMessCache> getPlanMessCache(){
		return planMessList;
	}
	
	//获取外协消息缓存
	public List<WaiXieMessCache> getWaiXieMessCache(){
		return waixieMessList;
	}
	
	//获取仓库消息缓存
	public List<CangkuMessCache> getCangkuMessCache(){
		return cangkuMessList;
	}
	
	//清空所有缓存的方法
	public void clearAllCache(){
		errorMessList.clear();
		planMessList.clear();
		waixieMessList.clear();
		cangkuMessList.clear();
	}
	
	//添加新的未推送故障消息
	public void insertErrorMessCache(ErrorMessCache errorMessCache) {
		errorMessList.add(errorMessCache);
	}
	
	//添加新的未推送计划消息
	public void insertPlanMessCache(PlanMessCache planMessCache) {
		planMessList.add(planMessCache);
	}
	
	//添加新的未推送外协消息
	public void insertWaiXieMessCache(WaiXieMessCache waiXieMessCache) {
		waixieMessList.add(waiXieMessCache);
	}
	
	//添加新的未推送的仓库消息
	public void insertCangkuMessCache(CangkuMessCache cangkuMessCache){
		cangkuMessList.add(cangkuMessCache);
	}
	
	//当一条故障消息被推送后，清除人员名单
	public void updateErrorMess(String username,int index) {
		Map<String,String> map = errorMessList.get(index).getPush_people();
		map.remove(username);
		if(map.size()==0) {
			errorMessList.remove(index);
		}
	}
	
	//当一条计划消息被推送后，清除人员名单
	public void updatePlanMess(String username,int index) {
		Map<String,String> map = planMessList.get(index).getPush_people();
		map.remove(username);
		if(map.size()==0) {
			planMessList.remove(index);
		}
	}
	
	//当一条外协消息被推送后，清除人员名单
		public void updateWaiXieMess(String username,int index) {
			String push_people = waixieMessList.get(index).getPush_people();
			if(username.equals(push_people)) {
				waixieMessList.remove(index);
			}
		}
	
	//当一条仓库消息被推送后，清除人员名单
	public void updateCangkuMess(String username,int index){
		Map<String,String> map = cangkuMessList.get(index).getPush_people();
		map.remove(username);
		if(map.size()==0) {
			cangkuMessList.remove(index);
		}
	}
	
	//查询指定用户有没有未接收的故障消息
	public void pushNoPushErrorMess(String username) {
		if(errorMessList.size()!=0) {
			for(int i=0;i<errorMessList.size();i++) {
				ErrorMessCache one = errorMessList.get(i);
				HashMap<String,String> map = one.getPush_people();
				//List<Message> message = one.getMessage();
				String mess = one.getJson_message();
				if(map.size()==0 && errorMessList.size()!=0) {
					errorMessList.remove(i);
					continue;
				}else {
					String is_push = map.get(username);
					if(is_push != null) { //0为未推送，1为已推送
						//这里执行推送代码
						try {
							JSONObject jsonObject = JSONObject.fromObject(mess);
							errorMessToUser(jsonObject, username,i);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	//查询指定用户有没有未接收的计划消息
	public void pushNoPushPlanMess(String username) {
		if(planMessList.size()!=0) {
			for(int i=0;i<planMessList.size();i++) {
				PlanMessCache one = planMessList.get(i);
				HashMap<String,String> map = one.getPush_people();
				String shop_plan = one.getMessage();
				if(map.size()==0 && planMessList.size()!=0) {
					planMessList.remove(i);
					continue;
				}else {
					String is_push = map.get(username);
					if(is_push != null) { //0为未推送，1为已推送
						//这里执行推送代码
						try {
							planMessToUser(shop_plan, username,i);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	
	//查询指定用户有没有未接收的仓库消息
		public void pushNoPushCangkuMess(String username) {
			if(cangkuMessList.size()!=0) {
				for(int i=0;i<cangkuMessList.size();i++) {
					CangkuMessCache one = cangkuMessList.get(i);
					HashMap<String,String> map = one.getPush_people();
					String cangku = one.getMessage();
					if(map.size()==0 && cangkuMessList.size()!=0) {
						cangkuMessList.remove(i);
						continue;
					}else {
						String is_push = map.get(username);
						if(is_push != null) { //0为未推送，1为已推送
							//这里执行推送代码
							try {
								cangkuMessToUser(cangku, username,i);
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		
	//查询指定用户有没有未接收的外协消息
		public void pushNoPushWaiXieMess(String username) {
			if(waixieMessList.size()!=0) {
				for(int i=0;i<waixieMessList.size();i++) {
					WaiXieMessCache one = waixieMessList.get(i);
					String push_people = one.getPush_people();
					String waixie = one.getMessage();
					if(push_people.equals("1") && waixieMessList.size()!=0) {
						waixieMessList.remove(i);
						continue;
					}else {
						if(username.equals(push_people)) {
							try {
								waixieMessToUser(waixie, username,i);
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	
	//推送故障消息
	private void errorMessToUser(JSONObject jsonObject,String username,int index) {
		Collection<IoSession> sessions = null;
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
		}else {
			System.out.println("缓存推送数目："+sessions.size());
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(c[0].equals(username)) {
					s.write(jsonObject.toString());
					System.out.println("缓存推送："+jsonObject.toString());
					updateErrorMess(username, index);
					break;
				}
			}
		}
	}
	
	//推送计划消息
	private void planMessToUser(String shop_plan,String username,int index) {
		Collection<IoSession> sessions = null;
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
		}else {
			System.out.println("缓存推送数目："+sessions.size());
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(c[0].equals(username)) {
					s.write(shop_plan);
					System.out.println("缓存推送："+shop_plan);
					updatePlanMess(username, index);
					break;
				}
			}
		}
	}
	
	//推送外协消息
	private void waixieMessToUser(String waixie,String username,int index) {
		Collection<IoSession> sessions = null;
		for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
			if(BindPortAccpector.ioSession.get(i).isConnected()) {
				sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
				break;
			}
		}
		if(sessions == null) {
			System.out.println("没有可用连接！");
		}else {
			System.out.println("缓存推送数目："+sessions.size());
			for(IoSession s : sessions) {
				String c[] = (String[]) s.getAttribute("details");
				if(c[0].equals(username)) {
					s.write(waixie);
					System.out.println("缓存推送："+waixie);
					updateWaiXieMess(username, index);
					break;
				}
			}
		}
	}
	
	//推送计划消息
		private void cangkuMessToUser(String shop_plan,String username,int index) {
			Collection<IoSession> sessions = null;
			for(int i=0;i<BindPortAccpector.ioSession.size();i++) {
				if(BindPortAccpector.ioSession.get(i).isConnected()) {
					sessions = BindPortAccpector.ioSession.get(i).getService().getManagedSessions().values();
					break;
				}
			}
			if(sessions == null) {
				System.out.println("没有可用连接！");
			}else {
				System.out.println("缓存推送数目："+sessions.size());
				for(IoSession s : sessions) {
					String c[] = (String[]) s.getAttribute("details");
					if(c[0].equals(username)) {
						s.write(shop_plan);
						System.out.println("缓存推送："+shop_plan);
						updateCangkuMess(username, index);
						break;
					}
				}
			}
		}
}
