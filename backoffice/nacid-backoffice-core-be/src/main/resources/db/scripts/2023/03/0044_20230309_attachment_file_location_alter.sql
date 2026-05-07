--liquibase formatted sql

--changeset mnakova:0044
alter table common.attachments alter column file_location type varchar(255);
