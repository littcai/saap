package com.litt.saap.core.module.dynamicform;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.litt.core.common.CoreConstants;
import com.litt.core.util.ArrayUtils;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.module.dynamicform.model.DynamicForm;
import com.litt.saap.core.module.dynamicform.model.Field;
import com.litt.saap.core.module.dynamicform.model.Fieldset;
import com.litt.saap.core.util.ConfigUtils;

/**
 * 
 * DynamicFormManager.
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
public class DynamicFormManager {

	public static final String CONF_MAPPING_FILE_PATH = "/module/dynamicform/dynamicform-conf-mapping.xml";
	
	public static String BASE_PATH = "dynamicform";	
	
	private String[] names = new String[0];
	private Map<String, DynamicForm> dynamicFormMap = new HashMap<String, DynamicForm>();
	
	public DynamicFormManager()
	{
		init();
	}
	
	public void init()
	{
		File dir = new File(SaapConstants.HOME_PATH, BASE_PATH);
		if(dir.exists() && dir.isDirectory())
		{
			File[] files = dir.listFiles();
			names = new String[files.length];
			int i=0;
			for (File file : files) {
				String name = file.getName().substring(0, file.getName().length() - 4);
				DynamicForm dynamicForm = ConfigUtils.loadByCastor(DynamicForm.class, CONF_MAPPING_FILE_PATH, file.getAbsolutePath());
				
				names[i] = name;
				this.dynamicFormMap.put(name, dynamicForm);
				i++;
			}
		}			
	}	
	
	public void reload() {
		
		this.init();
	} 
	
	public DynamicForm getDynamicForm(String name)
	{
		if(CoreConstants.IS_DEBUG && names.length==0)
		{
			this.init();
		}	
		else 
		{
		  File dir = new File(SaapConstants.HOME_PATH, BASE_PATH);
			File file = new File(dir, name+".xml");
			DynamicForm dynamicForm = ConfigUtils.loadByCastor(DynamicForm.class, CONF_MAPPING_FILE_PATH, file.getAbsolutePath());
			if(!ArrayUtils.contains(names, name))
			{
				names = (String[])ArrayUtils.add(names, name);
			}
			this.dynamicFormMap.put(name, dynamicForm);
		}
		return dynamicFormMap.get(name);
	}
	
	/**
	 * 
	 *
	 * @return the all fields
	 */
	public List<Field> getAllFields()
	{
		List<Field> retList = new ArrayList<Field>();
		Map<String, Field> tmpMap = new HashMap<String, Field>();
		for (String name : names) {
			DynamicForm dynamicForm = dynamicFormMap.get(name);
			List<Fieldset> fieldsetList = dynamicForm.getFieldsetList();
			for (Fieldset fieldset : fieldsetList) {
				List<Field> fieldList = fieldset.getFieldList();
				for (Field field : fieldList) {
					if(!tmpMap.containsKey(field.getKey()))
					{
						tmpMap.put(field.getKey(), field);
						retList.add(field);
					}
				}
			}
		}
		return retList;
	}
	
	
	public static void main(String[] args) throws Exception {
		DynamicForm dynamicForm = DynamicFormManager.getInstance().getDynamicForm("default");
		System.out.println(dynamicForm.toString());
	}
	
	private static class SingletonClassInstance { 
	    private static final DynamicFormManager instance = new DynamicFormManager(); 
	} 

	/**
	 * 
	 * @return
	 */
	public static DynamicFormManager getInstance() { 
	    return SingletonClassInstance.instance; 
	}
	
}
