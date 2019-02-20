/*    
 * DefaultAyncConsumerMain.java
 * 创建时间：2019年2月20日 下午5:03:13
 */ 
package com.gs.springboot.dubbo.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

import com.gs.springboot.dubbo.rocketmq.listener.DefaultMessageListener;

/**    
*	defalut aync consumer main class
*   @author - gs
*/
public class DefaultAyncConsumerMain {

	public static void main( String[] args ) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer( "defaultAyncPushConsumer" );
		consumer.setNamesrvAddr( "127.0.0.1:9876" );
		consumer.subscribe( "defaultAyncTopic", "*" );
		consumer.registerMessageListener( new DefaultMessageListener() );
		consumer.start();
	}
}
