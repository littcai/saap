package com.litt.saap.core.module.dynamicform.model;

/**
 * 
 * Field.
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
public class Field {
	
	public static final String DATE_TYPE_INT = "int";
	public static final String DATE_TYPE_LONG = "long";
	public static final String DATE_TYPE_DATE = "date";
	public static final String DATE_TYPE_DECIMAL = "decimal";
	public static final String DATE_TYPE_STRING = "string";
	public static final String DATE_TYPE_BOOLEAN = "boolean";
	
	private String key;
	
	private String displayName;
	
	private String descr;
	
	private String dataType;
	
	private String dataUnit;
	
	private String format;
	
	private FieldItems items;
	
	private boolean hide = false;
	
	private boolean editable = true;	

	private String handler;
	
	private Input input;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getDisplayKey()
	{
		return key+"DisplayName";
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the input
	 */
	public Input getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Input input) {
		this.input = input;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * @return the dataUnit
	 */
	public String getDataUnit() {
		return dataUnit;
	}

	/**
	 * @param dataUnit the dataUnit to set
	 */
	public void setDataUnit(String dataUnit) {
		this.dataUnit = dataUnit;
	}

	/**
	 * @return the handler
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	/**
	 * @return the items
	 */
	public FieldItems getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(FieldItems items) {
		this.items = items;
	}

	/**
	 * @return the hide
	 */
	public boolean isHide() {
		return hide;
	}

	/**
	 * @param hide the hide to set
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}

}
