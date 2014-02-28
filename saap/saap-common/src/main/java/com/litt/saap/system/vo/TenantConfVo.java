package com.litt.saap.system.vo;

import java.io.Serializable;

/**
 * 租户配置信息.
 * 
 * <pre><b>描述：</b>
 *    租户内系统配置信息，如邮件服务器信息、短信服务器信息等，由租户管理员进行配置。
 * 采用动态属性进行存储和读取   
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2014年2月18日
 * @version 1.0
 */
public class TenantConfVo implements Serializable {
	
	/** 属性键值. */
	private String propKey;
	
	/** 属性名. */
	private String propValue;
	
	

	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

}
