package cn.itcast.ssm.method;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	private static MinaServer mainServer = null;       
    private SocketAcceptor acceptor = new NioSocketAcceptor();       
    private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();       
    private int bindPort = 9000;       
      
      
    public static MinaServer getInstances() {       
        if (null == mainServer) {       
            mainServer = new MinaServer();       
        }       
        return mainServer;       
    }       
      
    public MinaServer() {       
        chain.addLast("codec", new ProtocolCodecFilter(       
                new ObjectSerializationCodecFactory())); 
        chain.addLast("logger",new LoggingFilter());
        //acceptor.setHandler(SocketHandler.getInstances());  
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        try {       
            acceptor.bind(new InetSocketAddress(bindPort));       
        } catch (IOException e) {       
            e.printStackTrace();       
        }       
    }     
}
