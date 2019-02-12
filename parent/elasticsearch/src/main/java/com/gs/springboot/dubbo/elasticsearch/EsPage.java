/*    
 * EsPage.java
 * 创建时间：2019年2月12日 下午2:41:22
 */ 
package com.gs.springboot.dubbo.elasticsearch;

/**    
*	elasicsearch query page class
*   @author - gs
*/
public class EsPage {

	private int pageNo;
	
	private int pageSize;
	
	private long total;
	
	private String json;
	
	public EsPage( int pageNo, int pageSize ) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo( int pageNo ) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize( int pageSize ) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal( long total ) {
		this.total = total;
	}

	public String getJson() {
		return json;
	}

	public void setJson( String json ) {
		this.json = json;
	}
	
}
