/*    
 * DubboSpringBootConsumer.java
 * 创建时间：2019年1月29日 下午2:49:45
 */ 
package com.gs.springboot.dubbo.consumer;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**    
*	dubbo spring boot consumer run application
*   @author - gs
*/
@SpringBootApplication
@DubboComponentScan( basePackages = { "com.gs.springboot.dubbo.consumer" } )
public class DubboSpringBootConsumer {

	private static SpringApplicationBuilder configureSpringBuilder( SpringApplicationBuilder builder ) {
		builder.application().addPrimarySources( Collections.singletonList( DubboSpringBootConsumer.class ) );
        return builder.sources( DubboSpringBootConsumer.class );
	}
	
	public static void main( String[] args ) {
		configureSpringBuilder( new SpringApplicationBuilder() ).application().run( args );
	}
}
