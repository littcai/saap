package com.litt.saap.core.module.quickview.model.dataset;


/**
 * 
 * SQLDataset.
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
public class SQLDataset extends BaseDataset implements IDataset
{	
	private String sql;
	
	/**
	 * @param id
	 */
	public SQLDataset() {}

	public SQLDataset(String id)
	{
		super(id);
	}	

	/**
	 * @return the sql.
	 */
	public String getSql()
	{
		return sql;
	}

	/**
	 * @param sql the sql to set.
	 */
	public void setSql(String sql)
	{
		this.sql = sql;
	}
}
