package com.litt.saap.personal.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.common.Utility;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.personal.po.Note;
import com.litt.saap.personal.service.INoteService;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-10
 * @version 1.0
 */
@Controller
public class NoteController extends BaseController {
	
	@Resource
	private INoteService noteService;
	
	@Func(funcCode="query",moduleCode="personal.note", enableLog=false)
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		
		IPageList pageList = noteService.listPage(pageParam);
		
		noteService.listPage(pageParam);
		
		return new ModelAndView("/personal/note/index").addObject("pageParam", pageParam).addObject("pageList", pageList);		
	}
	
	/**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="add",moduleCode="personal.note")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Note note = new Note();
		BeanUtils.populate(note, request.getParameterMap());			
		noteService.save(note);		
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="edit",moduleCode="personal.note")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Note note = noteService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(note, request.getParameterMap());
		noteService.update(note);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="personal.note")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		noteService.delete(id);
	}
}
