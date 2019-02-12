/*    
 * EsTempModel.java
 * 创建时间：2019年2月12日 下午3:23:44
 */ 
package com.gs.springboot.dubbo.elasticsearch.model;

import java.io.Serializable;

/**    
*	elasticsearch temp model class
*   @author - gs
*/
public class EsTempModel implements Serializable {

	private static final long serialVersionUID = 360782153609791753L;

	private Long id;
	
	private String name;
	
	private String sex;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

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
	
}
