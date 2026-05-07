--liquibase formatted sql

--changeset mnakova:0045
alter table common.attachments alter column content_type type varchar(255);
