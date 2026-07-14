--liquibase formatted sql

--changeset veizov:0111
ALTER TABLE common.attachments drop column docflow_key;