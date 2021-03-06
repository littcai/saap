package com.litt.saap.personal.biz.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.util.BeanCopier;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.biz.IContactsBizService;
import com.litt.saap.personal.bo.ContactsGroupBo;
import com.litt.saap.personal.dao.ContactsGroupMemberDao;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.po.ContactsGroup;
import com.litt.saap.personal.po.ContactsGroupMember;
import com.litt.saap.personal.service.IContactsGroupService;
import com.litt.saap.personal.service.IContactsService;
import com.litt.saap.personal.vo.ContactsGroupVo;
import com.litt.saap.personal.vo.ContactsVo;

/**
 * ContactsBizServiceImpl.
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
 * @since 2014年3月3日
 * @version 1.0
 */
public class ContactsBizServiceImpl implements IContactsBizService {
	@Resource
	private IContactsService contactsService;
	@Resource
	private IContactsGroupService contactsGroupService;
	@Resource
	private ContactsGroupMemberDao contactsGroupMemberDao;
	
	/**
	 * @param contacts
	 * @param contactsGroupIds
	 * @see com.litt.saap.personal.biz.IContactsBizService#save(com.litt.saap.personal.po.Contacts, java.lang.Integer[])
	 */
	@Override
	public void save(Contacts contacts, Integer[] contactsGroupIds)
	{
		Integer contactsId = contactsService.save(contacts);
		for (Integer groupId : contactsGroupIds) {
			ContactsGroupMember member = new ContactsGroupMember();			
			member.setContactsId(contactsId);
			member.setGroupId(groupId);
			member.setCreateBy(LoginUtils.getLoginOpId().intValue());
			member.setCreateDatetime(new Date());
			contactsGroupMemberDao.save(member);
		}
	}
	
	/**
	 * @param contacts
	 * @param contactsGroupIds
	 * @see com.litt.saap.personal.biz.IContactsBizService#update(com.litt.saap.personal.po.Contacts, java.lang.Integer[])
	 */
	@Override
	public void update(Contacts contacts, Integer[] contactsGroupIds)
	{
		Integer contactsId = contacts.getId();
		contactsService.update(contacts);
		//比较数据库与当前所选的差异，删除旧的，增加新的
		List<ContactsGroupMember> memberList = contactsGroupMemberDao.listByContacts(contactsId);
		List<ContactsGroupMember> deleteList = new ArrayList<ContactsGroupMember>();
		List<ContactsGroupMember> addList = new ArrayList<ContactsGroupMember>();
		//删除不存在的
		for (ContactsGroupMember contactsGroupMember : memberList) {
			boolean isFound = false;
			for (Integer contactsGroupId : contactsGroupIds) {
				if(contactsGroupMember.getGroupId()==contactsGroupId)
				{
					isFound = true;
					break;
				}
			}
			if(!isFound)
			{
				deleteList.add(contactsGroupMember);
			}
		}
		//发现新的
		for (Integer contactsGroupId : contactsGroupIds) 
		{
			boolean isFound = false;
			for (ContactsGroupMember contactsGroupMember : memberList) 
			{
				if(contactsGroupMember.getGroupId()==contactsGroupId)
				{
					isFound = true;
					break;
				}
			}
			if(!isFound)
			{
				ContactsGroupMember member = new ContactsGroupMember();
				member.setContactsId(contactsId);
				member.setGroupId(contactsGroupId);
				member.setCreateBy(LoginUtils.getLoginOpId().intValue());
				member.setCreateDatetime(new Date());
				addList.add(member);
			}
		}
		//执行操作
		for (ContactsGroupMember contactsGroupMember : deleteList) {
			contactsGroupMemberDao.delete(contactsGroupMember);
		}
		for (ContactsGroupMember contactsGroupMember : addList) {
			contactsGroupMemberDao.save(contactsGroupMember);
		}
	}
	
	/**
	 * 数据导入.
	 *
	 * @param file the file
	 */
	public void doImp(File file) throws NotLoginException
	{
		try {
			
			//HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			Workbook workbook = WorkbookFactory.create(file);
			int sheetCount = workbook.getNumberOfSheets();
			int totalCount = 0;
			for(int i=0;i<sheetCount;i++)
			{
				//HSSFSheet sheet = workbook.getSheetAt(i);	//每sheet一个部门
				Sheet sheet = workbook.getSheetAt(i);
				String groupName = sheet.getSheetName();
				//保存组
				Integer groupId = contactsGroupService.doImp(groupName);
				
				int rowCount = sheet.getLastRowNum();
				for(int j=1;j<=rowCount;j++)
				{
					//HSSFRow row = sheet.getRow(j);
					Row row = sheet.getRow(j);
					String name = row.getCell(0).getStringCellValue();
					
					Cell mobileCell = row.getCell(1);
					String mobile = "";
					if(mobileCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						mobile = String.valueOf(Double.valueOf(mobileCell.getNumericCellValue()).intValue());
					else 
						mobile = mobileCell.getStringCellValue();
					
					boolean isExist = contactsService.validateExist(mobile);
					if(!isExist)
					{
						Contacts contacts = new Contacts();
						contacts.setName(name);
						contacts.setMobile(mobile);
						contacts.setEmail("");
						contacts.setPhone("");
						contacts.setFax("");
						contacts.setGender(0);
						contacts.setAddress("");
						contacts.setZipCode("");
						Integer contactsId = contactsService.doImp(contacts);
						
						ContactsGroupMember groupMember = new ContactsGroupMember(contactsId, groupId, LoginUtils.getLoginOpId().intValue(), new Date());
						contactsGroupMemberDao.save(groupMember);
					}
				}
			}
		} catch (InvalidFormatException e) {
			throw new BusiCodeException("contacts.func.imp.error", e);
		} catch (FileNotFoundException e) {
			throw new BusiCodeException("contacts.func.imp.error", e);
		} catch (IOException e) {
			throw new BusiCodeException("contacts.func.imp.error", e);
		}	
	}
	
	/**
	 * List member by contacts.
	 *
	 * @param contactsId the contacts id
	 * @return the list
	 */
	public List<ContactsGroupMember> listMemberByContacts(int contactsId)
	{
		return contactsGroupMemberDao.listByContacts(contactsId);
	}
	
	/**
	 * Find group with members by user.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<ContactsGroupBo> findGroupWithMembersByUser(int userId)
	{
		List<ContactsGroupBo> boList = new ArrayList<ContactsGroupBo>();
		
		List<ContactsGroup> contactsGroupList = contactsGroupService.listByUser(userId);
		for (ContactsGroup contactsGroup : contactsGroupList) {
			
			
			ContactsGroupVo vo = BeanCopier.copy(contactsGroup, ContactsGroupVo.class);
			
			List<ContactsVo> contactsList = contactsService.findByGroup(vo.getId());
			
			ContactsGroupBo bo = new ContactsGroupBo(vo, contactsList);
			boList.add(bo);
		}
		return boList;
	}
	
}
