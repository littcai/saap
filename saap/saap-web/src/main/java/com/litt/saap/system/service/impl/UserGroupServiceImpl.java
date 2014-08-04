package com.litt.saap.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.UserGroupDao;
import com.litt.saap.system.dao.UserGroupMemberDao;
import com.litt.saap.system.po.UserGroup;
import com.litt.saap.system.service.IUserGroupService;

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
 * @since 2013-9-2
 * @version 1.0
 */
public class UserGroupServiceImpl implements IUserGroupService {
	
	@Resource
	private UserGroupDao userGroupDao;
	
	@Resource
	private UserGroupMemberDao userGroupMemberDao;
	
	/**
	 * @param userGroup
	 * @return
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#save(com.litt.saap.system.po.UserGroup)
	 */
	@Override
	public Integer save(UserGroup userGroup) throws NotLoginException
	{
	  this.validate(0, userGroup.getCode(), userGroup.getName());
	  
		ILoginVo loginVo = LoginUtils.getLoginVo();
		userGroup.setParentId(0);
		userGroup.setType(1);
		userGroup.setStatus(1);
		
		userGroup.setTenantId(LoginUtils.getTenantId());
		userGroup.setCreateBy(loginVo.getOpId().intValue());
		userGroup.setCreateDatetime(new Date());
		userGroup.setUpdateBy(userGroup.getCreateBy());
		userGroup.setUpdateDatetime(userGroup.getCreateDatetime());
		
		return userGroupDao.save(userGroup);
	}
	
   	/**
	 * @param userGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#update(com.litt.saap.system.po.UserGroup)
	 */
	@Override
	public void update(UserGroup userGroup) throws NotLoginException
	{				
		this.validatePermission(userGroup);	
		this.validate(userGroup.getId(), userGroup.getCode(), userGroup.getName());
		
		userGroup.setUpdateDatetime(new Date());
		userGroupDao.update(userGroup);
	}			
   
   	/**
	 * @param id
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws NotLoginException
	{
		UserGroup userGroup = this.load(id);
		this.delete(userGroup);
	}
	
	/**
	 * @param userGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.system.service.IUserGroupService#delete(com.litt.saap.system.po.UserGroup)
	 */
	@Override
	public void delete(UserGroup userGroup)
	{		
		this.validatePermission(userGroup);	
		
		userGroupMemberDao.deleteByUserGroup(userGroup.getId());
		
		userGroupDao.delete(userGroup);
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
	 * @param todo
	 * @throws NotLoginException
	 */
	private void validatePermission(UserGroup userGroup)
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		if(userGroup.getTenantId()!=LoginUtils.getTenantId())
		{
			throw new BusiCodeException("error.biz.permissionDenied", loginVo.toLocale());
		}
	}		
	
	/**
   * 数据有效性检查.
   *
   * @param id the id
   * @param name the name
   */
  private void validate(Integer id, String code, String name)
  {   
    int tenantId = LoginUtils.getTenantId();
    
    String countHql = "select count(*) from UserGroup where tenantId=? and code=? and (id>? or id<?)";
    boolean invalid = userGroupDao.count(countHql, new Object[]{tenantId, code, id, id})>0;    
    if(invalid)
    {
      throw new BusiCodeException("userGroup.error.codeDuplicated");
    }
    
    countHql = "select count(*) from UserGroup where tenantId=? and name=? and (id>? or id<?)";
    invalid = userGroupDao.count(countHql, new Object[]{tenantId, name, id, id})>0;    
    if(invalid)
    {
      throw new BusiCodeException("userGroup.error.nameDuplicated");
    }
  }
	
	/**
	 * @param id
	 * @return
	 * @see com.litt.saap.system.service.IUserGroupService#load(java.lang.Integer)
	 */
	@Override
	public UserGroup load(Integer id)
	{
		return userGroupDao.load(UserGroup.class, id);
	}
	
	/**
	 * @param pageParam
	 * @return
	 * @see com.litt.saap.system.service.IUserGroupService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	@Override
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from UserGroup obj"
			+ "-- and obj.code={code}"
			+ "-- and obj.name={name}"
			;	
		return userGroupDao.listPage(listHql, pageParam);
	}
	
	public List<UserGroup> listByTenant(int tenantId)
  {
    String listHql = "select a from UserGroup a where a.tenantId=?";
    return userGroupDao.listAll(listHql, new Object[]{tenantId});
  }
	
	/**
	 * List by tenant and user.
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<UserGroup> listByTenantAndUser(int tenantId, int userId)
	{
		String listHql = "select a from UserGroup a, UserGroupMember b where b.tenantId=? and b.userId=? and a.id=b.groupId";
		return userGroupDao.listAll(listHql, new Object[]{tenantId, userId});
	}

}
