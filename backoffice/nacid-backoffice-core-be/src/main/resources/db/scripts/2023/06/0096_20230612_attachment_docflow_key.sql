--liquibase formatted sql

--changeset veizov:0096
ALTER TABLE common.attachments ADD COLUMN docflow_key varchar(50);
