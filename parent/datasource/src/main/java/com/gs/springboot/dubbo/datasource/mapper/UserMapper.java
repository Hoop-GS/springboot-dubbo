/*    
 * UserMapper.java
 * 创建时间：2019年2月14日 下午5:15:24
 */ 
package com.gs.springboot.dubbo.datasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gs.springboot.dubbo.datasource.model.UserModel;

/**    
*	t_user table mapper interfacle class
*   @author - gs
*/
@Mapper
public interface UserMapper {

	UserModel getById( @Param( "id" ) long id );
	
	UserModel getByName( @Param( "name" ) String name );
	
	int addUser( @Param( "model" ) UserModel model );
}
