package com.litt.saap.core.module.quickview;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.litt.core.common.CoreConstants;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.module.quickview.model.QuickView;
import com.litt.saap.core.util.ConfigUtils;

/**
 * 
 * QuickViewManager.
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
public class QuickViewManager {

	public static final String CONF_MAPPING_FILE_PATH = "/module/quickview/quickview-conf-mapping.xml";
	
	public static String BASE_PATH = "quickview";	
	
	private Map<String, QuickView> quickViewMap = new HashMap<String, QuickView>();
	
	public QuickViewManager()
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
				QuickView quickView = ConfigUtils.loadByCastor(QuickView.class, CONF_MAPPING_FILE_PATH, file.getAbsolutePath());
				
				this.quickViewMap.put(name, quickView);
			}
		}
					
	}	
	
	public void reload() {
		
		this.init();
	} 
	
	public QuickView getQuickView(String name)
	{
		if(CoreConstants.IS_DEBUG)
			this.init();
		return quickViewMap.get(name);
	}
	
	
	public static void main(String[] args) throws Exception {
		QuickView quickView = QuickViewManager.getInstance().getQuickView("assetInfo-quickview");
		System.out.println(quickView.toString());
		System.out.println(quickView.getTable().getTitle());
	}
	
	private static class SingletonClassInstance { 
	    private static final QuickViewManager instance = new QuickViewManager(); 
	} 

	/**
	 * Gets the single instance of QuickViewManager.
	 *
	 * @return single instance of QuickViewManager
	 */
	public static QuickViewManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}
	
}
