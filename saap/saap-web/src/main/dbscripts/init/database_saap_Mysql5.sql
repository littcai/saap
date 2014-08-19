/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/8/19 14:54:02                           */
/*==============================================================*/


DROP TABLE IF EXISTS ACTIVATION_CODE;

DROP TABLE IF EXISTS AFFICHE;

DROP INDEX IDX_AO_ORDER_NO ON APP_ORDER;

DROP INDEX IDX_AO_TENANT ON APP_ORDER;

DROP INDEX IDX_AO_APP ON APP_ORDER;

DROP TABLE IF EXISTS APP_ORDER;

DROP INDEX IDX_AS_KEY ON APP_STORE;

DROP TABLE IF EXISTS APP_STORE;

DROP INDEX IDX_ATTACHMENT ON ATTACHMENT;

DROP INDEX IDX_ATTACHMENT_UID ON ATTACHMENT;

DROP TABLE IF EXISTS ATTACHMENT;

DROP INDEX IDX_CALENDAR_DATETIME ON CALENDAR;

DROP INDEX IDX_CALENDAR_USER ON CALENDAR;

DROP TABLE IF EXISTS CALENDAR;

DROP INDEX IDX_CONTACTS_USER ON CONTACTS;

DROP TABLE IF EXISTS CONTACTS;

DROP TABLE IF EXISTS CONTACTS_GROUP;

DROP TABLE IF EXISTS CONTACTS_GROUP_MEMBER;

DROP INDEX IDX_CUSTOMER_TENANT ON CUSTOMER;

DROP INDEX IDX_CUSTOMER_STATUS ON CUSTOMER;

DROP INDEX IDX_CUSTOMER_CHARGE_OP ON CUSTOMER;

DROP TABLE IF EXISTS CUSTOMER;

DROP INDEX IDX_CF_CUSTOMER ON CUSTOMER_FEATURE;

DROP TABLE IF EXISTS CUSTOMER_FEATURE;

DROP TABLE IF EXISTS CUSTOMER_STATE;

DROP INDEX IDX_CA_TENANT ON CUST_ACTIVITY;

DROP TABLE IF EXISTS CUST_ACTIVITY;

DROP INDEX IDX_CUST_CONTACTS_USER ON CUST_CONTACTS;

DROP TABLE IF EXISTS CUST_CONTACTS;

DROP INDEX IDX_CE_EVENT_DATE ON CUST_EVENT;

DROP TABLE IF EXISTS CUST_EVENT;

DROP TABLE IF EXISTS DICT_PARAM;

DROP INDEX IDX_DPT_CODE ON DICT_PARAM_TYPE;

DROP TABLE IF EXISTS DICT_PARAM_TYPE;

DROP TABLE IF EXISTS DYNAMIC_SEARCH;

DROP TABLE IF EXISTS DYNAMIC_SEARCH_FILTER;

DROP TABLE IF EXISTS FEEDBACK;

DROP TABLE IF EXISTS FORGET_PASSWORD;

DROP TABLE IF EXISTS PERMISSION;

DROP TABLE IF EXISTS FUNC;

DROP INDEX IDX_MENU_CODE ON MENU;

DROP TABLE IF EXISTS MENU;

DROP TABLE IF EXISTS MODULE;

DROP INDEX IDX_NOTE_USER ON NOTE;

DROP TABLE IF EXISTS NOTE;

DROP INDEX IDX_QV_NAME ON QUICK_VIEW;

DROP TABLE IF EXISTS QUICK_VIEW;

DROP INDEX IDX_QVF_QV_ID ON QUICK_VIEW_FILTER;

DROP TABLE IF EXISTS QUICK_VIEW_FILTER;

DROP INDEX IDX_QVG_QV_ID ON QUICK_VIEW_GRID;

DROP TABLE IF EXISTS QUICK_VIEW_GRID;

DROP TABLE IF EXISTS ROLE;

DROP TABLE IF EXISTS ROLE_FUNC_PERMISSION;

DROP INDEX IDX_SM_SEND ON SHORT_MESSAGE;

DROP INDEX IDX_SM_RECEIVE ON SHORT_MESSAGE;

DROP TABLE IF EXISTS SHORT_MESSAGE;

DROP TABLE IF EXISTS SMS_IN;

DROP INDEX IDX_SO_SEND_FLAG ON SMS_OUT;

DROP TABLE IF EXISTS SMS_OUT;

DROP TABLE IF EXISTS SYSTEM_INFO;

DROP INDEX IDX_TAG_NAME ON TAG;

DROP TABLE IF EXISTS TAG;

DROP INDEX IDX_TENANT_CODE ON TENANT;

DROP TABLE IF EXISTS TENANT;

DROP INDEX IDX_TDP_DICT_TYPE ON TENANT_DICT_PARAM;

DROP TABLE IF EXISTS TENANT_DICT_PARAM;

DROP INDEX IDX_TM_USER ON TENANT_MEMBER;

DROP TABLE IF EXISTS TENANT_MEMBER;

DROP INDEX IDX_TO_ORDER_NO ON TENANT_ORDER;

DROP TABLE IF EXISTS TENANT_ORDER;

DROP TABLE IF EXISTS TENANT_STAT;

DROP INDEX IDX_TODO_USER ON TODO;

DROP TABLE IF EXISTS TODO;

DROP INDEX IDX_UC_MODULE ON USER_COMMENT;

DROP TABLE IF EXISTS USER_COMMENT;

DROP TABLE IF EXISTS USER_EXT;

DROP TABLE IF EXISTS USER_GROUP;

DROP TABLE IF EXISTS USER_GROUP_MEMBER;

DROP TABLE IF EXISTS USER_GROUP_ROLE;

DROP TABLE IF EXISTS USER_INFO;

DROP INDEX IDX_UMESSAGE_USER ON USER_MESSAGE;

DROP INDEX IDX_UMESSAGE_CREATE ON USER_MESSAGE;

DROP TABLE IF EXISTS USER_MESSAGE;

DROP TABLE IF EXISTS USER_OP_LOG;

DROP TABLE IF EXISTS USER_ROLE;

DROP TABLE IF EXISTS USER_STATE;

