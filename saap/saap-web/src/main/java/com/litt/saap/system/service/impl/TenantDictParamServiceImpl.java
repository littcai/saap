package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.util.BeanCopier;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.TenantDictParamDao;
import com.litt.saap.system.po.TenantDictParam;
import com.litt.saap.system.vo.TenantDictParamVo;
import com.litt.saap.system.webservice.ITenantDictParamWebService;

/**
 * 
 * 字典管理业务层实现类.
 * <pre><b>描述：</b>
 *    系统参数字典管理
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-03-24 12:06:58
 * @version 1.0
 */
public class TenantDictParamServiceImpl implements ITenantDictParamWebService
{ 
	@Resource
	private TenantDictParamDao tenantDictParamDao;	
	
	@Resource
	private MessageSource messageSource;
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#save(com.litt.cidp.system.po.TenantDictParam)
	 */
	public Integer save(TenantDictParam tenantDictParam)
	{
		tenantDictParam.setStatus((byte)1);
		return tenantDictParamDao.save(tenantDictParam);
	}
	
	public Integer save(TenantDictParamVo tenantDictParam)
	{
		TenantDictParam po = BeanCopier.copy(tenantDictParam, TenantDictParam.class);
		return this.save(po);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#update(com.litt.cidp.system.po.TenantDictParam)
	 */
	public void update(TenantDictParam tenantDictParam)
	{
		tenantDictParamDao.update(tenantDictParam);
	}	
	
	public void update(TenantDictParamVo tenantDictParam)
	{
		TenantDictParam po = this.load(tenantDictParam.getId());
		po = BeanCopier.copy(tenantDictParam, po);
		this.update(po);
	}	
   
   	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#delete(java.lang.Long)
	 */
	public void delete(Integer id)
	{				
		tenantDictParamDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#load(java.lang.Long)
	 */
	public TenantDictParam load(Integer id)
	{
		return tenantDictParamDao.load(id);
	}
	
	public TenantDictParamVo find(Integer id)
	{
		TenantDictParam po = this.load(id);
		return BeanCopier.copy(po, TenantDictParamVo.class);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#load(java.lang.String, java.lang.String)
	 */
	public TenantDictParam load(int tenantId, String dictType, String dictValue)
	{
		return tenantDictParamDao.load(tenantId, dictType, dictValue);
	}	
	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#listByType(java.lang.String)
	 */
	public List<TenantDictParam> listByType(int tenantId, String dictType) 
	{
		return tenantDictParamDao.listByType(tenantId, dictType);
	}	
	
	/**
	 * 根据类型和过滤条件过滤.
	 *
	 * @param dictType the dict type
	 * @param filter the filter
	 * @return the list
	 */
	public List<TenantDictParam> listByTypeAndFilter(int tenantId, String dictType, String filter) 
	{
		return tenantDictParamDao.listByTypeAndFilter(tenantId, dictType, filter);
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.IDictParamService#findyType(java.lang.String, java.util.Locale)
	 */
	public List<TenantDictParamVo> findByType(int tenantId, String dictType) 
	{
		List<TenantDictParam> dictParamList = tenantDictParamDao.listByType(tenantId, dictType);
		List<TenantDictParamVo> retList = new ArrayList<TenantDictParamVo>(dictParamList.size());
		for (TenantDictParam tenantDictParam : dictParamList)
		{
			TenantDictParamVo vo = BeanCopier.copy(tenantDictParam, TenantDictParamVo.class);	
			retList.add(vo);
		}
		return retList;
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.IDictParamService#findyType(java.lang.String, java.util.Locale)
	 */
	public List<TenantDictParamVo> findByType(int tenantId, String dictType, Locale locale) 
	{
		List<TenantDictParamVo> dictParamList = tenantDictParamDao.listByType(tenantId, dictType);
		for (TenantDictParamVo vo : dictParamList)
		{	
			vo.setDictContent(messageSource.getMessage("dictparam."+vo.getDictType()+"."+vo.getDictValue(), null, locale));
		}
		return dictParamList;
	}	
	
	public List<TenantDictParamVo> findByTypeAndFilter(int tenantId, String dictType, String filter) 
	{
		List<TenantDictParam> dictParamList = tenantDictParamDao.listByTypeAndFilter(LoginUtils.getTenantId(), dictType, filter);
		List<TenantDictParamVo> retList = new ArrayList<TenantDictParamVo>(dictParamList.size());
		for (TenantDictParam tenantDictParam : dictParamList)
		{
			TenantDictParamVo vo = BeanCopier.copy(tenantDictParam, TenantDictParamVo.class);	
			retList.add(vo);
		}
		return retList;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#findMaxId(java.lang.String)
	 */
	public int findMaxId(int tenantId, String dictType)
	{
		return tenantDictParamDao.findMaxId(tenantId, dictType);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#doUpPosition(java.lang.Long)
	 */
	public void doUpPosition(int tenantId, Integer id)
	{
		TenantDictParam tenantDictParam = this.load(id);
		String dictType = tenantDictParam.getDictType();
		int position = tenantDictParam.getPosition();
		//1、检查有没有同级的
		List sameList = tenantDictParamDao.listSamePosition(tenantId, dictType, id, position);
		int sameCount = sameList.size();
		if(sameCount>0)
		{
			tenantDictParamDao.updateUpPosition(tenantId, dictType, position, sameCount);
			//2、顺序将重复的位置加到不重复
			for(int i=0;i<sameCount;i++)
			{
				TenantDictParam temp = (TenantDictParam)sameList.get(i);
				temp.setPosition(position+i+1);
			}
		}
		//将该组件与比它大且最相邻的一个互换位置		
		TenantDictParam upParam = tenantDictParamDao.loadUpPosition(tenantId, position);
		if(upParam!=null)
		{
			tenantDictParam.setPosition(upParam.getPosition());
			upParam.setPosition(position);
			tenantDictParamDao.update(tenantDictParam);
			tenantDictParamDao.update(upParam);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#doDownPosition(java.lang.Long)
	 */
	public void doDownPosition(int tenantId, Integer id)
	{
		TenantDictParam tenantDictParam = this.load(id);
		String dictType = tenantDictParam.getDictType();
		int position = tenantDictParam.getPosition();
		//1、检查有没有同级的
		List sameList = tenantDictParamDao.listSamePosition(tenantId, dictType, id, position);
		int sameCount = sameList.size();
		if(sameCount>0)
		{
			tenantDictParamDao.updateDownPosition(tenantId, dictType, position, sameCount);
			//2、顺序将重复的位置加到不重复
			for(int i=0;i<sameCount;i++)
			{
				TenantDictParam temp = (TenantDictParam)sameList.get(i);
				temp.setPosition(position-i-1);
			}
		}
		//将该组件与比它小且最相邻的一个互换位置		
		TenantDictParam downParam = tenantDictParamDao.loadDownPosition(tenantId, position);
		if(downParam!=null)
		{
			tenantDictParam.setPosition(downParam.getPosition());
			downParam.setPosition(position);
			tenantDictParamDao.update(tenantDictParam);
			tenantDictParamDao.update(downParam);
		}
	}	

}