package com.litt.saap.core.module.quickview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.common.CoreConstants;
import com.litt.core.util.ResourceUtils;
import com.litt.core.util.StringUtils;
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
	
	public static final Logger logger = LoggerFactory.getLogger(QuickViewManager.class);

	public static final String CONF_MAPPING_FILE_PATH = "/module/quickview/quickview-conf-mapping.xml";
	
	public static String BASE_PATH = "quickview";	
	
	private Map<String, QuickView> quickViewMap = new HashMap<String, QuickView>();
	
	public QuickViewManager()
	{
		init();
	}
	
	public void init()
	{
		logger.info("Init QuickView module...");
		//读取系统内置的
		{
			try {
				File dir = ResourceUtils.getFile("classpath:module/quickview");
				loadConfig(dir);
			} catch (FileNotFoundException e) {
				logger.error("Load quickview config files from classpath failed.", e);
			}
		}
		
		//读取生产环境中保存的，相同的将覆盖系统内置的
		{
			File dir = new File(SaapConstants.HOME_PATH, BASE_PATH);
			loadConfig(dir);
		}			
	}

	private void loadConfig(File dir) {
		if(dir.exists() && dir.isDirectory())
		{
			File[] files = dir.listFiles();
			for (File file : files) {
				if(StringUtils.equals(file.getName(), "quickview-conf-mapping.xml"))
					continue;
				logger.debug("load quickview config file:{}", new Object[]{file.getName()});
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
		QuickView quickView = QuickViewManager.getInstance().getQuickView("product-quickview");
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
