package com.litt.saap.core.module.quickview.model.table;

import java.io.Serializable;

import com.litt.core.util.ArrayUtils;
import com.litt.core.util.StringUtils;


/**
 * The Class AbstractColumn.
 */
public class AbstractColumn implements Serializable {
	
	/** The name. */
	private String name;
	
	/** The title. */
	private String title;
	
	/** The format. */
	private String format;
	
	/** The converter. */
	private String converter;
	
	/** The data type. */
	private String dataType;
	
	/** The custom. */
	private String custom;
	
	/** The hide. */
	private boolean hide;	
	
	private int width;	
	
	private boolean htmlEnabled = true;	
	
	private boolean excelEnabled = true;	
	
	private boolean csvEnabled = true;	
	
	private boolean pdfEnabled = true;
	

	private Attribute[] attributes = new Attribute[0];
	
	/**
	 * Sets the attribute.
	 *
	 * @param attribute the new attribute
	 */
	public void setAttribute(Attribute attribute)
	{
		attributes = (Attribute[])ArrayUtils.add(attributes, attribute);
	}
	
	/**
	 * Gets the attributes output.
	 *
	 * @return the attributes output
	 */
	public String takeAttributesOutput()
	{
		StringBuffer sb = new StringBuffer(attributes.length * 10);
		for (Attribute attribute : attributes) {
			sb.append(attribute.getName())
				.append("=\"")
				.append(attribute.getValue())
				.append("\" ");
		}		
		return sb.toString();
	}
	
	/**
	 * 
	 *
	 * @return the checks if is custom
	 */
	public boolean getIsCustom()
	{
		return !StringUtils.isEmpty(custom);
	}
	
	/**
	 * 
	 *
	 * @return the column type
	 */
	public int getColumnType()
	{
		return 1;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the title to set.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width.
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the width to set.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Gets the data type.
	 *
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Sets the data type.
	 *
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public Attribute[] getAttributes() {
		return attributes;
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

	/**
	 * @return the custom
	 */
	public String getCustom() {
		return custom;
	}

	/**
	 * @param custom the custom to set
	 */
	public void setCustom(String custom) {
		this.custom = custom;
	}

	/**
	 * @return the converter
	 */
	public String getConverter() {
		return converter;
	}

	/**
	 * @param converter the converter to set
	 */
	public void setConverter(String converter) {
		this.converter = converter;
	}

}
