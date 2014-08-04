package com.litt.saap.system.service;

import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.saap.system.po.UserGroup;

/**
 * IUserGroupService.
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
 * @since 2014年3月3日
 * @version 1.0
 */
public interface IUserGroupService {

	/**
	 * @param userGroup
	 * @return
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#save(com.litt.saap.system.po.UserGroup)
	 */
	public abstract Integer save(UserGroup userGroup) throws NotLoginException;

	/**
	 * @param userGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#update(com.litt.saap.system.po.UserGroup)
	 */
	public abstract void update(UserGroup userGroup) throws NotLoginException;

	/**
	 * @param id
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#delete(java.lang.Integer)
	 */
	public abstract void delete(Integer id) throws NotLoginException;

	/**
	 * @param userGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#delete(com.litt.saap.system.po.UserGroup)
	 */
	public abstract void delete(UserGroup userGroup) throws NotLoginException;
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);

	/**
	 * @param id
	 * @return
	 * @see com.litt.saap.system.service.IUserGroupService#load(java.lang.Integer)
	 */
	public abstract UserGroup load(Integer id);

	/**
	 * @param pageParam
	 * @return
	 * @see com.litt.saap.system.service.IUserGroupService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	public abstract IPageList listPage(PageParam pageParam);
	
	public List<UserGroup> listByTenant(int tenantId);

	/**
	 * @param userId
	 * @return
	 * @see com.litt.saap.system.service.IUserGroupService#listByContacts(int)
	 */
	public abstract List<UserGroup> listByTenantAndUser(int tenantId, int userId);

}