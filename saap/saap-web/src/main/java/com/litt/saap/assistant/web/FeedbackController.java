package com.litt.saap.assistant.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.LocaleUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.litt.core.common.BeanManager;
import com.litt.core.util.StringUtils;
import com.litt.saap.common.service.IEmailService;
import com.litt.saap.common.service.ITemplateService;
import com.litt.saap.core.web.util.LoginUtils;


/**
 * FeedbackController.
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
 * @since 2014年6月25日
 * @version 1.0
 */
@Controller
public class FeedbackController {
	
	@Resource
	private IEmailService emailService;
	@Resource
	private ITemplateService templateService;
	
	@RequestMapping 
	public void save(@RequestParam(required=false) String moduleCode, @RequestParam String currentUrl, @RequestParam int type, @RequestParam String content) throws Exception
	{	
		Locale locale = LocaleUtils.toLocale(LoginUtils.getLoginVo().getLocale());
		String from = emailService.getFrom();
		String subject = BeanManager.getMessage("feedback.type."+type, locale);
		
		Map<String, Object> propMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(moduleCode))
		{
		  propMap.put("moduleCode", moduleCode);
		  propMap.put("moduleName", BeanManager.getMessage("module."+moduleCode, locale));
		}
		propMap.put("currentUrl", currentUrl);
		propMap.put("type", type);
		propMap.put("content", content);
		
		String finalContent = templateService.genString("feedback", propMap);
		
		emailService.sendSimple(from, subject, finalContent);
	}

}
