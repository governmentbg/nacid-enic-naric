--liquibase formatted sql

--changeset ggeorgiev:0083
alter table nomenclatures.cfg_doc_type_to_doc_category alter column condition type text;