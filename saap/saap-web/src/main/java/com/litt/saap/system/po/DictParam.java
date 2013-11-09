package com.litt.saap.system.po;

import java.io.Serializable;

/**
 * 参数字典表<br>
 * 表名：dict_param<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-15 11:44:08
 */
public class DictParam implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 参数类型.
	 */
	private String dictType;

	/**
	 * 参数值.
	 */
	private String dictValue;

	/**
	 * 参数描述.
	 */
	private String dictContent;

	/**
	 * 排序.
	 */
	private int position;

	/**
	 * 状态(1007)
	        1：正常
	        2：屏蔽
	        9：系统.
	 */
	private byte status;

	/**
	 * 备注.
	 */
	private String remark;

	public DictParam() {
	}

	public DictParam(String dictType, String dictValue, String dictContent,
			int position, byte status) {
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictContent = dictContent;
		this.position = position;
		this.status = status;
	}

	public DictParam(String dictType, String dictValue, String dictContent,
			int position, byte status, String remark) {
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.dictContent = dictContent;
		this.position = position;
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
	 * 取得 参数值.
	 * @return 参数值
	 */
	public String getDictValue() {
		return this.dictValue;
	}

	/**
	 * 设置 参数值.
	 * @param dictValue 参数值
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	/**  
	 * 取得 参数描述.
	 * @return 参数描述
	 */
	public String getDictContent() {
		return this.dictContent;
	}

	/**
	 * 设置 参数描述.
	 * @param dictContent 参数描述
	 */
	public void setDictContent(String dictContent) {
		this.dictContent = dictContent;
	}

	/**  
	 * 取得 排序.
	 * @return 排序
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * 设置 排序.
	 * @param position 排序
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**  
	 * 取得 状态(1007)
	        1：正常
	        2：屏蔽
	        9：系统.
	 * @return 状态(1007)
	        1：正常
	        2：屏蔽
	        9：系统
	 */
	public byte getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态(1007)
	        1：正常
	        2：屏蔽
	        9：系统.
	 * @param status 状态(1007)
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
