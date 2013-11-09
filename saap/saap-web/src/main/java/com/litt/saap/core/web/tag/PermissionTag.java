package com.litt.saap.core.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.litt.core.common.CoreConstants;
import com.litt.core.shield.vo.ILoginVo;

/** 
 * 
 * 功能权限标签.
 * 
 * <pre><b>描述：</b>
 *    实现对页面功能权限的管理，如果没有对应权限，则标签中的内容不输出。 
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-4-10
 * @version 1.0
 *
 */
public class PermissionTag extends BodyTagSupport
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 权限编号. */
	private String code;

	/**
	 * 在解析标签时判断权限.
	 * 
	 * @return 是否需要处理标签内容
	 * 
	 * @throws JspException the jsp exception
	 */
	public int doStartTag() throws JspException
	{		
		super.doStartTag();
		ILoginVo loginVo = (ILoginVo)this.pageContext.findAttribute(CoreConstants.SESSION_OPER);	//获取当前登录操作员信息
		if(loginVo!=null)
		{
			boolean withPermission = loginVo.withPermission(code);
			if(withPermission)
			{
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}
	
	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}	

}