/*==============================================================*/
/* Table: ACTIVATION_CODE                                       */
/*==============================================================*/
CREATE TABLE ACTIVATION_CODE
(
   ID                   VARCHAR(36) NOT NULL COMMENT 'UUID',
   USER_ID              INT NOT NULL COMMENT '用户ID',
   MODULE_CODE          VARCHAR(20) NOT NULL COMMENT '模块编号',
   PARAMS               VARCHAR(2000) NOT NULL COMMENT '参数',
   SECURITY_KEY         VARCHAR(100) NOT NULL COMMENT '安全密钥',
   EXPIRED_DATETIME     DATETIME NOT NULL COMMENT '过期时间',
   PRIMARY KEY (ID)
)
ENGINE = MYISAM;

ALTER TABLE ACTIVATION_CODE COMMENT '激活码';

/*==============================================================*/
/* Table: AFFICHE                                               */
/*==============================================================*/
CREATE TABLE AFFICHE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   TYPE                 INT NOT NULL COMMENT '类型',
   ICON_TYPE            VARCHAR(10) COMMENT '图标类型',
   TITLE                VARCHAR(100) NOT NULL COMMENT '标题',
   CONTENT              TEXT NOT NULL COMMENT '内容',
   EXPIRED_DATE         DATETIME COMMENT '过期日期',
   IS_CHECKED           BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否已审核',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE AFFICHE COMMENT '通知公告';

/*==============================================================*/
/* Table: APP_ORDER                                             */
/*==============================================================*/
CREATE TABLE APP_ORDER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   ORDER_NO             VARCHAR(100) NOT NULL COMMENT '订单号',
   ORDER_TYPE           INT NOT NULL COMMENT '订单类型',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   TENANT_CODE          VARCHAR(50) NOT NULL COMMENT '租户编号',
   APP_ID               VARCHAR(50) NOT NULL COMMENT '应用ID',
   PRICE                DECIMAL(10,4) NOT NULL COMMENT '价格',
   QUANTITY             INT NOT NULL COMMENT '数量',
   STATUS               INT NOT NULL COMMENT '状态',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PAY_CHANNEL          VARCHAR(50) NOT NULL COMMENT '付款通道',
   PAY_DATETIME         DATETIME COMMENT '付款时间',
   ACTIVATE_DATETIME    DATETIME COMMENT '生效时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE APP_ORDER COMMENT '应用订单';

/*==============================================================*/
/* Index: IDX_AO_APP                                            */
/*==============================================================*/
CREATE INDEX IDX_AO_APP ON APP_ORDER
(
   APP_ID
);

/*==============================================================*/
/* Index: IDX_AO_TENANT                                         */
/*==============================================================*/
CREATE INDEX IDX_AO_TENANT ON APP_ORDER
(
   TENANT_ID
);

/*==============================================================*/
/* Index: IDX_AO_ORDER_NO                                       */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_AO_ORDER_NO ON APP_ORDER
(
   ORDER_NO
);

/*==============================================================*/
/* Table: APP_STORE                                             */
/*==============================================================*/
CREATE TABLE APP_STORE
(
   UID                  VARCHAR(36) NOT NULL COMMENT '组件标识（全局唯一）',
   AUTH_KEY             VARCHAR(50) NOT NULL COMMENT '授权KEY',
   PASSWORD             VARCHAR(200) NOT NULL COMMENT '授权密钥',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   TYPE                 INT(1) NOT NULL DEFAULT 1 COMMENT '类型(3001)
            1：个人
            2：企业
            3：通用',
   DESCRIPTION          TEXT COMMENT '详细描述',
   ICON_URL             VARCHAR(200) NOT NULL COMMENT '组件图标地址',
   STATUS               INT(1) NOT NULL DEFAULT 1 COMMENT '状态(3004)
            0：未上线
            1：正常
            2：删除
            3：锁定',
   POSITION             INT NOT NULL DEFAULT 0 COMMENT '排行',
   REVISION             VARCHAR(10) NOT NULL COMMENT '版本号',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (UID)
);

ALTER TABLE APP_STORE COMMENT '应用商店';

/*==============================================================*/
/* Index: IDX_AS_KEY                                            */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_AS_KEY ON APP_STORE
(
   AUTH_KEY
);

/*==============================================================*/
/* Table: ATTACHMENT                                            */
/*==============================================================*/
CREATE TABLE ATTACHMENT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   UID                  VARCHAR(32) NOT NULL COMMENT '唯一ID',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   RECORD_ID            INT NOT NULL COMMENT '记录ID',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   DISPLAY_NAME         VARCHAR(100) NOT NULL COMMENT '显示名称',
   FILE_NAME            VARCHAR(100) NOT NULL COMMENT '文件名',
   FILE_PATH            VARCHAR(500) NOT NULL COMMENT '文件路径',
   FILE_SIZE            VARCHAR(50) NOT NULL COMMENT '文件大小',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE ATTACHMENT COMMENT '附件表';

/*==============================================================*/
/* Index: IDX_ATTACHMENT_UID                                    */
/*==============================================================*/
CREATE INDEX IDX_ATTACHMENT_UID ON ATTACHMENT
(
   UID
);

/*==============================================================*/
/* Index: IDX_ATTACHMENT                                        */
/*==============================================================*/
CREATE INDEX IDX_ATTACHMENT ON ATTACHMENT
(
   TENANT_ID,
   RECORD_ID,
   MODULE_CODE
);

/*==============================================================*/
/* Table: CALENDAR                                              */
/*==============================================================*/
CREATE TABLE CALENDAR
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   SUBJECT              VARCHAR(200) NOT NULL COMMENT '主题',
   TYPE                 INT(2) NOT NULL COMMENT '类型（3801）
            1：电话
            2：Email
            3：会议
            4：拜访
            5：直邮
            6：短信',
   START_DATETIME       DATETIME NOT NULL COMMENT '开始时间',
   END_DATETIME         DATETIME NOT NULL COMMENT '结束时间',
   STATUS               INT(2) NOT NULL DEFAULT 1 COMMENT '状态（3802）
            1：尚未开始
            2：处理中
            3：暂停
            4：已完成
            5：延迟
            6：取消',
   CONTENT              VARCHAR(2000) COMMENT '内容',
   IS_REMIND            BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否提醒',
   REMIND_MINUTES       INT DEFAULT 10 COMMENT '提醒时间（提前分钟数）',
   REMIND_TIME          DATETIME COMMENT '提醒时间
            计算后的时间，用于查询',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME COMMENT '更新时间',
   REPEATABLE           BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否可重复',
   REPEAT_PERIOD        INT COMMENT '重复周期
            1、日
            2、周
            3、月',
   PRIMARY KEY (ID)
);

