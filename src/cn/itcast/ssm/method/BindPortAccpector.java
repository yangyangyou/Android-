package cn.itcast.ssm.method;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import cn.itcast.ssm.controller.ChecklistController;

public class BindPortAccpector {
	
	public static List<IoSession> ioSession = new LinkedList();
	public static IoSession session_one;
	//30s超时
	private static final int IDELTIMOUT=30;
	//15s发送一次心跳包
	private static final int HEARTBEATRATE=15;
	//心跳包内容
	private static final String HEARTBEATREQUEST="is_on";
	private static final String HEARTBEATRESPONSE="on";

	public void init() {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger",new LoggingFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
				IdleStatus.BOTH_IDLE);
		
		heartBeat.setForwardEvent(true);
		heartBeat.setRequestInterval(HEARTBEATRATE);
		heartBeat.setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandlerImpl());
		acceptor.getFilterChain().addLast("heartbeat", heartBeat);
		acceptor.setHandler(new MinaHandler());
		try {
			acceptor.bind(new InetSocketAddress(9000));
			System.out.println("端口绑定成功！");
		}catch (Exception e) {
			System.out.println("出错了！");
			e.printStackTrace();
		}
	}
	
	private static class MinaHandler extends IoHandlerAdapter{
		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			// TODO Auto-generated method stub
			super.messageReceived(session, message);
			int i = 0;
			String s[] = message.toString().split(",");
			System.out.println("字符数组"+s.length);
			session.setAttribute("details",s);
			ioSession.add(session);
			session_one = session;
			MessCacheManager.getInstance().pushNoPushWaiXieMess(s[0]);
			if(s[1].equals("电工") || s[1].equals("保钳班长") ) {
				System.out.println(MessCacheManager.getInstance().getErrorMessCache().toString());
				MessCacheManager.getInstance().pushNoPushErrorMess(s[0]);
			}
			if(s[1].equals("工段长") || s[1].equals("保钳班长") || s[1].equals("发货员")) {
				MessCacheManager.getInstance().pushNoPushPlanMess(s[0]);
			}
			if(s[1].equals("仓库管理员")){
				MessCacheManager.getInstance().pushNoPushCangkuMess(s[0]);
			}
		}
		
		@Override
		public void sessionCreated(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionCreated(session);
			System.out.println("当前会话数："+session.getService().getManagedSessions().values().size());
		}
		
		@Override
		public void sessionOpened(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionOpened(session);
		}
		
		@Override
		public void messageSent(IoSession session, Object message) throws Exception {
			// TODO Auto-generated method stub
			super.messageSent(session, message);
		}
		
		@Override
		public void sessionClosed(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionClosed(session);
			System.out.println("当前会话数："+session.getService().getManagedSessions().values().size());
		}
	}
	
	private static class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory{

		@Override
		public Object getRequest(IoSession arg0) {
			System.out.println("发送给客户端的心跳包消息："+HEARTBEATREQUEST);
			return HEARTBEATREQUEST;
		}

		@Override
		public Object getResponse(IoSession arg0, Object arg1) {
			// TODO Auto-generated method stub
			System.out.println("预设响应信息："+HEARTBEATRESPONSE);
			return HEARTBEATRESPONSE;
		}

		@Override
		public boolean isRequest(IoSession arg0, Object arg1) {
			// TODO Auto-generated method stub
			System.out.println("服务器判断消息是否为请求包信息"+arg1.toString());
			if(arg1.toString().equals(HEARTBEATREQUEST)){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public boolean isResponse(IoSession arg0, Object arg1) {
			// TODO Auto-generated method stub
			System.out.println("服务器判断响应心跳包信息"+arg1.toString());
			if(arg1.toString().equals(HEARTBEATRESPONSE)){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private static class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{

		@Override
		public void keepAliveRequestTimedOut(KeepAliveFilter arg0,
				IoSession arg1) throws Exception {
			// TODO Auto-generated method stub
			arg1.close(true);
			System.out.println("客户端挂了，已经关闭");
		}
	}
}
