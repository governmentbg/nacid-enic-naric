--liquibase formatted sql

--changeset aneva:core_0028
ALTER TABLE nomenclatures.cfg_doc_type_to_app_type ADD COLUMN show_expression character varying (255);