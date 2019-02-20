/*    
 * DefaultOnewayProducerMain.java
 * 创建时间：2019年2月20日 上午11:12:52
 */ 
package com.gs.springboot.dubbo.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**    
*	default oneway producer main class
*   @author - gs
*/
public class DefaultOnewayProducerMain {

	public static void main( String[] args ) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer( "defaultOnewayProducerGroup" );
		producer.setNamesrvAddr( "127.0.0.1:9876" );
		producer.start();
		for( int i = 0; i < 100; i++ ) {
			Message message = new Message( "defaultOnewayTopic", "defaultOnewayTags", 
					( "Hello Oneway. RockeyMQ." + i ).getBytes( RemotingHelper.DEFAULT_CHARSET ) );
			producer.sendOneway( message );
		}
		producer.shutdown();
	}
}
