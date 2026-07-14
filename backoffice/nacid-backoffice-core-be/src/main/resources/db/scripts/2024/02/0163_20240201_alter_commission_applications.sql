--liquibase formatted sql

--changeset murlev:0163
alter table rudi.commission_applications add column attached_doc_id integer constraint commission_application_attached_doc_id references common.application_attached_docs;