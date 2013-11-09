package com.litt.saap.personal.web;

import java.util.Date;

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
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.util.DateUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.saap.core.common.SaapConstants.TodoStatus;
import com.litt.saap.personal.po.Todo;
import com.litt.saap.personal.service.ITodoService;

/**
 * 待办事项.
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
 * @since 2013-9-12
 * @version 1.0
 */
@Controller
public class TodoController extends BaseController {
	
	@Resource
	private ITodoService todoService;
	
	@Func(moduleCode="0302", funcCode="04", enableLog=false)
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws NotLoginException
	{
		String filter = request.getParameter("filter");
		
		PageParam pageParam = new PageParam();
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		if("done".equals(filter))
		{
			pageParam.addCond("status", TodoStatus.DONE);
		}
		else if("today".equals(filter))
		{
			pageParam.addCond("startTodoTime", DateUtils.getStartOfDay(new Date()));
			pageParam.addCond("endTodoTime", DateUtils.getEndOfDay(new Date()));
		}
		else if("overdue".equals(filter))
		{
			//只有NEW跟InProgress状态的才在overdue中显示
			pageParam.addCond("status", TodoStatus.NEW);
			pageParam.addCond("statusExt", TodoStatus.IN_PROGRESS);
			pageParam.addCond("endTodoTime", new Date());
		}
		
		IPageList pageList = todoService.listPage(pageParam);
		
		return new ModelAndView("/personal/todo/index").addObject("pageList", pageList);		
	}
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@RequestMapping
	public ModelAndView add() 
	{        
        return new ModelAndView("/personal/todo/add");
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Todo todo = todoService.load(id);		
        return new ModelAndView("/personal/todo/edit").addObject("todo", todo);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Todo todo = todoService.load(id);		
        return new ModelAndView("/personal/todo/show").addObject("todo", todo);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Todo todo = new Todo();
		BeanUtils.populate(todo, request.getParameterMap());		
		
		todoService.save(todo);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Todo todo = todoService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(todo, request.getParameterMap());
		todoService.update(todo);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@RequestMapping 
	public void updateContent(@RequestParam Integer id, @RequestParam String content) throws Exception
	{
		Todo todo = todoService.load(id);
		todo.setContent(content);
		
		todoService.update(todo);
	}
	
	/**
	 * Update status.
	 *
	 * @param id the id
	 * @param status the status
	 * @throws Exception the exception
	 */
	@RequestMapping 	
	public void updateStatus(@RequestParam Integer id, @RequestParam Integer status) throws Exception
	{		
		todoService.updateStatus(id, status);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		todoService.delete(id);
	}

}
