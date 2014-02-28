package com.litt.saap.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory; 

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.core.exception.BusiException;
import com.litt.core.service.BaseService;
import com.litt.saap.system.service.ITenantMemberService;

import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.dao.TenantMemberDao;

/**
 * 
 * Members service impl.
 * <pre><b>Description：</b>
 *    Member Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
public class TenantMemberServiceImpl implements ITenantMemberService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TenantMemberServiceImpl.class);
    
    @Resource
    private TenantMemberDao tenantMemberDao;

   	/**
	 * Save.
	 * @param tenantMember TenantMember
	 * @return id
	 */
	public Integer save(TenantMember tenantMember)
	{
		return tenantMemberDao.save(tenantMember);
	}
	
   	/**
	 * Update.
	 * @param tenantMember TenantMember
	 */
	public void update(TenantMember tenantMember) 
	{
		//校验租户权限
		LoginUtils.validateTenant(tenantMember.getTenantId());
	
		tenantMemberDao.update(tenantMember);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		TenantMember tenantMember = this.load(id);
		this.delete(tenantMember);
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
	public void delete(TenantMember tenantMember) 
	{
		//校验租户权限
		LoginUtils.validateTenant(tenantMember.getTenantId());
	
		tenantMemberDao.delete(tenantMember);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantMember
	 */
	public TenantMember load(Integer id) 
	{
		TenantMember tenantMember = tenantMemberDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(tenantMember.getTenantId());
	
		return tenantMember;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from TenantMember obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return tenantMemberDao.listPage(listHql, pageParam);
	}
}