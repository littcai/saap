BEGIN;
DELETE FROM MENU;
-- domain:系统工具
SET @TEMP_DOMAIN_SEQ = 0;
-- domain:系统管理
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('01','系统管理', 0, 0 , '', 1, '包括配置系统运行时参数，用户管理，角色管理，操作日志查询，系统授权证书等功能。');
SELECT @TEMP_DOMAIN_SEQ_01:=MAX(MENU_ID) FROM MENU;
-- --module:系统配置     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0101','', @TEMP_DOMAIN_SEQ_01, 1, '/system/systemInfo/index.do', 1, '配置信息运行参数，包括系统信息，邮件服务器配置，文件上传下载配置等。');
-- --module:操作员管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0102','', @TEMP_DOMAIN_SEQ_01, 1, '/system/operator/index.do', 1, '维护系统操作员信息，配置操作员的相关角色');
-- --module:角色管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0103','', @TEMP_DOMAIN_SEQ_01, 1, '/system/role/index.do', 1, '管理系统用户角色，通过角色为用户分配权限。');
-- --module:操作日志     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0107','', @TEMP_DOMAIN_SEQ_01, 1, '/system/opLog/index.do', 1, '用户操作日志查询');
-- --module:部门     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0108','部门管理', @TEMP_DOMAIN_SEQ_01, 1, '/system/department/index.do', 1, '管理部门信息。');
-- --module:IP访问控制     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0114','', @TEMP_DOMAIN_SEQ_01, 1, '/system/ipAccessLimit/index.do', 1, '配置可访问系统的IP范围，非授权IP将无法访问本系统。');
-- --module:职务管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0115','', @TEMP_DOMAIN_SEQ_01, 1, '/system/job/index.do', 1, '管理职务信息。');
-- --module:在线用户     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0116','在线用户管理', @TEMP_DOMAIN_SEQ_01, 1, '/system/onlineOperator/index.do', 1, '查询所有在线用户，可强制踢出用户。');
-- --module:授权证书     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0117','', @TEMP_DOMAIN_SEQ_01, 1, '/system/license/index.do', 1, '系统使用授权证书管理');
-- --module:上传文件     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0118','上传文件管理', @TEMP_DOMAIN_SEQ_01, 1, '/system/upload/index.do', 1, '上传文件管理');
-- --module:数据清理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0119','', @TEMP_DOMAIN_SEQ_01, 1, '/system/cleanup/index.do', 1, '配置数据清理规则，满足规则的数据将被系统自动清理。');
-- --module:数据备份     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0120','', @TEMP_DOMAIN_SEQ_01, 1, '/system/backup/index.do', 1, '配置数据备份规则，用于备份指定范围数据，指定数据表，整个数据库。');
-- domain:系统辅助
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('02','系统辅助', 0, 0 , '', 1, '包含一些用于系统辅助的扩展功能，如公告、FAQ等。');
SELECT @TEMP_DOMAIN_SEQ_02:=MAX(MENU_ID) FROM MENU;
-- --module:FAQ分类     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0205','FAQ分类管理', @TEMP_DOMAIN_SEQ_02, 1, '/assistant/faqCategory/index.do', 1, 'FAQ分类管理');
-- --module:FAQ     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0206','FAQ管理', @TEMP_DOMAIN_SEQ_02, 1, '/assistant/faq/index.do', 1, 'FAQ管理');
-- --module:站内搜索     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0207','站内搜索', @TEMP_DOMAIN_SEQ_02, 1, '/assistant/siteSearch/index.do', 1, '站内全文搜索，可检索内容：公告，FAQ');
-- domain:个人门户
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('03','个人门户', 0, 0 , '', 1, '个人门户，为用户提供个人事务管理及定制化功能');
SELECT @TEMP_DOMAIN_SEQ_03:=MAX(MENU_ID) FROM MENU;
-- --module:Note     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0301','笔记本', @TEMP_DOMAIN_SEQ_03, 1, '/personal/note/index.do', 1, 'Personal Note');
-- --module:Todo     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0302','代办事项', @TEMP_DOMAIN_SEQ_03, 1, '/personal/todo/index.do', 1, 'todo list.');
-- --module:Calendar     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0303','日程计划', @TEMP_DOMAIN_SEQ_03, 1, '/personal/calendar/index.do', 1, 'Calendar');
-- --module:Document     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0304','文档', @TEMP_DOMAIN_SEQ_03, 1, '/personal/document/index.do', 1, 'Document');
-- --module:Contacts     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0305','联系人', @TEMP_DOMAIN_SEQ_03, 1, '/personal/contacts/index.do', 1, 'Contacts');
-- domain:综合报表
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('09','综合报表', 0, 0 , '', 1, '综合报表');
SELECT @TEMP_DOMAIN_SEQ_09:=MAX(MENU_ID) FROM MENU;
-- --module:报表分类     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0901','报表分类管理', @TEMP_DOMAIN_SEQ_09, 1, '/report/reportCategory/index.do', 1, '报表分类管理');
-- --module:报表     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('0902','报表管理', @TEMP_DOMAIN_SEQ_09, 1, '/report/report/index.do', 1, '报表管理');
-- domain:CRM
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('11','CRM', 0, 0 , '', 1, '');
SELECT @TEMP_DOMAIN_SEQ_11:=MAX(MENU_ID) FROM MENU;
-- --module:客户管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('1101','客户', @TEMP_DOMAIN_SEQ_11, 1, '/crm/customer/index.do', 1, 'Customer Management');
-- --module:联系人管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('1102','联系人', @TEMP_DOMAIN_SEQ_11, 1, '/crm/custContacts/index.do', 1, 'Customer Contacts Management');
-- --module:客户往来     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('1103','客户往来', @TEMP_DOMAIN_SEQ_11, 1, '/crm/custActivy/index.do', 1, 'Customer Activities Management');
-- --module:客户事件     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('1104','客户事件', @TEMP_DOMAIN_SEQ_11, 1, '/crm/custEvent/index.do', 1, 'Customer Events Management');
-- domain:消息中心
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('80','消息中心', 0, 0 , '', 1, '');
SELECT @TEMP_DOMAIN_SEQ_80:=MAX(MENU_ID) FROM MENU;
-- --module:短信发件箱     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('8001','短信发件箱', @TEMP_DOMAIN_SEQ_80, 1, '/message/smsOut/index.do', 1, '短信发件箱');
-- --module:短信收件箱     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('8002','短信收件箱', @TEMP_DOMAIN_SEQ_80, 1, '/message/smsIn/index.do', 1, '短信收件箱');
-- --module:公告     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('8003','公告管理', @TEMP_DOMAIN_SEQ_80, 1, '/message/affiche/index.do', 1, '系统公告管理');
-- --module:用户反馈     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('8004','反馈管理', @TEMP_DOMAIN_SEQ_80, 1, '/message/feedback/index.do', 1, '用户反馈管理');
-- domain:系统管理
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('90','系统管理', 0, 0 , '', 1, 'system management for tenant');
SELECT @TEMP_DOMAIN_SEQ_90:=MAX(MENU_ID) FROM MENU;
-- --module:空间管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('9001','空间管理', @TEMP_DOMAIN_SEQ_90, 1, '/system/tenant/index.do', 1, 'Workspace Management');
-- --module:成员管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('9002','成员管理', @TEMP_DOMAIN_SEQ_90, 1, '/system/tenantMember/index.do', 1, 'Member Management');
-- --module:组织架构     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('9003','组织架构', @TEMP_DOMAIN_SEQ_90, 1, '/system/userGroup/index.do', 1, 'Group Management');
-- --module:角色权限     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('9004','角色权限', @TEMP_DOMAIN_SEQ_90, 1, '/system/role/index.do', 1, 'Role and privilege Management');
-- --module:订单管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('9005','订单管理', @TEMP_DOMAIN_SEQ_90, 1, '/system/tenantOrder/index.do', 1, '工作空间订单管理');
-- domain:协作
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('teamwork','协作', 0, 0 , '', 1, '');
SELECT @TEMP_DOMAIN_SEQ_teamwork:=MAX(MENU_ID) FROM MENU;
-- --sub domain:基础配置
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('teamwork.basic','', @TEMP_DOMAIN_SEQ_teamwork, 0 , '', 1, '');
	SELECT @TEMP_DOMAIN_SEQ_teamwork.basic:=MAX(MENU_ID) FROM MENU;
-- --module:任务类型  
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('teamwork.basic.issueType','任务类型', @TEMP_DOMAIN_SEQ_teamwork.basic, 1, '/teamwork/issueType/index.do', 1, '任务类型管理');  
-- --module:项目     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('teamwork.project','项目', @TEMP_DOMAIN_SEQ_teamwork, 1, '/teamwork/project/index.do', 1, '项目管理');
-- --module:任务     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('teamwork.issue','任务', @TEMP_DOMAIN_SEQ_teamwork, 1, '/teamwork/issue/index.do', 1, '任务管理');
COMMIT;