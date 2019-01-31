/*    
 * DubboSpringBootProvider.java
 * 创建时间：2019年1月29日 上午11:09:12
 */ 
package com.gs.springboot.dubbo.provider;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**    
*	dubbo spring boot provider run application
*	@author gs
*/

@SpringBootApplication
@DubboComponentScan( basePackages = { "com.gs.springboot.dubbo.provider" } )
public class DubboSpringBootProvider {
	
	private static SpringApplicationBuilder configureSpringBuilder( SpringApplicationBuilder builder ) {
		builder.application().addPrimarySources( Collections.singletonList( DubboSpringBootProvider.class ) );
        return builder.sources( DubboSpringBootProvider.class );
	}

	public static void main( String[] args ) {
		configureSpringBuilder( new SpringApplicationBuilder() ).application().run( args );
	}
}
