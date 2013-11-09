package com.litt.saap.system.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.litt.saap.system.po.Menu;

/**
 * 树型菜单.
 * 
 * <pre><b>Description：</b>
 *    采用递归实现
 * </pre>
 * 
 * <pre><b>Changelog：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2012-1-9
 * @version 1.0
 */
public class MenuTreeNodeVo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final Long serialVersionUID = 1L;

	private Integer menuId;

	private String menuCode = "";

	private String menuName;

	private String menuUrl;

	private int parentId = -1;

	private boolean isLeaf = true;

	private int status;

	private int position;
	
	private String iconUrl;
	
	private String imageUrl;

	private String remark;
	
	private List<MenuTreeNodeVo> subList = new ArrayList<MenuTreeNodeVo>();	
	
	public MenuTreeNodeVo(){}
		
	public void addSub(MenuTreeNodeVo subNode)
	{
		subList.add(subNode);
		if(isLeaf)
			isLeaf = false;
	}
	
	public boolean isRoot()
	{
		return parentId==0;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel()
	{
		return (menuCode.length() - 2) / 2;
	}
	

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the isLeaf
	 */
	public boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
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
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * @return the subList
	 */
	public List<MenuTreeNodeVo> getSubList() {
		return subList;
	}


	/**
	 * @param subList the subList to set
	 */
	public void setSubList(List<MenuTreeNodeVo> subList) {
		this.subList = subList;
	}

}
