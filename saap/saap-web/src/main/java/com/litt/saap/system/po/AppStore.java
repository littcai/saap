package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 应用商店<br>
 * 表名：app_store<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 17:55:22
 */
public class AppStore implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 组件标识（全局唯一）.
	 */
	private String uuid;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 类型(3001)
	        1：个人
	        2：企业
	        3：通用.
	 */
	private int type;

	/**
	 * 授权类型(3002)
	        1：免费
	        2：收费.
	 */
	private int authType;

	/**
	 * 认证地址.
	 */
	private String authAddress;

	/**
	 * 接入类型(3003)
	        1：接入型
	        2：介绍型.
	 */
	private int accessType;

	/**
	 * 接入地址.
	 */
	private String accessAddress;

	/**
	 * 详细描述.
	 */
	private String description;

	/**
	 * 组件图标地址.
	 */
	private String iconUrl;

	/**
	 * 安全编码(数字签名).
	 */
	private String certCode;

	/**
	 * 是否审核通过.
	 */
	private boolean isChecked;

	/**
	 * 状态(3004)
	        0：未上线
	        1：正常
	        2：删除
	        3：锁定.
	 */
	private int status;

	/**
	 * 排行.
	 */
	private int position;

	/**
	 * 版本号.
	 */
	private String revision;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 上线时间.
	 */
	private Date onlineDatetime;

	public AppStore() {
	}

	public AppStore(String uuid, String name, int type, int authType,
			int accessType, String certCode, boolean isChecked, int status,
			int position, String revision, Date createDatetime) {
		this.uuid = uuid;
		this.name = name;
		this.type = type;
		this.authType = authType;
		this.accessType = accessType;
		this.certCode = certCode;
		this.isChecked = isChecked;
		this.status = status;
		this.position = position;
		this.revision = revision;
		this.createDatetime = createDatetime;
	}

	public AppStore(String uuid, String name, int type, int authType,
			String authAddress, int accessType, String accessAddress,
			String description, String iconUrl, String certCode,
			boolean isChecked, int status, int position, String revision,
			Date createDatetime, Date updateDatetime, Date onlineDatetime) {
		this.uuid = uuid;
		this.name = name;
		this.type = type;
		this.authType = authType;
		this.authAddress = authAddress;
		this.accessType = accessType;
		this.accessAddress = accessAddress;
		this.description = description;
		this.iconUrl = iconUrl;
		this.certCode = certCode;
		this.isChecked = isChecked;
		this.status = status;
		this.position = position;
		this.revision = revision;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.onlineDatetime = onlineDatetime;
	}

	/**  
	 * 取得 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * 取得 组件标识（全局唯一）.
	 * @return 组件标识（全局唯一）
	 */
	public String getUuid() {
		return this.uuid;
	}

	/**
	 * 设置 组件标识（全局唯一）.
	 * @param uuid 组件标识（全局唯一）
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**  
	 * 取得 名称.
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 取得 类型(3001)
	        1：个人
	        2：企业
	        3：通用.
	 * @return 类型(3001)
	        1：个人
	        2：企业
	        3：通用
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 设置 类型(3001)
	        1：个人
	        2：企业
	        3：通用.
	 * @param type 类型(3001)
	        1：个人
	        2：企业
	        3：通用
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**  
	 * 取得 授权类型(3002)
	        1：免费
	        2：收费.
	 * @return 授权类型(3002)
	        1：免费
	        2：收费
	 */
	public int getAuthType() {
		return this.authType;
	}

	/**
	 * 设置 授权类型(3002)
	        1：免费
	        2：收费.
	 * @param authType 授权类型(3002)
	        1：免费
	        2：收费
	 */
	public void setAuthType(int authType) {
		this.authType = authType;
	}

	/**  
	 * 取得 认证地址.
	 * @return 认证地址
	 */
	public String getAuthAddress() {
		return this.authAddress;
	}

	/**
	 * 设置 认证地址.
	 * @param authAddress 认证地址
	 */
	public void setAuthAddress(String authAddress) {
		this.authAddress = authAddress;
	}

	/**  
	 * 取得 接入类型(3003)
	        1：接入型
	        2：介绍型.
	 * @return 接入类型(3003)
	        1：接入型
	        2：介绍型
	 */
	public int getAccessType() {
		return this.accessType;
	}

	/**
	 * 设置 接入类型(3003)
	        1：接入型
	        2：介绍型.
	 * @param accessType 接入类型(3003)
	        1：接入型
	        2：介绍型
	 */
	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}

	/**  
	 * 取得 接入地址.
	 * @return 接入地址
	 */
	public String getAccessAddress() {
		return this.accessAddress;
	}

	/**
	 * 设置 接入地址.
	 * @param accessAddress 接入地址
	 */
	public void setAccessAddress(String accessAddress) {
		this.accessAddress = accessAddress;
	}

	/**  
	 * 取得 详细描述.
	 * @return 详细描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置 详细描述.
	 * @param description 详细描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 取得 组件图标地址.
	 * @return 组件图标地址
	 */
	public String getIconUrl() {
		return this.iconUrl;
	}

	/**
	 * 设置 组件图标地址.
	 * @param iconUrl 组件图标地址
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**  
	 * 取得 安全编码(数字签名).
	 * @return 安全编码(数字签名)
	 */
	public String getCertCode() {
		return this.certCode;
	}

	/**
	 * 设置 安全编码(数字签名).
	 * @param certCode 安全编码(数字签名)
	 */
	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	/**  
	 * 取得 是否审核通过.
	 * @return 是否审核通过
	 */
	public boolean isIsChecked() {
		return this.isChecked;
	}

	/**
	 * 设置 是否审核通过.
	 * @param isChecked 是否审核通过
	 */
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**  
	 * 取得 状态(3004)
	        0：未上线
	        1：正常
	        2：删除
	        3：锁定.
	 * @return 状态(3004)
	        0：未上线
	        1：正常
	        2：删除
	        3：锁定
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态(3004)
	        0：未上线
	        1：正常
	        2：删除
	        3：锁定.
	 * @param status 状态(3004)
	        0：未上线
	        1：正常
	        2：删除
	        3：锁定
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * 取得 排行.
	 * @return 排行
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * 设置 排行.
	 * @param position 排行
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**  
	 * 取得 版本号.
	 * @return 版本号
	 */
	public String getRevision() {
		return this.revision;
	}

	/**
	 * 设置 版本号.
	 * @param revision 版本号
	 */
	public void setRevision(String revision) {
		this.revision = revision;
	}

	/**  
	 * 取得 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * 设置 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * 取得 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * 设置 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * 取得 上线时间.
	 * @return 上线时间
	 */
	public Date getOnlineDatetime() {
		return this.onlineDatetime;
	}

	/**
	 * 设置 上线时间.
	 * @param onlineDatetime 上线时间
	 */
	public void setOnlineDatetime(Date onlineDatetime) {
		this.onlineDatetime = onlineDatetime;
	}

}
