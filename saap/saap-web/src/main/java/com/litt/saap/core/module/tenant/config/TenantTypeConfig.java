package com.litt.saap.core.module.tenant.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @since 2013-11-14
 * @version 1.0
 */
public class TenantTypeConfig {
	
	private Map<String, TenantDefConfig> tenantDefMap = new HashMap<String, TenantDefConfig>();
	
	private TenantDefConfig[] tenantDefList;

	/**
	 * 获得租户定义.
	 *
	 * @param code the code
	 * @return the tenant def
	 */
	public TenantDefConfig getTenantDef(String code)
	{
		return tenantDefMap.get(code);
	}
	
	/**
	 * @return the tenantDefMap
	 */
	public Map<String, TenantDefConfig> getTenantDefMap() {
		return tenantDefMap;
	}

	/**
	 * @param tenantDefMap the tenantDefMap to set
	 */
	public void setTenantDefMap(Map<String, TenantDefConfig> tenantDefMap) {
		this.tenantDefMap = tenantDefMap;
	}

  
  /**
   * @return the tenantDefList
   */
  public TenantDefConfig[] getTenantDefList()
  {
    return tenantDefList;
  }

  
  /**
   * @param tenantDefList the tenantDefList to set
   */
  public void setTenantDefList(TenantDefConfig[] tenantDefList)
  {
    this.tenantDefList = tenantDefList;
    for (TenantDefConfig tenantDefConfig : tenantDefList)
    {
      tenantDefMap.put(tenantDefConfig.getCode(), tenantDefConfig);
    }
  }
	
	
	

}
