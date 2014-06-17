package com.litt.saap.system.service.impl;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.commons.lang.LocaleUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.context.Theme;

import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.ErrorCode;
import com.litt.core.format.FormatDateTime;
import com.litt.core.security.EncryptFailedException;
import com.litt.core.security.MessageDigestTool;
import com.litt.core.shield.vo.AutoLoginToken;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.StringUtils;
import com.litt.saap.common.vo.IUserInfo;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.Gender;
import com.litt.saap.core.common.SaapConstants.UserStatus;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.ActivationCodeDao;
import com.litt.saap.system.dao.UserExtDao;
import com.litt.saap.system.dao.UserInfoDao;
import com.litt.saap.system.dao.UserStateDao;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.po.UserExt;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.UserInfoVo;
import com.litt.saap.system.vo.UserStateVo;

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
 * @since 2013-8-29
 * @version 1.0
 */
public class UserInfoServiceImpl implements IUserInfoService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);	
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserExtDao userExtDao;
	
	@Resource
	private UserStateDao userStateDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#save(com.litt.saap.system.po.UserInfo)
	 */
	public Integer save(UserInfo userInfo)
	{
		//data validate 
		
		//MD5加密密码
		String encryptPassoword = userInfo.getPassword();
		try
		{
			encryptPassoword = MessageDigestTool.encryptMD5(encryptPassoword);
			userInfo.setPassword(encryptPassoword);
		}
		catch (EncryptFailedException e)
		{
			throw new BusiCodeException("error.login.failed", e);
		}	
		
		logger.debug("New User register, loginId:{}, email:{}", new Object[]{userInfo.getLoginId(), userInfo.getEmail()});
		
		userInfo.setCreateDatetime(new Date());
		userInfo.setUpdateDatetime(userInfo.getCreateDatetime());
		Integer userId = userInfoDao.save(userInfo);
		//同时保存一个UserExt和UserState，以简化这类数据的后续管理只要update
		UserExt userExt = new UserExt(userId);
		userExtDao.save(userExt);
		
		UserState userState = new UserState(userId, 0, 0, 0, new Date(), "");
		userStateDao.save(userState);
		
		return userId;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#update(com.litt.saap.system.po.UserInfo)
	 */
	public void update(UserInfo userInfo)
	{
		userInfo.setUpdateDatetime(new Date());
		userInfoDao.update(userInfo);
	}
	
	/**
	 * 物理删除.
	 * 1、用户注册超时未激活.
	 *
	 * @param userInfo the user info
	 */
	public void delete(UserInfo userInfo)
	{
		userExtDao.deleteById(userInfo.getId());
		userStateDao.deleteById(userInfo.getId());
		
		userInfoDao.delete(userInfo);
	}
	
	/**
	 * 物理删除.
	 * 1、用户注册超时未激活.
	 *
	 * @param userId the user id
	 */
	public void delete(Integer userId)
	{
		UserInfo userInfo = this.load(userId);
		this.delete(userInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#deleteLogic(java.lang.Integer)
	 */
	public void deleteLogic(Integer userId)
	{
		UserInfo userInfo = this.load(userId);		
		userInfo.setStatus(UserStatus.LOGIC_DELETED);
		this.update(userInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#doResume(java.lang.Integer, java.util.Locale)
	 */
	public void doResume(Integer userId)
	{
		UserInfo userInfo = this.load(userId);		
		if(userInfo.getStatus()!=UserStatus.LOGIC_DELETED)
		{
			throw new BusiCodeException("error.UserInfo.resumeFailed");
		}
		userInfo.setStatus(UserStatus.NORMAL);
		this.update(userInfo);
	}
	
	/**
	 * 用户注册.
	 *
	 * @param loginId 登录ID
	 * @param password 密码
	 * @param email the email
	 * @param loginIp 登陆IP
	 * @param locale 语言
	 * @param timeZone 时区
	 * @param theme 主题
	 * @return the user info vo
	 */
	public IUserInfo doRegister(String loginId, String password, String email, String loginIp, Locale locale, TimeZone timeZone, Theme theme)
	{
		//数据校验
		/* 
		 * 检查登录名和邮箱的重复性
		 */
		if(this.loadByLoginId(loginId)!=null)
		{
			throw new BusiCodeException("register.error.loginIdExist");
		}
		if(this.loadByEmail(email)!=null)
		{
			throw new BusiCodeException("register.error.emailExist");
		}
		
		/*
		 * 登录名黑名单检测，如admin,superadmin等都不能注册
		 */
		int timeZoneOffset = timeZone.getRawOffset() / 3600000;
		
		UserInfo userInfo = new UserInfo(loginId, password, SaapConstants.UserType.UNKNOWN, loginId, "", Gender.UNKNOWN, UserStatus.UNAUTHERIZED, locale.toString(), timeZoneOffset, theme.getName(), new Date());
		userInfo.setEmail(email);
		this.save(userInfo);
		
		IUserInfo vo = BeanCopier.copy(userInfo, UserInfoVo.class);
		return vo;
	}
	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#doLogin(java.lang.String, java.lang.String, java.lang.String, boolean, java.util.Locale)
	 */
	public LoginUserVo doLogin(String loginId, String password, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException
	{
		if(logger.isDebugEnabled())
		{
			logger.debug((new StringBuffer("Operator login..."))
					.append("LoginId:").append(loginId)									
					.toString());
		}
		UserInfo userInfo = null;
		//如果loginId是数字，且符合手机位数(11位)，则通过手机号登录
		//TODO 还可以判断是邮件地址，则通过邮箱读取
		if(StringUtils.isNumeric(loginId) && 11==loginId.length())
		{
			userInfo = this.loadByMobile(loginId);
		}
		else 
		{
			userInfo = this.loadByLoginId(loginId);
		} 
		//是否读取到用户
		if(userInfo==null)
			throw new BusiCodeException("error.login.failed");
		
		UserState userState = userStateDao.load(userInfo.getId());
		
		int loginRetryTimes = userState.getLoginRetryTimes();	//登录重试次数
		if(loginRetryTimes>=8)	//重试次数超过限定，锁定帐户
		{
			if(userInfo.getStatus()==SaapConstants.UserStatus.NORMAL)	//帐户状态为正常才更新锁定状态
			{
				userInfo.setStatus(SaapConstants.UserStatus.LOCKED);	//设置帐户状态为锁定
				this.update(userInfo);
			}
		}
		String encryptPassowrd = password;
		try
		{
			encryptPassowrd = MessageDigestTool.encryptMD5(password);	//MD5加密密码
		}
		catch (EncryptFailedException e)
		{
			throw new BusiCodeException("error.login.failed", e);
		}	
		//加密密码
		if(!encryptPassowrd.equals(userInfo.getPassword()))	//密码不匹配
		{
			loginRetryTimes++;	//重试记录数加1
			userState.setLoginRetryTimes(loginRetryTimes);
			userStateDao.update(userState);	//更新数据库中重试次数
			throw new BusiCodeException("error.login.failed");	
		}
	
		return this.doLogin(userInfo, userState, loginIp, isAutoLogin, locale);		
	}
	
	/**
	 * 自动登录.
	 *
	 * @param token the token
	 * @param loginIp 请求IP
	 * @param locale 语言
	 * @return LoginVo
	 */
	public LoginUserVo doAutoLogin(String token, String loginIp, Locale locale)
	{
		logger.debug("User try to login with token:{}", new Object[]{token});
		AutoLoginToken autoLoginToken = AutoLoginToken.fromString(token);
		//默认不启动
//		if(!autoLoginToken.getAutoLoginIp().equals(loginIp))
//		{
//			throw new BusiException(new ErrorCode("autoLogin.error.ipNotMatch", locale));
//		}
		DateTime expiredDatetime = new DateTime(autoLoginToken.getCreateDatetime()).plus(autoLoginToken.getExpiredTime());
		if(expiredDatetime.isBeforeNow())
		{
			logger.warn("autologin expired. createTime:{}, expiredTime:{}"
					, new Object[]{FormatDateTime.formatDateTime(autoLoginToken.getCreateDatetime()), FormatDateTime.formatDateTime(expiredDatetime.toDate())});
			throw new BusiCodeException("autoLogin.error.expired");
		}
		
		String loginId = autoLoginToken.getLoginId();
		String encryptedPassowrd = autoLoginToken.getEncryptedPassword();
		
		if(logger.isDebugEnabled())
		{
			logger.debug((new StringBuffer("User AUTO login..."))
					.append("LoginId:").append(loginId)									
					.toString());
		}
		UserInfo userInfo = this.loadByLoginId(loginId);
		if(userInfo==null)	//登录ID不存在
			throw new BusiCodeException("error.login.failed");
		
		UserState userState = userStateDao.load(userInfo.getId());		
		
		//加密密码
		if(!encryptedPassowrd.equals(userInfo.getPassword()))	//密码不匹配
		{
			throw new BusiCodeException("error.login.failed");	
		}
	
		return this.doLogin(userInfo, userState, loginIp, false, locale);	
	}

	/**
	 * 用户登录.
	 *
	 * @param userInfo 用户对象
	 * @param userState 用户状态对象
	 * @param loginIp 登录IP
	 * @param isAutoLogin the is auto login
	 * @param locale the locale
	 * @return the login vo
	 * @throws BusiException the busi exception
	 */
	public LoginUserVo doLogin(UserInfo userInfo, UserState userState, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException
	{		
		if(userInfo==null)
		{
			throw new BusiCodeException("error.login.failed");	
		}
		else
		{
			int status = userInfo.getStatus();
			if(UserStatus.LOGIC_DELETED == status)
				throw new BusiCodeException("error.UserInfo.logicDeleted");
			else if(UserStatus.DELETED == status)
				throw new BusiCodeException("error.UserInfo.deleted");
			else if(UserStatus.LOCKED == status)
				throw new BusiCodeException("error.UserInfo.locked");
		}
		if(logger.isDebugEnabled())
		{
			logger.debug("User:{} login success...UserName:{}", new Object[]{userInfo.getLoginId(), userInfo.getUserName()});
		}
		Integer userId = userInfo.getId();
		String loginId = userInfo.getLoginId();		
		
		IUserInfo userInfoVo = BeanCopier.copy(userInfo, UserInfoVo.class);
		
		//用户状态统计也要放到登录信息中, 这里优先设置，界面上能看到上次登录时间和上次登录IP
		UserStateVo userStateVo = new UserStateVo(userState.getCurrentTenantId()
				, userState.getTotalLoginTimes(), userState.getLoginRetryTimes(), userState.getLastLoginDatetime(), userState.getLastLoginIp());
		
		//更新用户状态和统计
		userState.setTotalLoginTimes(userState.getTotalLoginTimes()+1);
		userState.setLastLoginDatetime(new Date());
		userState.setLastLoginIp(loginIp);
		userState.setLoginRetryTimes(0);	//登录成功后重置为0
		userStateDao.update(userState);
				
		LoginUserVo loginVo = new LoginUserVo(userInfoVo, userStateVo, loginIp);
		
		//初始化权限列表
		//loginVo.initPermission(permissionCodeList);			
		
		if(isAutoLogin)
		{
			//创建自动登录令牌并保存
			AutoLoginToken autoLoginToken = new AutoLoginToken(loginIp, loginId, userInfo.getPassword());				
			loginVo.setAutoLoginToken(autoLoginToken.toString());
		}
		return loginVo;
	}

	/**
	 * Do logout.
	 *
	 * @param userId the user id
	 * @param loginIp the login ip
	 * @param autoLoginToken the auto login token
	 */
	public void doLogout(Integer userId, String loginIp, String autoLoginToken)
	{		
		//用户退出，统计相关数据	
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#load(java.lang.Integer)
	 */
	public UserInfo load(Integer userId)
	{
		return userInfoDao.load(userId);
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IUserInfoService#loadByLoginId(java.lang.String)
	 */
	public UserInfo loadByLoginId(String loginId)
	{
		return userInfoDao.load(UserInfo.class, "loginId", loginId);
	}	
	
	/**
	 * Load by email.
	 *
	 * @param email the email
	 * @return the user info
	 */
	public UserInfo loadByEmail(String email)
	{
		return userInfoDao.load(UserInfo.class, "email", email);
	}
	
	public UserInfo loadByMobile(String mobile)
	{
		return userInfoDao.load(UserInfo.class, "mobile", mobile);
	}
	
	/**
	 * Load user state.
	 *
	 * @param userId the user id
	 * @return the user state
	 */
	public UserState loadUserState(Integer userId)
	{
		return userStateDao.load(userId);
	}
	
	/**
	 * Find.
	 *
	 * @param userId the user id
	 * @return the user info vo
	 */
	public IUserInfo find(Integer userId)
	{
		UserInfo po = this.load(userId);
		if(po==null)
			throw new BusiCodeException("userInfo.error.notExist");
		else {
			IUserInfo vo = BeanCopier.copy(po, UserInfoVo.class);
			return vo;
		}
	}
}
