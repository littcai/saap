package com.litt.saap.core.module.dynamicsearch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * DynamicSearchResult.
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
 * @since 2014-6-19
 * @version 1.0
 */
public class DynamicSearchResult {
	
	private List<String> selectList = new ArrayList<String>();
	
	private List<String> fromList = new ArrayList<String>();
	
	private List<String> whereList = new ArrayList<String>();
	
	private List<String> orderList = new ArrayList<String>();
	
	private List<Object> params = new ArrayList<Object>();
	
	
	public String genSQL()
	{
		StringBuilder sb = new StringBuilder(200);
		sb.append("SELECT ").append(selectList.get(0));
		for (int i=1; i<selectList.size(); i++) {
			String column = selectList.get(i);
			sb.append(", ").append(column);
		}
		//from
		sb.append(" FROM ").append(fromList.get(0));
		for (int i=1; i<fromList.size(); i++) {
			String from = fromList.get(i);
			sb.append(" ").append(from);
		}
		//where
		if(!whereList.isEmpty())
		{
			sb.append(" WHERE ").append(whereList.get(0));
			for (int i=1; i<whereList.size(); i++) {
				String where = whereList.get(i);
				sb.append(" AND ").append(where);
			}
		}
		//order
		if(!orderList.isEmpty())
		{
			sb.append(" ORDER BY ").append(orderList.get(0));
			for (int i=1; i<orderList.size(); i++) {
				String order = orderList.get(i);
				sb.append(", ").append(order);
			}
		}
		return sb.toString();
	}
	
	public void addSelect(String select)
	{
		this.selectList.add(select);
	}
	
	public void addFrom(String from)
	{
		this.fromList.add(from);
	}
	
	public void addWhere(String where)
	{
		this.whereList.add(where);
	}
	
	public void addOrder(String order)
	{
		this.orderList.add(order);
	}
	
	public void addParam(Object value)
	{
		this.params.add(value);
	}

	/**
	 * @return the params
	 */
	public List<Object> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<Object> params) {
		this.params = params;
	}

	/**
	 * @return the selectList
	 */
	public List<String> getSelectList() {
		return selectList;
	}

	/**
	 * @param selectList the selectList to set
	 */
	public void setSelectList(List<String> selectList) {
		this.selectList = selectList;
	}

	/**
	 * @return the fromList
	 */
	public List<String> getFromList() {
		return fromList;
	}

	/**
	 * @param fromList the fromList to set
	 */
	public void setFromList(List<String> fromList) {
		this.fromList = fromList;
	}

	/**
	 * @return the whereList
	 */
	public List<String> getWhereList() {
		return whereList;
	}

	/**
	 * @param whereList the whereList to set
	 */
	public void setWhereList(List<String> whereList) {
		this.whereList = whereList;
	}

	/**
	 * @return the orderList
	 */
	public List<String> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List<String> orderList) {
		this.orderList = orderList;
	}

}
