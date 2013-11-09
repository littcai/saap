package com.litt.saap.core.web.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.litt.core.common.BeanManager;
import com.litt.saap.system.service.IDictParamService;
import com.litt.saap.system.util.DictParamHelper;
import com.litt.saap.system.vo.DictParamVo;

/** 
 * 
 * 参数字典复选框选项标签.
 * 
 * <pre><b>描述：</b>
 *    根据参数字典动态生成复选框选项 
 *    
 * Dependencies: dictParamService
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 * Date:2013-07-22 增加获取国际化语言的支持   
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-4-16
 * @version 1.0
 * 
 * @since 2013-07-22
 * @version 1.1
 *
 */
public class DictOptionsTag extends SimpleTagSupport
{
	
	/** 参数类型. */
	private String dictType;
	
	/** 参数值. */
	private String dictValue;
		

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException
	{		
		super.doTag();
		//2013-07-22 增加获取国际化语言的支持
		PageContext pageContext = (PageContext)super.getJspContext();
		Locale locale = (Locale)pageContext.findAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE");		
		
		JspWriter out = getJspContext().getOut();   
		
        StringBuffer sb = new StringBuffer(200);	        
		List<DictParamVo> dictParamList = DictParamHelper.getDictList(dictType, locale);
		DictParamVo dictParam = null;
		for(int i=0;i<dictParamList.size();i++)
		{
			
			dictParam = dictParamList.get(i);		
			sb.append("<option value=\"");
			sb.append(dictParam.getDictValue());
			sb.append("\" ");
			if(dictParam.getDictValue().equals(dictValue))
				sb.append("selected=\"selected\"");
			sb.append(">");
			sb.append(dictParam.getDictContent());
			sb.append("</option>");
		}
		out.println(sb.toString());
	}


	/**
	 * @return the dictType
	 */
	public String getDictType()
	{
		return dictType;
	}


	/**
	 * @param dictType the dictType to set
	 */
	public void setDictType(String dictType)
	{
		this.dictType = dictType;
	}


	/**
	 * @return the dictValue
	 */
	public String getDictValue()
	{
		return dictValue;
	}


	/**
	 * @param dictValue the dictValue to set
	 */
	public void setDictValue(String dictValue)
	{
		this.dictValue = dictValue;
	}

}
