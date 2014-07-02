package com.litt.saap.core.module.dynamicform.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Fieldset.
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
public class Fieldset {
	
	private String name;
	
	private String title;
	
	private List<Field> fieldList = new ArrayList<Field>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the fieldList
	 */
	public List<Field> getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList the fieldList to set
	 */
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

}
