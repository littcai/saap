INSERT INTO TENANT(ID, APP_CODE, APP_ALIAS, STATUS, CREATE_USER_ID, CREATE_DATETIME, UPDATE_DATETIME, MAX_MEMBERS, EXPIRED_DATE) VALUES(1, 'PLAT-001', 'ƽ̨Ӧ��', 1, 1, now(), now(), 10, '2099-10-10 00:00:00');

INSERT INTO TENANT_MEMBER(TENANT_ID, APP_ID, USER_ID, STATUS, CREATE_USER_ID, CREATE_DATETIME, UPDATE_USER_ID, UPDATE_DATETIME) VALUES(1, 1, 1, 1, 1, NOW(), 1, NOW());

-- PERSONAL
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '03');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '0301');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '0302');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '030201');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '030202');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '030203');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '030204');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '0303');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '0304');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '0305');

-- CUSTOMER
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '11');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '1101');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '1102');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '110201');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '110202');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '110203');
INSERT INTO ROLE_FUNC_PERMISSION(TENANT_ID, ROLE_ID, PERMISSION_CODE) VALUES(1, 1, '110204');