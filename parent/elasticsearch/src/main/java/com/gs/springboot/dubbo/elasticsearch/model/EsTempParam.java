/*    
 * EsTempParam.java
 * 创建时间：2019年2月12日 下午3:40:19
 */ 
package com.gs.springboot.dubbo.elasticsearch.model;

/**    
*	elasticsearch temp param class
*   @author - gs
*/
public class EsTempParam {

	private String name;
	
	private String sex;
	
	private Integer start;
	
	private Integer length;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex( String sex ) {
		this.sex = sex;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart( Integer start ) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength( Integer length ) {
		this.length = length;
	}
	
}
