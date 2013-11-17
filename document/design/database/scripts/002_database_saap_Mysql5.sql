/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/11/14 13:00:02                          */
/*==============================================================*/


DROP TABLE IF EXISTS APP_STAT;

DROP INDEX IDX_AS_KEY ON APP_STORE;

DROP INDEX IDX_AS_CODE ON APP_STORE;

DROP TABLE IF EXISTS APP_STORE;

DROP INDEX IDX_CALENDAR_DATETIME ON CALENDAR;

DROP INDEX IDX_CALENDAR_USER ON CALENDAR;

DROP TABLE IF EXISTS CALENDAR;

DROP TABLE IF EXISTS COMPANY;

DROP INDEX IDX_CONTACTS_USER ON CONTACTS;

DROP TABLE IF EXISTS CONTACTS;

DROP INDEX IDX_CUSTOMER_TENANT ON CUSTOMER;

DROP INDEX IDX_CUSTOMER_STATUS ON CUSTOMER;

DROP INDEX IDX_CUSTOMER_CHARGE_OP ON CUSTOMER;

DROP TABLE IF EXISTS CUSTOMER;

DROP INDEX IDX_CF_CUSTOMER ON CUSTOMER_FEATURE;

DROP TABLE IF EXISTS CUSTOMER_FEATURE;

DROP TABLE IF EXISTS CUSTOMER_STATE;

DROP INDEX IDX_CUST_CONTACTS_USER ON CUST_CONTACTS;

DROP TABLE IF EXISTS CUST_CONTACTS;

DROP TABLE IF EXISTS DATA_PERMISSION;

DROP INDEX IDX_DP_DICT_TYPE ON DICT_PARAM;

DROP TABLE IF EXISTS DICT_PARAM;

DROP TABLE IF EXISTS DICT_PARAM_TYPE;

DROP TABLE IF EXISTS FORGET_PASSWORD;

DROP TABLE IF EXISTS FUNC;

DROP INDEX IDX_MENU_CODE ON MENU;

DROP TABLE IF EXISTS MENU;

DROP TABLE IF EXISTS MESSAGE_BOARD;

DROP TABLE IF EXISTS METADATA_FIELD;

DROP TABLE IF EXISTS MODULE;

DROP INDEX IDX_NOTE_USER ON NOTE;

DROP TABLE IF EXISTS NOTE;

DROP TABLE IF EXISTS PERMISSION;

DROP TABLE IF EXISTS ROLE;

DROP TABLE IF EXISTS ROLE_FUNC_PERMISSION;

DROP INDEX IDX_SM_SEND ON SHORT_MESSAGE;

DROP INDEX IDX_SM_RECEIVE ON SHORT_MESSAGE;

DROP TABLE IF EXISTS SHORT_MESSAGE;

DROP INDEX IDX_TENANT_CODE ON TENANT;

DROP TABLE IF EXISTS TENANT;

DROP INDEX IDX_TM_USER ON TENANT_MEMBER;

DROP TABLE IF EXISTS TENANT_MEMBER;

DROP TABLE IF EXISTS TENANT_STAT;

DROP INDEX IDX_TODO_USER ON TODO;

DROP TABLE IF EXISTS TODO;

DROP TABLE IF EXISTS USER_DEFINED_ATTR;

DROP TABLE IF EXISTS USER_EXT;

DROP TABLE IF EXISTS USER_GROUP;

DROP TABLE IF EXISTS USER_GROUP_MEMBER;

DROP TABLE IF EXISTS USER_GROUP_ROLE;

DROP TABLE IF EXISTS USER_INFO;

DROP TABLE IF EXISTS USER_OP_LOG;

DROP TABLE IF EXISTS USER_ROLE;

DROP TABLE IF EXISTS USER_STATE;

/*==============================================================*/
/* Table: APP_STAT                                              */
/*==============================================================*/
CREATE TABLE APP_STAT
(
   ID                   INT COMMENT 'Ӧ��ID',
   TOTAL_SALE_COUNT     INT COMMENT '����������',
   LAST_SALE_DATETIME   DATETIME COMMENT '�������ʱ��',
   TOTAL_ACCESS_COUNT   INT COMMENT '�ܷ�����������¼����'
)
ENGINE = INNODB;

ALTER TABLE APP_STAT COMMENT 'Ӧ��ͳ��';

