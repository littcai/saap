package com.litt.saap.core.module.message.model;

import java.io.Serializable;

/**
 * SmsHttpResponse.
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2014年2月23日
 * @version 1.0
 */
public class SmsHttpResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private SmsResponse response;

	/**
	 * @return the response
	 */
	public SmsResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(SmsResponse response) {
		this.response = response;
	}

}
