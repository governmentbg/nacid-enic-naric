--liquibase formatted sql

--changeset veizov:0029
alter table common.personal_nacid_id add column date_generated timestamp with time zone not null;
