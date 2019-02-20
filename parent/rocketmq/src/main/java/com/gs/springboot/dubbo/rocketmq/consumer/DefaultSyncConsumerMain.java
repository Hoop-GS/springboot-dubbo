/*    
 * DefaultSyncConsumerMain.java
 * 创建时间：2019年2月20日 上午11:23:51
 */ 
package com.gs.springboot.dubbo.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

import com.gs.springboot.dubbo.rocketmq.listener.DefaultMessageListener;

/**    
*	default sync consumer main class
*   @author - gs
*/
public class DefaultSyncConsumerMain {

	public static void main( String[] args ) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer( "defaultSyncPushConsumer" );
		consumer.setNamesrvAddr( "127.0.0.1:9876" );
		consumer.subscribe( "defaultSyncTopic", "*" );
		consumer.registerMessageListener( new DefaultMessageListener() );
		consumer.start();
	}
}
