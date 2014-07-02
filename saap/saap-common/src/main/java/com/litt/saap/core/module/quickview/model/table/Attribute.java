package com.litt.saap.core.module.quickview.model.table;

/**
 * Attribute.
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
 * @version 1.0
 * @since 2014-7-2
 */
public class Attribute {
	
	/** The name. */
	private String name;
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new attribute.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

}
