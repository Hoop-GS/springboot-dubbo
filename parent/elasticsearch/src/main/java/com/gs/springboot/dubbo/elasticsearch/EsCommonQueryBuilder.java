/*    
 * EsCommonQueryBuilder.java
 * 创建时间：2019年2月12日 下午3:20:27
 */ 
package com.gs.springboot.dubbo.elasticsearch;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**    
*	elasticsearch common query builder class
*   @author - gs
*/
public class EsCommonQueryBuilder {
	
	public static BoolQueryBuilder mustTerm( String name, Object value ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( value ) ) {
			queryBuilder.must( QueryBuilders.termQuery( name, value ) );
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder mustMatch( String name, Object value ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( value ) ) {
			queryBuilder.must( QueryBuilders.matchQuery( name, value ) );
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder mustRange( String name, Object from, Object to ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( from ) ) {
			queryBuilder.must( QueryBuilders.rangeQuery( name ).gte( from ) );
		}
		if( check( to ) ) {
			queryBuilder.must( QueryBuilders.rangeQuery( name ).lte( to ) );
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder mustTerm( Map<String, Object> map ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( null != map && !map.isEmpty() ) {
			for( Entry<String, Object> entry : map.entrySet() ) {
				String name = entry.getKey();
				Object value = entry.getValue();
				queryBuilder.must( mustTerm( name, value ) );
			}
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder shouldTerm( String name, Object value ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( value ) ) {
			queryBuilder.should( QueryBuilders.termQuery( name, value ) );
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder shouldMatch( String name, Object value ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( value ) ) {
			queryBuilder.should( QueryBuilders.matchQuery( name, value ) );
		}
		return queryBuilder;
	}
	
	public static BoolQueryBuilder shouldRange( String name, Object from, Object to ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( from ) ) {
			queryBuilder.should( QueryBuilders.rangeQuery( name ).gte( from ) );
		}
		if( check( to ) ) {
			queryBuilder.should( QueryBuilders.rangeQuery( name ).gte( to ) );
		}
		return queryBuilder;
	}
	
	/**
	 * 不分词，模糊查询
	 * @param name
	 * @param value
	 * @return
	 */
	public static BoolQueryBuilder mustWildcard( String name, Object value ) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if( check( value ) ) {
			queryBuilder.must( QueryBuilders.wildcardQuery( name, String.format( "*%s*", String.valueOf( value ) ) ) );
		}
		return queryBuilder;
	}

	private static boolean check( Object obj ) {
		if( obj instanceof Integer ) {
			return gtZero( ( Integer )obj );
		}
		if( obj instanceof Long ) {
			return gtZero( ( Long )obj );
		}
		if( obj instanceof Date ) {
			return null != obj;
		}
		if( obj instanceof String ) {
			return StringUtils.isNotEmpty( obj.toString() );
		}
		return false;
	}

	private static boolean gtZero( Integer i ) {
		return null != i && 0 <= i;
	}

	private static boolean gtZero( Long l ) {
		return null != l && 0l <= l;
	}
}
