package com.litt.saap.system.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;

/**
 * 
 * Members service interface.
 * <pre><b>Description：</b>
 *    Member Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
public interface ITenantMemberService
{ 

   	/**
	 * Save.
	 * @param tenantMember TenantMember
	 * @return id
	 */
	public Integer save(TenantMember tenantMember);
	
   	/**
	 * Update.
	 * @param tenantMember TenantMember
	 */
	public void update(TenantMember tenantMember);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(TenantMember tenantMember);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantMember
	 */
	public TenantMember load(Integer id);	
	
	/**
	 * 根据登录名读取租户用户
	 * @param loginId
	 * @param tenantId
	 * @return
	 */
	public UserInfo loadUserInfoByLoginIdAndTenantId(String loginId, Integer tenantId);
	
	/**
	 * 
	 * @param tenantId
	 * @param userId
	 * @return
	 */
	public TenantMember load(int tenantId, int userId);
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}