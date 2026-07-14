--liquibase formatted sql

--changeset murlev:0120
alter table rudi.commission_calendar add column secretary varchar(100);

