package com.litt.saap.system.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.service.ITenantMemberService;

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
		tenantMember.setUpdateDatetime(new Date());
		tenantMember.setUpdateUserId(LoginUtils.getLoginOpId().intValue());
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
	 * 根据登录名读取租户用户
	 * @param loginId
	 * @param tenantId
	 * @return
	 */
	public UserInfo loadUserInfoByLoginIdAndTenantId(String loginId, Integer tenantId)
	{
		String hql = "select u from UserInfo u, TenantMember m where u.loginId=? and u.id=m.userId and m.tenantId=?";
		return tenantMemberDao.uniqueResult(hql, new Object[]{loginId, tenantId}, UserInfo.class);
	}
	
	/**
	 * 
	 * @param tenantId
	 * @param userId
	 * @return
	 */
	public TenantMember load(int tenantId, int userId)
	{
		String hql = "from TenantMember where tenantId=? and userId=?";
		TenantMember tenantMember = tenantMemberDao.uniqueResult(hql, new Object[]{tenantId, userId}, TenantMember.class);
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
		String listHql = "select new map(obj as tenantMember, u as userInfo) from TenantMember obj, UserInfo u"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.userId=u.id"	
			+ "-- and u.loginId like {loginId%}"	
			+ "-- and u.userName like {userName%}"	
			+ "-- and u.nickName like {nickName%}"	
			+ "-- and u.mobile like {mobile%}"	
			+ "-- and u.email like {email%}"	
			;	
		return tenantMemberDao.listPage(listHql, pageParam);
	}
}