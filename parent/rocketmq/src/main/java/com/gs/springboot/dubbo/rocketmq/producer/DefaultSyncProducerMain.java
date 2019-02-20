/*    
 * DefaultSyncProducerMain.java
 * 创建时间：2019年2月18日 下午2:14:28
 */ 
package com.gs.springboot.dubbo.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import lombok.extern.slf4j.Slf4j;

/**    
*	default sync producer main class
*   @author - gs
*/
@Slf4j
public class DefaultSyncProducerMain {

	public static void main( String[] args ) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer( "defaultSyncProducerGroup" );
		producer.setNamesrvAddr( "127.0.0.1:9876" );
		producer.start();
		for( int i = 0;i < 100; i++ ) {
			Message message = new Message( "defaultSyncTopic", "defaultSyncTags" , 
					( "Hello Default Sync. RocketMQ." + i ).getBytes( RemotingHelper.DEFAULT_CHARSET ) );
			SendResult sendResult = producer.send( message );
			log.info( "{}", sendResult );
		}
//		producer.shutdown();
	}
}
