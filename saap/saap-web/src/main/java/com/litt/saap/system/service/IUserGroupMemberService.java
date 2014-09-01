package com.litt.saap.system.service;

import java.util.List;
import java.util.Map;

import com.litt.saap.system.po.UserGroupMember;

/**
 * IUserGroupMemberService.
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
 * @since 2014年8月1日
 * @version 1.0
 */
public interface IUserGroupMemberService {

  public Integer save(UserGroupMember userGroupMember);
  
  public void saveBatch(Integer groupId, Integer[] userIds);
  
  public void saveBatch(List<UserGroupMember> memberList);
  
  public void delete(Integer id);

  public void delete(UserGroupMember userGroupMember);

  public void deleteBatch(Integer[] ids);
  
  public void deleteBatch(List<UserGroupMember> memberList);
  
  public void deleteByUser(int tenantId, int userId);

  public UserGroupMember load(Integer id);

  public List<UserGroupMember> listByGroup(int userGroupId);
  
  public List<UserGroupMember> listByUser(int tenantId, int userId);

  public List<Map<String, Object>> listWithUserInfoByGroup(int userGroupId);

}