/*==============================================================*/
/* Table: APP_STORE                                             */
/*==============================================================*/
CREATE TABLE APP_STORE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   UUID                 VARCHAR(36) NOT NULL COMMENT '�����ʶ��ȫ��Ψһ��',
   CODE                 VARCHAR(50) NOT NULL COMMENT '���',
   KEY                  INT NOT NULL COMMENT '��ȨKEY',
   PASSWORD             VARCHAR(200) NOT NULL COMMENT '��Ȩ��Կ',
   NAME                 VARCHAR(50) NOT NULL COMMENT '����',
   TYPE                 INT(1) NOT NULL DEFAULT 1 COMMENT '����(3001)
            1������
            2����ҵ
            3��ͨ��',
   AUTH_TYPE            INT(1) NOT NULL COMMENT '��Ȩ����(3002)
            1�����
            2���շ�',
   AUTH_ADDRESS         VARCHAR(100) COMMENT '��֤��ַ',
   ACCESS_TYPE          INT NOT NULL COMMENT '��������(3003)
            1��������
            2��������',
   ACCESS_ADDRESS       VARCHAR(100) COMMENT '�����ַ',
   DESCRIPTION          TEXT COMMENT '��ϸ����',
   ICON_URL             VARCHAR(200) COMMENT '���ͼ���ַ',
   CERT_CODE            VARCHAR(100) NOT NULL COMMENT '��ȫ����(����ǩ��)',
   IS_CHECKED           BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ����ͨ��',
   STATUS               INT(1) NOT NULL DEFAULT 1 COMMENT '״̬(3004)
            0��δ����
            1������
            2��ɾ��
            3������',
   POSITION             INT NOT NULL DEFAULT 0 COMMENT '����',
   REVISION             VARCHAR(10) NOT NULL COMMENT '�汾��',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME COMMENT '����ʱ��',
   ONLINE_DATETIME      DATETIME COMMENT '����ʱ��',
   PRIMARY KEY (ID)
);

ALTER TABLE APP_STORE COMMENT 'Ӧ���̵�';

/*==============================================================*/
/* Index: IDX_AS_CODE                                           */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_AS_CODE ON APP_STORE
(
   CODE
);

/*==============================================================*/
/* Index: IDX_AS_KEY                                            */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_AS_KEY ON APP_STORE
(
   KEY
);

/*==============================================================*/
/* Table: CALENDAR                                              */
/*==============================================================*/
CREATE TABLE CALENDAR
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   SUBJECT              VARCHAR(200) NOT NULL COMMENT '����',
   TYPE                 INT(2) NOT NULL COMMENT '���ͣ�3801��
            1���绰
            2��Email
            3������
            4���ݷ�
            5��ֱ��
            6������',
   START_DATETIME       DATETIME NOT NULL COMMENT '��ʼʱ��',
   END_DATETIME         DATETIME NOT NULL COMMENT '����ʱ��',
   STATUS               INT(2) NOT NULL DEFAULT 1 COMMENT '״̬��3802��
            1����δ��ʼ
            2��������
            3����ͣ
            4�������
            5���ӳ�
            6��ȡ��',
   CONTENT              VARCHAR(2000) COMMENT '����',
   IS_REMIND            BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ�����',
   REMIND_MINUTES       INT DEFAULT 10 COMMENT '����ʱ�䣨��ǰ��������',
   REMIND_TIME          DATETIME COMMENT '����ʱ��
            ������ʱ�䣬���ڲ�ѯ',
   CREATE_USER_ID       INT NOT NULL COMMENT '������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME COMMENT '����ʱ��',
   REPEATABLE           BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ���ظ�',
   REPEAT_PERIOD        INT COMMENT '�ظ�����
            1����
            2����
            3����',
   PRIMARY KEY (ID)
);

ALTER TABLE CALENDAR COMMENT '�����ճ̼ƻ���';

/*==============================================================*/
/* Index: IDX_CALENDAR_USER                                     */
/*==============================================================*/
CREATE INDEX IDX_CALENDAR_USER ON CALENDAR
(
   CREATE_USER_ID,
   TYPE,
   STATUS
);

/*==============================================================*/
/* Index: IDX_CALENDAR_DATETIME                                 */
/*==============================================================*/
CREATE INDEX IDX_CALENDAR_DATETIME ON CALENDAR
(
   START_DATETIME,
   END_DATETIME
);

/*==============================================================*/
/* Table: COMPANY                                               */
/*==============================================================*/
CREATE TABLE COMPANY
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   CODE                 VARCHAR(50) NOT NULL,
   NAME                 VARCHAR(200) NOT NULL,
   DESCRIPTION          VARCHAR(2000),
   CREATE_USER_ID       INT NOT NULL,
   CREATE_DATETIME      DATETIME NOT NULL,
   UPDATE_USER_ID       INT NOT NULL,
   UPDATE_DATETIME      DATETIME NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE COMPANY COMMENT '��ҵ��Ϣ��';