ALTER TABLE CALENDAR COMMENT '个人日程计划表';

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
/* Table: CONTACTS                                              */
/*==============================================================*/
CREATE TABLE CONTACTS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   NAME                 VARCHAR(200) NOT NULL COMMENT '名称',
   GENDER               TINYINT NOT NULL DEFAULT 0 COMMENT '性别(0002)
            0:unknown
            1:male
            2:female',
   MOBILE               VARCHAR(50) NOT NULL COMMENT '手机号',
   EMAIL                VARCHAR(100) NOT NULL COMMENT '电子邮件',
   PHONE                VARCHAR(50) NOT NULL COMMENT '联系电话',
   FAX                  VARCHAR(50) NOT NULL COMMENT '传真号',
   ADDRESS              VARCHAR(200) NOT NULL COMMENT '地址',
   ZIP_CODE             VARCHAR(20) NOT NULL COMMENT '邮编',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CONTACTS COMMENT '联系人';

/*==============================================================*/
/* Index: IDX_CONTACTS_USER                                     */
/*==============================================================*/
CREATE INDEX IDX_CONTACTS_USER ON CONTACTS
(
   CREATE_BY
);

/*==============================================================*/
/* Table: CONTACTS_GROUP                                        */
/*==============================================================*/
CREATE TABLE CONTACTS_GROUP
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   NAME                 VARCHAR(100) NOT NULL COMMENT '名称',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CONTACTS_GROUP COMMENT '联系人分组';

/*==============================================================*/
/* Table: CONTACTS_GROUP_MEMBER                                 */
/*==============================================================*/
CREATE TABLE CONTACTS_GROUP_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   CONTACTS_ID          INT NOT NULL COMMENT '联系人ID',
   GROUP_ID             INT NOT NULL COMMENT '分组ID',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CONTACTS_GROUP_MEMBER COMMENT '联系人分组成员';

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
CREATE TABLE CUSTOMER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   CODE                 VARCHAR(50) NOT NULL COMMENT '编号',
   PARENT_ID            INT NOT NULL DEFAULT 0 COMMENT '上级单位：通过该字段形成客户关系网',
   IS_LEAF              BOOLEAN NOT NULL DEFAULT 1 COMMENT '是否叶子节点',
   NAME                 VARCHAR(400) NOT NULL COMMENT '名称',
   PHONE                VARCHAR(100) COMMENT '联系电话',
   EMAIL                VARCHAR(100) COMMENT '电子邮件',
   FAX                  VARCHAR(100) COMMENT '传真',
   ADDRESS              VARCHAR(400) COMMENT '联系地址',
   ZIP_CODE             VARCHAR(6) COMMENT '邮政编码',
   WEBSITE              VARCHAR(400) COMMENT '网址',
   REMARK               VARCHAR(2000) COMMENT '备注',
   CHARGE_BY            INT NOT NULL COMMENT '负责人（操作员或部门）',
   CONTACTS_ID          INT COMMENT '默认联系人ID',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '上次修改人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '上次修改时间',
   GRADE                INT(2) COMMENT '客户等级（3102）
            1：1星
            2：2星
            3：3星
            4：4星
            5：5星',
   STATUS               INT(2) COMMENT '客户状态（3103）
            1：潜在
            2：有意向
            3：失败
            4：已流失
            5：已成交
            6：维护
            7：重点维护',
   LOGO_URL             VARCHAR(200) COMMENT 'LOGO存放路径',
   IS_DELETED           BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否已删除',
   PRIMARY KEY (ID),
   KEY AK_CUSTOMER_CODE (CODE)
);

ALTER TABLE CUSTOMER COMMENT '客户信息表';

