--liquibase formatted sql

--changeset veizov:0125
alter table common.error_log add column reference_id varchar(100);