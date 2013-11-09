package com.litt.saap.system.vo;


/** 
 * 
 * 导航菜单对象.
 * 
 * <pre><b>描述：</b>
 *     
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2010-11-16
 * @version 1.0
 *
 */
public class NavMenuVo
{	
	/**
	 * 菜单编号.
	 */
	private String code;
	
	/**
	 * 菜单名称.
	 */
	private String name;
	
	/**
	 * 菜单链接.
	 */
	private String url;
	
	/**
	 * @param name
	 */
	public NavMenuVo(String name)
	{
		this.name = name;
	}

	/**
	 * @return the code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code the code to set.
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return the name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the url.
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url the url to set.
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}
}