/*==============================================================*/
/* Index: IDX_CUSTOMER_CHARGE_OP                                */
/*==============================================================*/
CREATE INDEX IDX_CUSTOMER_CHARGE_OP ON CUSTOMER
(
   CHARGE_BY
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
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '客户ID',
   CODE                 VARCHAR(20) NOT NULL COMMENT '编号',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   DATA_TYPE            TINYINT NOT NULL COMMENT '数据类型',
   DATA_VALUE           VARCHAR(255) COMMENT '数据值',
   DATA_VALUE_INT       INT COMMENT '整型值',
   DATA_VALUE_DATE      DATETIME COMMENT '日期值',
   DATA_VALUE_DECIMAL   DECIMAL(12,4) COMMENT '浮点值',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUSTOMER_FEATURE COMMENT '客户特征表';

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
   ID                   INT NOT NULL COMMENT '客户ID',
   TENANT_ID            INT COMMENT '租户ID',
   LAST_CONTACT_TIME    DATETIME COMMENT '最近联系时间',
   CONTACT_TIMES        INT NOT NULL DEFAULT 0 COMMENT '总联系次数',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUSTOMER_STATE COMMENT '客户统计表';

/*==============================================================*/
/* Table: CUST_ACTIVITY                                         */
/*==============================================================*/
CREATE TABLE CUST_ACTIVITY
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '客户ID',
   CONTACT_ID           INT NOT NULL COMMENT '联系人ID',
   ACT_TYPE             INT NOT NULL COMMENT '联系类型',
   SUBJECT              VARCHAR(50) NOT NULL COMMENT '主题',
   CONTENT              VARCHAR(2000) COMMENT '内容',
   ACT_DATE             DATETIME NOT NULL COMMENT '往来日期',
   NEXT_ACT_DATE        DATETIME COMMENT '下次往来日期',
   CHARGE_BY            INT NOT NULL COMMENT '负责人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUST_ACTIVITY COMMENT '往来联系记录';

/*==============================================================*/
/* Index: IDX_CA_TENANT                                         */
/*==============================================================*/
CREATE INDEX IDX_CA_TENANT ON CUST_ACTIVITY
(
   TENANT_ID,
   CUSTOMER_ID,
   ACT_DATE,
   ACT_TYPE
);

/*==============================================================*/
/* Table: CUST_CONTACTS                                         */
/*==============================================================*/
CREATE TABLE CUST_CONTACTS
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '客户ID',
   NAME                 VARCHAR(200) NOT NULL COMMENT '名称',
   GENDER               TINYINT NOT NULL DEFAULT 0 COMMENT '性别(0002)
            0:unknown
            1:male
            2:female',
   MOBILE               VARCHAR(50) NOT NULL COMMENT '手机号',
   EMAIL                VARCHAR(100) NOT NULL COMMENT '电子邮件',
   PHONE                VARCHAR(50) NOT NULL COMMENT '联系电话',
   FAX                  VARCHAR(50) NOT NULL COMMENT '传真号',
   ADDRESS              VARCHAR(200) NOT NULL COMMENT '地址',
   ZIP_CODE             VARCHAR(20) NOT NULL COMMENT '邮编',
   HEAD_IMG_URL         VARCHAR(100) COMMENT '头像URL',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUST_CONTACTS COMMENT '客户联系人';

/*==============================================================*/
/* Index: IDX_CUST_CONTACTS_USER                                */
/*==============================================================*/
CREATE INDEX IDX_CUST_CONTACTS_USER ON CUST_CONTACTS
(
   TENANT_ID,
   CREATE_BY,
   CUSTOMER_ID
);

/*==============================================================*/
/* Table: CUST_EVENT                                            */
/*==============================================================*/
CREATE TABLE CUST_EVENT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   CUSTOMER_ID          INT NOT NULL COMMENT '客户ID',
   RECORD_ID            INT NOT NULL COMMENT '记录ID',
   EVENT_TYPE           INT NOT NULL COMMENT '事件类型',
   CONTENT              VARCHAR(2000) NOT NULL COMMENT '内容',
   EVENT_BY             INT NOT NULL COMMENT '事件发生用户',
   EVENT_DATE           DATETIME NOT NULL COMMENT '事件发生时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE CUST_EVENT COMMENT '客户事件表';

/*==============================================================*/
/* Index: IDX_CE_EVENT_DATE                                     */
/*==============================================================*/
CREATE INDEX IDX_CE_EVENT_DATE ON CUST_EVENT
(
   TENANT_ID,
   EVENT_DATE,
   EVENT_TYPE,
   CUSTOMER_ID
);

/*==============================================================*/
/* Table: DICT_PARAM_TYPE                                       */
/*==============================================================*/
CREATE TABLE DICT_PARAM_TYPE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   DICT_TYPE            VARCHAR(20) NOT NULL COMMENT '参数类型',
   DICT_TYPE_NAME       VARCHAR(50) NOT NULL COMMENT '参数类型名称',
   ALTER_MODE           TINYINT NOT NULL DEFAULT 1 COMMENT '更改方式
            1：不可修改
            2：可增加
            3：可修改
            4：可删除',
   STATUS               TINYINT NOT NULL DEFAULT 9 COMMENT '状态
            1：正常
            2：屏蔽
            9：系统',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (ID),
   KEY UNQ_DICT_TYPE (DICT_TYPE)
);

ALTER TABLE DICT_PARAM_TYPE COMMENT '参数字典类型表';

/*==============================================================*/
/* Index: IDX_DPT_CODE                                          */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_DPT_CODE ON DICT_PARAM_TYPE
(
   DICT_TYPE
);

/*==============================================================*/
/* Table: DICT_PARAM                                            */
/*==============================================================*/
CREATE TABLE DICT_PARAM
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   DICT_TYPE            VARCHAR(20) NOT NULL COMMENT '参数类型',
   DICT_VALUE           VARCHAR(100) NOT NULL COMMENT '参数值',
   DICT_CONTENT         VARCHAR(200) NOT NULL COMMENT '参数描述',
   FILTER               VARCHAR(500) NOT NULL COMMENT '过滤条件',
   PARAMS               VARCHAR(2000) NOT NULL COMMENT '附加参数',
   POSITION             INT NOT NULL DEFAULT 10 COMMENT '排序',
   STATUS               TINYINT NOT NULL DEFAULT 9 COMMENT '状态
            1：正常
            2：屏蔽
            9：系统',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (ID)
);

ALTER TABLE DICT_PARAM COMMENT '参数字典表';

/*==============================================================*/
/* Table: DYNAMIC_SEARCH                                        */
/*==============================================================*/
CREATE TABLE DYNAMIC_SEARCH
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   PUBLISH_FLAG         BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否公开',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE DYNAMIC_SEARCH COMMENT '动态查询';

/*==============================================================*/
/* Table: DYNAMIC_SEARCH_FILTER                                 */
/*==============================================================*/
CREATE TABLE DYNAMIC_SEARCH_FILTER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   DS_ID                INT NOT NULL COMMENT '动态查询ID',
   FIELD_NAME           VARCHAR(50) NOT NULL COMMENT '字段名',
   FIELD_TYPE           VARCHAR(20) NOT NULL COMMENT '字段类型',
   SYMBOL               VARCHAR(10) NOT NULL COMMENT '运算符',
   FIELD_VALUE          VARCHAR(200) NOT NULL COMMENT '字段值',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE DYNAMIC_SEARCH_FILTER COMMENT '动态查询过滤条件';

/*==============================================================*/
/* Table: FEEDBACK                                              */
/*==============================================================*/
CREATE TABLE FEEDBACK
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   TYPE                 INT NOT NULL COMMENT '类型',
   CONTENT              TEXT NOT NULL COMMENT '内容',
   REPLY                TEXT COMMENT '回复',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   REPLY_BY             INT COMMENT '回复人',
   REPLY_DATETIME       DATETIME COMMENT '回复时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE FEEDBACK COMMENT '用户反馈';

