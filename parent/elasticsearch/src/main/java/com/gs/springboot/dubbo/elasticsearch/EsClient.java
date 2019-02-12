/*    
 * EsClient.java
 * 创建时间：2019年2月12日 下午2:17:38
 */ 
package com.gs.springboot.dubbo.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**    
*	elasticsearch client class
*   @author - gs
*/
@Configuration
@Slf4j
public class EsClient {

	@Value( "${elasticsearch.server.ip}" )
	private String ip;
	
	@Value( "${elasticsearch.server.port}" )
	private Integer port;
	
	@Value( "${elasticsearch.cluster.name}" )
	private String name;
	
	@Value( "${elasticsearch.client.transport.sniff}" )
	private boolean sniff;
	
	@SuppressWarnings( "resource" )
	@Bean
	public Client getClient() {
		Settings settings = Settings.builder()
				.put( "cluster-name", name )
				.put( "client.transport.sniff", sniff )
				.build();
		try {
			return new PreBuiltTransportClient( settings )
					.addTransportAddress( new InetSocketTransportAddress( InetAddress.getByName( ip ), port ) );
		} catch ( UnknownHostException e ) {
			log.error( "create elasticsearch client by transport address error.", e );
		}
		return null;
	}
}
