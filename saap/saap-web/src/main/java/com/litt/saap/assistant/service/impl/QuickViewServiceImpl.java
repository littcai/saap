package com.litt.saap.assistant.service.impl;

import javax.annotation.Resource;

import com.litt.core.dao.BaseJdbcDao;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.assistant.service.IQuickViewService;
import com.litt.saap.assistant.webservice.IQuickViewWebService;

/**
 * @author littcai
 *
 */
public class QuickViewServiceImpl implements IQuickViewService, IQuickViewWebService {
	
	@Resource
	private BaseJdbcDao jdbDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.assistant.service.impl.IQuickViewService#listPage(java.lang.String, com.litt.core.dao.ql.PageParam)
	 */
	@Override
	public IPageList listPage(String sql, PageParam pageParam)
	{
		IPageList pageList = jdbDao.listPage(sql, pageParam);
		return pageList;
	}
	

}
