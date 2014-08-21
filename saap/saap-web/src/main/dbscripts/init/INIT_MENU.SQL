BEGIN;
DELETE FROM MENU;
-- domain:系统工具
SET @TEMP_DOMAIN_SEQ = 0;
-- domain:系统管理
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin','系统管理', 0, 0 , '', 1, '包括配置系统运行时参数，用户管理，角色管理，操作日志查询，系统授权证书等功能。');
SELECT @TEMP_DOMAIN_SEQ_admin:=MAX(MENU_ID) FROM MENU;
-- --module:系统配置     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.systemInfo','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/systemInfo/index.do', 1, '配置信息运行参数，包括系统信息，邮件服务器配置，文件上传下载配置等。');
-- --module:操作员管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.operator','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/operator/index.do', 1, '维护系统操作员信息，配置操作员的相关角色');
-- --module:角色管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.role','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/role/index.do', 1, '管理系统用户角色，通过角色为用户分配权限。');
-- --module:操作日志     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.opLog','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/opLog/index.do', 1, '用户操作日志查询');
-- --module:部门     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.department','部门管理', @TEMP_DOMAIN_SEQ_admin, 1, '/system/department/index.do', 1, '管理部门信息。');
-- --module:IP访问控制     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.ipAccessLimit','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/ipAccessLimit/index.do', 1, '配置可访问系统的IP范围，非授权IP将无法访问本系统。');
-- --module:职务管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.job','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/job/index.do', 1, '管理职务信息。');
-- --module:在线用户     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.onlineOperator','在线用户管理', @TEMP_DOMAIN_SEQ_admin, 1, '/system/onlineOperator/index.do', 1, '查询所有在线用户，可强制踢出用户。');
-- --module:授权证书     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.license','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/license/index.do', 1, '系统使用授权证书管理');
-- --module:数据清理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.dataCleanup','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/cleanup/index.do', 1, '配置数据清理规则，满足规则的数据将被系统自动清理。');
-- --module:数据备份     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('admin.dataBackup','', @TEMP_DOMAIN_SEQ_admin, 1, '/system/backup/index.do', 1, '配置数据备份规则，用于备份指定范围数据，指定数据表，整个数据库。');
-- domain:系统辅助
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('assistant','系统辅助', 0, 0 , '', 1, '包含一些用于系统辅助的扩展功能，如公告、FAQ等。');
SELECT @TEMP_DOMAIN_SEQ_assistant:=MAX(MENU_ID) FROM MENU;
-- --module:Document     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('assistant.document','文档管理', @TEMP_DOMAIN_SEQ_assistant, 1, '/assistant/document/index.do', 1, '文档管理');
-- domain:个人门户
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal','个人门户', 0, 0 , '', 1, '个人门户，为用户提供个人事务管理及定制化功能');
SELECT @TEMP_DOMAIN_SEQ_personal:=MAX(MENU_ID) FROM MENU;
-- --module:Note     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal.note','笔记本', @TEMP_DOMAIN_SEQ_personal, 1, '/personal/note/index.do', 1, 'Personal Note');
-- --module:Todo     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal.todo','代办事项', @TEMP_DOMAIN_SEQ_personal, 1, '/personal/todo/index.do', 1, 'todo list.');
-- --module:Calendar     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal.calendar','日程计划', @TEMP_DOMAIN_SEQ_personal, 1, '/personal/calendar/index.do', 1, 'Calendar');
-- --module:Document     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal.document','文档', @TEMP_DOMAIN_SEQ_personal, 1, '/personal/document/index.do', 1, 'Document');
-- --module:Contacts     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('personal.contacts','联系人', @TEMP_DOMAIN_SEQ_personal, 1, '/personal/contacts/index.do', 1, 'Contacts');
-- domain:综合报表
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('report','综合报表', 0, 0 , '', 1, '综合报表');
SELECT @TEMP_DOMAIN_SEQ_report:=MAX(MENU_ID) FROM MENU;
-- --module:报表分类     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('report.reportCategory','报表分类管理', @TEMP_DOMAIN_SEQ_report, 1, '/report/reportCategory/index.do', 1, '报表分类管理');
-- --module:报表     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('report.report','报表管理', @TEMP_DOMAIN_SEQ_report, 1, '/report/report/index.do', 1, '报表管理');
-- domain:CRM
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('crm','CRM', 0, 0 , '', 1, '');
SELECT @TEMP_DOMAIN_SEQ_crm:=MAX(MENU_ID) FROM MENU;
-- --module:客户管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('crm.customer','客户', @TEMP_DOMAIN_SEQ_crm, 1, '/crm/customer/index.do', 1, 'Customer Management');
-- --module:联系人管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('crm.custContacts','联系人', @TEMP_DOMAIN_SEQ_crm, 1, '/crm/custContacts/index.do', 1, 'Customer Contacts Management');
-- --module:客户往来     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('crm.custActivity','客户往来', @TEMP_DOMAIN_SEQ_crm, 1, '/crm/custActivity/index.do', 1, 'Customer Activities Management');
-- --module:客户事件     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('crm.custEvent','客户事件', @TEMP_DOMAIN_SEQ_crm, 1, '/crm/custEvent/index.do', 1, 'Customer Events Management');
-- domain:消息中心
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('message','消息中心', 0, 0 , '', 1, '');
SELECT @TEMP_DOMAIN_SEQ_message:=MAX(MENU_ID) FROM MENU;
-- --module:短信发件箱     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('message.smsOut','短信发件箱', @TEMP_DOMAIN_SEQ_message, 1, '/message/smsOut/index.do', 1, '短信发件箱');
-- --module:短信收件箱     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('message.smsIn','短信收件箱', @TEMP_DOMAIN_SEQ_message, 1, '/message/smsIn/index.do', 1, '短信收件箱');
-- --module:公告     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('message.affiche','公告管理', @TEMP_DOMAIN_SEQ_message, 1, '/message/affiche/index.do', 1, '系统公告管理');
-- --module:用户反馈     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('message.feedback','反馈管理', @TEMP_DOMAIN_SEQ_message, 1, '/message/feedback/index.do', 1, '用户反馈管理');
-- domain:系统管理
SET @TEMP_DOMAIN_SEQ = 0;
INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant','系统管理', 0, 0 , '', 1, 'system management for tenant');
SELECT @TEMP_DOMAIN_SEQ_tenant:=MAX(MENU_ID) FROM MENU;
-- --module:空间管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant.tenant','空间管理', @TEMP_DOMAIN_SEQ_tenant, 1, '/system/tenant/index.do', 1, 'Workspace Management');
-- --module:成员管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant.tenantMember','成员管理', @TEMP_DOMAIN_SEQ_tenant, 1, '/system/tenantMember/index.do', 1, 'Member Management');
-- --module:组织架构     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant.userGroup','组织架构', @TEMP_DOMAIN_SEQ_tenant, 1, '/system/userGroup/index.do', 1, 'Group Management');
-- --module:角色权限     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant.role','角色权限', @TEMP_DOMAIN_SEQ_tenant, 1, '/system/role/index.do', 1, 'Role and privilege Management');
-- --module:订单管理     
	INSERT INTO MENU(MENU_CODE,MENU_NAME,PARENT_ID,IS_LEAF,MENU_URL,STATUS, REMARK) VALUES('tenant.tenantOrder','订单管理', @TEMP_DOMAIN_SEQ_tenant, 1, '/system/tenantOrder/index.do', 1, '工作空间订单管理');
COMMIT;