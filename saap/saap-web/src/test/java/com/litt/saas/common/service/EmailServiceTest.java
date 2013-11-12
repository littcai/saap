package com.litt.saas.common.service;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.litt.core.test.BaseServiceTester;
import com.litt.saap.common.service.IEmailService;
import com.litt.saap.common.service.impl.EmailServiceImpl;

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
 * @since 2013-11-12
 * @version 1.0
 */
public class EmailServiceTest extends BaseServiceTester {
	
	@SpringBeanByType
	private IEmailService emailService;
	
	@Test
	public void sendHtml() throws Exception
	{
		emailService.sendMime("service@webestlife.com", "测试", "文本", "<b>html></b>");
	}

}
