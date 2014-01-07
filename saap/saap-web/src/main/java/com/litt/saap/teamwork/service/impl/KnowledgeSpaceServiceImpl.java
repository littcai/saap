package com.litt.saap.teamwork.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.teamwork.dao.KnowledgeSpaceDao;
import com.litt.saap.teamwork.po.KnowledgeSpace;
import com.litt.saap.teamwork.service.IKnowledgeSpaceService;

/**
 * 
 * KnowledgeBase service impl.
 * <pre><b>Description：</b>
 *    KnowledgeBase
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public class KnowledgeSpaceServiceImpl implements IKnowledgeSpaceService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(KnowledgeSpaceServiceImpl.class);
    
    @Resource
    private KnowledgeSpaceDao knowledgeSpaceDao;

   	/**
	 * Save.
	 * @param knowledgeSpace KnowledgeSpace
	 * @return id
	 */
	public Integer save(KnowledgeSpace knowledgeSpace)
	{
		return knowledgeSpaceDao.save(knowledgeSpace);
	}
	
   	/**
	 * Update.
	 * @param knowledgeSpace KnowledgeSpace
	 */
	public void update(KnowledgeSpace knowledgeSpace) 
	{
		//校验租户权限
		LoginUtils.validateTenant(knowledgeSpace.getTenantId());
	
		knowledgeSpaceDao.update(knowledgeSpace);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		KnowledgeSpace knowledgeSpace = this.load(id);
		knowledgeSpaceDao.delete(knowledgeSpace);
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
	public void delete(KnowledgeSpace knowledgeSpace) 
	{
		//校验租户权限
		LoginUtils.validateTenant(knowledgeSpace.getTenantId());
	
		knowledgeSpaceDao.delete(knowledgeSpace);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return KnowledgeSpace
	 */
	public KnowledgeSpace load(Integer id) 
	{
		KnowledgeSpace knowledgeSpace = knowledgeSpaceDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(knowledgeSpace.getTenantId());
	
		return knowledgeSpace;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from KnowledgeSpace obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return knowledgeSpaceDao.listPage(listHql, pageParam);
	}
}