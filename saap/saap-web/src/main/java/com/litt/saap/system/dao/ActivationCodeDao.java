package com.litt.saap.system.dao;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.core.exception.BusiException;
import com.litt.core.uid.UUID;
import com.litt.core.util.JsonUtils;
import com.litt.core.util.StringUtils;
import com.litt.saap.system.po.ActivationCode;

/**
 * 激活码.
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
 * @since 2013-9-24
 * @version 1.0
 */
public class ActivationCodeDao extends GenericHibernateDao<ActivationCode, String> {
	
	public ActivationCode gen(int userId, long validTime)
	{
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, "-");
		
		Date expiredDatetime = new DateTime().plus(validTime).toDate();
		
		ActivationCode entity = new ActivationCode();
		entity.setId(uuid);
		entity.setUserId(userId);
		entity.setModuleCode("");
		entity.setParams("");
		entity.setSecurityKey("");
		entity.setExpiredDatetime(expiredDatetime);
		
		super.save(entity);
		return entity;
	}
	
	public ActivationCode gen(int userId, long validTime, Map<String, Object> paramMap)
	{
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, "-");
		
		String params = "";
		try {
			params = JsonUtils.toJSON(paramMap);
		} catch (IOException e) {
			throw new BusiException("convert activation code's params error.", e);
		}
		
		Date expiredDatetime = new DateTime().plus(validTime).toDate();
		
		ActivationCode entity = new ActivationCode();
		entity.setId(uuid);
		entity.setUserId(userId);
		entity.setModuleCode("");
		entity.setParams(params);
		entity.setSecurityKey("");
		entity.setExpiredDatetime(expiredDatetime);
		
		super.save(entity);
		return entity;
	}
	
	

}
