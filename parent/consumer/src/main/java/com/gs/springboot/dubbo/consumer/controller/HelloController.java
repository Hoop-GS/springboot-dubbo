/*    
 * HelloController.java
 * 创建时间：2019年1月29日 下午3:22:14
 */ 
package com.gs.springboot.dubbo.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gs.springboot.dubbo.api.HelloService;

/**    
*	dubbo spring boot consumer controller
*   @author - gs
*/
@RestController
@RequestMapping( value = "/hello" )
public class HelloController {

	@Reference
	private HelloService helloService;
	
	@RequestMapping( value = "/sayHelloWithReturn" )
	public String sayHello( @RequestParam( defaultValue = "" ) String userName, @RequestParam( defaultValue = "" ) String saySomething ) {
		return helloService.sayHell( userName, saySomething );
	}
}
