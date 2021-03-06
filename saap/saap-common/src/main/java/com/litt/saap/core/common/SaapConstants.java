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
	
	/** 
	 * 附件保存相对目录.
	 * 附件完整目录为: HOME_PATH / TENANT_ID / ATTACHMENT_PATH;
	 *  
	 */
    public static String ATTACHMENT_PATH = "attachment";	
    
    /** 
	 * 文档保存相对目录.
	 * 文档完整目录为: HOME_PATH / TENANT_ID / DOCUMENT_PATH;
	 *  
	 */
    public static String DOCUMENT_PATH = "document";	
	
	/** 默认查询条件字段前缀. */
    public static final String DEFAULT_SEARCH_PREFIX = "s_";		   
    
    /** SESSION操作员. */
    public static final String SESSION_USER = "SESSION_USER";	
    
    /** 
     * COOKIE操作员自动登录信息. 
     * 
     */
    public static final String COOKIE_USER_TOKEN = "COOKIE_USER_TOKEN";	
    
    /** SESSION中存放的验证码图片KEY. */
    public static final String SESSION_CAPTCHA = "SESSION_CAPTCHA";	
    
    /** 默认激活码有效时间(30分钟). */
    public static final long DEFAULT_ACTIVATION_CODE_VALID_TIME = 30 * 60 * 1000;	
    
    /** 自营系统APP ID. */
    public static final int PLATFORM_APP_ID = 1;	
    
    /** 自营系统APP CODE. */
    public static final String PLATFORM_APP_CODE = "PLAT-001";	
    
    /** 个人事务默认角色. */
    public static final int DEFAULT_ROLE_ID = 1;	
	
	/**
	 * 用户类型.
	 */
	public interface UserType{
		
		public static final int UNKNOWN = 0;
		public static final int PERSON = 1;	//个人用户
		public static final int COMPANY = 2;	//企业用户
		
	}
	
	/**
	 * 用户类型.
	 */
	public interface Gender{
		
		public static final int UNKNOWN = 0;
		public static final int MALE = 1;	
		public static final int FEMALE = 2;	
		
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
	
	/**
	 * 用户类型.
	 */
	public interface DefaultRoleName{
		
		public static final String ADMIN = "admin";
		public static final String MEMBER = "member";	
		
	}
	
	/**
	 * 角色状态.
	 */
	public interface RoleStatus{
		
		public static final int NORMAL = 1;
		public static final int LOGIC_DELETED = 2;
		public static final int SYSTEM_DEFINED = 9;
	}
	
	/**
	 * 租户订单类型.
	 */
	public interface TenantOrderType{
		
		public static final int NEW = 1;				//新订单
		public static final int UPGRADE = 2;		//升级
		public static final int RENEW = 3;			//续费
	}
	
	/**
	 * 租户订单状态.
	 */
	public interface TenantOrderStatus{
		
		public static final int TOBE_ACTIVATE = -2;	//待生效
		public static final int TOBE_PAY = -1;		//待支付
		public static final int ACTIVATED = 1;		//已生效
		public static final int CANCELED = 2;		//取消（主动）
		public static final int INVALID = 3;		//失效（超时被动）
		
	}
	
	/**
	 * 租户状态.
	 */
	public interface TenantStatus{
		public static final int CANCELED = -4;	//注销（主动）
		public static final int EXPIRED = -3;		//到期
		public static final int DISABLED = -2;	//禁用
		public static final int DELETED = -1;		//删除
		public static final int UNAUTHERIZED = 0;//尚未激活
		public static final int NORMAL = 1;		//启用
		
	}
	
	/**
	 * 租户隔离模式.
	 */
	public interface IsolatedMode{
		public static final int NO_ISOLATION = 1;			//不隔离，完全共享
		public static final int SCHEMA_ISOLATION= 2;		//SCHEMA隔离
		public static final int DB_ISOLATION = 3;				//数据库隔离
	}
	
	public interface TenantMemberStatus{
		
		public static final int EXPIRED = -3;		//到期
		public static final int DISABLED = -2;	//禁用
		public static final int DELETED = -1;		//删除
		public static final int UNAUTHERIZED = 0;//尚未激活
		public static final int NORMAL = 1;		//启用
		
	}
	
	public interface TodoStatus{
		
		public static final int DELAY = -2;
		public static final int CANCEL = -1;
		public static final int NEW = 1;
		public static final int IN_PROGRESS = 2;		
		public static final int DONE = 3;	
	}
	

}
