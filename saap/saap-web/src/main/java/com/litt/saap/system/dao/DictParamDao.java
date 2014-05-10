package com.litt.saap.system.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.litt.core.common.Utility;
import com.litt.core.dao.GenericHibernateDao;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.DictParam;


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
public class DictParamDao extends GenericHibernateDao<DictParam, Integer>
{
	/** 日志工具. */
    private static final Log logger = LogFactory.getLog(DictParamDao.class);    
	
	/**
	 * 批量将大于该位置的组建统一加大.
	 * 
	 * @param position the position
	 * @param upPos the up pos
	 * @param dictType 参数类型
	 */
	public void updateUpPosition(String dictType, int position, int upPos)
	{
		String hql = "update DictParam d set d.position=d.position+? where d.dictType=? and d.position>?";
		super.update(hql, new Object[]{upPos, dictType, position});
	}
	
	/**
	 * 批量将大于该位置的组建统一加大.
	 * @param position
	 * @param downPos
	 * @param dictType 参数类型
	 */
	public void updateDownPosition(String dictType, int position, int downPos)
	{
		String hql = "update DictParam d set d.position=d.position-? where d.dictType=? and d.position<?";
		super.update(hql, new Object[]{downPos, dictType, position});
	}
	
	/**
	 * 分页查询字典管理.
	 * 
	 * @param pageParam 查询参数
	 * @return IPageList IPageList对象
	 */
	public IPageList listByPage(PageParam pageParam)
	{		
		String listHql = "select dictParam from DictParam dictParam"
			+"-- and dictParam.name={name}"
			;		
		return super.listPage(listHql, pageParam);
	}	
	
	/**
	 * 根据参数类型查询参数字典信息.
	 * 
	 * @param dictType 参数类型
	 * @return List 参数字典对象
	 */
	public List listByType(String dictType) 
	{
		getHibernateTemplate().setCacheQueries(true);  
		String hql = "from DictParam where dictType=? order by position desc";
		return super.listAll(hql, new String[]{dictType});
	}
	
	/**
	 * 根据参数类型查询参数字典信息.
	 *
	 * @param dictType 参数类型
	 * @param filter 过滤条件
	 * @return List 参数字典对象
	 */
	public List listByTypeAndFilter(String dictType, String filter) 
	{
		getHibernateTemplate().setCacheQueries(true);  
		String hql = "from DictParam where dictType=? and filter=? order by position desc";
		return super.listAll(hql, new String[]{dictType, filter});
	}
	
	/**
	 * 获得某个类型下最大的ID值
	 * @param dictType
	 * @return
	 */
	public int findMaxId(String dictType)
	{
		String hql = "select max(id) from DictParam where dictType=?";
		Object id = super.uniqueResult(hql, new Object[]{dictType});
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
	public int findMaxPosition(String dictType)
	{		
		String hql = "select max(d.position) from DictParam d where d.dictType=?";
		Integer position = (Integer)super.uniqueResult(hql, new Object[]{dictType});
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
	public int findMinPosition(String dictType)
	{		
		String hql = "select min(d.position) from DictParam d where d.dictType=?";
		Integer position = (Integer)super.uniqueResult(hql, new Object[]{dictType});
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
	 * @return List the DictParam
	 */
	public List listSamePosition(String dictType, Integer id ,int position)
	{		
		String hql = "from DictParam d where  d.dictType=? and d.position=? and d.id<>?";
		return super.listAll(hql, new Object[]{dictType, position, id});
	}
	
	/**
	 * 读取.
	 * 
	 * @param dictType 参数类型
	 * @param dictValue 参数值
	 * 
	 * @return DictParam
	 */
	public DictParam load(String dictType, String dictValue)
	{		
		getHibernateTemplate().setCacheQueries(true);
		String hql = "from DictParam d where d.dictType=? and d.dictValue=?";
		DictParam dictParam = (DictParam)super.uniqueResult(hql, new Object[]{dictType, dictValue});
		return dictParam;
	}	
	
	/**
	 * 根据位置查找上一位.
	 * 
	 * @param position 位置
	 * 
	 * @return the dict param
	 */
	public DictParam loadUpPosition(int position)
	{		
		String hql = "from DictParam d where d.position>? order by d.position asc";
		DictParam dictParam = (DictParam)super.uniqueResult(hql, new Object[]{position});
		return dictParam;
	}	
	
	/**
	 * 根据位置查找下一位.
	 * 
	 * @param position 位置
	 * 
	 * @return the dict param
	 */
	public DictParam loadDownPosition(int position)
	{		
		String hql = "from DictParam d WHERE d.position<? order by d.position desc";
		DictParam dictParam = (DictParam)super.uniqueResult(hql, new Object[]{position});
		return dictParam;
	}	

}