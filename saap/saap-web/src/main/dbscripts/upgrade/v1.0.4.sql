DROP TABLE IF EXISTS AFFICHE;

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

DROP TABLE IF EXISTS FEEDBACK;

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


ALTER TABLE DICT_PARAM_TYPE CHANGE DICT_TYPE DICT_TYPE VARCHAR(20) NOT NULL;
ALTER TABLE DICT_PARAM CHANGE DICT_TYPE DICT_TYPE VARCHAR(20) NOT NULL;
ALTER TABLE TENANT_DICT_PARAM CHANGE DICT_TYPE DICT_TYPE VARCHAR(20) NOT NULL;