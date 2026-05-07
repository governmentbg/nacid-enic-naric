--liquibase formatted sql

--changeset ggeorgiev:0110
alter table nomenclatures.cfg_doc_type_to_doc_category drop column docflow_flag;