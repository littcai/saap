DROP TABLE IF EXISTS BIZ_ROLE;

/*==============================================================*/
/* Table: BIZ_ROLE                                              */
/*==============================================================*/
CREATE TABLE BIZ_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   STATUS               INT NOT NULL DEFAULT 1 COMMENT '状态',
   REMARK               VARCHAR(200) COMMENT '备注',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE BIZ_ROLE COMMENT '业务角色表';

DROP TABLE IF EXISTS BIZ_ROLE_DATA_PERMISSION;

/*==============================================================*/
/* Table: BIZ_ROLE_DATA_PERMISSION                              */
/*==============================================================*/
CREATE TABLE BIZ_ROLE_DATA_PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   BIZ_ROLE_ID          INT NOT NULL COMMENT '业务角色ID',
   CHARGE_BY            INT NOT NULL COMMENT '授权用户ID',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

DROP TABLE IF EXISTS BIZ_ROLE_MEMBER;

/*==============================================================*/
/* Table: BIZ_ROLE_MEMBER                                       */
/*==============================================================*/
CREATE TABLE BIZ_ROLE_MEMBER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   BIZ_ROLE_ID          INT NOT NULL COMMENT '业务角色ID',
   USER_ID              INT COMMENT '用户ID',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE BIZ_ROLE_MEMBER COMMENT '角色成员表';

DROP TABLE IF EXISTS BIZ_ROLE_FIELD_PERMISSION;

/*==============================================================*/
/* Table: BIZ_ROLE_FIELD_PERMISSION                             */
/*==============================================================*/
CREATE TABLE BIZ_ROLE_FIELD_PERMISSION
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   BIZ_ROLE_ID          INT NOT NULL COMMENT '业务角色ID',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   FIELD_LIST           VARCHAR(1000) NOT NULL COMMENT '字段列表',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE BIZ_ROLE_FIELD_PERMISSION COMMENT '字段权限';


