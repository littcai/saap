DROP TABLE IF EXISTS TAG;

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

DROP TABLE IF EXISTS PROJECT;

/*==============================================================*/
/* Table: PROJECT                                               */
/*==============================================================*/
CREATE TABLE PROJECT
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   NAME                 VARCHAR(200) NOT NULL COMMENT '名称',
   DESCR                VARCHAR(2000) COMMENT '详细描述',
   STATUS               INT NOT NULL COMMENT '状态',
   TAGS                 VARCHAR(255) NOT NULL COMMENT '标签集',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_USER_ID       INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE PROJECT COMMENT '项目';

DROP TABLE IF EXISTS PROJECT_TAG;

/*==============================================================*/
/* Table: PROJECT_TAG                                           */
/*==============================================================*/
CREATE TABLE PROJECT_TAG
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TENANT_ID            INT NOT NULL,
   PROJECT_ID           INT NOT NULL,
   TAG_ID               INT NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE PROJECT_TAG COMMENT '项目标签关系表';

DROP TABLE IF EXISTS PROJECT_USER_GROUP;

/*==============================================================*/
/* Table: PROJECT_USER_GROUP                                    */
/*==============================================================*/
CREATE TABLE PROJECT_USER_GROUP
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TENANT_ID            INT NOT NULL,
   PROJECT_ID           INT NOT NULL,
   USER_GROUP_ID        INT NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE PROJECT_USER_GROUP COMMENT '项目与用户组关系';

DROP TABLE IF EXISTS TASK;

/*==============================================================*/
/* Table: TASK                                                  */
/*==============================================================*/
CREATE TABLE TASK
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   PROJECT_ID           INT NOT NULL COMMENT '项目ID',
   TITLE                VARCHAR(255) NOT NULL COMMENT '标题',
   CONTENT              VARCHAR(4000) NOT NULL COMMENT '内容',
   PRIORITY             INT NOT NULL DEFAULT 2 COMMENT '优先级
            0：Suggestion
            1：Minor
            2：Major
            3：Critical
            4：Blocked',
   STATUS               INT NOT NULL DEFAULT 0 COMMENT '状态
            -2：中止
            -1：取消
            0：创建
            1：进行中
            2：暂停
            3：完成',
   ASSIGNEE             INT NOT NULL COMMENT '受任人',
   TAGS                 VARCHAR(255) NOT NULL COMMENT '标签集',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_USER_ID       INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TASK COMMENT '任务';

/*==============================================================*/
/* Index: IDX_TASK_ASSIGNEE                                     */
/*==============================================================*/
CREATE INDEX IDX_TASK_ASSIGNEE ON TASK
(
   TENANT_ID,
   ASSIGNEE,
   STATUS
);

/*==============================================================*/
/* Index: IDX_TASK_CREATOR                                      */
/*==============================================================*/
CREATE INDEX IDX_TASK_CREATOR ON TASK
(
   TENANT_ID,
   CREATE_USER_ID,
   STATUS
);

/*==============================================================*/
/* Index: IDX_TASK_STATUS                                       */
/*==============================================================*/
CREATE INDEX IDX_TASK_STATUS ON TASK
(
   TENANT_ID,
   STATUS
);

/*==============================================================*/
/* Index: IDX_TASK_PROJECT                                      */
/*==============================================================*/
CREATE INDEX IDX_TASK_PROJECT ON TASK
(
   TENANT_ID,
   PROJECT_ID
);

DROP TABLE IF EXISTS TASK_TAG;

/*==============================================================*/
/* Table: TASK_TAG                                              */
/*==============================================================*/
CREATE TABLE TASK_TAG
(
   ID                   INT NOT NULL AUTO_INCREMENT,
   TENANT_ID            INT NOT NULL,
   TASK_ID              INT NOT NULL,
   TAG_ID               INT NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TASK_TAG COMMENT '任务标签关系表';

DROP TABLE IF EXISTS TOPIC;

/*==============================================================*/
/* Table: TOPIC                                                 */
/*==============================================================*/
CREATE TABLE TOPIC
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '序号',
   TENANT_ID            INT NOT NULL COMMENT '租户ID',
   PROJECT_ID           INT NOT NULL COMMENT '项目ID',
   TITLE                VARCHAR(200) NOT NULL COMMENT '标题',
   CONTENT              VARCHAR(4000) NOT NULL COMMENT '内容',
   CREATE_USER_ID       INT NOT NULL COMMENT '创建人',
   CREATE_DATETIME      DATETIME NOT NULL COMMENT '创建时间',
   UPDATE_USER_ID       INT NOT NULL COMMENT '更新人',
   UPDATE_DATETIME      DATETIME NOT NULL COMMENT '更新时间',
   PRIMARY KEY (ID)
)
ENGINE = INNODB;

ALTER TABLE TOPIC COMMENT '讨论';
