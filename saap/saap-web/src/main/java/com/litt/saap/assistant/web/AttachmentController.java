package com.litt.saap.assistant.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
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
import com.litt.core.io.util.FileUtils;
import com.litt.core.io.util.ZipUtils;
import com.litt.core.module.annotation.Func;
import com.litt.core.util.StringUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.assistant.po.Attachment;
import com.litt.saap.assistant.service.IAttachmentService;
import com.litt.saap.assistant.vo.AttachmentVo;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.web.util.LoginUtils;

/**
 * 
 * Attachment controller.
 * <pre><b>Description：</b>
 *    Attachment Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-04-23
 * @version 1.0
 */
@Controller
public class AttachmentController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Resource
	private IAttachmentService attachmentService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="query", moduleCode="assistant.attachment", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		String code = request.getParameter("code");
		String name = request.getParameter("name");
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond("code", code);	
		pageParam.addCond("name", name);	
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = attachmentService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/assistant/attachment/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="add", moduleCode="assistant.attachment", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/assistant/attachment/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="edit", moduleCode="assistant.attachment", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Attachment attachment = attachmentService.load(id);		
        return new ModelAndView("/assistant/attachment/edit").addObject("attachment", attachment);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="query", moduleCode="assistant.attachment", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Attachment attachment = attachmentService.load(id);		
        return new ModelAndView("/assistant/attachment/show").addObject("attachment", attachment);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="add",moduleCode="assistant.attachment")
	@RequestMapping 
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		
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
		fileUpload.setSizeMax(100 * 1024 * 1024 * 1024); //设置最大上传尺寸10G
		fileUpload.setFileSizeMax(1024 * 1024 * 1024);	//设置单个文件上传尺寸1G	
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
			//contactsBizService.doImp(file);		
			
			return new ModelAndView("jsonView").addObject("error", false);
		}
