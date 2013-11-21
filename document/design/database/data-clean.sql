TRUNCATE TABLE USER_INFO;
TRUNCATE TABLE USER_EXT;
TRUNCATE TABLE USER_DEFINED_ATTR;
TRUNCATE TABLE USER_STATE;
TRUNCATE TABLE USER_ROLE;
TRUNCATE TABLE USER_OP_LOG;
TRUNCATE TABLE USER_MESSAGE;
TRUNCATE TABLE USER_COMMENT;

TRUNCATE TABLE ACTIVATION_CODE;

-- CLEAN PERSONAL
TRUNCATE TABLE CALENDAR;
TRUNCATE TABLE CONTACTS;
TRUNCATE TABLE NOTE;
TRUNCATE TABLE TODO;
TRUNCATE TABLE SHORT_MESSAGE;

-- CLEAN TENANT
TRUNCATE TABLE TENANT;
TRUNCATE TABLE TENANT_MEMBER;
TRUNCATE TABLE TENANT_STAT;
DELETE FROM ROLE WHERE TENANT_ID>0;
DELETE FROM ROLE_FUNC_PERMISSION WHERE TENANT_ID>0;

TRUNCATE TABLE USER_GROUP;
TRUNCATE TABLE USER_GROUP_MEMBER;
TRUNCATE TABLE USER_GROUP_ROLE;

-- CLEAN CRM
TRUNCATE TABLE CUSTOMER;
TRUNCATE TABLE CUSTOMER_FEATURE;
TRUNCATE TABLE CUST_CONTACTS;
TRUNCATE TABLE CUST_ACTIVITY;
