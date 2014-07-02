package com.litt.saap.core.module.quickview.model.dataset;

import org.dom4j.Element;

/**
 * 
 * CustomDataset.
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
public class CustomDataset extends BaseDataset implements IDataset
{
	
	private String impl;	

	/**
	 * @param name
	 */
	public CustomDataset(String name)
	{
		super(name);
	}
	
	public CustomDataset(Element datasetNode)
	{
		super(datasetNode);
		this.impl = datasetNode.attributeValue("impl");
	}

	/**
	 * @return the impl.
	 */
	public String getImpl()
	{
		return impl;
	}

	/**
	 * @param impl the impl to set.
	 */
	public void setImpl(String impl)
	{
		this.impl = impl;
	}

}
