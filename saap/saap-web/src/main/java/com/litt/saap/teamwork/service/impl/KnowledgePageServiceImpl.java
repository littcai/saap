package com.litt.saap.teamwork.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.teamwork.dao.KnowledgePageDao;
import com.litt.saap.teamwork.po.KnowledgePage;
import com.litt.saap.teamwork.service.IKnowledgePageService;

/**
 * 
 * KnowledgePage service impl.
 * <pre><b>Description：</b>
 *    KnowledgePage
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public class KnowledgePageServiceImpl implements IKnowledgePageService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(KnowledgePageServiceImpl.class);
    
    @Resource
    private KnowledgePageDao knowledgePageDao;

   	/**
	 * Save.
	 * @param knowledgePage KnowledgePage
	 * @return id
	 */
	public Integer save(KnowledgePage knowledgePage)
	{
		return knowledgePageDao.save(knowledgePage);
	}
	
   	/**
	 * Update.
	 * @param knowledgePage KnowledgePage
	 */
	public void update(KnowledgePage knowledgePage) 
	{
		//校验租户权限
		LoginUtils.validateTenant(knowledgePage.getTenantId());
	
		knowledgePageDao.update(knowledgePage);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		KnowledgePage knowledgePage = this.load(id);
		knowledgePageDao.delete(knowledgePage);
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
	public void delete(KnowledgePage knowledgePage) 
	{
		//校验租户权限
		LoginUtils.validateTenant(knowledgePage.getTenantId());
	
		knowledgePageDao.delete(knowledgePage);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return KnowledgePage
	 */
	public KnowledgePage load(Integer id) 
	{
		KnowledgePage knowledgePage = knowledgePageDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(knowledgePage.getTenantId());
	
		return knowledgePage;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from KnowledgePage obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return knowledgePageDao.listPage(listHql, pageParam);
	}
}