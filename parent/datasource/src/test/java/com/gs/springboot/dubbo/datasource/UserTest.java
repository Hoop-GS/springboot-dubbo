/*    
 * UserTest.java
 * 创建时间：2019年2月14日 下午5:30:14
 */ 
package com.gs.springboot.dubbo.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gs.springboot.dubbo.datasource.mapper.UserMapper;
import com.gs.springboot.dubbo.datasource.model.UserModel;

import lombok.extern.slf4j.Slf4j;

/**    
*	user mapper test class
*   @author - gs
*/
@RunWith( SpringRunner.class )
@SpringBootTest
@Slf4j
public class UserTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void getById() {
		try {
			if( null != userMapper ) {
				long id = 1;
				UserModel model = userMapper.getById( id );
				if( null != model ) {
					log.info( model.toString() );
				}else {
					log.info( "query result is null." );
				}
			}else {
				log.info( "userMapper is null, it has not been inject." );
			}
		} catch ( Exception e ) {
			log.error( "test func getById error.", e );
		}
	}
}
