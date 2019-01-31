/*    
 * HttpClientServiceTest.java
 * 创建时间：2019年1月31日 下午4:15:13
 */ 
package com.gs.spring.boot.httpclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**    
*	httpclient service test class
*   @author - gs
*/
@RunWith( SpringRunner.class )
@SpringBootTest( classes = { HttpClient.class, HttpClientService.class } )
public class HttpClientServiceTest {

	@Autowired
	private HttpClientService httpClientService;
	
	@Test
	public void get() {
		String url = "https://baike.baidu.com/search/word?word=西南交通出版社";
		try {
			HttpResult httpResult = httpClientService.get( url );
			System.out.println( "code: " + httpResult.getCode() );
			System.out.println( "result: " + httpResult.getResult() );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
