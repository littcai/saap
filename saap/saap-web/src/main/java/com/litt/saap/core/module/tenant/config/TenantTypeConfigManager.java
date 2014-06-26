package com.litt.saap.core.module.tenant.config;

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
public class TenantTypeConfigManager {
	
	public static final String CONF_MAPPING_FILE_PATH = "/module/tenant-type-conf-mapping.xml";
	
	public static final String CONF_FILE_PATH = "classpath:module/tenant-type-conf.xml";
	
	private TenantTypeConfig config;
	
	public TenantTypeConfigManager()
	{
		init();
	}
	
	public void init()
	{
		//读取配置文件
		this.config = ConfigUtils.loadByCastor(TenantTypeConfig.class, CONF_MAPPING_FILE_PATH, CONF_FILE_PATH);				
	}	
	
	public void reload() {
		
		this.config = ConfigUtils.loadByCastor(TenantTypeConfig.class, CONF_MAPPING_FILE_PATH, CONF_FILE_PATH);
	} 
	
	public TenantDefConfig getTenantDefConfig(String code)
	{
		return config.getTenantDef(code);
	}
	
	
	public static void main(String[] args) throws Exception {
		TenantTypeConfig config = TenantTypeConfigManager.getInstance().getConfig();
		System.out.println(config.getTenantDef("basic"));
	}
	
	private static class SingletonClassInstance { 
	    private static final TenantTypeConfigManager instance = new TenantTypeConfigManager(); 
	} 

	/**
	 * 获得实例对象.
	 * @return
	 */
	public static TenantTypeConfigManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}
	
	/**
	 * @return the config
	 */
	public TenantTypeConfig getConfig() {
		return config;
	}
}
