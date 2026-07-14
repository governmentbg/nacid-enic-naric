--liquibase formatted sql

--changeset raneva:core_0037
ALTER TABLE nomenclatures.cfg_doc_type_requirement ALTER COLUMN requirement_expression TYPE character varying (500);