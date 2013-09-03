package com.litt.saap.system.service.impl;

import com.litt.saap.system.vo.SystemInfoVo;

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
 * @since 2013-9-2
 * @version 1.0
 */
public interface ISystemInfoService {

	/**
	 * Update.
	 *
	 * @param vo the vo
	 */
	public void update(SystemInfoVo vo);

	/**
	 * Gets the system info.
	 *
	 * @return the system info
	 */
	public SystemInfoVo getSystemInfo();

}