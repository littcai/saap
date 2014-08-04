package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;

import com.litt.core.util.ArrayUtils;
import com.litt.core.util.StringUtils;

/**
 * 
 * Column.
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
public class Column implements Serializable {
	
	/** The name. */
	private String name;
	
	/** The alias. */
	private String alias;
	
	private String title;
	
	/** The data type. */
	private String dataType;
	
	private String format;
	
	private String custom;
	
	private boolean hide;
	
	
	
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
	 * @return the alias
	 */
	public String getAlias() {
		return StringUtils.isEmpty(alias)?name:alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

  
  /**
   * @return the format
   */
  public String getFormat()
  {
    return format;
  }

  
  /**
   * @param format the format to set
   */
  public void setFormat(String format)
  {
    this.format = format;
  }

  
  /**
   * @return the custom
   */
  public String getCustom()
  {
    return custom;
  }

  
  /**
   * @param custom the custom to set
   */
  public void setCustom(String custom)
  {
    this.custom = custom;
  }

  
  /**
   * @return the hide
   */
  public boolean isHide()
  {
    return hide;
  }

  
  /**
   * @param hide the hide to set
   */
  public void setHide(boolean hide)
  {
    this.hide = hide;
  }

  
  /**
   * @return the title
   */
  public String getTitle()
  {
    return title;
  }

  
  /**
   * @param title the title to set
   */
  public void setTitle(String title)
  {
    this.title = title;
  }
	

}
