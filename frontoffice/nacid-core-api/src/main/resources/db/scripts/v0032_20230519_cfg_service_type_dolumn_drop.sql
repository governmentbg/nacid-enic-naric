--liquibase formatted sql

--changeset raneva:core_0032
ALTER TABLE nomenclatures.cfg_service_type DROP COLUMN liability_code;