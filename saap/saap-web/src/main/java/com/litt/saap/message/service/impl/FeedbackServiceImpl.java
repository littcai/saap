package com.litt.saap.message.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.core.common.BeanManager;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.util.StringUtils;
import com.litt.saap.common.service.IEmailService;
import com.litt.saap.common.service.ITemplateService;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.message.dao.FeedbackDao;
import com.litt.saap.message.po.Feedback;
import com.litt.saap.message.service.IFeedbackService;

/**
 * 
 * Feedback service impl.
 * <pre><b>Description：</b>
 *    Feedback Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
public class FeedbackServiceImpl implements IFeedbackService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    
    @Resource
    private FeedbackDao feedbackDao;
    
    @Resource
    private IEmailService emailService;
    
    @Resource
    private ITemplateService templateService;
    
    public Integer save(int tenantId, int userId, String moduleCode, String currentUrl, int type, String content)
    { 
      Feedback feedback = new Feedback();
      feedback.setTenantId(tenantId);
      feedback.setCreateBy(userId);
      feedback.setType(type);
      feedback.setContent(content);
      feedback.setCreateDatetime(new Date());
      Integer id = feedbackDao.save(feedback);
      
      LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo();
      
      Locale locale = LocaleUtils.toLocale(loginUser.getLocale());
      String from = emailService.getFrom();
      String subject = BeanManager.getMessage("feedback.type."+type, locale);
      
      Map<String, Object> propMap = new HashMap<String, Object>();
      if(!StringUtils.isEmpty(moduleCode))
      {
        propMap.put("moduleCode", moduleCode);
        propMap.put("moduleName", BeanManager.getMessage("module."+moduleCode, locale));
      }
      
      propMap.put("loginUser", loginUser);
      propMap.put("currentUrl", currentUrl);
      propMap.put("type", type);
      propMap.put("content", content);
      
      String finalContent = templateService.genString("feedback", propMap);
      
      emailService.sendSimple(from, subject, finalContent);
      
      return id;
    }
    
   	/**
	 * Update.
	 * @param feedback Feedback
	 */
	public void update(Feedback feedback) 
	{
		//校验租户权限
		LoginUtils.validateTenant(feedback.getTenantId());
	
		feedbackDao.update(feedback);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		Feedback feedback = this.load(id);
		this.delete(feedback);
	}
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Feedback feedback) 
	{
		//校验租户权限
		LoginUtils.validateTenant(feedback.getTenantId());
	
		feedbackDao.delete(feedback);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Feedback
	 */
	public Feedback load(Integer id) 
	{
		Feedback feedback = feedbackDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(feedback.getTenantId());
	
		return feedback;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Feedback obj"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.type={type}"
			+ "-- and obj.createBy={createBy}"
			;	
		return feedbackDao.listPage(listHql, pageParam);
	}
}