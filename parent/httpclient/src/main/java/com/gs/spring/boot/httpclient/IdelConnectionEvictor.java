/*    
 * IdelConnectionEvictor.java
 * 创建时间：2019年1月31日 下午3:35:31
 */ 
package com.gs.spring.boot.httpclient;

import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**    
*	http client 连接线程逐出器
*   @author - gs
*/
@Slf4j
@Component
public class IdelConnectionEvictor extends Thread {

	@Autowired
	private HttpClientConnectionManager httpClientConnectionManager;
	
	private volatile boolean shutdown;
	
	public IdelConnectionEvictor() {
		super();
		super.start();
	}

	@Override
	public void run() {
		try {
			while( !shutdown ) {
				synchronized ( this ) {
					wait( 5000 );
					httpClientConnectionManager.closeExpiredConnections();
				}
			}
		} catch ( Exception e ) {
			log.error( "thread: {} shutdown error.", this.getName(), e );
			shutdown();
		}
	}
	
	private void shutdown() {
		shutdown = true;
		synchronized ( this ) {
			notifyAll();
		}
	}
}
