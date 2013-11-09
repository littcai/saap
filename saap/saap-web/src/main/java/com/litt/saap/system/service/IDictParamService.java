package com.litt.saap.system.service;

import java.util.List;
import java.util.Locale;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.DictParam;
import com.litt.saap.system.vo.DictParamVo;

public interface IDictParamService {

	/**
	 * 保存字典管理
	 * @param dictParam DictParam对象
	 * @return 主键
	 */
	public Integer save(DictParam dictParam);

	/**
	 * 修改字典管理
	 * @param dictParam DictParam对象
	 */
	public void update(DictParam dictParam);

	/**
	 * 根据主键删除字典.
	 * @param id 主键
	 */
	public void delete(Integer id);

	/**
	 * 根据主键查找字典管理
	 * @param id 主键
	 * @return DictParam对象
	 */
	public DictParam load(Integer id);

	/**
	 * 读取.
	 * 
	 * @param dictType 参数类型
	 * @param dictValue 参数值
	 * 
	 * @return DictParam
	 */
	public DictParam load(String dictType, String dictValue);

	/**
	 * 分页查询字典管理.
	 * 
	 * @param pageParam 查询参数
	 * @return IPageList IPageList对象
	 */
	public IPageList listByPage(PageParam pageParam);

	/**
	 * 根据参数类型查询参数字典信息.
	 * 
	 * @param dictType 参数类型
	 * @return List 参数字典对象
	 */
	public List<DictParam> listByType(String dictType);
	
	/**
	 * Findy type.
	 *
	 * @param dictType the dict type
	 * @param locale the locale
	 * @return the list
	 */
	public List<DictParamVo> findyType(String dictType, Locale locale);

	/**
	 * 获得某个类型下最大的ID值
	 * @param dictType
	 * @return
	 */
	public int findMaxId(String dictType);

	/**
	 * 上移.
	 * 
	 * @param id 参数ID
	 */
	public void doUpPosition(Integer id);

	/**
	 * 下移.
	 * 
	 * @param id 参数ID
	 */
	public void doDownPosition(Integer id);

}