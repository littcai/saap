package com.litt.saap.core.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.common.BeanManager;
import com.litt.core.common.ConfigManager;
import com.litt.core.common.CoreConstants;
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
 *    2014-07-02 通过ConfigManager统一读取，classpath下至少要有config.xml, init.properties, system-config.xml
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
		Configuration config = ConfigManager.getInstance().getConfig();
		if(config.isEmpty())
		{
			logger.error("初始化配置文件未找到！");
			throw new java.lang.RuntimeException("初始化配置文件未找到！");
		}
		//homePath = props.getProperty("home.path");
		String homePath = config.getString("home.path");
		CoreConstants.IS_DEBUG = config.getBoolean("debug", false);
		
		SaapConstants.HOME_PATH = homePath;		//保存到静态常量，方便使用
		logger.info("系统数据存储路径："+homePath);
		File homeFile = new File(homePath);			
		if(!homeFile.exists())	//如果目录不存在则创建，并且复制配置文件到该目录
		{
			homeFile.mkdirs();					
		}			
		
		
		//read license.
//		File licensePath = new File(homePath, "license");
//		File licenseFile = new File(licensePath, "license.xml");
//		File publicKeyFile = new File(licensePath, "license.key");
//		try {
//			LicenseManager.reload(licenseFile.getPath(), publicKeyFile.getPath());
//		} catch (LicenseException e) {
//			logger.error("Can't read license file.", e);
//		}
		
		String contextPath = application.getContextPath();
		//读取系统配置信息，存入APPLICATION
		ISystemInfoService systemInfoService = BeanManager.getBean("systemInfoService", ISystemInfoService.class);
		SystemInfoVo systemInfoVo = systemInfoService.getSystemInfo();	
		systemInfoVo.setBaseUrl(config.getString("baseUrl")+contextPath);
		systemInfoVo.setHomePath(homePath);
		application.setAttribute(CoreConstants.APP_SYSTEMINFO, systemInfoVo);	//缓存到servlet容器供页面使用
	}
	
}
