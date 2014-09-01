package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.UserGroupMemberDao;
import com.litt.saap.system.po.UserGroupMember;
import com.litt.saap.system.po.UserGroupMember;
import com.litt.saap.system.service.IUserGroupMemberService;

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
public class UserGroupMemberServiceImpl implements IUserGroupMemberService{
	
	@Resource
	private UserGroupMemberDao userGroupMemberDao;

	/**
   * @param userGroupMember
   * @return
   * @see com.litt.saap.system.service.IUserGroupMemberService#save(com.litt.saap.system.po.UserGroupMember)
   */
	@Override
  public Integer save(UserGroupMember userGroupMember)
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();
		int tenantId = LoginUtils.getTenantId();
				
		userGroupMember.setTenantId(tenantId);
		userGroupMember.setCreateBy(loginVo.getOpId().intValue());
		userGroupMember.setCreateDatetime(new Date());
		//重复性检查
		boolean isValid = validate(userGroupMember);
		if(!isValid)
		  return 0;
		return userGroupMemberDao.save(userGroupMember);
	}

  /**
   * @param userGroupMember
   */
  private boolean validate(UserGroupMember userGroupMember)
  {
    int count = userGroupMemberDao.countByGroupAndUserId(userGroupMember.getTenantId(), userGroupMember.getGroupId(), userGroupMember.getUserId());
		if(count>0)
		  return false;
		return true;
  }	
	
	public void saveBatch(Integer groupId, Integer[] userIds) 
  {
    if(userIds!=null)
    {
      int tenantId = LoginUtils.getTenantId();
      ILoginVo loginVo = LoginUtils.getLoginVo();
      Date curDate = new Date();
      
      List<UserGroupMember> memberList = new ArrayList<UserGroupMember>();
      for (Integer userId : userIds) {
        UserGroupMember userGroupMember = new UserGroupMember();
        userGroupMember.setTenantId(tenantId);
        userGroupMember.setGroupId(groupId);
        userGroupMember.setUserId(userId);
        userGroupMember.setCreateBy(loginVo.getOpId().intValue());
        userGroupMember.setCreateDatetime(curDate);
        boolean isValid = this.validate(userGroupMember);
        if(isValid)
        {
          memberList.add(userGroupMember);
        }
      }
      saveBatch(memberList);
    }
  }

  /**
   * @param memberList
   */
  public void saveBatch(List<UserGroupMember> memberList)
  {
    userGroupMemberDao.saveBatch(memberList);
  }
   
	/**
   * @param id
   * @see com.litt.saap.system.service.IUserGroupMemberService#delete(java.lang.Integer)
   */
	@Override
  public void delete(Integer id)
	{
		UserGroupMember userGroupMember = this.load(id);
		this.delete(userGroupMember);
	}
	
	/**
   * @param userGroupMember
   * @see com.litt.saap.system.service.IUserGroupMemberService#delete(com.litt.saap.system.po.UserGroupMember)
   */
	@Override
  public void delete(UserGroupMember userGroupMember) 
	{		
		userGroupMemberDao.delete(userGroupMember);
	}
	
	/**
   * @param ids
   * @see com.litt.saap.system.service.IUserGroupMemberService#deleteBatch(java.lang.Integer[])
   */
	@Override
  public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	public void deleteBatch(List<UserGroupMember> memberList)
	{
	  for (UserGroupMember userGroupMember : memberList)
    {
      this.delete(userGroupMember);
    }
	}
	
	public void deleteByUser(int tenantId, int userId)
	{
	  userGroupMemberDao.deleteByUser(tenantId, userId);
	}
	
	/**
   * @param id
   * @return
   * @see com.litt.saap.system.service.IUserGroupMemberService#load(java.lang.Integer)
   */
	@Override
  public UserGroupMember load(Integer id)
	{
		return userGroupMemberDao.load(UserGroupMember.class, id);
	}

	/**
   * @param userGroupId
   * @return
   * @see com.litt.saap.system.service.IUserGroupMemberService#listByGroup(int)
   */
	@Override
  public List<UserGroupMember> listByGroup(int userGroupId)
	{
		String listHql = "from UserGroupMember where groupId=?";
		return userGroupMemberDao.listAll(listHql, new Object[]{userGroupId});
	}
	
	public List<UserGroupMember> listByUser(int tenantId, int userId)
  {
    String listHql = "from UserGroupMember where tenantId=? and userId=?";
    return userGroupMemberDao.listAll(listHql, new Object[]{tenantId, userId});
  }
	
	/**
   * @param userGroupId
   * @return
   * @see com.litt.saap.system.service.IUserGroupMemberService#listWithUserInfoByGroup(int)
   */
	@Override
  public List<Map<String, Object>> listWithUserInfoByGroup(int userGroupId)
  {
	  int tenantId = LoginUtils.getTenantId();
    String listHql = "select new map(userGroupMember as userGroupMember, userInfo as userInfo) from UserGroupMember userGroupMember, UserInfo userInfo where userGroupMember.tenantId=? and userGroupMember.groupId=? and userGroupMember.userId=userInfo.id";
    return userGroupMemberDao.listAll(listHql, new Object[]{tenantId, userGroupId});
  }

}
