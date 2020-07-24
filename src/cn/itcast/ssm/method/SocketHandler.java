package cn.itcast.ssm.method;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class SocketHandler extends IoHandlerAdapter implements IoHandler {  
	   @Override
	    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	       super.exceptionCaught(session, cause);
		   System.out.println("exceptionCaught: " + cause);
	    }

	    @Override
	    public void messageReceived(IoSession session, Object message) throws Exception {
	        super.messageReceived(session, message);
	        String str = message.toString();
	        Date date = new Date();
	        session.write(date.toString());
	        System.out.println("接收到的数据："+str);
	    }

	    @Override
	    public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
	    }

	    @Override
	    public void sessionClosed(IoSession session) throws Exception {
	        super.sessionClosed(session);
	    	System.out.println("sessionClosed");
	    }

	    @Override
	    public void sessionOpened(IoSession session) throws Exception {
	        super.sessionOpened(session);
	    	System.out.println("sessionOpen");
	    }

	    @Override
	    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	    	super.sessionIdle(session, status);
	    }
	    
	    @Override
	    public void sessionCreated(IoSession session) throws Exception {
	    	super.sessionCreated(session);
	    }
}
