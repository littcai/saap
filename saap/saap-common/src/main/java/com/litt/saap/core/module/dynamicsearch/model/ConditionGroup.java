package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;

import com.litt.core.util.ArrayUtils;

/**
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
 * @since 2014-06-13
 * @version 1.0
 */
public class ConditionGroup implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public static final String DATE_TYPE_INT = "int";
	public static final String DATE_TYPE_LONG = "long";
	public static final String DATE_TYPE_DATE = "date";
	public static final String DATE_TYPE_DECIMAL = "decimal";
	public static final String DATE_TYPE_STRING = "string";
	public static final String DATE_TYPE_BOOLEAN = "boolean";

	private String fieldName = DATE_TYPE_STRING;
	
	private String dataType;
	
	private Condition<?>[] conditions = new Condition<?>[0];
	
	public ConditionGroup() {}
	
	/**
	 * @param fieldName
	 */
	public ConditionGroup(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * Instantiates a new condition group.
	 *
	 * @param fieldName the field name
	 * @param dataType the data type
	 */
	public ConditionGroup(String fieldName, String dataType) {
		this.fieldName = fieldName;
		this.dataType = dataType;
	}

	public void addCondition(Condition<?> condition)
	{
		conditions = (Condition<?>[])ArrayUtils.add(conditions, condition);
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the conditions
	 */
	public Condition<?>[] getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(Condition<?>[] conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
