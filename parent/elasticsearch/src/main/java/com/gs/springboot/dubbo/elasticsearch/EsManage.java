/*    
 * EsManage.java
 * 创建时间：2019年2月12日 下午2:46:46
 */ 
package com.gs.springboot.dubbo.elasticsearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**    
*	elasticsearch common manage class
*   @author - gs
*/
@Component
@Slf4j
public class EsManage {

	@Autowired
	private Client client;
	
	/**
	 * save multi elasticsearch document by json list
	 * @param index
	 * @param type
	 * @param idName
	 * @param listData
	 * @return
	 */
	public boolean save( String index, String type, String idName, JSONArray listData ) {
		if( listData.isEmpty() ) {
			return false;
		}
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for( Object data : listData ) {
			if( null == data ) {
				continue;
			}
			JSONObject json = JSONObject.parseObject( data.toString() );
			if( StringUtils.isEmpty( idName ) ) {
				IndexRequestBuilder request = client.prepareIndex( index, type ).setSource( json );
				bulkRequest.add( request );
			}else {
				String idValue = json.getString( idName );
				IndexRequestBuilder request = client.prepareIndex( index, type, idValue ).setSource( json );
				bulkRequest.add( request );
			}
		}
		BulkResponse bulkResponse = bulkRequest.get();
		if( bulkResponse.hasFailures() ) {
			Iterator<BulkItemResponse> iter = bulkResponse.iterator();
			while( iter.hasNext() ) {
				BulkItemResponse item = iter.next();
				if( item.isFailed() ) {
					log.error( "save elasticsearch document error.", item.getFailureMessage() );
				}
			}
		}
		return true;
	}
	
	/**
	 * save elasticsearch document by json
	 * @param index
	 * @param type
	 * @param idName
	 * @param json
	 * @return
	 */
	public boolean save( String index, String type, String idName, JSONObject json ) {
		if( null == json ) {
			return false;
		}
		JSONArray arr = new JSONArray();
		arr.add( json );
		return save( index, type, idName, arr );
	}
	
	/**
	 * delete elasticsearch document by document's id
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean deleteById( String index, String type, String id ) {
		if( StringUtils.isEmpty( index ) || StringUtils.isEmpty( type ) || StringUtils.isEmpty( id ) ) {
			return false;
		}
		DeleteRequestBuilder request = client.prepareDelete( index, type, id );
		DeleteResponse response = request.get();
		return response.status() == RestStatus.OK;
	}
	
	/**
	 * search elasticsearch document by page
	 * @param index
	 * @param type
	 * @param queryBuilder
	 * @param fieldAscList
	 * @param fieldDescList
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public EsPage searchByPage( String index, String type, QueryBuilder queryBuilder, List<String> fieldAscList, List<String> fieldDescList, 
			HighlightBuilder highlightBuilder, int pageSize, int pageNo ) {
		EsPage page = new EsPage( pageNo, pageSize );
		if( StringUtils.isEmpty( index ) || StringUtils.isEmpty( type ) ) {
			return page;
		}
		SearchRequestBuilder searchReq = client.prepareSearch( index )
				.setTypes( type )
				.setQuery( queryBuilder );
		if( !CollectionUtils.isEmpty( fieldAscList ) ) {
			for( String field : fieldAscList ) {
				searchReq.addSort( field, SortOrder.ASC );
			}
		}
		if( !CollectionUtils.isEmpty( fieldDescList ) ) {
			for( String field : fieldDescList ) {
				searchReq.addSort( field, SortOrder.DESC );
			}
		}
		if( null != highlightBuilder ) {
			searchReq.highlighter( highlightBuilder );
		}
		SearchHits hits = searchReq.setFrom( pageNo * pageSize ).setSize( pageSize ).get().getHits();
		page.setTotal( hits.getTotalHits() );
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for( SearchHit searchHit : hits ) {
			list.add( searchHit.getSource() );
		}
		page.setJson( JSONObject.toJSONString( list ) );
		return page;
	}
	
	/**
	 * search elasticsearch document by id
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public String searchById( String index, String type, String id ) {
		GetResponse resp = client.prepareGet( index, type, id ).get();
		if( !resp.isSourceEmpty() ) {
			return JSONObject.toJSONString( resp.getSource() );
		}
		return null;
	}
}
