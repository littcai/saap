package com.litt.saap.core.model;

import java.io.Serializable;

/**
 * 复选项对象.
 * 
 * <pre><b>描述：</b>
 *    在对象外封装一层选中状态，用于传递给上层
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2014年4月16日
 * @version 1.0
 */
public class CheckItem<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 是否选中. */
	private boolean isChecked = false;

	private T obj;	

	/**
	 * @param obj
	 */
	public CheckItem(T obj) {
		this.obj = obj;
	}	

	/**
	 * @param isChecked
	 * @param obj
	 */
	public CheckItem(boolean isChecked, T obj) {
		super();
		this.isChecked = isChecked;
		this.obj = obj;
	}

	/**
	 * @return the isChecked
	 */
	public boolean isChecked() {
		return isChecked;
	}
	
	/**
	 * @param isChecked the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	/**
	 * @return the obj
	 */
	public T getObj() {
		return obj;
	}

}
