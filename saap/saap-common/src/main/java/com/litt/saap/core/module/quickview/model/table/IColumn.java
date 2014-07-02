package com.litt.saap.core.module.quickview.model.table;

public interface IColumn
{
	/**
	 * Sets the attribute.
	 *
	 * @param attribute the new attribute
	 */
	public void setAttribute(Attribute attribute);
	
	/**
	 * @return the attributes
	 */
	public Attribute[] getAttributes();
	
	/**
	 * 
	 * @return
	 */
	public int getColumnType();	

	/**
	 * @return the name.
	 */
	public String getName();

	/**
	 * @param name the name to set.
	 */
	public void setName(String name);

	/**
	 * @return the title.
	 */
	public String getTitle();

	/**
	 * @param title the title to set.
	 */
	public void setTitle(String title);
	
	/**
	 * @return the width.
	 */
	public int getWidth();
	
	/**
	 * @param width the width to set.
	 */
	public void setWidth(int width);
	
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType);
	
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format);
	
	/**
	 * @return the hide
	 */
	public boolean isHide() ;

}