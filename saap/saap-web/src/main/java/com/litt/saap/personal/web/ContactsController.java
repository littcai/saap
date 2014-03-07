package com.litt.saap.personal.web;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.common.BeanManager;
import com.litt.core.common.Utility;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.io.fileupload.HttpFileUpload;
import com.litt.core.io.fileupload.UploadFile;
import com.litt.core.module.annotation.Func;
import com.litt.core.util.ArrayUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.biz.IContactsBizService;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.po.ContactsGroup;
import com.litt.saap.personal.po.ContactsGroupMember;
import com.litt.saap.personal.service.IContactsGroupService;
import com.litt.saap.personal.service.IContactsService;

/**
 * 
 * Contacts controller.
 * <pre><b>Description：</b>
 *    Contacts
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
@Controller
public class ContactsController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(ContactsController.class);

	@Resource
	private IContactsService contactsService;	
	@Resource
	private IContactsGroupService contactsGroupService;
	@Resource
	private IContactsBizService contactsBizService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04",moduleCode="0305", enableLog=false)
	@RequestMapping 
	public ModelAndView index(Locale locale, WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		
		//get params from request
		String searchField = request.getParameter("s_searchField");
		String searchValue = request.getParameter("s_searchValue");
					
			
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		pageParam.addCond(searchField, searchValue);
		
		IPageList pageList = contactsService.listPage(pageParam);
		
        return new ModelAndView("/personal/contacts/index")
        		.addObject("pageParam", pageParam).addObject("pageList", pageList);
	}   
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01",moduleCode="0305", enableLog=false)
	@RequestMapping
	public ModelAndView add() 
	{    
		List<ContactsGroup> contactsGroupList = contactsGroupService.listByUser(LoginUtils.getLoginOpId().intValue());
		
        return new ModelAndView("/personal/contacts/add").addObject("contactsGroupList", contactsGroupList);
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02",moduleCode="0305", enableLog=false)
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Contacts contacts = contactsService.load(id);	
		List<ContactsGroup> contactsGroupList = contactsGroupService.listByUser(LoginUtils.getLoginOpId().intValue());
		
		List<ContactsGroupMember> memberList = contactsBizService.listMemberByContacts(id);
		Integer[] memberGroupIds = new Integer[memberList.size()];
		for (int i=0;i<memberList.size();i++) {
			ContactsGroupMember contactsGroupMember = memberList.get(i);
			memberGroupIds[i] = contactsGroupMember.getGroupId();
		}
		
        return new ModelAndView("/personal/contacts/edit")
        		.addObject("contacts", contacts)
        		.addObject("contactsGroupList", contactsGroupList)
        		.addObject("memberGroupIds", memberGroupIds);
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
		Contacts contacts = contactsService.load(id);		
        return new ModelAndView("/personal/contacts/show").addObject("contacts", contacts);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="0305")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Contacts contacts = new Contacts();
		BeanUtils.populate(contacts, request.getParameterMap());			
		
		Integer[] contactsGroupIds = ArrayUtils.toInteger(request.getParameterValues("contactsGroupIds"));
		
		contactsBizService.save(contacts, contactsGroupIds);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="0305")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Contacts contacts = contactsService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(contacts, request.getParameterMap());
		
		Integer[] contactsGroupIds = ArrayUtils.toInteger(request.getParameterValues("contactsGroupIds"));
		contactsBizService.update(contacts, contactsGroupIds);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="0305")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		contactsService.delete(id);
	}
	
	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="0305")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		contactsService.deleteBatch(ids);
	}
	
	@RequestMapping
	public ModelAndView imp(HttpServletRequest request, HttpServletResponse response)
	{
		String homePath = super.getHomePath();
		//处理文件上传		
		boolean isMultipart = HttpFileUpload.isMultipartContent(request);	//是否为文件上传
		if(isMultipart)
		{			
			if(logger.isDebugEnabled())
			{
				logger.debug("处理文件上传...");
			}					
			String characterEncoding = request.getCharacterEncoding(); 	//编码			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);	//内存使用4K	
			String uploadPath = "tmp";	//相对目录			
			String finalPath = homePath + File.separator + uploadPath;
			HttpFileUpload fileUpload = new HttpFileUpload(homePath, uploadPath, factory);
			fileUpload.setAllowField(true); //允许表单字段
			fileUpload.setAllowFileTypes("");
			fileUpload.addAllowFileTypes("application/vnd.ms-excel");
			fileUpload.addAllowFileTypes("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			fileUpload.setHeaderEncoding(characterEncoding); //设置头编码
			fileUpload.setSizeMax(100 * 1024 * 1024); //设置最大上传尺寸100M
			fileUpload.setFileSizeMax(40 * 1024 * 1024);	//设置单个文件上传尺寸10M			
			try
			{
				fileUpload.parseRequest(request);
				fileUpload.upload();	//上传文件并保存
			}
			catch (Exception e)
			{
				logger.error("File upload failed.", e);
				return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", BeanManager.getMessage("contacts.func.imp.error", LoginUtils.getLocale(request)));
			}
			finally
			{
				fileUpload.dispose();
			}
			List<UploadFile> fileList =  fileUpload.getSucceedFiles();
			//这里只会上传一个文件
			UploadFile uploadFile = fileList.get(0);
			
			try
			{
				File file = new File(uploadFile.getFilePath(), uploadFile.getFileName());
				contactsBizService.doImp(file);		
				
				return new ModelAndView("jsonView").addObject("error", false);
			}
//			catch (IOException e)
//			{				
//				return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "Upload failed.").addObject("errorDetail", e.getMessage());
//			}
			catch (BusiCodeException e)
			{	
				logger.error("Contacts import failed.", e);
				return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", BeanManager.getMessage(e.getErrorCode(), LoginUtils.getLocale(request)));
			}
			catch (BusiException e)
			{	
				logger.error("Contacts import failed.", e);
				return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "Upload failed.");
			}
		}	
		else {
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "No file selected.");
		}
	}

	/**
	 * Gets the contact list.
	 *
	 * @return the contact list
	 */
	@RequestMapping 
	public ModelAndView getContactList()
	{
		List<Contacts> contactList = contactsService.listByUser(LoginUtils.getLoginOpId().intValue());
		return new ModelAndView("jsonView").addObject("contactList", contactList);
	}
	

}
