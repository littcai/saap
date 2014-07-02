package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DynamicSearch.
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
 * @since 2014-6-13
 * @version 1.0
 */
public class DynamicSearch implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private List<FieldGroup> fieldGroupList = new ArrayList<FieldGroup>();

	
	/**
	 * Gets the base field group.
	 *
	 * @return the base field group
	 */
	public FieldGroup getBaseFieldGroup()
	{
		return fieldGroupList.get(0);
	}
	
	/**
	 * @return the fieldGroupList
	 */
	public List<FieldGroup> getFieldGroupList() {
		return fieldGroupList;
	}

	/**
	 * @param fieldGroupList the fieldGroupList to set
	 */
	public void setFieldGroupList(List<FieldGroup> fieldGroupList) {
		this.fieldGroupList = fieldGroupList;
	}
}
