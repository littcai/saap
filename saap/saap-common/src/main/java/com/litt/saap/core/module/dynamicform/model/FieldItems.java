
package com.litt.saap.core.module.dynamicform.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * FieldItems.
 * 
 * <pre><b>Descr:</b>
 *    
 * </pre>
 * 
 * <pre><b>Changelog:</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Caiyuan</a>
 * @since 2014年7月2日
 * @version 1.0
 */
public class FieldItems {
	
	public static final String PROVIDER_STATIC = "static";
	public static final String PROVIDER_DICTPARAM = "dictparam";
	public static final String PROVIDER_SQL = "sql";
	public static final String PROVIDER_USER = "user";	
	
	private String provider;
	
	private String filter;
	
	private List<FieldItem> itemList = new ArrayList<FieldItem>();

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return the itemList
	 */
	public List<FieldItem> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<FieldItem> itemList) {
		this.itemList = itemList;
	}

  
  /**
   * @return the filter
   */
  public String getFilter()
  {
    return filter;
  }

  
  /**
   * @param filter the filter to set
   */
  public void setFilter(String filter)
  {
    this.filter = filter;
  }

}
