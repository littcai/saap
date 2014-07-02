package com.litt.saap.core.module.quickview.model.table;

import java.util.ArrayList;
import java.util.List;


public class Table
{
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String title;
	
	/**
	 *
	 */
	private String dataset;
	
	/** */
	private String primaryKey;
	
	/**
	 *
	 */
	private List<Column> columnList = new ArrayList<Column>();
	
	public Table(){}

	/**
	 * @param id
	 * @param title
	 * @param dataset
	 */
	public Table(String id, String title, String dataset)
	{
		this.id = id;
		this.title = title;
		this.dataset = dataset;
	}
	
	public boolean isContainsColumn(String name)
	{
		for (Column column : columnList) {
			if(column.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public Table addColumn(Column column)
	{
		columnList.add(column);
		return this;
	}
	
	public Table addColumnList(List<Column> columnList)
	{
		columnList.addAll(columnList);
		return this;
	}
	
	/**
	 * Take column.
	 *
	 * @param name the name
	 * @return the column
	 */
	public Column takeColumn(String name) {
		for (Column column : columnList) {
			if(column.getName().equals(name))
				return column;
		}
		return null;
	}

	/**
	 * @return the columnList.
	 */
	public List<Column> getColumnList()
	{
		return columnList;
	}

	/**
	 * @return the dataset.
	 */
	public String getDataset()
	{
		return dataset;
	}

	/**
	 * @return the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return the title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return the primaryKey
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	/**
	 * @param columnList the columnList to set
	 */
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}	
}
