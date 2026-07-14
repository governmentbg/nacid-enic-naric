--liquibase formatted sql

--changeset raneva:services_0014

ALTER TABLE services.application ALTER COLUMN personal_data_usage_flag DROP NOT NULL;
ALTER TABLE services.application ALTER COLUMN data_authentic_flag DROP NOT NULL;