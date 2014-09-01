package com.litt.saap.system.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.litt.saap.system.dao.ActivationCodeDao;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.service.IActivationCodeService;

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
public class ActivationCodeServiceImpl implements IActivationCodeService {
	
	@Resource
	private ActivationCodeDao activationCodeDao;
	
	/**
	 * Gen.
	 *
	 * @param userId the user id
	 * @param validTime the valid time
	 * @return the activation code
	 */
	public ActivationCode gen(Integer userId, long validTime)
	{
		return activationCodeDao.gen(userId, validTime);
	}
	
	/**
	 * Gen.
	 *
	 * @param userId the user id
	 * @param validTime the valid time
	 * @return the activation code
	 */
	public ActivationCode gen(Integer userId, long validTime, Map<String, Object> paramMap)
	{
		return activationCodeDao.gen(userId, validTime, paramMap);
	}

	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IActivationCodeService#loadAndDelete(java.lang.String)
	 */
	public ActivationCode loadAndDelete(String code)
	{
		ActivationCode entity = activationCodeDao.load(code);
		if(entity!=null)
		{
			activationCodeDao.delete(entity);
		}
		return entity;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IActivationCodeService#load(java.lang.String)
	 */
	public ActivationCode load(String code)
	{
		return activationCodeDao.load(code);
	}
	
}
