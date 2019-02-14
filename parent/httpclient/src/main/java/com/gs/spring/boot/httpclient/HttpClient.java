/*    
 * HttpClient.java
 * 创建时间：2019年1月31日 下午3:00:19
 */ 
package com.gs.spring.boot.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**    
*	local httpClient implements poolingHttpClientConnectionManager
*   @author - gs
*/
@Configuration
public class HttpClient {
	
	// 最大连接数
	@Value( "${http.maxTotal}" )
	private int maxTotal;
	
	// 默认最大并发数
	@Value( "${http.defaultMaxPerRoute}" )
	private int defaultMaxPerRoute;
	
	// 连接超时时间
	@Value( "${http.connectionTimeout}" )
	private int connectionTimeout;
	
	// 请求连接池超时时间
	@Value( "${http.connectionRequestTimeout}" )
	private int connectionRequestTimeout;
	
	// 数据传输超时时间
	@Value( "${http.socketTimeout}" )
	private int socketTimeout;
	
	// 持久连接心跳校验
	@Value( "${http.validateAfterInactivity}" )
	private int validateAfterInactivity;
	
	/**
	 * 初始化连接池管理器
	 * @return
	 */
	@Bean( name = "httpClientConnectionManager" )
	public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal( maxTotal );
		httpClientConnectionManager.setDefaultMaxPerRoute( defaultMaxPerRoute );
		httpClientConnectionManager.setValidateAfterInactivity( validateAfterInactivity );
		return httpClientConnectionManager;
	}
	
	/**
	 * 实例化连接池
	 * @param httpClientConnectionManager
	 * @return
	 */
	@Bean( name = "httpClientBuilder" )
	public HttpClientBuilder getHttpClientBuilder( @Qualifier( "httpClientConnectionManager" ) PoolingHttpClientConnectionManager httpClientConnectionManager ) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager( httpClientConnectionManager );
		return httpClientBuilder;
	}
	
	/**
	 * 注入连接池
	 * @param httpClientBuilder
	 * @return
	 */
	@Bean
	public CloseableHttpClient getCloseableHttpClient( @Qualifier( "httpClientBuilder" ) HttpClientBuilder httpClientBuilder ) {
		return httpClientBuilder.build();
	}
	
	/**
	 * 设置连接属性
	 * 这里还可以扩展proxy、cookies等属性
	 * @return
	 */
	@Bean( name = "builder" )
	public RequestConfig.Builder getBuilder() {
		RequestConfig.Builder builder = RequestConfig.custom();
		builder.setConnectTimeout( connectionTimeout )
			.setConnectionRequestTimeout( connectionRequestTimeout )
			.setSocketTimeout( socketTimeout );
		HttpHost proxy = new HttpHost( "200.54.194.10", 53281, "http" );
		builder.setProxy( proxy );
		return builder;
	}
	
	/**
	 * 创建连接配置
	 * @param builder
	 * @return
	 */
	@Bean
	public RequestConfig getRequestConfig( @Qualifier( "builder" ) RequestConfig.Builder builder ) {
		return builder.build();
	}
}
