package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.service.BaseService;
import com.litt.core.util.BeanCopier;
import com.litt.saap.system.dao.DictParamDao;
import com.litt.saap.system.po.DictParam;
import com.litt.saap.system.service.IDictParamService;
import com.litt.saap.system.vo.DictParamVo;

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
public class DictParamServiceImpl implements IDictParamService
{ 
	@Resource
	private DictParamDao dictParamDao;	
	
	@Resource
	private MessageSource messageSource;
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#save(com.litt.cidp.system.po.DictParam)
	 */
	public Integer save(DictParam dictParam)
	{
		return dictParamDao.save(dictParam);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#update(com.litt.cidp.system.po.DictParam)
	 */
	public void update(DictParam dictParam)
	{
		dictParamDao.update(dictParam);
	}	
   
   	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#delete(java.lang.Long)
	 */
	public void delete(Integer id)
	{				
		dictParamDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#load(java.lang.Long)
	 */
	public DictParam load(Integer id)
	{
		return dictParamDao.load(id);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#load(java.lang.String, java.lang.String)
	 */
	public DictParam load(String dictType, String dictValue)
	{
		return dictParamDao.load(dictType, dictValue);
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#listByPage(com.litt.core.dao.ql.PageParam)
	 */
	public IPageList listByPage(PageParam pageParam)
	{
		return dictParamDao.listByPage(pageParam);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#listByType(java.lang.String)
	 */
	public List<DictParam> listByType(String dictType) 
	{
		return dictParamDao.listByType(dictType);
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.IDictParamService#findyType(java.lang.String, java.util.Locale)
	 */
	public List<DictParamVo> findyType(String dictType, Locale locale) 
	{
		List<DictParam> dictParamList = dictParamDao.listByType(dictType);
		List<DictParamVo> retList = new ArrayList<DictParamVo>(dictParamList.size());
		for (DictParam dictParam : dictParamList)
		{
			DictParamVo vo = BeanCopier.copy(dictParam, DictParamVo.class);			
			vo.setDictContent(messageSource.getMessage("dictparam."+vo.getDictType()+"."+dictParam.getDictValue(), null, locale));
			retList.add(vo);
		}
		return retList;
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#findMaxId(java.lang.String)
	 */
	public int findMaxId(String dictType)
	{
		return dictParamDao.findMaxId(dictType);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#doUpPosition(java.lang.Long)
	 */
	public void doUpPosition(Integer id)
	{
		DictParam dictParam = this.load(id);
		String dictType = dictParam.getDictType();
		int position = dictParam.getPosition();
		//1、检查有没有同级的
		List sameList = dictParamDao.listSamePosition(dictType, id, position);
		int sameCount = sameList.size();
		if(sameCount>0)
		{
			dictParamDao.updateUpPosition(dictType, position, sameCount);
			//2、顺序将重复的位置加到不重复
			for(int i=0;i<sameCount;i++)
			{
				DictParam temp = (DictParam)sameList.get(i);
				temp.setPosition(position+i+1);
			}
		}
		//将该组件与比它大且最相邻的一个互换位置		
		DictParam upParam = dictParamDao.loadUpPosition(position);
		if(upParam!=null)
		{
			dictParam.setPosition(upParam.getPosition());
			upParam.setPosition(position);
			dictParamDao.update(dictParam);
			dictParamDao.update(upParam);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.litt.cidp.system.service.impl.IDictParamService#doDownPosition(java.lang.Long)
	 */
	public void doDownPosition(Integer id)
	{
		DictParam dictParam = this.load(id);
		String dictType = dictParam.getDictType();
		int position = dictParam.getPosition();
		//1、检查有没有同级的
		List sameList = dictParamDao.listSamePosition(dictType, id, position);
		int sameCount = sameList.size();
		if(sameCount>0)
		{
			dictParamDao.updateDownPosition(dictType, position, sameCount);
			//2、顺序将重复的位置加到不重复
			for(int i=0;i<sameCount;i++)
			{
				DictParam temp = (DictParam)sameList.get(i);
				temp.setPosition(position-i-1);
			}
		}
		//将该组件与比它小且最相邻的一个互换位置		
		DictParam downParam = dictParamDao.loadDownPosition(position);
		if(downParam!=null)
		{
			dictParam.setPosition(downParam.getPosition());
			downParam.setPosition(position);
			dictParamDao.update(dictParam);
			dictParamDao.update(downParam);
		}
	}	

}