/*    
 * HttpClientController.java
 * 创建时间：2019年1月31日 下午4:31:49
 */ 
package com.gs.spring.boot.httpclient.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.spring.boot.httpclient.HttpClientService;
import com.gs.spring.boot.httpclient.HttpResult;

/**    
*	httpclient test controller
*   @author - gs
*/
@RestController
@RequestMapping( value = "/httpclient" )
public class HttpClientController {

	@Resource
	private HttpClientService httpClientService;
	
	@RequestMapping( value = "/get" )
	public String get( @RequestParam( defaultValue = "" ) String url ) {
		HttpResult result = null;
		try {
			result = httpClientService.get( url );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
