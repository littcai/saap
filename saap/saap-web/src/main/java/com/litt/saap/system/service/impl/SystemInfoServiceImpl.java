package com.litt.saap.system.service.impl;

import com.litt.saap.system.service.ISystemInfoService;
import com.litt.saap.system.vo.SystemInfoVo;

/**
 * 系统信息服务.
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
public class SystemInfoServiceImpl implements ISystemInfoService {
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.ISystemInfoService#update(com.litt.saap.system.vo.SystemInfoVo)
	 */
	public void update(SystemInfoVo vo)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.ISystemInfoService#getSystemInfo()
	 */
	public SystemInfoVo getSystemInfo()
	{
		SystemInfoVo vo = new SystemInfoVo();		
		//这里应读取数据最新信息
		return vo;
	}

}
