package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Table.
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
public class Table implements Serializable {

	private String name;
	
	private String joinType = "inner join";
	
	private String joinOn;
	
	private String where;
	
	private List<Column> columnList = new ArrayList<Column>();

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
	 * @return the joinType
	 */
	public String getJoinType() {
		return joinType;
	}

	/**
	 * @param joinType the joinType to set
	 */
	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	/**
	 * @return the joinOn
	 */
	public String getJoinOn() {
		return joinOn;
	}

	/**
	 * @param joinOn the joinOn to set
	 */
	public void setJoinOn(String joinOn) {
		this.joinOn = joinOn;
	}

	/**
	 * @return the where
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * @param where the where to set
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * @return the columnList
	 */
	public List<Column> getColumnList() {
		return columnList;
	}

	/**
	 * @param columnList the columnList to set
	 */
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
			
	
}
