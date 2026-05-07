--liquibase formatted sql

--changeset veizov:0126
alter table libserv.inquiry alter column period_from drop not null;
alter table libserv.inquiry alter column period_to drop not null;