/*==============================================================*/
/* Table: FORGET_PASSWORD                                       */
/*==============================================================*/
CREATE TABLE FORGET_PASSWORD
(
   ID                   VARCHAR(36) NOT NULL COMMENT 'UUID',
   USER_ID              INT NOT NULL COMMENT '用户ID',
   SECURITY_KEY         VARCHAR(50) NOT NULL COMMENT '安全密钥',
   EXPIRED_DATETIME     DATETIME NOT NULL COMMENT '过期时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE FORGET_PASSWORD COMMENT '找回密码';

/*==============================================================*/
/* Table: MODULE                                                */
/*==============================================================*/
CREATE TABLE MODULE
(
   MODULE_ID            INT NOT NULL AUTO_INCREMENT COMMENT '模块ID',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   MODULE_NAME          VARCHAR(50) NOT NULL COMMENT '模块名称',
   PARENT_ID            INT NOT NULL COMMENT '父模块ID',
   IS_LEAF              BOOLEAN NOT NULL DEFAULT 1 COMMENT '是否叶子模块',
   STATUS               TINYINT NOT NULL DEFAULT 1 COMMENT '状态
            1：正常
            2：屏蔽',
   PRIMARY KEY (MODULE_ID)
);

ALTER TABLE MODULE COMMENT '模块表';

/*==============================================================*/
/* Table: FUNC                                                  */
/*==============================================================*/
CREATE TABLE FUNC
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   MODULE_ID            INT NOT NULL COMMENT '模块ID',
   FUNC_CODE            VARCHAR(50) NOT NULL COMMENT '功能点编号',
   FUNC_TYPE            INT(1) NOT NULL DEFAULT 5 COMMENT '操作类型
            1：增
            2：改
            3：删
            4：查
            5：综合',
   FUNC_NAME            VARCHAR(50) NOT NULL COMMENT '功能点名称',
   PRIMARY KEY (ID)
);

ALTER TABLE FUNC COMMENT '功能点';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
CREATE TABLE MENU
(
   MENU_ID              INT NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
   MENU_CODE            VARCHAR(50) NOT NULL COMMENT '菜单编号',
   MENU_NAME            VARCHAR(50) NOT NULL COMMENT '菜单名称',
   MENU_URL             VARCHAR(100) NOT NULL COMMENT '菜单链接',
   PARENT_ID            INT NOT NULL DEFAULT 0 COMMENT '父菜单ID',
   IS_LEAF              INT(1) NOT NULL DEFAULT 1 COMMENT '是否叶子节点',
   STATUS               INT(1) NOT NULL DEFAULT 1 COMMENT '状态
            1：正常
            2：屏蔽
            9：不可更改
            ',
   POSITION             INT(4) NOT NULL DEFAULT 0 COMMENT '排序位置',
   ICON_URL             VARCHAR(200) COMMENT '图标URL',
   IMAGE_URL            VARCHAR(200) COMMENT '图片URL',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (MENU_ID),
   UNIQUE KEY AK_MENU_CODE (MENU_CODE)
);

ALTER TABLE MENU COMMENT '菜单表';

/*==============================================================*/
/* Index: IDX_MENU_CODE                                         */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_MENU_CODE ON MENU
(
   MENU_CODE
);

/*==============================================================*/
/* Table: NOTE                                                  */
/*==============================================================*/
CREATE TABLE NOTE
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TITLE                VARCHAR(50) NOT NULL COMMENT '主题',
   CONTENT              TEXT COMMENT '内容',
   CREATE_USER_ID       INT NOT NULL COMMENT '操作员ID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '最近更新时间',
   PRIMARY KEY (ID)
);

ALTER TABLE NOTE COMMENT '便签';

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
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   PERMISSION_CODE      VARCHAR(50) NOT NULL COMMENT '权限编号
            资源编号（模块+功能点）',
   FUNC_ID              INT NOT NULL COMMENT '功能点ID',
   MODULE_ID            INT NOT NULL COMMENT '模块ID',
   PRIMARY KEY (ID),
   KEY UNQ_PERMISSION_CODE (PERMISSION_CODE)
);

ALTER TABLE PERMISSION COMMENT '权限表';

/*==============================================================*/
/* Table: QUICK_VIEW                                            */
/*==============================================================*/
CREATE TABLE QUICK_VIEW
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   NAME                 VARCHAR(20) NOT NULL COMMENT '名称',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   PUBLIC_FLAG          BOOLEAN NOT NULL DEFAULT 0 COMMENT '共享标志',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE QUICK_VIEW COMMENT '快速视图表';

/*==============================================================*/
/* Index: IDX_QV_NAME                                           */
/*==============================================================*/
CREATE INDEX IDX_QV_NAME ON QUICK_VIEW
(
   MODULE_CODE,
   CREATE_BY,
   NAME
);

/*==============================================================*/
/* Table: QUICK_VIEW_FILTER                                     */
/*==============================================================*/
CREATE TABLE QUICK_VIEW_FILTER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   QV_ID                INT NOT NULL COMMENT '快速视图ID',
   FIELD_NAME           VARCHAR(20) NOT NULL COMMENT '字段名',
   FIELD_TYPE           VARCHAR(20) NOT NULL COMMENT '字段类型',
   FIELD_VALUE          VARCHAR(200) COMMENT '字段值',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE QUICK_VIEW_FILTER COMMENT '快速视图过滤器';

/*==============================================================*/
/* Index: IDX_QVF_QV_ID                                         */
/*==============================================================*/
CREATE INDEX IDX_QVF_QV_ID ON QUICK_VIEW_FILTER
(
   QV_ID
);

/*==============================================================*/
/* Table: QUICK_VIEW_GRID                                       */
/*==============================================================*/
CREATE TABLE QUICK_VIEW_GRID
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   QV_ID                INT NOT NULL,
   COLUMN_NAME          VARCHAR(20) NOT NULL,
   FORMAT               VARCHAR(200),
   POSITION             INT NOT NULL DEFAULT 0,
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE QUICK_VIEW_GRID COMMENT '快速视图表格';

/*==============================================================*/
/* Index: IDX_QVG_QV_ID                                         */
/*==============================================================*/
CREATE INDEX IDX_QVG_QV_ID ON QUICK_VIEW_GRID
(
   QV_ID
);

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
CREATE TABLE ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   STATUS               TINYINT NOT NULL COMMENT '状态',
   REMARK               VARCHAR(200) COMMENT '备注',
   CREATE_BY            INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE ROLE COMMENT '角色表';

