package com.litt.saap.core.module.tenant.config;

import org.beanio.internal.config.GroupConfig;
import org.unitils.core.util.ResourceConfig;

import com.litt.saap.core.util.ConfigUtils;

/**
 * 租户配置管理器.
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
 * @since 2013-11-14
 * @version 1.0
 */
public class TenantConfigManager {
	
	public static final String CONF_MAPPING_FILE_PATH = "classpath:module/tenant-conf-mapping.xml";
	
	public static final String CONF_FILE_PATH = "classpath:module/tenant-conf.xml";
	
	private TenantConfig config;
	
	public TenantConfigManager()
	{
		init();
	}
	
	public void init()
	{
		//读取配置文件
		this.config = ConfigUtils.loadByCastor(TenantConfig.class, CONF_MAPPING_FILE_PATH, CONF_FILE_PATH);				
	}	
	
	public void reload() {
		this.config = ConfigUtils.loadByCastor(TenantConfig.class, CONF_MAPPING_FILE_PATH, CONF_FILE_PATH);
	} 
	
	public TenantDefConfig getTenantDefConfig(String code)
	{
		return config.getTenantDef(code);
	}
	
	
	public static void main(String[] args) throws Exception {
		TenantConfig config = TenantConfigManager.getInstance().getConfig();
		System.out.println(config.getTenantDef("basic"));
	}
	
	private static class SingletonClassInstance { 
	    private static final TenantConfigManager instance = new TenantConfigManager(); 
	} 

	/**
	 * 获得实例对象.
	 * @return
	 */
	public static TenantConfigManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}
	
	/**
	 * @return the config
	 */
	public TenantConfig getConfig() {
		return config;
	}
}
