--liquibase formatted sql

--changeset veizov:0081
alter table libserv.libserv_application add column multiple_apn_id varchar(100);
