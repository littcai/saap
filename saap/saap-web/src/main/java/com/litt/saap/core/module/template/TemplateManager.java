package com.litt.saap.core.module.template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.litt.core.util.Assert;
import com.litt.core.util.ResourceUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 模板.
 * 
 * <pre><b>描述：</b>
 *    FreeMarker 自带的几个 TemplateLoader 分别是：
 *    ClassTemplateLoader ：基于类路径的模版加载器
 *    FileTemplateLoader ：基于文件目录的模版加载器
 *    MultiTemplateLoader ：多种加载器的混合
 *    StringTemplateLoader ：基于字符串的模版加载器
 *    URLTemplateLoader ：基于 URL 的模版加载器
 *    WebappTemplateLoader ：基于 Web 上下文的模版加载器
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-11-12
 * @version 1.0
 */
public class TemplateManager {
	
	private Configuration templateConfig = new Configuration(); 
	
	/**
	 * Inits the.
	 *
	 * @param templatePath the template path
	 * @throws ConfigurationException the configuration exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateModelException the template model exception
	 */
	public void init(String templatePath) throws ConfigurationException, IOException, TemplateModelException
	{		
		Assert.notEmpty(templatePath);
		File dir = new File(templatePath);		
		init(dir);
	}

	/**
	 * @param dir
	 * @throws IOException
	 * @throws ConfigurationException
	 * @throws TemplateModelException
	 */
	public void init(File dir) throws IOException, ConfigurationException,
			TemplateModelException {
		//避免目标目录不存在，强制创建一个
		if(!dir.exists())
			dir.mkdirs();
		TemplateLoader classpathLoader = new FileTemplateLoader(ResourceUtils.getFile("classpath:template"));	//默认从程序的classpath目录下加载
		TemplateLoader dirLoader = new FileTemplateLoader(dir);
		
		MultiTemplateLoader loads = new MultiTemplateLoader(new TemplateLoader[]{classpathLoader, dirLoader});
		
		templateConfig.setTemplateLoader(loads);  
		//templateConfig.setDirectoryForTemplateLoading(dir);
		templateConfig.setDefaultEncoding("UTF-8"); 
		templateConfig.setClassicCompatible(true);	//允许NULL值
		//templateConfig.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));	//设置缓存 
		BeansWrapper wrapper = (BeansWrapper)BeansWrapper.BEANS_WRAPPER;   
		wrapper.setExposureLevel(BeansWrapper.EXPOSE_ALL);
		TemplateHashModel tempStatics = wrapper.getStaticModels();
		PropertiesConfiguration config = new PropertiesConfiguration("freemarkerstatic.properties");
		Iterator<String> keyIterator = config.getKeys();
		while(keyIterator.hasNext())
		{
			String key = keyIterator.next();
			templateConfig.setSharedVariable(key, tempStatics.get(config.getString(key)));
		}		
		// 指定模版如何查看数据模型.
		templateConfig.setObjectWrapper(new DefaultObjectWrapper());	
		
		//初始化基础属性映射
		templateConfig.setSharedVariable("static", ((BeansWrapper)templateConfig.getObjectWrapper()).getStaticModels());  
		templateConfig.setSharedVariable("thread", useClass("java.lang.Thread"));  
		templateConfig.setSharedVariable("system", useClass("java.lang.System")); 
	}
	
	/**
	 * 添加全局共享变量.
	 *
	 * @param name the name
	 * @param obj the obj
	 */
	public void addSharedVariable(String name, Object obj)throws TemplateModelException
	{
		templateConfig.setSharedVariable(name, obj); 
	}
	
	//拿到静态Class的Model  
	public TemplateModel useClass(String className) throws TemplateModelException  
	{  
	    BeansWrapper wrapper = (BeansWrapper) templateConfig.getObjectWrapper();  
	    TemplateHashModel staticModels = wrapper.getStaticModels();  
	    return staticModels.get(className);  
	}
	
	//拿到目标对象的model  
	public TemplateModel useObjectModel(Object target) throws TemplateModelException  
	{  
	    ObjectWrapper wrapper = templateConfig.getObjectWrapper();  
	    TemplateModel model = wrapper.wrap(target);  
	    return model;         
	}  
	  
	//拿到目标对象某个方法的Model    
	public TemplateModel useObjectMethod(Object target, String methodName) throws TemplateModelException  
	{     
	    TemplateHashModel model = (TemplateHashModel) useObjectModel(target);  
	    return model.get(methodName);         
	}  
	
	/**
	 * 生成字符串.
	 *
	 * @param templateName 模板名
	 * @param propMap 属性映射
	 * @return the string
	 */
	public String genString(String templateName, Map<String, Object> propMap) throws IOException, TemplateException
	{		
		Template template = templateConfig.getTemplate(templateName);
		template.setEncoding("UTF-8");
		
		StringWriter writer = new StringWriter();
		template.process(propMap, writer);
		return writer.toString();
	}
	
	public static void main(String[] args) throws Exception
	{
		TemplateManager manager = new TemplateManager();
		manager.init(ResourceUtils.getFile("classpath:template"));
		
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("email", "littcai@hotmail.com");
		propMap.put("url", "http://localhost");
		propMap.put("code", "123456");
		propMap.put("systemTitle", "测试系统");
		
		String result = manager.genString("register.ftl", propMap);
		System.out.println(result);
		
	}

}