/*==============================================================*/
/* Table: CONTACTS                                              */
/*==============================================================*/
CREATE TABLE CONTACTS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   NAME                 VARCHAR(200) NOT NULL COMMENT '����',
   GENDER               TINYINT NOT NULL DEFAULT 0 COMMENT '�Ա�(0002)
            0:unknown
            1:male
            2:female',
   MOBILE               VARCHAR(50) NOT NULL COMMENT '�ֻ���',
   EMAIL                VARCHAR(100) NOT NULL COMMENT '�����ʼ�',
   PHONE                VARCHAR(50) NOT NULL COMMENT '��ϵ�绰',
   FAX                  VARCHAR(50) NOT NULL COMMENT '�����',
   ADDRESS              VARCHAR(200) NOT NULL COMMENT '��ַ',
   ZIP_CODE             VARCHAR(20) NOT NULL COMMENT '�ʱ�',
   CREATE_USER_ID       INT NOT NULL COMMENT '������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CONTACTS COMMENT '��ϵ��';

/*==============================================================*/
/* Index: IDX_CONTACTS_USER                                     */
/*==============================================================*/
CREATE INDEX IDX_CONTACTS_USER ON CONTACTS
(
   CREATE_USER_ID
);

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
CREATE TABLE CUSTOMER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   CODE                 VARCHAR(50) NOT NULL COMMENT '���',
   PARENT_ID            INT NOT NULL DEFAULT 0 COMMENT '�ϼ���λ��ͨ�����ֶ��γɿͻ���ϵ��',
   IS_LEAF              BOOLEAN NOT NULL DEFAULT 1 COMMENT '�Ƿ�Ҷ�ӽڵ�',
   NAME                 VARCHAR(400) NOT NULL COMMENT '����',
   PHONE                VARCHAR(100) COMMENT '��ϵ�绰',
   EMAIL                VARCHAR(100) COMMENT '�����ʼ�',
   FAX                  VARCHAR(100) COMMENT '����',
   ADDRESS              VARCHAR(400) COMMENT '��ϵ��ַ',
   ZIP_CODE             VARCHAR(6) COMMENT '��������',
   WEBSITE              VARCHAR(400) COMMENT '��ַ',
   REMARK               VARCHAR(2000) COMMENT '��ע',
   CHARGE_USER_ID       INT NOT NULL COMMENT '�����ˣ�����Ա���ţ�',
   CONTACTS_ID          INT COMMENT 'Ĭ����ϵ��ID',
   CREATE_USER_ID       INT NOT NULL COMMENT '������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_USER_ID       INT NOT NULL COMMENT '�ϴ��޸���',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '�ϴ��޸�ʱ��',
   GRADE                INT(2) COMMENT '�ͻ��ȼ���3102��
            1��1��
            2��2��
            3��3��
            4��4��
            5��5��',
   STATUS               INT(2) COMMENT '�ͻ�״̬��3103��
            1��Ǳ��
            2��������
            3��ʧ��
            4������ʧ
            5���ѳɽ�
            6��ά��
            7���ص�ά��',
   LOGO_URL             VARCHAR(200) COMMENT 'LOGO���·��',
   IS_DELETED           BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ���ɾ��',
   PRIMARY KEY (ID),
   KEY AK_CUSTOMER_CODE (CODE)
);

ALTER TABLE CUSTOMER COMMENT '�ͻ���Ϣ��';

/*==============================================================*/
/* Index: IDX_CUSTOMER_CHARGE_OP                                */
/*==============================================================*/
CREATE INDEX IDX_CUSTOMER_CHARGE_OP ON CUSTOMER
(
   CHARGE_USER_ID
);

/*==============================================================*/
/* Index: IDX_CUSTOMER_STATUS                                   */
/*==============================================================*/
CREATE INDEX IDX_CUSTOMER_STATUS ON CUSTOMER
(
   STATUS
);

/*==============================================================*/
/* Index: IDX_CUSTOMER_TENANT                                   */
/*==============================================================*/
CREATE INDEX IDX_CUSTOMER_TENANT ON CUSTOMER
(
   TENANT_ID,
   CODE
);

/*==============================================================*/
/* Table: CUSTOMER_FEATURE                                      */
/*==============================================================*/
CREATE TABLE CUSTOMER_FEATURE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '�ͻ�ID',
   CODE                 VARCHAR(20) NOT NULL COMMENT '���',
   NAME                 VARCHAR(50) NOT NULL COMMENT '����',
   DATA_TYPE            TINYINT NOT NULL COMMENT '��������',
   DATA_VALUE           VARCHAR(255) COMMENT '����ֵ',
   DATA_VALUE_INT       INT COMMENT '����ֵ',
   DATA_VALUE_DATE      DATETIME COMMENT '����ֵ',
   DATA_VALUE_DECIMAL   DECIMAL(12,4) COMMENT '����ֵ',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUSTOMER_FEATURE COMMENT '�ͻ�������';

/*==============================================================*/
/* Index: IDX_CF_CUSTOMER                                       */
/*==============================================================*/
CREATE INDEX IDX_CF_CUSTOMER ON CUSTOMER_FEATURE
(
   TENANT_ID,
   CUSTOMER_ID,
   CODE
);

