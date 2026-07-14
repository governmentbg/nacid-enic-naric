--liquibase formatted sql

--changeset veizov:0036
alter table nomenclatures.cfg_abdocs_document add column doc_subject text;
