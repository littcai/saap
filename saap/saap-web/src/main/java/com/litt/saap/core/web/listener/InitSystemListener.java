package com.litt.saap.core.web.listener;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.common.BeanManager;
import com.litt.core.common.CoreConstants;
import com.litt.core.license.LicenseException;
import com.litt.core.license.LicenseManager;
import com.litt.core.util.PropertiesUtils;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.system.service.ISystemInfoService;
import com.litt.saap.system.vo.SystemInfoVo;

/**
 * 系统初始化监听器.
 * 
 * <pre><b>描述：</b>
 *    在系统初始化时执行一些附加业务的初始化.
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
public class InitSystemListener extends
		com.litt.core.web.listener.InitSystemListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(InitSystemListener.class);

	/**
	 * 容器初始化.
	 */
	public void contextInitialized(ServletContextEvent event)
	{	
		super.contextInitialized(event);
		ServletContext application = event.getServletContext();
		//HOME初始化
		String homePath;
		try
		{			
			Properties props = PropertiesUtils.loadProperties("init.properties", PropertiesUtils.BY_CLASSLOADER);
			homePath = props.getProperty("home.path");
			SaapConstants.HOME_PATH = homePath;		//保存到静态常量，方便使用
			logger.info("系统数据存储路径："+homePath);
			File homeFile = new File(homePath);			
			if(!homeFile.exists())	//如果目录不存在则创建，并且复制配置文件到该目录
			{
				homeFile.mkdirs();					
			}			
		}
		catch (IOException e)
		{
			logger.error("初始化配置文件未找到！",e);
			throw new java.lang.RuntimeException("初始化配置文件未找到！",e);
		}
		
		//read license.
		File licensePath = new File(homePath, "license");
		File licenseFile = new File(licensePath, "license.xml");
		File publicKeyFile = new File(licensePath, "license.key");
		try {
			LicenseManager.reload(licenseFile.getPath(), publicKeyFile.getPath());
		} catch (LicenseException e) {
			logger.error("Can't read license file.", e);
		}
		
		//读取系统配置信息，存入APPLICATION
		ISystemInfoService systemInfoService = BeanManager.getBean("systemInfoService", ISystemInfoService.class);
		SystemInfoVo systemInfoVo = systemInfoService.getSystemInfo();		
		application.setAttribute(CoreConstants.APP_SYSTEMINFO, systemInfoVo);	//缓存到servlet容器供页面使用
	}
	
}
