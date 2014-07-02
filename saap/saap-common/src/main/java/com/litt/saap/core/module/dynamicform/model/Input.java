package com.litt.saap.core.module.dynamicform.model;

/**
 * 
 * Input.
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
public class Input {
	
	public static final String TYPE_TEXT = "text";
	public static final String TYPE_SELECT = "select";
	public static final String TYPE_RADIO = "radio";
	public static final String TYPE_CHECKBOX = "checkbox";
	public static final String TYPE_TEXTAREA = "textarea";
	
	public static final String TYPE_DATEPICKER = "datepicker";
	public static final String TYPE_TIMEPICKER = "timepicker";
	public static final String TYPE_DATETIMEPICKER = "datetimepicker";
	public static final String TYPE_AUTOCOMPLETE = "autocomplete";
	
	private String type;
	
	private String comment;
	
	private String defaultValue;
	
	private String scripts;
	
	private String styles;
	
	private Boolean block;
	
	private Validator validator;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the scripts
	 */
	public String getScripts() {
		return scripts;
	}

	/**
	 * @param scripts the scripts to set
	 */
	public void setScripts(String scripts) {
		this.scripts = scripts;
	}

	/**
	 * @return the validator
	 */
	public Validator getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
	 */
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the styles
	 */
	public String getStyles() {
		return styles;
	}

	/**
	 * @param styles the styles to set
	 */
	public void setStyles(String styles) {
		this.styles = styles;
	}

	/**
	 * @return the block
	 */
	public Boolean getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(Boolean block) {
		this.block = block;
	}

}
