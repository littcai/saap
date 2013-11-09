package com.litt.saap.system.po;

import java.io.Serializable;

/**
 * 参数字典类型表<br>
 * 表名：dict_param_type<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-15 11:44:08
 */
public class DictParamType implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 系统ID
	        1：默认及平台
	        2：子系统ID.
	 */
	private int systemId;

	/**
	 * 参数类型.
	 */
	private String dictType;

	/**
	 * 参数类型名称.
	 */
	private String dictTypeName;

	/**
	 * 更改方式
	        1：不可修改
	        2：可增加
	        3：可修改
	        4：可删除.
	 */
	private byte alterMode;

	/**
	 * 状态(1006)
	        1：正常
	        2：屏蔽
	        9：系统.
	 */
	private byte status;

	/**
	 * 备注.
	 */
	private String remark;

	public DictParamType() {
	}

	public DictParamType(int systemId, String dictType, String dictTypeName,
			byte alterMode, byte status) {
		this.systemId = systemId;
		this.dictType = dictType;
		this.dictTypeName = dictTypeName;
		this.alterMode = alterMode;
		this.status = status;
	}

	public DictParamType(int systemId, String dictType, String dictTypeName,
			byte alterMode, byte status, String remark) {
		this.systemId = systemId;
		this.dictType = dictType;
		this.dictTypeName = dictTypeName;
		this.alterMode = alterMode;
		this.status = status;
		this.remark = remark;
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
	 * 取得 系统ID
	        1：默认及平台
	        2：子系统ID.
	 * @return 系统ID
	        1：默认及平台
	        2：子系统ID
	 */
	public int getSystemId() {
		return this.systemId;
	}

	/**
	 * 设置 系统ID
	        1：默认及平台
	        2：子系统ID.
	 * @param systemId 系统ID
	        1：默认及平台
	        2：子系统ID
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	/**  
	 * 取得 参数类型.
	 * @return 参数类型
	 */
	public String getDictType() {
		return this.dictType;
	}

	/**
	 * 设置 参数类型.
	 * @param dictType 参数类型
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	/**  
	 * 取得 参数类型名称.
	 * @return 参数类型名称
	 */
	public String getDictTypeName() {
		return this.dictTypeName;
	}

	/**
	 * 设置 参数类型名称.
	 * @param dictTypeName 参数类型名称
	 */
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	/**  
	 * 取得 更改方式
	        1：不可修改
	        2：可增加
	        3：可修改
	        4：可删除.
	 * @return 更改方式
	        1：不可修改
	        2：可增加
	        3：可修改
	        4：可删除
	 */
	public byte getAlterMode() {
		return this.alterMode;
	}

	/**
	 * 设置 更改方式
	        1：不可修改
	        2：可增加
	        3：可修改
	        4：可删除.
	 * @param alterMode 更改方式
	        1：不可修改
	        2：可增加
	        3：可修改
	        4：可删除
	 */
	public void setAlterMode(byte alterMode) {
		this.alterMode = alterMode;
	}

	/**  
	 * 取得 状态(1006)
	        1：正常
	        2：屏蔽
	        9：系统.
	 * @return 状态(1006)
	        1：正常
	        2：屏蔽
	        9：系统
	 */
	public byte getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态(1006)
	        1：正常
	        2：屏蔽
	        9：系统.
	 * @param status 状态(1006)
	        1：正常
	        2：屏蔽
	        9：系统
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**  
	 * 取得 备注.
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