/*==============================================================*/
/* Table: ROLE_FUNC_PERMISSION                                  */
/*==============================================================*/
CREATE TABLE ROLE_FUNC_PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   ROLE_ID              INT NOT NULL COMMENT '角色ID',
   PERMISSION_CODE      VARCHAR(50) NOT NULL COMMENT '权限编号',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE ROLE_FUNC_PERMISSION COMMENT '角色功能权限表';

/*==============================================================*/
/* Table: SHORT_MESSAGE                                         */
/*==============================================================*/
CREATE TABLE SHORT_MESSAGE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TITLE                VARCHAR(200) NOT NULL COMMENT '标题',
   TYPE                 TINYINT NOT NULL DEFAULT 2 COMMENT '短信类型(2001)
            1：系统
            2：用户',
   CONTENT              VARCHAR(2000) COMMENT '发送内容',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建人ID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   RECEIVE_USER_ID      INT NOT NULL COMMENT '接收人ID',
   RECEIVE_FLAG         BOOLEAN NOT NULL DEFAULT 0 COMMENT '接收标志',
   RECEIVE_DATETIME     DATE COMMENT '接收时间',
   PRIMARY KEY (ID)
);

ALTER TABLE SHORT_MESSAGE COMMENT '站内短信';

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
/* Table: SMS_IN                                                */
/*==============================================================*/
CREATE TABLE SMS_IN
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户',
   SENDER               VARCHAR(50) NOT NULL COMMENT '发送人',
   RECEIVER             VARCHAR(50) NOT NULL COMMENT '接收人',
   CONTENT              VARCHAR(500) NOT NULL COMMENT '内容',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE SMS_IN COMMENT '短信接收';

/*==============================================================*/
/* Table: SMS_OUT                                               */
/*==============================================================*/
CREATE TABLE SMS_OUT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   SENDER               VARCHAR(50) NOT NULL COMMENT '发送人',
   RECEIVER             VARCHAR(50) NOT NULL COMMENT '接收人',
   CONTENT              VARCHAR(500) NOT NULL COMMENT '内容',
   SEND_FLAG            BOOLEAN NOT NULL DEFAULT 0 COMMENT '发送标志',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   SEND_DATETIME        DATETIME COMMENT '发送时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE SMS_OUT COMMENT '短信发送表';

/*==============================================================*/
/* Index: IDX_SO_SEND_FLAG                                      */
/*==============================================================*/
CREATE INDEX IDX_SO_SEND_FLAG ON SMS_OUT
(
   TENANT_ID,
   SEND_FLAG
);

/*==============================================================*/
/* Table: SYSTEM_INFO                                           */
/*==============================================================*/
CREATE TABLE SYSTEM_INFO
(
   SYSTEM_ID            INT NOT NULL COMMENT '系统ID',
   SYSTEM_CODE          VARCHAR(50) COMMENT '系统编号',
   SYSTEM_NAME          VARCHAR(200) NOT NULL COMMENT '系统名称',
   SYSTEM_VERSION       VARCHAR(50) NOT NULL COMMENT '系统版本',
   COPYRIGHT            VARCHAR(100) NOT NULL COMMENT '版权信息',
   COMPANY_NAME         VARCHAR(100) NOT NULL COMMENT '公司名称',
   STATUS               INT(1) NOT NULL DEFAULT 9 COMMENT '系统状态(1000)
            1：正常
            2：系统维护中
            9：首次运行
            ',
   LAST_UPDATE_DATETIME DATETIME COMMENT '系统最后更新时间',
   HOME_PATH            VARCHAR(200) NOT NULL COMMENT '数据保存路径',
   PRIMARY KEY (SYSTEM_ID)
);

ALTER TABLE SYSTEM_INFO COMMENT '系统信息表';

/*==============================================================*/
/* Table: TAG                                                   */
/*==============================================================*/
CREATE TABLE TAG
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   TYPE                 VARCHAR(50) NOT NULL COMMENT '类别',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   TOTAL_COUNT          INT NOT NULL COMMENT '总数量',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TAG COMMENT '标签';

/*==============================================================*/
/* Index: IDX_TAG_NAME                                          */
/*==============================================================*/
CREATE INDEX IDX_TAG_NAME ON TAG
(
   TENANT_ID,
   TYPE,
   NAME
);

/*==============================================================*/
/* Table: TENANT                                                */
/*==============================================================*/
CREATE TABLE TENANT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_CODE          VARCHAR(50) NOT NULL COMMENT '应用编号',
   TENANT_ALIAS         VARCHAR(100) NOT NULL COMMENT '应用别名',
   BAG_CODE             VARCHAR(50) NOT NULL COMMENT '产品功能包编号',
   ISOLATED_MODE        INT(1) NOT NULL DEFAULT 1 COMMENT '租用类型
            1：完全共享型
            2：独立Schema
            3：独立数据库',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '状态
            -3：到期
            -2：禁用
            -1：删除
            0：尚未激活
            1：启用',
   CREATE_BY            INT NOT NULL COMMENT '创建用户ID',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   MAX_MEMBERS          INT NOT NULL DEFAULT 1 COMMENT '最大成员数',
   MAX_STORAGE          INT NOT NULL DEFAULT 1 COMMENT '最大存储容量',
   TRIAL_DAYS           INT(3) NOT NULL DEFAULT 30 COMMENT '试用期限',
   EXPIRED_DATE         DATE NOT NULL COMMENT '到期日',
   PRICE                INT NOT NULL DEFAULT 0 COMMENT '购买价格',
   LOGO_URL             VARCHAR(200) COMMENT '图标地址',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TENANT COMMENT '应用租用表';

/*==============================================================*/
/* Index: IDX_TENANT_CODE                                       */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_TENANT_CODE ON TENANT
(
   TENANT_CODE
);

/*==============================================================*/
/* Table: TENANT_DICT_PARAM                                     */
/*==============================================================*/
CREATE TABLE TENANT_DICT_PARAM
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   DICT_TYPE            VARCHAR(4) NOT NULL COMMENT '参数类型',
   DICT_VALUE           VARCHAR(100) NOT NULL COMMENT '参数值',
   DICT_CONTENT         VARCHAR(100) NOT NULL COMMENT '参数描述',
   FILTER               VARCHAR(500) NOT NULL COMMENT '过滤条件',
   PARAMS               VARCHAR(2000) NOT NULL COMMENT '附加参数',
   POSITION             INT NOT NULL DEFAULT 10 COMMENT '排序',
   STATUS               TINYINT NOT NULL DEFAULT 9 COMMENT '状态
            1：正常
            2：屏蔽
            9：系统',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (ID)
);

