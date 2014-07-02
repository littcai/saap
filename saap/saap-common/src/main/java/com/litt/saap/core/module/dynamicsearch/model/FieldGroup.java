package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;
import java.util.List;

import com.litt.saap.core.module.dynamicform.model.Field;

/**
 * FieldGroup.
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
 * @since 2014-6-19
 * @version 1.0
 */
public class FieldGroup implements Serializable {
	
	private String name;
	
	private Table table;
	
	private boolean dynamic;
	
	private List<Field> fieldList;

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
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
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

	/**
	 * @return the dynamic
	 */
	public boolean isDynamic() {
		return dynamic;
	}

	/**
	 * @param dynamic the dynamic to set
	 */
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}


}
