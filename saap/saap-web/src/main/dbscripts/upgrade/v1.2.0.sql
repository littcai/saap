ALTER TABLE TENANT CHANGE CREATE_USER_ID CREATE_BY INT(11) NOT NULL  COMMENT '创建用户ID';
ALTER TABLE TENANT ADD MAX_STORAGE INT NOT NULL DEFAULT 1 COMMENT '最大存储容量';

/*==============================================================*/
/* Table: DOCUMENT                                              */
/*==============================================================*/
CREATE TABLE DOCUMENT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   UID                  VARCHAR(36) NOT NULL COMMENT 'UID',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   MODULE_CODE          VARCHAR(50) NOT NULL COMMENT '模块编号',
   RECORD_ID            INT NOT NULL COMMENT '记录ID',
   ATTACHMENT_ID        INT NOT NULL COMMENT '附件ID',
   CODE                 VARCHAR(50) NOT NULL COMMENT '编号',
   NAME                 VARCHAR(100) NOT NULL COMMENT '名称',
   EXT                  VARCHAR(10) NOT NULL COMMENT '类型',
   BRIEF                VARCHAR(200) COMMENT '文档摘要',
   REVISION             INT NOT NULL DEFAULT 1 COMMENT '修订号',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_BY            INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID),
   KEY AK_DOCUMENT_UID (UID)
)
ENGINE = INNODB;

ALTER TABLE DOCUMENT COMMENT '文档';

/*==============================================================*/
/* Index: IDX_DOCUMENT_CODE                                     */
/*==============================================================*/
CREATE INDEX IDX_DOCUMENT_CODE ON DOCUMENT
(
   TENANT_ID,
   MODULE_CODE,
   CODE
);

/*==============================================================*/
/* Table: DOCUMENT_HISTORY                                      */
/*==============================================================*/
CREATE TABLE DOCUMENT_HISTORY
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   DOC_ID               INT NOT NULL COMMENT '文档ID',
   ATTACHMENT_ID        INT NOT NULL COMMENT '附件ID',
   CODE                 VARCHAR(50) NOT NULL COMMENT '编号',
   NAME                 VARCHAR(100) NOT NULL COMMENT '名称',
   EXT                  VARCHAR(10) NOT NULL COMMENT '类型',
   BRIEF                VARCHAR(200) COMMENT '文档摘要',
   REVISION             INT NOT NULL DEFAULT 1 COMMENT '修订号',
   CREATE_BY            INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE DOCUMENT_HISTORY COMMENT '文档历史';

/*==============================================================*/
/* Index: IDX_DOC_HIS                                           */
/*==============================================================*/
CREATE INDEX IDX_DOC_HIS ON DOCUMENT_HISTORY
(
   TENANT_ID,
   DOC_ID
);