ALTER TABLE TENANT_DICT_PARAM COMMENT '租户自定义参数字典表';

/*==============================================================*/
/* Index: IDX_TDP_DICT_TYPE                                     */
/*==============================================================*/
CREATE INDEX IDX_TDP_DICT_TYPE ON TENANT_DICT_PARAM
(
   TENANT_ID,
   DICT_TYPE,
   DICT_VALUE,
   FILTER
);

/*==============================================================*/
/* Table: TENANT_MEMBER                                         */
/*==============================================================*/
CREATE TABLE TENANT_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租赁ID',
   APP_ID               INT NOT NULL COMMENT '应用ID',
   USER_ID              INT COMMENT '用户ID',
   IS_ADMIN             BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否管理员',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '状态
            0：未认证
            1：通过认证
            2：锁定',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_USER_ID       INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TENANT_MEMBER COMMENT '租赁成员表';

/*==============================================================*/
/* Index: IDX_TM_USER                                           */
/*==============================================================*/
CREATE INDEX IDX_TM_USER ON TENANT_MEMBER
(
   USER_ID,
   APP_ID
);

/*==============================================================*/
/* Table: TENANT_ORDER                                          */
/*==============================================================*/
CREATE TABLE TENANT_ORDER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   ORDER_NO             VARCHAR(50) NOT NULL COMMENT '订单编号',
   ORDER_TYPE           INT NOT NULL COMMENT '订单类型',
   TENANT_ID            INT NOT NULL DEFAULT 0 COMMENT '租户ID',
   TENANT_CODE          VARCHAR(50) NOT NULL COMMENT '租户编号',
   TENANT_ALIAS         VARCHAR(50) NOT NULL COMMENT '租户别名',
   BAG_CODE             VARCHAR(50) NOT NULL COMMENT '功能包编号',
   ISOLATED_MODE        INT NOT NULL COMMENT '隔离级别',
   PRICE                DECIMAL(10,4) NOT NULL COMMENT '价格',
   QUANTITY             INT NOT NULL DEFAULT 1 COMMENT '购买份数',
   STATUS               INT NOT NULL DEFAULT -1 COMMENT '状态',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PAY_CHANNEL          VARCHAR(50) COMMENT '付款通道',
   PAY_DATETIME         DATETIME COMMENT '付款时间',
   ACTIVATE_DATETIME    DATETIME COMMENT '激活时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TENANT_ORDER COMMENT '租户订单';

/*==============================================================*/
/* Index: IDX_TO_ORDER_NO                                       */
/*==============================================================*/
CREATE UNIQUE INDEX IDX_TO_ORDER_NO ON TENANT_ORDER
(
   ORDER_NO
);

/*==============================================================*/
/* Table: TENANT_STAT                                           */
/*==============================================================*/
CREATE TABLE TENANT_STAT
(
   ID                   INT NOT NULL COMMENT '租户ID',
   TOTAL_ACCESS_COUNT   INT NOT NULL DEFAULT 0 COMMENT '总访问数',
   LAST_ACCESS_DATETIME DATETIME COMMENT '最近访问时间'
)
ENGINE = INNODB;

ALTER TABLE TENANT_STAT COMMENT '租用统计表';

/*==============================================================*/
/* Table: TODO                                                  */
/*==============================================================*/
CREATE TABLE TODO
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   CONTENT              VARCHAR(500) NOT NULL COMMENT '内容',
   STATUS               INT(2) NOT NULL DEFAULT 1 COMMENT '状态(2002)
            -2：推迟
            -1：取消
            1：未开始
            2：正在进行
            3：完成',
   IS_NEED_REMIND       BOOLEAN NOT NULL DEFAULT 0 COMMENT '是否需要提醒',
   REMIND_DATETIME      DATETIME COMMENT '提醒时间',
   TODO_DATETIME        DATETIME NOT NULL COMMENT '代办时间',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   ICON_URL             VARCHAR(200) COMMENT '图标',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TODO COMMENT '代办事项';

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
/* Table: USER_COMMENT                                          */
/*==============================================================*/
CREATE TABLE USER_COMMENT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   DATA_ID              INT NOT NULL COMMENT '数据ID',
   CONTENT              VARCHAR(2000) NOT NULL COMMENT '内容',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_COMMENT COMMENT '用户注释';

/*==============================================================*/
/* Index: IDX_UC_MODULE                                         */
/*==============================================================*/
CREATE INDEX IDX_UC_MODULE ON USER_COMMENT
(
   TENANT_ID,
   MODULE_CODE,
   DATA_ID
);

