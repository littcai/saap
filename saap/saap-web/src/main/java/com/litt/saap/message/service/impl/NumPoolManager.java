package com.litt.saap.message.service.impl;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.litt.core.util.ResourceUtils;
import com.litt.core.util.StringUtils;

/**
 * 号码段管理.
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
 * @since 2013-12-16
 * @version 1.0
 */
public class NumPoolManager {
	
	public static String[] UNICOM_POOL = new String[0];  	//联通号码段
	public static String[] TELECOM_POOL = new String[0];  	//电信号码段
	public static String[] MOBILE_POOL = new String[0];  	//移动号码段	
	
	public static String UNICOM_ACCOUNT = "";
	public static String TELECOM_ACCOUNT = "";
	public static String MOBILE_ACCOUNT = "";
	
	static
	{
		try {
			Configuration config = new PropertiesConfiguration(ResourceUtils.getFile("classpath:init.properties"));		
			UNICOM_POOL = config.getStringArray("unicom");
			TELECOM_POOL = config.getStringArray("telecom");
			MOBILE_POOL = config.getStringArray("mobile");
			
			UNICOM_ACCOUNT = config.getString("unicom.account");
			TELECOM_ACCOUNT = config.getString("telecom.account");
			MOBILE_ACCOUNT = config.getString("mobile.account");
		} catch (Exception e) {
			throw new RuntimeException("Can't init num pool", e);
		}
	}
	
	public static boolean isUnicom(String mobile)
	{
		for (String prefix : UNICOM_POOL) {
			if(StringUtils.startsWith(mobile, prefix))
				return true;
		}
		return false;
	}
	
	public static boolean isTelecom(String mobile)
	{
		for (String prefix : TELECOM_POOL) {
			if(StringUtils.startsWith(mobile, prefix))
				return true;
		}
		return false;
	}
	
	public static boolean isMobile(String mobile)
	{
		for (String prefix : MOBILE_POOL) {
			if(StringUtils.startsWith(mobile, prefix))
				return true;
		}
		return false;
	}
	
	
	private static class SingletonClassInstance { 
	    private static final NumPoolManager instance = new NumPoolManager(); 
	} 

	/**
	 * 获得实例对象.
	 * @return
	 */
	public static NumPoolManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(NumPoolManager.UNICOM_POOL.length);
		System.out.println(NumPoolManager.TELECOM_POOL.length);
		System.out.println(NumPoolManager.MOBILE_POOL.length);
		
		System.out.println(NumPoolManager.isMobile("13818143407"));
	}

}
