package cn.itcast.ssm.method;

import java.util.Collection;
import java.util.Collections;

import org.apache.mina.core.session.IoSession;

import cn.itcast.ssm.controller.ChecklistController;

public class MessPushSession {
	
     public static void SessionPush() {
    	 IoSession ioSession = ChecklistController.user_session;
    	 if(ioSession != null) {
    		 Collection<IoSession> sessions = ioSession.getService().getManagedSessions().values();
    		 for(IoSession s : sessions) {
    			 if(s.isConnected()) {
    				 s.write("123");
    			 }
    		 }
    	 }
     }
}
