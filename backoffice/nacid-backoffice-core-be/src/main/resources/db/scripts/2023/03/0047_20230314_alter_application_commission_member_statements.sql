--liquibase formatted sql

--changeset murlev:0047
alter table rudi.application_commission_member_statements drop column attachment_id;
ALTER TABLE rudi.application_commission_member_statements ADD attachment_id integer;