/*==============================================================*/
/* Table: CUSTOMER_STATE                                        */
/*==============================================================*/
CREATE TABLE CUSTOMER_STATE
(
   ID                   INT NOT NULL COMMENT '�ͻ�ID',
   TENANT_ID            INT COMMENT '�⻧ID',
   LAST_CONTACT_TIME    DATETIME COMMENT '�����ϵʱ��',
   CONTACT_TIMES        INT NOT NULL DEFAULT 0 COMMENT '����ϵ����',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUSTOMER_STATE COMMENT '�ͻ�ͳ�Ʊ�';

/*==============================================================*/
/* Table: CUST_CONTACTS                                         */
/*==============================================================*/
CREATE TABLE CUST_CONTACTS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '�ͻ�ID',
   NAME                 VARCHAR(200) NOT NULL COMMENT '����',
   GENDER               TINYINT NOT NULL DEFAULT 0 COMMENT '�Ա�(0002)
            0:unknown
            1:male
            2:female',
   MOBILE               VARCHAR(50) NOT NULL COMMENT '�ֻ���',
   EMAIL                VARCHAR(100) NOT NULL COMMENT '�����ʼ�',
   PHONE                VARCHAR(50) NOT NULL COMMENT '��ϵ�绰',
   FAX                  VARCHAR(50) NOT NULL COMMENT '�����',
   ADDRESS              VARCHAR(200) NOT NULL COMMENT '��ַ',
   ZIP_CODE             VARCHAR(20) NOT NULL COMMENT '�ʱ�',
   CREATE_USER_ID       INT NOT NULL COMMENT '������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUST_CONTACTS COMMENT '�ͻ���ϵ��';

/*==============================================================*/
/* Index: IDX_CUST_CONTACTS_USER                                */
/*==============================================================*/
CREATE INDEX IDX_CUST_CONTACTS_USER ON CUST_CONTACTS
(
   TENANT_ID,
   CREATE_USER_ID,
   CUSTOMER_ID
);

/*==============================================================*/
/* Table: DATA_PERMISSION                                       */
/*==============================================================*/
CREATE TABLE DATA_PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT 'Ȩ������',
   OBJECT_NAME          VARCHAR(50) NOT NULL COMMENT '��������',
   FIELD_NAME           VARCHAR(50) NOT NULL COMMENT '��������',
   "CONDITION"          VARCHAR(100) NOT NULL COMMENT '��������',
   STATUS               TINYINT NOT NULL COMMENT '״̬',
   CREATE_USER_ID       INT NOT NULL COMMENT '������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_USER_ID       INT NOT NULL COMMENT '������',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE DATA_PERMISSION COMMENT '���ݷ�����Ȩ';

/*==============================================================*/
/* Table: DICT_PARAM                                            */
/*==============================================================*/
CREATE TABLE DICT_PARAM
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   DICT_TYPE            VARCHAR(4) NOT NULL COMMENT '��������',
   DICT_VALUE           VARCHAR(100) NOT NULL COMMENT '����ֵ',
   DICT_CONTENT         VARCHAR(100) NOT NULL COMMENT '��������',
   POSITION             INT NOT NULL DEFAULT 10 COMMENT '����',
   STATUS               TINYINT NOT NULL DEFAULT 9 COMMENT '״̬
            1������
            2������
            9��ϵͳ',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (ID)
);

ALTER TABLE DICT_PARAM COMMENT '�����ֵ��';

/*==============================================================*/
/* Index: IDX_DP_DICT_TYPE                                      */
/*==============================================================*/
CREATE INDEX IDX_DP_DICT_TYPE ON DICT_PARAM
(
   DICT_TYPE,
   DICT_VALUE
);

/*==============================================================*/
/* Table: DICT_PARAM_TYPE                                       */
/*==============================================================*/
CREATE TABLE DICT_PARAM_TYPE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   DICT_TYPE            VARCHAR(4) NOT NULL COMMENT '��������',
   DICT_TYPE_NAME       VARCHAR(50) NOT NULL COMMENT '������������',
   ALTER_MODE           TINYINT NOT NULL DEFAULT 1 COMMENT '���ķ�ʽ
            1�������޸�
            2��������
            3�����޸�
            4����ɾ��',
   STATUS               TINYINT NOT NULL DEFAULT 9 COMMENT '״̬
            1������
            2������
            9��ϵͳ',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (ID),
   KEY UNQ_DICT_TYPE (DICT_TYPE)
);

ALTER TABLE DICT_PARAM_TYPE COMMENT '�����ֵ����ͱ�';

