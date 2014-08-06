package com.litt.saap.core.module.quickview.model.dataset;

import org.dom4j.Element;

/**
 * 
 * BaseDataset.
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
public abstract class BaseDataset implements IDataset
{
	private String id;
	
	public BaseDataset(){}

	/**
	 * @param id
	 */
	public BaseDataset(String id)
	{
		this.id = id;
	}

	/**
	 * @return the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set.
	 */
	public void setId(String id)
	{
		this.id = id;
	}

}
