--liquibase formatted sql

--changeset murlev:0124
alter table rudi.commission_calendar add column scanned_commission_protocol_id integer constraint sccr_dot_fk references common.attachments;