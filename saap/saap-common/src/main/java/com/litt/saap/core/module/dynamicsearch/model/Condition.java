package com.litt.saap.core.module.dynamicsearch.model;

import java.io.Serializable;

/**
 * 
 * Condition.
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
public class Condition<T> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public static final String EQ = "eq";
	public static final String NE = "ne";
	public static final String GE = "ge";
	public static final String LE = "le";
	public static final String GT = "gt";
	public static final String LT = "lt";
	public static final String LIKE = "like";
	public static final String START_WITH = "startWith";
	public static final String END_WITH = "endWith";
	public static final String BETWEEN = "between";
	//public static final String IN = "IN";	//TODO unsupport
	
	private String symbol;
	
	private T value;
	
	public String getRealSymbol()
	{
		if(EQ.equals(symbol))
		{
			return " =?";
		}
		else if(NE.equals(symbol))
		{
			return " <>?";
		}
		else if(GE.equals(symbol))
		{
			return " >=?";
		}
		else if(LE.equals(symbol))
		{
			return " <=?";
		}
		else if(GT.equals(symbol))
		{
			return " >?";
		}
		else if(LT.equals(symbol))
		{
			return " <?";
		}
		else if(LIKE.equals(symbol))
		{
			return " like ?";
		}
		else if(START_WITH.equals(symbol))
		{
			return " like ?";
		}
		else if(END_WITH.equals(symbol))
		{
			return " like ?";
		}
		else if(BETWEEN.equals(symbol))
		{
			return " between ? and ?";
		}
		else {
			throw new IllegalArgumentException("Unknown symbol "+symbol);
		}
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
}
