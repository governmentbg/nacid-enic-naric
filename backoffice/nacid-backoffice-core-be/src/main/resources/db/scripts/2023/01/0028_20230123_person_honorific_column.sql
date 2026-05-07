--liquibase formatted sql

--changeset veizov:0028
alter table common.person add column honorific varchar(255);
