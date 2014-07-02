package com.litt.saap.core.module.quickview.model.search;

import java.util.ArrayList;
import java.util.List;

import com.litt.saap.core.module.dynamicform.model.Field;

/**
 * 
 * Search.
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
 * @since 2014-7-2
 * @version 1.0
 */
public class Search
{
	private String title;		
	
	/** The param group list. */
	List<ParamGroup> paramGroupList = new ArrayList<ParamGroup>();
	
	public List<Field> getAllFieldList()
	{
		List<Field> paramList = new ArrayList<Field>();
		for (ParamGroup paramGroup : paramGroupList) {
			paramList.addAll(paramGroup.getFieldList());
		}
		return paramList;
	}	
	
	/**
	 * Adds the param group.
	 *
	 * @param paramGroup the param group
	 * @return the search
	 */
	public Search addParamGroup(ParamGroup paramGroup)
	{
		paramGroupList.add(paramGroup);
		return this;
	}
	
	/**
	 * Adds the param group list.
	 *
	 * @param paramGroupList the param group list
	 * @return the search
	 */
	public Search addParamGroupList(List<ParamGroup> paramGroupList)
	{
		paramGroupList.addAll(paramGroupList);
		return this;
	}	

	/**
	 * @return the title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the paramGroupList.
	 */
	public List<ParamGroup> getParamGroupList()
	{
		return paramGroupList;
	}

	/**
	 * @param paramGroupList the paramGroupList to set
	 */
	public void setParamGroupList(List<ParamGroup> paramGroupList) {
		this.paramGroupList = paramGroupList;
	}
}
