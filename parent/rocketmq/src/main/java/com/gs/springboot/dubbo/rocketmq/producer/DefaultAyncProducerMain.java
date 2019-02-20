/*    
 * DefaultAyncProducerMain.java
 * 创建时间：2019年2月20日 上午11:04:18
 */ 
package com.gs.springboot.dubbo.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import com.gs.springboot.dubbo.rocketmq.callback.DefaultSendCallback;

/**    
*	default aync producer main class
*   @author - gs
*/
public class DefaultAyncProducerMain {

	public static void main( String[] args ) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer( "defaultAyncProducerGroup" );
		producer.setNamesrvAddr( "127.0.0.1:9876" );
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed( 0 );
		for( int i = 0; i < 5; i++ ) {
			Message message = new Message( "defaultAyncTopic", "defaultAyncTags", 
					( "Hello Default Aync. RocketMQ." + i ).getBytes( RemotingHelper.DEFAULT_CHARSET ) );
			producer.send( message, new DefaultSendCallback() );
		}
//		producer.shutdown();
	}
}
