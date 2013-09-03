package com.litt.saap.system.service;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.litt.core.test.BaseServiceTester;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-2
 * @version 1.0
 */
public class UserInfoServiceTest extends BaseServiceTester {
	
	@SpringBeanByType
	private IUserInfoService userInfoService;
	
	@Test
	public void simple()
	{
		Assert.assertEquals("a", "b");
	}
	

}
