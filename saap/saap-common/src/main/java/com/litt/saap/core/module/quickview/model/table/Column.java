package com.litt.saap.core.module.quickview.model.table;

import java.io.Serializable;

import com.litt.core.util.StringUtils;

/**
 * 
 * 表格列输出定义.
 * 
 * <pre><b>描述：</b>
 *    定义表格列的输出方式
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2014年12月24日
 * @version 1.0
 */
public class Column implements Serializable {
	
	/** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

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
	
	/** The width. */
	private String width;	
	
	/** 是否允许排序. */
  private boolean sortable;	
  
  /** 
   * 排序字段.
   * 对于非原生输入的列，需要指定排序字段
   */
  private String sortField;
	
	/**
	 * Gets the checks if is custom.
	 *
	 * @return the checks if is custom
	 */
	public boolean getIsCustom()
	{
		return !StringUtils.isEmpty(custom);
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
	public String getWidth()
	{
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the width to set.
	 */
	public void setWidth(String width)
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
	 * Checks if is hide.
	 *
	 * @return the hide
	 */
	public boolean isHide() {
		return hide;
	}

	/**
	 * Sets the hide.
	 *
	 * @param hide the hide to set
	 */
	public void setHide(boolean hide) {
		this.hide = hide;
	}

	/**
	 * Gets the custom.
	 *
	 * @return the custom
	 */
	public String getCustom() {
		return custom;
	}

	/**
	 * Sets the custom.
	 *
	 * @param custom the custom to set
	 */
	public void setCustom(String custom) {
		this.custom = custom;
	}

	/**
	 * Gets the converter.
	 *
	 * @return the converter
	 */
	public String getConverter() {
		return converter;
	}

	/**
	 * Sets the converter.
	 *
	 * @param converter the converter to set
	 */
	public void setConverter(String converter) {
		this.converter = converter;
	}

  
  /**
   * Checks if is sortable.
   *
   * @return the sortable
   */
  public boolean isSortable()
  {
    return sortable;
  }

  
  /**
   * Sets the sortable.
   *
   * @param sortable the sortable to set
   */
  public void setSortable(boolean sortable)
  {
    this.sortable = sortable;
  }

  
  /**
   * Gets the sort field.
   *
   * @return the sortField
   */
  public String getSortField()
  {
    return StringUtils.isEmpty(sortField)?name:sortField;
  }

  
  /**
   * Sets the sort field.
   *
   * @param sortField the sortField to set
   */
  public void setSortField(String sortField)
  {
    this.sortField = sortField;
  }

}