/*==============================================================*/
/* Table: USER_EXT                                              */
/*==============================================================*/
CREATE TABLE USER_EXT
(
   ID                   INT NOT NULL COMMENT '用户ID',
   LEVEL                INT(2) COMMENT '用户等级',
   SOURCE               VARCHAR(100) COMMENT '用户来源',
   BIRTHDAY             DATETIME COMMENT '生日',
   ZIP_CODE             VARCHAR(6) COMMENT '邮政编码',
   ADDRESS              VARCHAR(200) COMMENT '联系地址',
   DISTRICT             VARCHAR(50) COMMENT '区',
   CITY                 VARCHAR(50) COMMENT '城市',
   STATE                VARCHAR(50) COMMENT '省份',
   COUNTRY              VARCHAR(50) COMMENT '国家',
   SECURITY_QUESTION    VARCHAR(100) COMMENT '安全问题',
   ANSWER               VARCHAR(100) COMMENT '答案',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_EXT COMMENT '用户扩展表';

/*==============================================================*/
/* Table: USER_GROUP                                            */
/*==============================================================*/
CREATE TABLE USER_GROUP
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   PARENT_ID            INT NOT NULL COMMENT '父组织ID',
   CODE                 VARCHAR(100) NOT NULL COMMENT '编号',
   NAME                 VARCHAR(100) NOT NULL COMMENT '名称',
   TYPE                 INT(2) NOT NULL COMMENT '类型
            1：部门
            2：群组',
   DESCRIPTION          VARCHAR(500) COMMENT '描述',
   STATUS               INT(2) NOT NULL COMMENT '状态',
   CREATE_BY            INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新用户',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP COMMENT '用户组';

/*==============================================================*/
/* Table: USER_GROUP_MEMBER                                     */
/*==============================================================*/
CREATE TABLE USER_GROUP_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   GROUP_ID             INT NOT NULL COMMENT '用户组ID',
   USER_ID              INT NOT NULL COMMENT '用户ID',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP_MEMBER COMMENT '用户组成员';

/*==============================================================*/
/* Table: USER_GROUP_ROLE                                       */
/*==============================================================*/
CREATE TABLE USER_GROUP_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   GROUP_ID             INT NOT NULL COMMENT '用户组ID',
   ROLE_ID              INT NOT NULL COMMENT '角色ID',
   CREATE_BY            INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_GROUP_ROLE COMMENT '用户组角色表';

/*==============================================================*/
/* Table: USER_INFO                                             */
/*==============================================================*/
CREATE TABLE USER_INFO
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   LOGIN_ID             VARCHAR(50) NOT NULL COMMENT '登录ID',
   PASSWORD             VARCHAR(50) NOT NULL COMMENT '密码',
   USER_TYPE            INT(2) NOT NULL COMMENT '用户类型(1001)
            1：个人用户
            2：企业用户',
   USER_NAME            VARCHAR(50) NOT NULL COMMENT '用户名',
   NICK_NAME            VARCHAR(100) NOT NULL COMMENT '昵称',
   GENDER               INT(1) NOT NULL DEFAULT 0 COMMENT '性别(0002)
            0:Unknown
            1:Male
            2:Female',
   EMAIL                VARCHAR(50) COMMENT '电子邮件',
   MOBILE               VARCHAR(20) COMMENT '手机号',
   STATUS               INT(1) NOT NULL DEFAULT 0 COMMENT '状态(1002)
            0：未审核
            1：正常
            2：注销
            3：删除
            4：锁定',
   LOCALE               VARCHAR(20) NOT NULL DEFAULT 'en' COMMENT '语言',
   TIMEZONE             INT(2) NOT NULL DEFAULT 8 COMMENT '时区',
   THEME                VARCHAR(20) NOT NULL DEFAULT 'default' COMMENT '主题',
   HEAD_IMG_URL         VARCHAR(200) COMMENT '头像地址',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_DATETIME      DATETIME COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_INFO COMMENT '用户信息表';

/*==============================================================*/
/* Table: USER_MESSAGE                                          */
/*==============================================================*/
CREATE TABLE USER_MESSAGE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '所属租户',
   USER_ID              INT NOT NULL COMMENT '所属用户',
   MODULE_CODE          VARCHAR(20) NOT NULL COMMENT '模块编号',
   PARAMS               VARCHAR(4000) NOT NULL COMMENT '参数（JSON结构）',
   CREATE_BY            INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_MESSAGE COMMENT '消息中心';

/*==============================================================*/
/* Index: IDX_UMESSAGE_CREATE                                   */
/*==============================================================*/
CREATE INDEX IDX_UMESSAGE_CREATE ON USER_MESSAGE
(
   TENANT_ID,
   MODULE_CODE,
   CREATE_BY,
   CREATE_DATETIME
);

/*==============================================================*/
/* Index: IDX_UMESSAGE_USER                                     */
/*==============================================================*/
CREATE INDEX IDX_UMESSAGE_USER ON USER_MESSAGE
(
   TENANT_ID,
   USER_ID,
   MODULE_CODE,
   CREATE_DATETIME
);

/*==============================================================*/
/* Table: USER_OP_LOG                                           */
/*==============================================================*/
CREATE TABLE USER_OP_LOG
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   USER_ID              INT NOT NULL COMMENT '用户ID',
   APP_ID               INT NOT NULL COMMENT '应用ID',
   LOGIN_ID             VARCHAR(50) NOT NULL COMMENT '登录名',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   FUNC_CODE            VARCHAR(50) NOT NULL COMMENT '功能编号',
   OP_DATETIME          DATETIME NOT NULL COMMENT '操作时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_OP_LOG COMMENT '用户操作日志';

/*==============================================================*/
/* Table: USER_ROLE                                             */
/*==============================================================*/
CREATE TABLE USER_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   USER_ID              INT NOT NULL COMMENT '用户ID',
   ROLE_ID              INT NOT NULL COMMENT '角色ID',
   CREATE_BY            INT NOT NULL COMMENT '创建用户',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_ROLE COMMENT '用户角色关系表';

/*==============================================================*/
/* Table: USER_STATE                                            */
/*==============================================================*/
CREATE TABLE USER_STATE
(
   ID                   INT NOT NULL COMMENT '序号(同用户ID)',
   CURRENT_TENANT_ID    INT NOT NULL DEFAULT 0 COMMENT '当前租户ID',
   TOTAL_LOGIN_TIMES    INT NOT NULL DEFAULT 0 COMMENT '总登录次数',
   LOGIN_RETRY_TIMES    INT(1) NOT NULL DEFAULT 0 COMMENT '登录重试次数',
   LAST_LOGIN_DATETIME  DATETIME NOT NULL COMMENT '上次登录时间',
   LAST_LOGIN_IP        VARCHAR(20) NOT NULL COMMENT '上次登录IP',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE USER_STATE COMMENT '用户状态信息表';

ALTER TABLE DICT_PARAM ADD CONSTRAINT FK_DP_DICT_TYPE FOREIGN KEY (DICT_TYPE)
      REFERENCES DICT_PARAM_TYPE (DICT_TYPE) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE FUNC ADD CONSTRAINT FK_F_MODULE_ID FOREIGN KEY (MODULE_ID)
      REFERENCES MODULE (MODULE_ID) ON DELETE CASCADE;

ALTER TABLE PERMISSION ADD CONSTRAINT FK_P_MODULE_ID FOREIGN KEY (MODULE_ID)
      REFERENCES MODULE (MODULE_ID) ON DELETE CASCADE;

ALTER TABLE PERMISSION ADD CONSTRAINT FK_P_FUNC_ID FOREIGN KEY (FUNC_ID)
      REFERENCES FUNC (ID) ON DELETE CASCADE;

