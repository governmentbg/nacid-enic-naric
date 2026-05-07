--liquibase formatted sql

--changeset murlev:0165
ALTER TABLE common.application ALTER COLUMN personal_data_usage_flag DROP NOT NULL;
ALTER TABLE common.application ALTER COLUMN data_authentic_flag DROP NOT NULL;
