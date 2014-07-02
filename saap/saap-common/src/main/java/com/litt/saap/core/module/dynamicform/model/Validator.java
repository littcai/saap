package com.litt.saap.core.module.dynamicform.model;

/**
 * 
 * Validator.
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
public class Validator {
	
	private boolean required;// "This field is required.",
	private boolean email;// "Please enter a valid email address.",
	private boolean url;// "Please enter a valid URL.",
	private boolean date;// "Please enter a valid date.",
	private boolean number;// "Please enter a valid number.",
	private boolean digits;// "Please enter only digits.",
	private int maxlength;// $.validator.format("Please enter no more than {0} characters."),
	private int minlength;// $.validator.format("Please enter at least {0} characters."),
	private int max;// $.validator.format("Please enter a value less than or equal to {0}."),
	private int min;// $.validator.format("Please enter a value greater than or equal to {0}.")
	private boolean isIp;	
	private String className;
	
	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}
	/**
	 * @return the email
	 */
	public boolean isEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(boolean email) {
		this.email = email;
	}
	/**
	 * @return the url
	 */
	public boolean isUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(boolean url) {
		this.url = url;
	}
	/**
	 * @return the date
	 */
	public boolean isDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(boolean date) {
		this.date = date;
	}
	/**
	 * @return the number
	 */
	public boolean isNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(boolean number) {
		this.number = number;
	}
	/**
	 * @return the digits
	 */
	public boolean isDigits() {
		return digits;
	}
	/**
	 * @param digits the digits to set
	 */
	public void setDigits(boolean digits) {
		this.digits = digits;
	}
	/**
	 * @return the maxlength
	 */
	public int getMaxlength() {
		return maxlength;
	}
	/**
	 * @param maxlength the maxlength to set
	 */
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	/**
	 * @return the minlength
	 */
	public int getMinlength() {
		return minlength;
	}
	/**
	 * @param minlength the minlength to set
	 */
	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}
	/**
	 * @return the isIp
	 */
	public boolean getIsIp() {
		return isIp;
	}
	/**
	 * @param isIp the isIp to set
	 */
	public void setIsIp(boolean isIp) {
		this.isIp = isIp;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
