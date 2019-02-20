/*    
 * DefaultSendCallback.java
 * 创建时间：2019年2月20日 下午2:44:34
 */ 
package com.gs.springboot.dubbo.rocketmq.callback;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

import lombok.extern.slf4j.Slf4j;

/**    
*	default send callback class
*   @author - gs
*/
@Slf4j
public class DefaultSendCallback implements SendCallback {

	@Override
	public void onSuccess( SendResult sendResult ) {
		log.info( "{}: {}", Thread.currentThread().getName(), sendResult );
	}

	@Override
	public void onException( Throwable e ) {
		log.error( "{}: aync send msg error.", Thread.currentThread().getName(), e );
	}

}