//		catch (IOException e)
//		{				
//			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "Upload failed.").addObject("errorDetail", e.getMessage());
//		}
		catch (BusiCodeException e)
		{	
			logger.error("Attachment upload failed.", e);
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", BeanManager.getMessage(e.getErrorCode(), LoginUtils.getLocale(request)));
		}
		catch (BusiException e)
		{	
			logger.error("Attachment upload failed.", e);
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "Upload failed.");
		}
		
		
				
		//attachmentService.save(srcFile, tenantId, recordId, moduleCode);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="edit",moduleCode="assistant.attachment")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Attachment attachment = attachmentService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(attachment, request.getParameterMap());
		attachmentService.update(attachment);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="assistant.attachment")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		attachmentService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="assistant.attachment")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		attachmentService.deleteBatch(ids);
	}
	
	@RequestMapping 
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		int tenantId = LoginUtils.getTenantId();
		
		String characterEncoding = request.getCharacterEncoding(); 	//编码			
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);	//内存使用4K	
		String uploadPath = tenantId + File.separator + "tmp";	//相对目录, 租户下临时目录			
		String finalPath = homePath + File.separator + uploadPath;
		HttpFileUpload fileUpload = new HttpFileUpload(homePath, uploadPath, factory);
		fileUpload.setAllowField(true); //允许表单字段
		fileUpload.setHeaderEncoding(characterEncoding); //设置头编码
		fileUpload.setSizeMax(100L * 1024 * 1024 * 1024); //设置最大上传尺寸10G
		fileUpload.setFileSizeMax(1024L * 1024 * 1024);	//设置单个文件上传尺寸1G		
		try
		{
			fileUpload.parseRequest(request);
			fileUpload.upload();	//上传文件并保存
			
			//上传成功的文件记录到数据库
			String moduleCode = fileUpload.getParameter("moduleCode");
			int recordId = Utility.parseInt(fileUpload.getParameter("recordId"));
			
			List<UploadFile> fileList = fileUpload.getSucceedFiles();
			for (UploadFile uploadFile : fileList) {
				File srcFile = new File(uploadFile.getFilePath(), uploadFile.getFileName());
				try {
					AttachmentVo attachment = attachmentService.save(srcFile, moduleCode, tenantId, recordId);
					uploadFile.setUid(attachment.getUid());
					uploadFile.setFileName(attachment.getFileName());	//文件名被重命名过
				} catch (BusiCodeException e) {
					uploadFile.setErrorCode(e.getErrorCode());
					uploadFile.setErrorMessage(BeanManager.getMessage(e.getErrorCode(), LoginUtils.getLocale(request)));
				}
			}
			return new ModelAndView("jsonView").addObject("error", false).addObject("fileList", fileList);			
		}
		catch (BusiCodeException e)
		{
			logger.error("File upload failed.", e);
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", BeanManager.getMessage(e.getErrorCode(), LoginUtils.getLocale(request)));
		}
		catch (Exception e)
		{
			logger.error("File upload failed.", e);
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", BeanManager.getMessage("attachement.error.uploadFileFailed", LoginUtils.getLocale(request)));
		}
		finally
		{
			fileUpload.dispose();
		}			
		
	}
	
	@RequestMapping 
	public ModelAndView deleteByName(@RequestParam String moduleCode, @RequestParam Integer recordId, @RequestParam String fileName) throws Exception
	{
		int tenantId = LoginUtils.getTenantId();
		
		attachmentService.deleteByName(tenantId, moduleCode, recordId, fileName);		
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping 
	public ModelAndView deleteByUid(@RequestParam String uid) throws Exception
	{
		attachmentService.deleteByUid(uid);		
		return new ModelAndView("jsonView");
	}
	
	@RequestMapping 
	public void downloadAll(@RequestParam String moduleCode, @RequestParam Integer recordId, 
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		int tenantId = LoginUtils.getTenantId();
		
		//创建临时目录
		//TODO 这里如果为了节省时间，可以用模块名+记录ID生成持久化目录，压缩好的ZIP保存在那，下次可直接用
		File destPath = new File(homePath, Integer.toString(tenantId) 
					+ File.separator + "tmp" 
					+ File.separator + moduleCode+File.separator +recordId);	//原始文件需要放到这里进行重命名
		if(!destPath.exists())
			FileUtils.createDirectory(destPath);
		
		List<Attachment> attachmentList = attachmentService.listByRecord(moduleCode, tenantId, recordId);		
		
		File[] files = new File[attachmentList.size()];		
		for (int i=0;i<attachmentList.size();i++) {
			Attachment attachment = attachmentList.get(i);
			File dir = new File(homePath, attachment.getFilePath());
			File srcFile = new File(dir, attachment.getFileName());
			File destFile = new File(destPath, attachment.getDisplayName());
			
			FileUtils.copyFile(srcFile, destFile);			
			files[i] = destFile;
		}
		//打包
		String fileName = FileUtils.currentToFileName()+".zip";
		File destFile = new File(destPath, fileName);		
		ZipUtils.batchZip(files, destFile.getAbsolutePath());
		
		super.download(response, destFile);
		
		FileUtils.deleteFile(destPath);
	}	
	
	@RequestMapping 
	public void download(@RequestParam(required=false) Integer id, @RequestParam(required=false) String uid
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		
		Attachment attachment = null;
		if(id!=null)
			attachment = attachmentService.load(id);
		else if (!StringUtils.isEmpty(uid)) {
			attachment = attachmentService.loadByUid(uid);
		}
		else {
			throw new BusiCodeException("error.invalidParam");
		}
		if(attachment==null)
		{
			throw new BusiCodeException("attachment.error.notExist");
		}
		File dir = new File(homePath, attachment.getFilePath());
		File file = new File(dir, attachment.getFileName());
		
		super.download(response, attachment.getDisplayName(), file);
	}	
	
	@RequestMapping 
  public void open(@RequestParam(required=false) Integer id, @RequestParam(required=false) String uid
      , HttpServletRequest request, HttpServletResponse response) throws Exception
  { 
    String homePath = super.getHomePath();
    
    Attachment attachment = null;
    if(id!=null)
      attachment = attachmentService.load(id);
    else if (!StringUtils.isEmpty(uid)) {
      attachment = attachmentService.loadByUid(uid);
    }
    else {
      throw new BusiCodeException("error.invalidParam");
    }
    if(attachment==null)
    {
      throw new BusiCodeException("attachment.error.notExist");
    }
    File dir = new File(homePath, attachment.getFilePath());
    File file = new File(dir, attachment.getFileName());
    String fileName = attachment.getDisplayName();
    try
    {
      fileName = URLEncoder.encode(fileName, "utf-8");
    }
    catch (UnsupportedEncodingException e)
    {
      logger.error("Encode FileName Error:", e);
      throw new RuntimeException(e);
    }
    response.reset();
    String content = "inline; filename=" + fileName;
    response.addHeader("Content-Disposition", content);
    response.setCharacterEncoding("utf-8");
    InputStream stream = null;
    try
    {
      stream = new FileInputStream(file);
      responseBinaryStream(response, this.getContentType(FilenameUtils.getExtension(fileName)), stream);     
    }
    catch (FileNotFoundException e)
    {
      logger.error("FileNotFoundException for " + file.getAbsolutePath());
      throw new RuntimeException(e);
    }   
    finally
    {
      IOUtils.closeQuietly(stream);
    }
  } 
	
	private String getContentType(String fileExt){  
    if("xls".equalsIgnoreCase(fileExt) || "xlsx".equalsIgnoreCase(fileExt))
      return  "application/vnd.ms-excel" ;    
    else if("doc".equalsIgnoreCase(fileExt) || "docx".equalsIgnoreCase(fileExt))
      return  "application/msword" ;  
    else if("pdf".equalsIgnoreCase(fileExt))
      return  "application/pdf" ;
    else if("txt".equalsIgnoreCase(fileExt))
      return  "text/plain";
    else if("jpg".equalsIgnoreCase(fileExt) || "jpeg".equalsIgnoreCase(fileExt))
      return  "image/jpeg";
    else if("gif".equalsIgnoreCase(fileExt))
      return  "image/gif";
    else if("png".equalsIgnoreCase(fileExt))
      return  "image/png";
    else {
      return "application/octet-stream";
    }  
	} 

}

