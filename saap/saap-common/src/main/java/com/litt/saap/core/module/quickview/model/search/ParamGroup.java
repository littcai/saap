package com.litt.saap.core.module.quickview.model.search;

import java.util.ArrayList;
import java.util.List;

import com.litt.saap.core.module.dynamicform.model.Field;

/**
 * 
 * ParamGroup.
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
public class ParamGroup
{
  
	List<Field> fieldList = new ArrayList<Field>();
	
	/**
	 * Adds the field.
	 *
	 * @param param the param
	 * @return the param group
	 */
	public ParamGroup addField(Field param)
	{
		fieldList.add(param);
		return this;
	}	
	
	/**
	 * Adds the field list.
	 *
	 * @param fieldList the field list
	 * @return the param group
	 */
	public ParamGroup addFieldList(List<Field> fieldList)
	{
		this.fieldList.addAll(fieldList);
		return this;
	}

	/**
	 * @return the paramList.
	 */
	public List<Field> getFieldList()
	{
		return fieldList;
	}	
}
