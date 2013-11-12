package com.litt.saap.common.service;

import java.util.Map;

/**
 * .
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
 * @since 2013-11-12
 * @version 1.0
 */
public interface ITemplateService {

	/**
	 * Gen string.
	 *
	 * @param templateName the template name
	 * @param propMap the prop map
	 * @return the string
	 */
	public String genString(String templateName, Map<String, Object> propMap);

}