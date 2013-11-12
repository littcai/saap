package com.litt.saap.common.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;

import com.litt.core.exception.BusiException;
import com.litt.core.util.ResourceUtils;
import com.litt.saap.common.service.ITemplateService;
import com.litt.saap.core.module.template.TemplateManager;

import freemarker.template.TemplateException;

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
public class TemplateServiceImpl implements ITemplateService {
	
	/**
	 * 模板路径
	 */
	private String templatePath;
	
	private TemplateManager manager = new TemplateManager();
	
	public void init() throws Exception
	{
		manager.init(templatePath);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.common.service.impl.ITemplateService#genString(java.lang.String, java.util.Map)
	 */
	public String genString(String templateName, Map<String, Object> propMap)
	{
		try {
			return manager.genString(templateName+".ftl", propMap);
		} catch (IOException e) {
			throw new BusiException("Gen content from template failed.",e);
		} catch (TemplateException e) {
			throw new BusiException("Gen content from template failed.",e);
		}
	}

	/**
	 * @return the templatePath
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/**
	 * @param templatePath the templatePath to set
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	

}
