/*    
 * DataSourceApplication.java
 * 创建时间：2019年2月14日 下午5:38:55
 */ 
package com.gs.springboot.dubbo.datasource;

import java.util.Collections;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**    
*	datasource application class
*   @author - gs
*/
@SpringBootApplication
public class DataSourceApplication {

	private static SpringApplicationBuilder configureSpringBuilder( SpringApplicationBuilder builder ) {
		builder.application().addPrimarySources( Collections.singletonList( DataSourceApplication.class ) );
        return builder.sources( DataSourceApplication.class );
	}

	public static void main( String[] args ) {
		configureSpringBuilder( new SpringApplicationBuilder() ).application().run( args );
	}
}
