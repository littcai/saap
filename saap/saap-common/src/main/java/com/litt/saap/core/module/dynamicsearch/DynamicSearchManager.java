package com.litt.saap.core.module.dynamicsearch;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.module.dynamicsearch.model.DynamicSearch;
import com.litt.saap.core.util.ConfigUtils;

/**
 * 
 * DynamicSearchManager.
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
 * @since 2014-7-2
 * @version 1.0
 */
public class DynamicSearchManager {

	public static final String CONF_MAPPING_FILE_PATH = "/module/dynamicsearch/dynamicsearch-conf-mapping.xml";
	
	public static String BASE_PATH = "dynamicsearch";
	
	private boolean isDebug = false;
	
	private Map<String, DynamicSearch> dynamicSearchMap = new HashMap<String, DynamicSearch>();
	
	public DynamicSearchManager()
	{
		init();
	}
	
	public void init()
	{
		File dir = new File(SaapConstants.HOME_PATH, BASE_PATH);
		if(dir.exists() && dir.isDirectory())
		{
			File[] files = dir.listFiles();
			for (File file : files) {
				String name = file.getName().substring(0, file.getName().length() - 4);
				DynamicSearch dynamicSearch = ConfigUtils.loadByCastor(DynamicSearch.class, CONF_MAPPING_FILE_PATH, file.getAbsolutePath());
				
				this.dynamicSearchMap.put(name, dynamicSearch);
			}
		}
					
	}	
	
	public void reload() {
		
		this.init();
	} 
	
	public DynamicSearch getDynamicSearch(String name)
	{
		if(isDebug)
			this.init();
		return dynamicSearchMap.get(name);
	}
	
	
	public static void main(String[] args) throws Exception {
		DynamicSearch dynamicSearch = DynamicSearchManager.getInstance().getDynamicSearch("assetInfo-dynamicsearch");
		System.out.println(dynamicSearch.toString());
	}
	
	private static class SingletonClassInstance { 
	    private static final DynamicSearchManager instance = new DynamicSearchManager(); 
	} 

	/**
	 * 
	 * @return
	 */
	public static DynamicSearchManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}
	
}
