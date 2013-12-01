
package com.litt.saap.personal.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.StringUtils;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.dao.NoteDao;
import com.litt.saap.personal.po.Note;
import com.litt.saap.personal.service.INoteService;

/**
 * @author littcai
 *
 */
public class NoteServiceImpl implements INoteService {
	
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	
	@Resource
	private NoteDao noteDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.personal.service.impl.INoteService#save(com.litt.saap.personal.po.Note)
	 */
	@Override
	public Integer save(Note note) throws NotLoginException
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();
		String content = note.getContent();
		int length = content.length();
		length = length<50?length:50;
		String title = StringUtils.substring(content, 0, length);
		note.setTitle(title);
		note.setCreateUserId(loginVo.getOpId().intValue());
		note.setCreateDatetime(new Date());
		note.setUpdateDatetime(note.getCreateDatetime());
		return noteDao.save(note);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.personal.service.impl.INoteService#update(com.litt.saap.personal.po.Note)
	 */
	@Override
	public void update(Note note)
	{
		note.setUpdateDatetime(new Date());
		noteDao.update(note);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.personal.service.impl.INoteService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id)
	{
		Note note = noteDao.load(id);
		noteDao.delete(note);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.personal.service.impl.INoteService#load(java.lang.Integer)
	 */
	@Override
	public Note load(Integer id)
	{
		return noteDao.load(id);
	}
	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.personal.service.impl.INoteService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	@Override
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Note obj"
			+ "-- and obj.createUserId={createUserId}"
			+ "-- and obj.updateDatetime>={startTime}"
			+ "-- and obj.updateDatetime<={endTime}"
			+ "-- order by updateDatetime desc"
			;	
		return noteDao.listPage(listHql, pageParam);
	}

}
