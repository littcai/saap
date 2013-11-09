package com.litt.saap.core.web.model;

import java.io.Serializable;

/**
 * 消息盒子.
 * 
 * <pre><b>描述：</b>
 *    结合javascript消息控件实现页面的信息提示.
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-25
 * @version 1.0
 */
public class MessageBox implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 消息类型. */
	private String type = "success";
	
	/** 消息内容. */
	private String content;	

	/**
	 * Instantiates a new message box.
	 *
	 * @param content the content
	 */
	public MessageBox(String content) {
		this.content = content;
	}

	/**
	 * Instantiates a new message box.
	 *
	 * @param type the type
	 * @param content the content
	 */
	public MessageBox(String type, String content) {
		this.type = type;
		this.content = content;
	}

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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