/*==============================================================*/
/* Table: FORGET_PASSWORD                                       */
/*==============================================================*/
CREATE TABLE FORGET_PASSWORD
(
   ID                   VARCHAR(36) NOT NULL COMMENT 'UUID',
   USER_ID              INT NOT NULL COMMENT '�û�ID',
   SECURITY_KEY         VARCHAR(50) NOT NULL COMMENT '��ȫ��Կ',
   EXPIRED_DATETIME     DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE FORGET_PASSWORD COMMENT '�һ�����';

/*==============================================================*/
/* Table: FUNC                                                  */
/*==============================================================*/
CREATE TABLE FUNC
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   MODULE_ID            INT NOT NULL COMMENT 'ģ��ID',
   FUNC_CODE            CHAR(2) NOT NULL COMMENT '���ܵ���',
   FUNC_TYPE            INT(1) NOT NULL DEFAULT 5 COMMENT '��������
            1����
            2����
            3��ɾ
            4����
            5���ۺ�',
   FUNC_NAME            VARCHAR(50) NOT NULL COMMENT '���ܵ�����',
   PRIMARY KEY (ID)
);

ALTER TABLE FUNC COMMENT '���ܵ�';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
CREATE TABLE MENU
(
   MENU_ID              INT NOT NULL AUTO_INCREMENT COMMENT '�˵�ID',
   MENU_CODE            VARCHAR(8) NOT NULL COMMENT '�˵����',
   MENU_NAME            VARCHAR(50) NOT NULL COMMENT '�˵�����',
   MENU_URL             VARCHAR(100) NOT NULL COMMENT '�˵�����',
   PARENT_ID            INT NOT NULL DEFAULT 0 COMMENT '���˵�ID',
   IS_LEAF              INT(1) NOT NULL DEFAULT 1 COMMENT '�Ƿ�Ҷ�ӽڵ�',
   STATUS               INT(1) NOT NULL DEFAULT 1 COMMENT '״̬
            1������
            2������
            9�����ɸ���
            ',
   POSITION             INT(4) NOT NULL DEFAULT 0 COMMENT '����λ��',
   ICON_URL             VARCHAR(200) COMMENT 'ͼ��URL',
   IMAGE_URL            VARCHAR(200) COMMENT 'ͼƬURL',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (MENU_ID),
   UNIQUE KEY AK_MENU_CODE (MENU_CODE)
);

ALTER TABLE MENU COMMENT '�˵���';

/*==============================================================*/
/* Index: IDX_MENU_CODE                                         */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_MENU_CODE ON MENU
(
   MENU_CODE
);

/*==============================================================*/
/* Table: MESSAGE_BOARD                                         */
/*==============================================================*/
CREATE TABLE MESSAGE_BOARD
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   CONTENT              VARCHAR(2000) COMMENT '����',
   CREATE_DATETIME      DATETIME COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE MESSAGE_BOARD COMMENT '���԰�';

/*==============================================================*/
/* Table: METADATA_FIELD                                        */
/*==============================================================*/
CREATE TABLE METADATA_FIELD
(
   ID                   INT NOT NULL COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID'
)
ENGINE = INNODB;

ALTER TABLE METADATA_FIELD COMMENT '�ֶ�Ԫ���ݱ�';

/*==============================================================*/
/* Table: MODULE                                                */
/*==============================================================*/
CREATE TABLE MODULE
(
   MODULE_ID            INT NOT NULL AUTO_INCREMENT COMMENT 'ģ��ID',
   MODULE_CODE          VARCHAR(8) NOT NULL COMMENT 'ģ����',
   MODULE_NAME          VARCHAR(50) NOT NULL COMMENT 'ģ������',
   PARENT_ID            INT NOT NULL COMMENT '��ģ��ID',
   IS_LEAF              BOOLEAN NOT NULL DEFAULT 1 COMMENT '�Ƿ�Ҷ��ģ��',
   STATUS               TINYINT NOT NULL DEFAULT 1 COMMENT '״̬
            1������
            2������',
   PRIMARY KEY (MODULE_ID)
);

ALTER TABLE MODULE COMMENT 'ģ���';

/*==============================================================*/
/* Table: NOTE                                                  */
/*==============================================================*/
CREATE TABLE NOTE
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TITLE                VARCHAR(50) NOT NULL COMMENT '����',
   CONTENT              TEXT COMMENT '����',
   CREATE_USER_ID       INT NOT NULL COMMENT '����ԱID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '�������ʱ��',
   PRIMARY KEY (ID)
);

ALTER TABLE NOTE COMMENT '��ǩ';

/*==============================================================*/
/* Index: IDX_NOTE_USER                                         */
/*==============================================================*/
CREATE INDEX IDX_NOTE_USER ON NOTE
(
   CREATE_USER_ID,
   UPDATE_DATETIME
);

/*==============================================================*/
/* Table: PERMISSION                                            */
/*==============================================================*/
CREATE TABLE PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   PERMISSION_CODE      VARCHAR(10) NOT NULL COMMENT 'Ȩ�ޱ��
            ��Դ��ţ�ģ��+���ܵ㣩',
   FUNC_ID              INT NOT NULL COMMENT '���ܵ�ID',
   MODULE_ID            INT NOT NULL COMMENT 'ģ��ID',
   PRIMARY KEY (ID),
   KEY UNQ_PERMISSION_CODE (PERMISSION_CODE)
);

