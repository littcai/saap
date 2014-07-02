package com.litt.saap.core.module.quickview.model;

import com.litt.saap.core.module.quickview.model.dataset.IDataset;
import com.litt.saap.core.module.quickview.model.search.Search;
import com.litt.saap.core.module.quickview.model.table.Table;

/**
 * 
 * QuickView.
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
public class QuickView {
	
	private Search search;
	
	private IDataset dataset;
	
	private Table table;

	/**
	 * @return the search
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * @return the table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @return the dataset
	 */
	public IDataset getDataset() {
		return dataset;
	}

	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(IDataset dataset) {
		this.dataset = dataset;
	}
	

}
