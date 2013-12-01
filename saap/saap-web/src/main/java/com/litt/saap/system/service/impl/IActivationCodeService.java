package com.litt.saap.system.service.impl;

import java.util.Map;

import com.litt.saap.system.po.ActivationCode;

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
 * @since 2013-11-20
 * @version 1.0
 */
public interface IActivationCodeService {
	
	/**
	 * Gen.
	 *
	 * @param userId the user id
	 * @param validTime the valid time
	 * @return the activation code
	 */
	public ActivationCode gen(Integer userId, long validTime);
	
	/**
	 * Gen.
	 *
	 * @param userId the user id
	 * @param validTime the valid time
	 * @return the activation code
	 */
	public ActivationCode gen(Integer userId, long validTime, Map<String, Object> paramMap);

	/**
	 * 读取并删除.
	 * 注：后台需使用MyISAM引擎
	 *
	 * @param code the code
	 * @return the activation code
	 */
	public ActivationCode loadAndDelete(String code);
	
	/**
	 * Load.
	 *
	 * @param code the code
	 * @return the activation code
	 */
	public ActivationCode load(String code);

}