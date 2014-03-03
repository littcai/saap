package com.litt.core.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnitBlockRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

import com.litt.core.common.BeanManager;

/** 
 * 
 * 测试基类.
 * 
 * <pre><b>描述：</b>
 *    通过基类初始化可重用的Spring上下文，减小每个测试类或测试方法重复初始化的额外开销
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-7-30
 * @version 1.0
 *
 */
@SpringApplicationContext("spring/applicationContext-*.xml")
@RunWith(UnitilsJUnitBlockRunner.class)
public class BaseServiceTester
{
	/**
	 * 获得由unitils构造的Spring应用上下文.
	 */
	@SpringApplicationContext
	private ApplicationContext application;
	
	//执行测试前先初始模拟对象  
    @Before  
    public void before() {       
        
        /*
         * 由于正常启动是通过InitSystemListener初始化的，而测试时通过unitils初始化，
         * 因此需要在unitils初始化后还要根据InitSystemListener需要初始化相关内容
         */
        BeanManager.setApplication(application);
    }
}
