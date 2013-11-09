package com.litt.saap.system.util;

import java.util.List;
import java.util.Locale;

import com.litt.core.common.BeanManager;
import com.litt.saap.system.po.DictParam;
import com.litt.saap.system.service.IDictParamService;
import com.litt.saap.system.vo.DictParamVo;

/**
 * 参数字典辅助类.
 * 
 * <pre><b>描述：</b>
 *    用于在Spring容器外使用参数字典服务
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2011-4-18
 * @version 1.0
 */
public class DictParamHelper {

	/**
	 * 生成参数字典值.
	 * (需注入dictParamService)
	 * 
	 * @param dictValue 参数值
	 * @param dictType 参数类型
	 */
	public static String getDictContent(String dictType, String dictValue)
	{		
		IDictParamService dictParamService = BeanManager.getBean("dictParamService", IDictParamService.class); 
		DictParam dictParam = dictParamService.load(dictType, dictValue);
		if(dictParam!=null)
			return dictParam.getDictContent();			
		else
			return "";
	}	
	
	/**
	 * 获得参数字典列表.
	 *
	 * @param dictType 参数类型
	 * @param locale 语言
	 * @return List<DictParamVo>
	 */
	public static List<DictParamVo> getDictList(String dictType, Locale locale)
	{
		IDictParamService dictParamService = BeanManager.getBean("dictParamService", IDictParamService.class); 
		List<DictParamVo> dictParamList = dictParamService.findyType(dictType, locale);
		return dictParamList;
	}
	
}
