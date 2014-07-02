package com.litt.saap.core.module.dynamicform.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * DynamicForm.
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
public class DynamicForm {
	
	private List<Fieldset> fieldsetList = new ArrayList<Fieldset>();
	
	/**
	 * Gets the all field names.
	 *
	 * @return the all field names
	 */
	public String[] getAllFieldNames()
	{
		List<String> fieldNameList = new ArrayList<String>();
		for (Fieldset fieldset : fieldsetList) 
		{
			List<Field> fieldList =  fieldset.getFieldList();
			for (Field field : fieldList) {
				
				String fieldKey = field.getKey();
				fieldNameList.add(fieldKey);
			}
		}	
		return fieldNameList.toArray(new String[0]);
	}

	/**
	 * @return the fieldsetList
	 */
	public List<Fieldset> getFieldsetList() {
		return fieldsetList;
	}

	/**
	 * @param fieldsetList the fieldsetList to set
	 */
	public void setFieldsetList(List<Fieldset> fieldsetList) {
		this.fieldsetList = fieldsetList;
	}

}
