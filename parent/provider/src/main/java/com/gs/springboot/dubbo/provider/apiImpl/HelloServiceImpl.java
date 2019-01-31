/*    
 * HelloServiceImpl.java
 * 创建时间：2019年1月29日 下午2:59:41
 */ 
package com.gs.springboot.dubbo.provider.apiImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gs.springboot.dubbo.api.HelloService;

import lombok.extern.slf4j.Slf4j;

/**    
*	dubbo server test service interface implement class
*   @author - gs
*/
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello( String userName, String saySomething ) {
		log.info( "{} say {}", userName, saySomething );
	}

	@Override
	public String sayHell( String userName, String saySomething ) {
		log.info( "received {} msg: {}", userName, saySomething );
		return "server has received msg.";
	}

}
