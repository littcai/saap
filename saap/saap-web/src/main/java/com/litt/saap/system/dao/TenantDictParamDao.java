package com.litt.saap.system.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.litt.core.common.Utility;
import com.litt.core.dao.GenericHibernateDao;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.TenantDictParam;


/**
 * 
 * 字典管理数据层实现类.
 * <pre><b>描述：</b>
 *    系统参数字典管理
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-03-24 12:06:58
 * @version 1.0
 */
public class TenantDictParamDao extends GenericHibernateDao<TenantDictParam, Integer>
{
	/** 日志工具. */
    private static final Log logger = LogFactory.getLog(TenantDictParamDao.class);    
	
	/**
	 * 批量将大于该位置的统一加大.
	 * 
	 * @param position the position
	 * @param upPos the up pos
	 * @param dictType 参数类型
	 */
	public void updateUpPosition(int tenantId, String dictType, int position, int upPos)
	{
		String hql = "update TenantDictParam d set d.position=d.position+? where d.tenantId=? and d.dictType=? and d.position>?";
		super.update(hql, new Object[]{upPos, tenantId, dictType, position});
	}
	
	/**
	 * 批量将大于该位置的统一加大.
	 * @param position
	 * @param downPos
	 * @param dictType 参数类型
	 */
	public void updateDownPosition(int tenantId, String dictType, int position, int downPos)
	{
		String hql = "update TenantDictParam d set d.position=d.position-? where d.tenantId=? and d.dictType=? and d.position<?";
		super.update(hql, new Object[]{downPos, tenantId, dictType, position});
	}
		
	/**
	 * 根据参数类型查询参数字典信息.
	 * 
	 * @param dictType 参数类型
	 * @return List 参数字典对象
	 */
	public List listByType(int tenantId, String dictType) 
	{
		getHibernateTemplate().setCacheQueries(true);  
		String hql = "from TenantDictParam where tenantId=? and dictType=? order by position desc";
		return super.listAll(hql, new Object[]{tenantId, dictType});
	}
	
	/**
	 * 根据参数类型查询参数字典信息.
	 *
	 * @param dictType 参数类型
	 * @param filter 过滤条件
	 * @return List 参数字典对象
	 */
	public List listByTypeAndFilter(int tenantId, String dictType, String filter) 
	{
		getHibernateTemplate().setCacheQueries(true);  
		String hql = "from TenantDictParam where tenantId=? and dictType=? and filter=? order by position desc";
		return super.listAll(hql, new Object[]{dictType, filter});
	}
	
	/**
	 * 获得某个类型下最大的ID值
	 * @param dictType
	 * @return
	 */
	public int findMaxId(int tenantId, String dictType)
	{
		String hql = "select max(id) from TenantDictParam where tenantId=? and dictType=?";
		Object id = super.uniqueResult(hql, new Object[]{tenantId, dictType});
		if(id==null)
			return 1;
		else
			return Utility.parseInt(id.toString());
	}
	
	/**
	 * 找到最大位置.
	 * 
	 * @param dictType 参数类型
	 * 
	 * @return 位置(int)
	 */
	public int findMaxPosition(int tenantId, String dictType)
	{		
		String hql = "select max(d.position) from TenantDictParam d where d.tenantId=? and d.dictType=?";
		Integer position = (Integer)super.uniqueResult(hql, new Object[]{tenantId, dictType});
		if(position!=null)
			return position.intValue();
		else
			return 0;
	}
	
	/**
	 * 找到最小位置.
	 * 
	 * @param dictType 参数类型
	 * 
	 * @return 位置(int)
	 */
	public int findMinPosition(int tenantId, String dictType)
	{		
		String hql = "select min(d.position) from TenantDictParam d where d.tenantId=? and d.dictType=?";
		Integer position = (Integer)super.uniqueResult(hql, new Object[]{tenantId, dictType});
		if(position!=null)
			return position.intValue();
		else
			return 0;
	}	
	
	/**
	 * 查找位置相同的.
	 * 
	 * @param position 位置
	 * @param dictType 参数类型
	 * @param id the id
	 * 
	 * @return List the TenantDictParam
	 */
	public List listSamePosition(int tenantId, String dictType, Integer id ,int position)
	{		
		String hql = "from TenantDictParam d where d.tenantId=? and d.dictType=? and d.position=? and (d.id>? or d.id<?)";
		return super.listAll(hql, new Object[]{tenantId, dictType, position, id, id});
	}
	
	/**
	 * 读取.
	 * 
	 * @param dictType 参数类型
	 * @param dictValue 参数值
	 * 
	 * @return TenantDictParam
	 */
	public TenantDictParam load(int tenantId, String dictType, String dictValue)
	{		
		getHibernateTemplate().setCacheQueries(true);
		String hql = "from TenantDictParam d where d.tenantId=? and d.dictType=? and d.dictValue=?";
		TenantDictParam dictParam = (TenantDictParam)super.uniqueResult(hql, new Object[]{tenantId, dictType, dictValue});
		return dictParam;
	}	
	
	/**
	 * 根据位置查找上一位.
	 * 
	 * @param position 位置
	 * 
	 * @return the dict param
	 */
	public TenantDictParam loadUpPosition(int tenantId, int position)
	{		
		String hql = "from TenantDictParam d where d.tenantId=? and d.position>? order by d.position asc";
		TenantDictParam dictParam = (TenantDictParam)super.uniqueResult(hql, new Object[]{tenantId, position});
		return dictParam;
	}	
	
	/**
	 * 根据位置查找下一位.
	 * 
	 * @param position 位置
	 * 
	 * @return the dict param
	 */
	public TenantDictParam loadDownPosition(int tenantId, int position)
	{		
		String hql = "from TenantDictParam d WHERE d.tenantId=? and d.position<? order by d.position desc";
		TenantDictParam dictParam = (TenantDictParam)super.uniqueResult(hql, new Object[]{tenantId, position});
		return dictParam;
	}	

}