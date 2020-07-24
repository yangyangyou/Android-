package cn.itcast.ssm.controller;

import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

@Component
public class HttpConnectionManager {
	PoolingHttpClientConnectionManager cm = null;
	
	@PostConstruct
	public void init(){
		LayeredConnectionSocketFactory sslsf = null;
		try{
			sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Registry<ConnectionSocketFactory> socketFactoryRegistry = 
				RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https",sslsf)
				.register("http", new PlainConnectionSocketFactory())
				.build();
		cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(50);
		cm.setDefaultMaxPerRoute(10);
	}
	
	public CloseableHttpClient getHttpClient(){
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm)
				.build();
		return httpClient;
	}
}
