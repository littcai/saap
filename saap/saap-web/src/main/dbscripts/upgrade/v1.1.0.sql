DROP TABLE IF EXISTS DATA_PERMISSION;
DROP TABLE IF EXISTS USER_DEFINED_ATTR;
DROP TABLE IF EXISTS MESSAGE_BOARD;
DROP TABLE IF EXISTS METADATA_FIELD;


DROP TABLE IF EXISTS APP_STORE;

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


DROP TABLE IF EXISTS APP_ORDER;

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

