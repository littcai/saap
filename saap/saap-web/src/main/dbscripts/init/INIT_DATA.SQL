BEGIN;
-- CLEAN DATA
TRUNCATE TABLE ROLE;
TRUNCATE TABLE ROLE_FUNC_PERMISSION;

-- INIT DEFAULT ROLES
INSERT INTO ROLE(ID, TENANT_ID, NAME, STATUS, REMARK, CREATE_DATETIME, UPDATE_DATETIME) VALUES(1, 0, 'GLOBAL_DEFAULT', 9, '系统预置全局角色，用户激活后即绑定该角色，获得该角色对应的权限，目前包含所有个人事务功能', now(), now());
INSERT INTO ROLE(ID, TENANT_ID, NAME, STATUS, REMARK, CREATE_DATETIME, UPDATE_DATETIME) VALUES(2, 0, 'tenantLevel1', 9, '系统预置1级租户，开通1级租户的用户即绑定该角色，获得对应权限', now(), now());
INSERT INTO ROLE(ID, TENANT_ID, NAME, STATUS, REMARK, CREATE_DATETIME, UPDATE_DATETIME) VALUES(3, 0, 'tenantLevel2', 9, '系统预置2级租户，开通2级租户的用户即绑定该角色，获得对应权限', now(), now());
INSERT INTO ROLE(ID, TENANT_ID, NAME, STATUS, REMARK, CREATE_DATETIME, UPDATE_DATETIME) VALUES(4, 0, 'tenantLevel3', 9, '系统预置3级租户，开通3级租户的用户即绑定该角色，获得对应权限', now(), now());

-- INIT PERSONAL PERMISSION
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.note');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.note.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.note.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.note.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.note.query');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.todo');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.todo.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.todo.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.todo.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.todo.query');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.calendar');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.calendar.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.calendar.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.calendar.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.calendar.query');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.contacts');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.contacts.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.contacts.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.contacts.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.contacts.query');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.userMessage');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.userMessage.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.userMessage.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.userMessage.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'personal.userMessage.query');

-- INIT MESSAGE CENTER PERMISSION
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.affiche');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.affiche.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.affiche.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.affiche.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.affiche.query');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.feedback');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.feedback.add');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.feedback.edit');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.feedback.delete');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(0, 1, 'message.feedback.query');
COMMIT;