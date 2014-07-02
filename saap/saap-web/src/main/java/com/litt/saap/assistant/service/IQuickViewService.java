package com.litt.saap.assistant.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

public interface IQuickViewService {

	/**
	 * List page.
	 *
	 * @param sql the sql
	 * @param pageParam the page param
	 * @return the i page list
	 */
	public IPageList listPage(String sql, PageParam pageParam);

}