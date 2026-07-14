--liquibase formatted sql

--changeset ndimov:core_0069
ALTER TABLE services.grading_scale_details ADD COLUMN IF NOT EXISTS index NUMERIC;

