/*    
 * HttpClientService.java
 * 创建时间：2019年1月31日 下午3:45:28
 */ 
package com.gs.spring.boot.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**    
*	http client 服务类
*   @author - gs
*/
@Service
public class HttpClientService {

	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private RequestConfig config;
	
	/**
	 * http get
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult get( String url ) throws Exception {
		// 初始化请求
		HttpGet get = new HttpGet( url );
		// 设置连接属性
		get.setConfig( config );
		// 发起请求
		HttpResponse response = httpClient.execute( get );
		return new HttpResult( response.getStatusLine().getStatusCode(), EntityUtils.toString( response.getEntity(), "UTF-8" ) );
	}
	
	/**
	 * http get with param
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HttpResult get( String url, Map<String, Object> paramMap ) throws Exception {
		URIBuilder builder = new URIBuilder( url );
		if( !CollectionUtils.isEmpty( paramMap ) ) {
			for( Entry<String, Object> entry : paramMap.entrySet() ) {
				builder.addParameter( entry.getKey(), entry.getValue().toString() );
			}
		}
		return get( builder.build().toString() );
	}
	
	/**
	 * http post with param
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HttpResult post( String url, Map<String, Object> paramMap ) throws Exception {
		// 初始化请求
		HttpPost post = new HttpPost( url );
		// 设置连接属性
		post.setConfig( config );
		if( !CollectionUtils.isEmpty( paramMap ) ) {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for( Entry<String, Object> entry : paramMap.entrySet() ) {
				list.add( new BasicNameValuePair( entry.getKey(), entry.getValue().toString() ) );
			}
			// 构造form表单对象
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity( list, "UTF-8" );
			// 设置form表单属性
			post.setEntity( urlEncodedFormEntity );
		}
		// 发起请求
		CloseableHttpResponse response = httpClient.execute( post );
		return new HttpResult( response.getStatusLine().getStatusCode(), EntityUtils.toString( response.getEntity(), "UTF-8" ) );
	}
	
	/**
	 * http post
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResult post( String url ) throws Exception {
		return post( url, null );
	}
}
