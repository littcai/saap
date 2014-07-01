package com.litt.saap.common.vo;

import com.litt.core.shield.vo.BaseLoginVo;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.system.vo.TenantVo;
import com.litt.saap.system.vo.UserStateVo;

/**
 * 
 * 
 * 登录用户对象.
 * 
 * <pre><b>描述：</b>
 *    该对象用来存储当前登录用户的相关信息
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-09-02
 * @version 1.0
 *
 */
public class LoginUserVo extends BaseLoginVo implements ILoginVo
{
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户类型.
	 */
	private int userType;
	
	/**
	 * 昵称.
	 */
	private String nickName;

	/**
	 * 性别
	        0:Unknown
	        1:Male
	        2:Female.
	 */
	private int gender;

	/**
	 * 电子邮件.
	 */
	private String email;

	/**
	 * 手机号.
	 */
	private String mobile;

	/**
	 * 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定.
	 */
	private int status;
	
	/**
	 * 头像地址.
	 */
	private String headImgUrl;
	
	private UserStateVo userState;

	/** 租户信息. */
	private TenantVo tenant;	
	
	/**
	 * 构造函数.
	 * 
	 * @param userId 登录用户ID
	 * @param loginId 登录名
	 * @param userName 操作员名称
	 * @param loginIp 登录IP
	 */
	public LoginUserVo(IUserInfo userInfo, UserStateVo userState, String loginIp)
	{
		super(userInfo.getId().longValue(), userInfo.getLoginId(), userInfo.getUserName(), loginIp);
		super.setStatus(userInfo.getStatus());
		super.setLocale(userInfo.getLocale());
		super.setTimezone(userInfo.getTimezone());	
		super.setTheme(userInfo.getTheme());
		
		this.userType = userInfo.getUserType();
		this.nickName = userInfo.getNickName();
		this.gender = userInfo.getGender();
		this.email = userInfo.getNickName();
		this.mobile = userInfo.getMobile();
		this.headImgUrl = userInfo.getHeadImgUrl();
		this.userState = userState;
	}
	
	/**
	 * @return the tenant
	 */
	public int getTenantId() {
		return tenant==null?-1:tenant.getId();
	}

	/**
	 * Sets the tenant.
	 *
	 * @param tenant the new tenant
	 */
	public void setTenant(TenantVo tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * @return the tenant
	 */
	public TenantVo getTenant() {
		return tenant;
	}

	/**
	 * @return the userState
	 */
	public UserStateVo getUserState() {
		return userState;
	}

	/**
	 * @param userState the userState to set
	 */
	public void setUserState(UserStateVo userState) {
		this.userState = userState;
	}

	/**
	 * @return the userType
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

}
