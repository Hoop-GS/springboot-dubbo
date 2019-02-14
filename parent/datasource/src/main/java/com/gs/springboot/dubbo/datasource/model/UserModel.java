/*    
 * UserModel.java
 * 创建时间：2019年2月14日 下午5:16:23
 */ 
package com.gs.springboot.dubbo.datasource.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**    
*	t_user table model class
*   @author - gs
*/
public class UserModel implements Serializable {

	private static final long serialVersionUID = -275982818392485083L;

	private long id;
	
	private String name;
	
	private String sex;
	
	private int age;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
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

	public int getAge() {
		return age;
	}

	public void setAge( int age ) {
		this.age = age;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.SHORT_PREFIX_STYLE );
	}
	
}
