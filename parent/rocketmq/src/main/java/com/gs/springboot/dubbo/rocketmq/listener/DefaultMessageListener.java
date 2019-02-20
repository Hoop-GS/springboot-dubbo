/*    
 * DefaultMessageListener.java
 * 创建时间：2019年2月20日 下午2:41:41
 */ 
package com.gs.springboot.dubbo.rocketmq.listener;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import lombok.extern.slf4j.Slf4j;

/**    
*	default message listener class
*   @author - gs
*/
@Slf4j
public class DefaultMessageListener implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage( List<MessageExt> msgs, ConsumeConcurrentlyContext context ) {
		for( MessageExt msg : msgs ) {
			try {
				log.info( "{}: {}", Thread.currentThread().getName(), new String( msg.getBody(), RemotingHelper.DEFAULT_CHARSET ) );
			} catch ( UnsupportedEncodingException e ) {
				log.error( "consumer message error. {}", Thread.currentThread().getName(), e );
			}
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