ALTER TABLE PERMISSION COMMENT 'Ȩ�ޱ�';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
CREATE TABLE ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT '����',
   STATUS               TINYINT NOT NULL COMMENT '״̬',
   REMARK               VARCHAR(200) COMMENT '��ע',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE ROLE COMMENT '��ɫ��';

/*==============================================================*/
/* Table: ROLE_FUNC_PERMISSION                                  */
/*==============================================================*/
CREATE TABLE ROLE_FUNC_PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   ROLE_ID              INT NOT NULL COMMENT '��ɫID',
   PERMISSION_CODE      VARCHAR(10) NOT NULL COMMENT 'Ȩ�ޱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE ROLE_FUNC_PERMISSION COMMENT '��ɫ����Ȩ�ޱ�';

/*==============================================================*/
/* Table: SHORT_MESSAGE                                         */
/*==============================================================*/
CREATE TABLE SHORT_MESSAGE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TITLE                VARCHAR(200) NOT NULL COMMENT '����',
   TYPE                 TINYINT NOT NULL DEFAULT 2 COMMENT '��������(2001)
            1��ϵͳ
            2���û�',
   CONTENT              VARCHAR(2000) COMMENT '��������',
   CREATE_USER_ID       INT NOT NULL COMMENT '������ID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   RECEIVE_USER_ID      INT NOT NULL COMMENT '������ID',
   RECEIVE_FLAG         BOOLEAN NOT NULL DEFAULT 0 COMMENT '���ձ�־',
   RECEIVE_DATETIME     DATE COMMENT '����ʱ��',
   PRIMARY KEY (ID)
);

ALTER TABLE SHORT_MESSAGE COMMENT 'վ�ڶ���';

/*==============================================================*/
/* Index: IDX_SM_RECEIVE                                        */
/*==============================================================*/
CREATE INDEX IDX_SM_RECEIVE ON SHORT_MESSAGE
(
   RECEIVE_USER_ID,
   RECEIVE_FLAG,
   RECEIVE_DATETIME
);

/*==============================================================*/
/* Index: IDX_SM_SEND                                           */
/*==============================================================*/
CREATE INDEX IDX_SM_SEND ON SHORT_MESSAGE
(
   CREATE_USER_ID,
   CREATE_DATETIME
);

/*==============================================================*/
/* Table: TENANT                                                */
/*==============================================================*/
CREATE TABLE TENANT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   CODE                 VARCHAR(50) NOT NULL COMMENT 'domain���',
   APP_CODE             VARCHAR(50) NOT NULL COMMENT 'Ӧ�ñ��',
   APP_ALIAS            VARCHAR(100) NOT NULL COMMENT 'Ӧ�ñ���',
   ISOLATED_MODE        INT(1) NOT NULL DEFAULT 1 COMMENT '��������
            1����ȫ������
            2������Schema
            3���������ݿ�',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '״̬
            -3������
            -2������
            -1��ɾ��
            0����δ����
            1������',
   CREATE_USER_ID       INT NOT NULL COMMENT '�����û�ID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   MAX_MEMBERS          INT NOT NULL DEFAULT 1 COMMENT '����Ա��',
   TRIAL_DAYS           INT(3) NOT NULL DEFAULT 30 COMMENT '��������',
   EXPIRED_DATE         DATE NOT NULL COMMENT '������',
   PRICE                INT NOT NULL DEFAULT 0 COMMENT '����۸�',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TENANT COMMENT 'Ӧ�����ñ�';

/*==============================================================*/
/* Index: IDX_TENANT_CODE                                       */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_TENANT_CODE ON TENANT
(
   CODE
);

/*==============================================================*/
/* Table: TENANT_MEMBER                                         */
/*==============================================================*/
CREATE TABLE TENANT_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '����ID',
   APP_ID               INT NOT NULL COMMENT 'Ӧ��ID',
   USER_ID              INT COMMENT '�û�ID',
   IS_ADMIN             BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ����Ա',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '״̬
            0��δ��֤
            1��ͨ����֤
            2������',
   CREATE_USER_ID       INT NOT NULL COMMENT '�����û�',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_USER_ID       INT NOT NULL COMMENT '������',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TENANT_MEMBER COMMENT '���޳�Ա��';

/*==============================================================*/
/* Index: IDX_TM_USER                                           */
/*==============================================================*/
CREATE INDEX IDX_TM_USER ON TENANT_MEMBER
(
   USER_ID,
   APP_ID
);

