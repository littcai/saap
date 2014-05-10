package com.litt.saap.system.vo;

import java.io.Serializable;

/** 
 * 
 * 参数字段VO对象.
 * 
 * <pre><b>描述：</b>
 *    参数字段VO对象 
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-7-22
 * @version 1.0
 *
 */
public class DictParamVo implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String dictType;

	private String dictValue;

	private String dictContent;
	
	private String filter;
	
	private String params;
	
	private Integer status;

	private Integer position;

	private String remark;

	/**
	 * @return the id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the dictType.
	 */
	public String getDictType()
	{
		return dictType;
	}

	/**
	 * @param dictType the dictType to set.
	 */
	public void setDictType(String dictType)
	{
		this.dictType = dictType;
	}

	/**
	 * @return the dictValue.
	 */
	public String getDictValue()
	{
		return dictValue;
	}

	/**
	 * @param dictValue the dictValue to set.
	 */
	public void setDictValue(String dictValue)
	{
		this.dictValue = dictValue;
	}

	/**
	 * @return the dictContent.
	 */
	public String getDictContent()
	{
		return dictContent;
	}

	/**
	 * @param dictContent the dictContent to set.
	 */
	public void setDictContent(String dictContent)
	{
		this.dictContent = dictContent;
	}

	/**
	 * @return the status.
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set.
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	/**
	 * @return the position.
	 */
	public Integer getPosition()
	{
		return position;
	}

	/**
	 * @param position the position to set.
	 */
	public void setPosition(Integer position)
	{
		this.position = position;
	}

	/**
	 * @return the remark.
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * @param remark the remark to set.
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}
}
