ALTER TABLE CONTACTS CHANGE CREATE_USER_ID CREATE_BY            INT NOT NULL COMMENT '创建人';
ALTER TABLE CONTACTS_GROUP_MEMBER ADD COLUMN CREATE_BY            INT NOT NULL COMMENT '创建人';