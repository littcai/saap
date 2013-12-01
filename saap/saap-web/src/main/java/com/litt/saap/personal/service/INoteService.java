package com.litt.saap.personal.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.saap.personal.po.Note;

public interface INoteService {

	public Integer save(Note note) throws NotLoginException;

	public void update(Note note);

	public void delete(Integer id);

	public Note load(Integer id);

	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);

}