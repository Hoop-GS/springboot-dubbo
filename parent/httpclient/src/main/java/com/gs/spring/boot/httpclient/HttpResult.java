/*    
 * HttpResult.java
 * 创建时间：2019年1月31日 下午4:04:08
 */ 
package com.gs.spring.boot.httpclient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**    
*	http client request result class
*   @author - gs
*/
public class HttpResult {

	// response status code
	private int code;
	
	// response entity string
	private String result;
	
	public HttpResult( int code, String result ) {
		this.code = code;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode( int code ) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult( String result ) {
		this.result = result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.SHORT_PREFIX_STYLE );
	}
	
}
