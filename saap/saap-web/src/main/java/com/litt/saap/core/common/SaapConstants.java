package com.litt.saap.core.common;

/**
 * 系统常量信息.
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
public class SaapConstants {
	
	public static String HOME_PATH = "";
	
	/** 默认查询条件字段前缀. */
    public static final String DEFAULT_SEARCH_PREFIX = "s_";		
    
    /** SESSION操作员. */
    public static final String SESSION_USER = "SESSION_USER";	
    
    /** 
     * COOKIE操作员系统ID. 
     * 
     * 在cookie中存在该ID号即认为该操作员可以自动登录
     * 
     */
    public static final String COOKIE_USER_TOKEN = "COOKIE_USER_TOKEN";	
    
    /** SESSION中存放的验证码图片KEY. */
    public static final String SESSION_CAPTCHA = "SESSION_CAPTCHA";	
	
	/**
	 * 用户类型.
	 */
	public interface UserType{
		
		public static final int UNKNOWN = 0;
		public static final int PERSON = 1;	//个人用户
		public static final int COMPANY = 2;	//企业用户
		
	}
	
	/**
	 * 用户状态.
	 */
	public interface UserStatus{
		
		public static final int UNAUTHERIZED = 0;
		public static final int NORMAL = 1;
		public static final int LOGIC_DELETED = 2;
		public static final int DELETED = 3;		
		public static final int LOCKED = 4;	
		
	}
	
	

}
