/*    
 * HttpClientApplication.java
 * 创建时间：2019年1月31日 下午4:13:10
 */ 
package com.gs.spring.boot.httpclient.application;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**    
*	httpclient spring boot run application
*   @author - gs
*/
@SpringBootApplication
@ComponentScan( basePackages = { "com.gs.spring.boot.httpclient" } )
public class HttpClientApplication {

	private static SpringApplicationBuilder configureSpringBuilder( SpringApplicationBuilder builder ) {
		builder.application().addPrimarySources( Collections.singletonList( HttpClientApplication.class ) );
        return builder.sources( HttpClientApplication.class );
	}

	public static void main( String[] args ) {
		configureSpringBuilder( new SpringApplicationBuilder() ).application().run( args );
	}
}
