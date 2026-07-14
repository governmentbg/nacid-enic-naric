--liquibase formatted sql

--changeset ggeorgiev:0152 splitStatements:false
alter table common.application_attached_docs drop column attachment_id;
alter table common.application_attached_docs drop column scanned_attachment_id;
