package com.litt.saap.message.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory; 

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.core.exception.BusiException;
import com.litt.core.service.BaseService;
import com.litt.saap.message.service.IAfficheService;

import com.litt.saap.message.po.Affiche;
import com.litt.saap.message.dao.AfficheDao;

/**
 * 
 * Affiche service impl.
 * <pre><b>Description：</b>
 *    Affiche Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
public class AfficheServiceImpl implements IAfficheService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(AfficheServiceImpl.class);
    
    @Resource
    private AfficheDao afficheDao;

   	/**
	 * Save.
	 * @param affiche Affiche
	 * @return id
	 */
	public Integer save(Affiche affiche)
	{
		return afficheDao.save(affiche);
	}
	
   	/**
	 * Update.
	 * @param affiche Affiche
	 */
	public void update(Affiche affiche) 
	{
		//校验租户权限
		LoginUtils.validateTenant(affiche.getTenantId());
	
		afficheDao.update(affiche);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		Affiche affiche = this.load(id);
		this.delete(affiche);
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
	public void delete(Affiche affiche) 
	{
		//校验租户权限
		LoginUtils.validateTenant(affiche.getTenantId());
	
		afficheDao.delete(affiche);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Affiche
	 */
	public Affiche load(Integer id) 
	{
		Affiche affiche = afficheDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(affiche.getTenantId());
	
		return affiche;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Affiche obj"
		   + "-- and obj.tenantId={tenantId}"
			 + "-- and obj.type={type}"
			 + "-- and obj.isChecked={isChecked}"
			 + "-- and obj.expiredDate<={expiredDate}"
			 + "-- and obj.title like {%type%}"
			;	
		return afficheDao.listPage(listHql, pageParam);
	}
	
	public IPageList listPageWithGlobal(PageParam pageParam)
  {
    String listHql = "select obj from Affiche obj"
       + "-- and obj.tenantId={tenantId} or obj.tenantId=0"
       + "-- and obj.type={type}"
       + "-- and obj.isChecked={isChecked}"
       + "-- and obj.expiredDate<={expiredDate}"
      ; 
    return afficheDao.listPage(listHql, pageParam);
  }
	
}