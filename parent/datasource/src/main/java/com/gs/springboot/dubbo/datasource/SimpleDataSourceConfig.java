/*    
 * SimpleDataSourceConfig.java
 * 创建时间：2019年2月14日 下午4:55:38
 */ 
package com.gs.springboot.dubbo.datasource;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**    
*	simple data source config class
*   @author - gs
*/
@Configuration
public class SimpleDataSourceConfig {
	
	@Value( "${mybatis.mapperLocaltions}" )
	private String mapperLocaltions;

	@Bean( name = "dataSource", initMethod = "init", destroyMethod = "close")
	@Primary
	@ConfigurationProperties( prefix = "druid.datasource" )
	public DataSource dataSource() {
		return new DruidDataSource();
	}
	
	@Bean( name = "sqlSessionFactory" )
	@Primary
	public SqlSessionFactoryBean sqlSessionFactory( @Qualifier( "dataSource" ) DataSource dataSource ) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource( dataSource );
		sqlSessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources( mapperLocaltions ) );
		return sqlSessionFactoryBean;
	}
	
	@Bean( name = "transactionManager" )
	@Primary
	public DataSourceTransactionManager transactionManager( @Qualifier( "dataSource" ) DataSource dataSource ) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource( dataSource );
		return transactionManager;
	}
}
