/*    
 * HelloService.java
 * 创建时间：2019年1月29日 上午10:31:53
 */ 
package com.gs.springboot.dubbo.api;

/**    
*	dubbo server test service interface
*	@author gs
*/
public interface HelloService {

	/**
	 * say hello with have no return
	 * @param userName
	 * @param saySomething
	 */
	void sayHello( String userName, String saySomething );
	
	/**
	 * say hello with have return msg
	 * @param userName
	 * @param saySomething
	 * @return
	 */
	String sayHell( String userName, String saySomething );
}