/*==============================================================*/
/* Table: TENANT_STAT                                           */
/*==============================================================*/
CREATE TABLE TENANT_STAT
(
   ID                   INT NOT NULL COMMENT '�⻧ID',
   TOTAL_ACCESS_COUNT   INT NOT NULL DEFAULT 0 COMMENT '�ܷ�����',
   LAST_ACCESS_DATETIME DATETIME COMMENT '�������ʱ��'
)
ENGINE = INNODB;

ALTER TABLE TENANT_STAT COMMENT '����ͳ�Ʊ�';

/*==============================================================*/
/* Table: TODO                                                  */
/*==============================================================*/
CREATE TABLE TODO
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   CONTENT              VARCHAR(500) NOT NULL COMMENT '����',
   STATUS               INT(2) NOT NULL DEFAULT 1 COMMENT '״̬(2002)
            -2���Ƴ�
            -1��ȡ��
            1��δ��ʼ
            2�����ڽ���
            3�����',
   IS_NEED_REMIND       BOOLEAN NOT NULL DEFAULT 0 COMMENT '�Ƿ���Ҫ����',
   REMIND_DATETIME      DATETIME COMMENT '����ʱ��',
   TODO_DATETIME        DATETIME NOT NULL COMMENT '����ʱ��',
   CREATE_USER_ID       INT NOT NULL COMMENT '�����û�',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   ICON_URL             VARCHAR(200) COMMENT 'ͼ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TODO COMMENT '��������';

/*==============================================================*/
/* Index: IDX_TODO_USER                                         */
/*==============================================================*/
CREATE INDEX IDX_TODO_USER ON TODO
(
   CREATE_USER_ID,
   STATUS,
   TODO_DATETIME
);

/*==============================================================*/
/* Table: USER_DEFINED_ATTR                                     */
/*==============================================================*/
CREATE TABLE USER_DEFINED_ATTR
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   USER_ID              INT NOT NULL COMMENT '�û�ID',
   DATA_TYPE            VARCHAR(50) NOT NULL COMMENT '��������',
   PROP_KEY             VARCHAR(100) NOT NULL COMMENT '����',
   PROP_VALUE           VARCHAR(2000) NOT NULL COMMENT '�ַ���ֵ',
   PROP_VALUE_INT       INT COMMENT '����ֵ',
   PROP_VALUE_DATE      DATETIME COMMENT '����ֵ',
   PROP_VALUE_DECIMAL   DECIMAL(8,4) COMMENT '����ֵ',
   DISPLAY_NAME         VARCHAR(100) COMMENT '��ʾ����',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_DEFINED_ATTR COMMENT '�û���չ���Ա�';

