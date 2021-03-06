ALTER TABLE TENANT_ORDER ADD ACTIVATE_DATETIME    DATETIME COMMENT '激活时间';

ALTER TABLE CUSTOMER CHANGE CHARGE_USER_ID CHARGE_BY INT NOT NULL;
ALTER TABLE CUSTOMER CHANGE CREATE_USER_ID CREATE_BY INT NOT NULL;
ALTER TABLE CUSTOMER CHANGE UPDATE_USER_ID UPDATE_BY INT NOT NULL;

ALTER TABLE CUST_ACTIVITY CHANGE CHARGE_USER_ID CHARGE_BY INT NOT NULL;
ALTER TABLE CUST_ACTIVITY CHANGE CREATE_USER_ID CREATE_BY INT NOT NULL;
ALTER TABLE CUST_ACTIVITY CHANGE UPDATE_USER_ID UPDATE_BY INT NOT NULL;
ALTER TABLE CUST_ACTIVITY ADD NEXT_ACT_DATE        DATETIME COMMENT '下次往来日期';

ALTER TABLE CUST_CONTACTS CHANGE CREATE_USER_ID CREATE_BY INT NOT NULL;
ALTER TABLE CUST_CONTACTS ADD HEAD_IMG_URL         VARCHAR(100) COMMENT '头像URL';

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
