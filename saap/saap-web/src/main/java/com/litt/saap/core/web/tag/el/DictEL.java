package com.litt.saap.core.web.tag.el;

import com.litt.saap.system.util.DictParamHelper;


/** 
 * 
 * 表单EL.
 * 
 * <pre><b>描述：</b>
 *    处理checkbox、select、radio等一些特殊表单控件
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2008-12-08
 * @version 1.0
 *
 */
public class DictEL extends com.litt.core.web.tag.el.FormEL
{		
	/**
	 * 生成参数字典值.
	 * (需注入dictParamService)
	 * 
	 * @param dictValue 参数值
	 * @param dictType 参数类型
	 */
	public static String genDictContent(String dictType,String dictValue)
	{		
		return DictParamHelper.getDictContent(dictType, dictValue);
	}
	
}
