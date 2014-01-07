package com.litt.saap.teamwork.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.teamwork.dao.TopicDao;
import com.litt.saap.teamwork.po.Topic;
import com.litt.saap.teamwork.service.ITopicService;

/**
 * 
 * Discussion service impl.
 * <pre><b>Description：</b>
 *    Discussion
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public class TopicServiceImpl implements ITopicService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);
    
    @Resource
    private TopicDao topicDao;

   	/**
	 * Save.
	 * @param topic Topic
	 * @return id
	 */
	public Integer save(Topic topic)
	{
		return topicDao.save(topic);
	}
	
   	/**
	 * Update.
	 * @param topic Topic
	 */
	public void update(Topic topic) 
	{
		//校验租户权限
		LoginUtils.validateTenant(topic.getTenantId());
	
		topicDao.update(topic);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		Topic topic = this.load(id);
		topicDao.delete(topic);
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
	public void delete(Topic topic) 
	{
		//校验租户权限
		LoginUtils.validateTenant(topic.getTenantId());
	
		topicDao.delete(topic);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Topic
	 */
	public Topic load(Integer id) 
	{
		Topic topic = topicDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(topic.getTenantId());
	
		return topic;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Topic obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return topicDao.listPage(listHql, pageParam);
	}
}