/*==============================================================*/
/* Table: USER_EXT                                              */
/*==============================================================*/
CREATE TABLE USER_EXT
(
   ID                   INT NOT NULL COMMENT '�û�ID',
   LEVEL                INT(2) COMMENT '�û��ȼ�',
   SOURCE               VARCHAR(100) COMMENT '�û���Դ',
   BIRTHDAY             DATETIME COMMENT '����',
   ZIP_CODE             VARCHAR(6) COMMENT '��������',
   ADDRESS              VARCHAR(200) COMMENT '��ϵ��ַ',
   DISTRICT             VARCHAR(50) COMMENT '��',
   CITY                 VARCHAR(50) COMMENT '����',
   STATE                VARCHAR(50) COMMENT 'ʡ��',
   COUNTRY              VARCHAR(50) COMMENT '����',
   SECURITY_QUESTION    VARCHAR(100) COMMENT '��ȫ����',
   ANSWER               VARCHAR(100) COMMENT '��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_EXT COMMENT '�û���չ��';

/*==============================================================*/
/* Table: USER_GROUP                                            */
/*==============================================================*/
CREATE TABLE USER_GROUP
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   PARENT_ID            INT NOT NULL COMMENT '����֯ID',
   CODE                 VARCHAR(100) NOT NULL COMMENT '���',
   NAME                 VARCHAR(100) NOT NULL COMMENT '����',
   TYPE                 INT(2) NOT NULL COMMENT '����
            1������
            2��Ⱥ��',
   DESCRIPTION          VARCHAR(500) NOT NULL COMMENT '����',
   STATUS               INT(2) NOT NULL COMMENT '״̬',
   CREATE_USER_ID       INT NOT NULL COMMENT '�����û�',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_USER_ID       INT COMMENT '�����û�',
   UPDATE_DATETIME      DATETIME COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP COMMENT '�û���';

/*==============================================================*/
/* Table: USER_GROUP_MEMBER                                     */
/*==============================================================*/
CREATE TABLE USER_GROUP_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   GROUP_ID             INT NOT NULL COMMENT '�û���ID',
   USER_ID              INT NOT NULL COMMENT '�û�ID',
   TYPE                 INT(2) NOT NULL COMMENT '����
            1��������
            2����ͨ��Ա
            3������Ա',
   STATUS               INT(2) NOT NULL COMMENT '״̬
            1������
            2������',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP_MEMBER COMMENT '�û����Ա';

/*==============================================================*/
/* Table: USER_GROUP_ROLE                                       */
/*==============================================================*/
CREATE TABLE USER_GROUP_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   GROUP_ID             INT NOT NULL COMMENT '�û���ID',
   ROLE_ID              INT NOT NULL COMMENT '��ɫID',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP_ROLE COMMENT '�û����ɫ��';

/*==============================================================*/
/* Table: USER_INFO                                             */
/*==============================================================*/
CREATE TABLE USER_INFO
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   LOGIN_ID             VARCHAR(50) NOT NULL COMMENT '��¼ID',
   PASSWORD             VARCHAR(50) NOT NULL COMMENT '����',
   USER_TYPE            INT(2) NOT NULL COMMENT '�û�����(1001)
            1�������û�
            2����ҵ�û�',
   USER_NAME            VARCHAR(50) NOT NULL COMMENT '�û���',
   NICK_NAME            VARCHAR(100) NOT NULL COMMENT '�ǳ�',
   GENDER               INT(1) NOT NULL DEFAULT 0 COMMENT '�Ա�(0002)
            0:Unknown
            1:Male
            2:Female',
   EMAIL                VARCHAR(50) COMMENT '�����ʼ�',
   MOBILE               VARCHAR(20) COMMENT '�ֻ���',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '״̬(1002)
            0��δ���
            1������
            2��ע��
            3��ɾ��
            4������',
   LOCALE               VARCHAR(20) NOT NULL DEFAULT 'en' COMMENT '����',
   TIMEZONE             INT(2) NOT NULL DEFAULT 8 COMMENT 'ʱ��',
   THEME                VARCHAR(20) NOT NULL DEFAULT 'default' COMMENT '����',
   HEAD_IMG_URL         VARCHAR(200) COMMENT 'ͷ���ַ',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '����ʱ��',
   UPDATE_DATETIME      DATETIME COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_INFO COMMENT '�û���Ϣ��';

/*==============================================================*/
/* Table: USER_OP_LOG                                           */
/*==============================================================*/
CREATE TABLE USER_OP_LOG
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   USER_ID              INT NOT NULL COMMENT '�û�ID',
   APP_ID               INT NOT NULL COMMENT 'Ӧ��ID',
   LOGIN_ID             VARCHAR(50) NOT NULL COMMENT '��¼��',
   MODULE_CODE          VARCHAR(10) NOT NULL COMMENT 'ģ����',
   FUNC_CODE            VARCHAR(2) NOT NULL COMMENT '���ܱ��',
   OP_DATETIME          DATETIME NOT NULL COMMENT '����ʱ��',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_OP_LOG COMMENT '�û�������־';

/*==============================================================*/
/* Table: USER_ROLE                                             */
/*==============================================================*/
CREATE TABLE USER_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '���',
   TENANT_ID            INT NOT NULL COMMENT '�⻧ID',
   USER_ID              INT NOT NULL COMMENT '�û�ID',
   ROLE_ID              INT NOT NULL COMMENT '��ɫID',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_ROLE COMMENT '�û���ɫ��ϵ��';

/*==============================================================*/
/* Table: USER_STATE                                            */
/*==============================================================*/
CREATE TABLE USER_STATE
(
   ID                   INT NOT NULL COMMENT '���(ͬ�û�ID)',
   TOTAL_LOGIN_TIMES    INT NOT NULL DEFAULT 0 COMMENT '�ܵ�¼����',
   LOGIN_RETRY_TIMES    INT(1) NOT NULL DEFAULT 0 COMMENT '��¼���Դ���',
   LAST_LOGIN_DATETIME  DATETIME NOT NULL COMMENT '�ϴε�¼ʱ��',
   LAST_LOGIN_IP        VARCHAR(20) NOT NULL COMMENT '�ϴε�¼IP',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_STATE COMMENT '�û�״̬��Ϣ��';

ALTER TABLE DICT_PARAM ADD CONSTRAINT FK_DP_DICT_TYPE FOREIGN KEY (DICT_TYPE)
      REFERENCES DICT_PARAM_TYPE (DICT_TYPE) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE FUNC ADD CONSTRAINT FK_F_MODULE_ID FOREIGN KEY (MODULE_ID)
      REFERENCES MODULE (MODULE_ID) ON DELETE CASCADE;

ALTER TABLE PERMISSION ADD CONSTRAINT FK_P_MODULE_ID FOREIGN KEY (MODULE_ID)
      REFERENCES MODULE (MODULE_ID) ON DELETE CASCADE;

ALTER TABLE PERMISSION ADD CONSTRAINT FK_P_FUNC_ID FOREIGN KEY (FUNC_ID)
      REFERENCES FUNC (ID) ON DELETE CASCADE;

