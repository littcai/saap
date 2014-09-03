
package com.littcore.saas.teamwork.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.common.BeanManager;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.io.fileupload.HttpFileUpload;
import com.litt.core.io.fileupload.UploadFile;
import com.litt.core.io.util.FileUtils;
import com.litt.core.io.util.ZipUtils;
import com.litt.core.module.annotation.Func;
import com.litt.core.util.StringUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.assistant.po.Attachment;
import com.litt.saap.assistant.po.Document;
import com.litt.saap.assistant.service.IDocumentService;
import com.litt.saap.core.web.util.LoginUtils;

/**
 * @author littcai
 *
 */
@Controller
public class DocumentController extends BaseController {
	
	@Resource
	private IDocumentService documentService;
	
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="query",moduleCode="teamwork.document")
	@RequestMapping 
	public ModelAndView index(WebRequest request) 
	{ 
		int tenantId = LoginUtils.getTenantId();
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.setPageIndex(0);
		pageParam.setPageSize(0);
		
		pageParam.addCond("tenantId", tenantId);
		
		IPageList pageList = documentService.listPage(pageParam);
		
        return new ModelAndView("/teamwork/document/index").addObject("docList", pageList.getRsList());
    }   
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		documentService.delete(id);
	}
	
	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		documentService.deleteBatch(ids);
	}
	
	@RequestMapping
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response)
	{
		int tenantId = LoginUtils.getTenantId();
		int userId = LoginUtils.getLoginOpId().intValue();
		
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
				
				List<UploadFile> fileList =  fileUpload.getSucceedFiles();
				List<Document> docList = new ArrayList<Document>(fileList.size());
				String code = fileUpload.getParameter("code");
				String name = fileUpload.getParameter("name");
				
				for (UploadFile uploadFile : fileList) {
					File file = new File(uploadFile.getFilePath(), uploadFile.getFileName());
					
					Integer docId = documentService.save(code, name, "", file, "document", 0, tenantId, userId);
					
					docList.add(documentService.load(docId));
				}
				
				return new ModelAndView("jsonView").addObject("error", false).addObject("docList", docList);
				
			}
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
			catch (Exception e)
			{
				logger.error("File upload failed.", e);
				return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", e.getMessage());
			}
			finally
			{
				fileUpload.dispose();
			}
		}	
		else {
			return new ModelAndView("jsonView").addObject("error", true).addObject("errorMessage", "No file selected.");
		}
	}

	@RequestMapping 
	public void downloadAll(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		int tenantId = LoginUtils.getTenantId();
		
		//创建临时目录
		//TODO 这里如果为了节省时间，可以用模块名+记录ID生成持久化目录，压缩好的ZIP保存在那，下次可直接用
		File destPath = new File(homePath, Integer.toString(tenantId) 
					+ File.separator + "tmp" 
					+ File.separator + "document"+ File.separator +0);	//原始文件需要放到这里进行重命名
		if(!destPath.exists())
			FileUtils.createDirectory(destPath);
		
		PageParam pageParam = new PageParam();
		pageParam.setPageIndex(0);
		pageParam.setPageSize(0);
		
		pageParam.addCond("tenantId", tenantId);
		
		List<Document> docList = documentService.listPage(pageParam).getRsList();
		
		File[] files = new File[docList.size()];		
		for (int i=0;i<docList.size();i++) {
			Document document = docList.get(i);
			File dir = new File(homePath, document.getFilePath());
			File srcFile = new File(dir, document.getFileName());
			File destFile = new File(destPath, document.getSrcFileName());
			
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
	public void download(@RequestParam(required=false) Integer id
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		String homePath = super.getHomePath();
		
		Document document = null;
		if(id!=null)
			document = documentService.load(id);
		else {
			throw new BusiCodeException("error.invalidParam");
		}
		if(document==null)
		{
			throw new BusiCodeException("attachment.error.notExist");
		}
		File dir = new File(homePath, document.getFilePath());
		File file = new File(dir, document.getFileName());
		
		super.download(response, document.getSrcFileName(), file);
	}	
}
