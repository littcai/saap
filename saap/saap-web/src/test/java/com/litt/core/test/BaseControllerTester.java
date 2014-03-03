package com.litt.core.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.litt.core.common.BeanManager;

/** 
 * 
 * Controller层测试基类.
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
@SpringApplicationContext({"spring/applicationContext-*.xml", "file:src/main/webapp/WEB-INF/applicationContext-*.xml"})
public class BaseControllerTester extends UnitilsJUnit4
{
	/**
	 * 获得由unitils构造的Spring应用上下文.
	 */
	@SpringApplicationContext
	private ApplicationContext application;
	
	//从Spring容器中加载AnnotationMethodHandlerAdapter  
    @SpringBeanByType  
    private AnnotationMethodHandlerAdapter handlerAdapter;  	
 
	// 声明Request与Response模拟对象  
	private MockHttpServletRequest request;  
	private MockHttpServletResponse response; 
	
	//执行测试前先初始模拟对象  
	@Before  
    public void before() {  
        request = new MockHttpServletRequest();  
        request.setCharacterEncoding("UTF-8");  
        response = new MockHttpServletResponse();  
        
        /*
         * 由于正常启动是通过InitSystemListener初始化的，而测试时通过unitils初始化，
         * 因此需要在unitils初始化后还要根据InitSystemListener需要初始化相关内容
         */
        BeanManager.setApplication(application);
    }
    
    /**
     * 向控制发起请求.
     *
     * @param controller the controller
     * @return the model and view
     * @throws Exception the exception
     */
    public ModelAndView handle(Object controller) throws Exception
    {
    	return handlerAdapter.handle(request, response, controller); 
    }

	/**
	 * @return the handlerAdapter.
	 */
	public AnnotationMethodHandlerAdapter getHandlerAdapter()
	{
		return handlerAdapter;
	}

	/**
	 * @return the request.
	 */
	public MockHttpServletRequest getRequest()
	{
		return request;
	}

	/**
	 * @return the response.
	 */
	public MockHttpServletResponse getResponse()
	{
		return response;
	}  
}
