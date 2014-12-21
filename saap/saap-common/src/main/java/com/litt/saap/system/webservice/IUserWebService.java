package com.litt.saap.system.webservice;

import java.util.List;

import com.litt.saap.common.vo.TenantUserVo;

/**
 * IUserWebService.
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
 * @since 2014年12月21日
 * @version 1.0
 */
public interface IUserWebService {

  /**
   * Find by tenant.
   *
   * @param tenantId the tenant id
   * @return the list
   */
  public List<TenantUserVo> findByTenant(int tenantId);
  
  /**
   * Find by id.
   *
   * @param tenantId the tenant id
   * @param userId the user id
   * @return the tenant user vo
   */
  public TenantUserVo findById(int tenantId, int userId);

}