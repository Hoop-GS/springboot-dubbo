/*    
 * EsTempService.java
 * 创建时间：2019年2月12日 下午3:25:41
 */ 
package com.gs.springboot.dubbo.elasticsearch.service;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gs.springboot.dubbo.elasticsearch.EsCommonQueryBuilder;
import com.gs.springboot.dubbo.elasticsearch.EsManage;
import com.gs.springboot.dubbo.elasticsearch.EsPage;
import com.gs.springboot.dubbo.elasticsearch.model.EsTempModel;
import com.gs.springboot.dubbo.elasticsearch.model.EsTempParam;

/**    
*	elasticsearch temp service class
*   @author - gs
*/
@Service
public class EsTempService {

	private static final String INDEX_NAME = "temp";
	
	private static final String TYPE_NAME = "info";
	
	private static final String ID_NAME = "id";
	
	private static final List<String> fieldAscList = Arrays.asList( "name" );
	
	private static final List<String> fieldDescList = Arrays.asList( "sex" );
	
	@Autowired
	private EsManage esManage;
	
	public boolean saveToIndex( EsTempModel esTempModel ) {
		String json = JSONObject.toJSONString( esTempModel );
		return esManage.save( INDEX_NAME, TYPE_NAME, ID_NAME, JSONObject.parseObject( json ) );
	}
	
	public boolean saveToIndex( List<EsTempModel> esTempModelList ) {
		String json = JSONObject.toJSONString( esTempModelList );
		return esManage.save( INDEX_NAME, TYPE_NAME, ID_NAME, JSONArray.parseArray( json ) );
	}
	
	public EsTempModel searchById( long id ) {
		String json = esManage.searchById( INDEX_NAME, TYPE_NAME, String.valueOf( id ) );
		return JSONObject.parseObject( json, EsTempModel.class );
	}
	
	public EsPage searchByPage( EsTempParam param ) {
		int pageNo = param.getStart() / param.getLength();
		int pageSize = param.getLength();
		EsPage page = esManage.searchByPage( INDEX_NAME, TYPE_NAME, generateQueryBuilderByParam( param ), fieldAscList, fieldDescList, null, pageSize, pageNo );
		return page;
	}
	
	public QueryBuilder generateQueryBuilderByParam( EsTempParam param ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must( EsCommonQueryBuilder.mustMatch( "name", param.getName() ) );
		queryBuilder.must( EsCommonQueryBuilder.mustTerm( "sex", param.getName() ) );
		return queryBuilder;
	}
	
	public HighlightBuilder generateHighlightBuilder( String tags, String fieldName ) {
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.preTags( String.format( "<%s>", tags ) );
		highlightBuilder.postTags( String.format( "</%s>", tags ) );
		highlightBuilder.field( fieldName );
		return highlightBuilder;
	}
}
