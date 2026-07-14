--liquibase formatted sql

--changeset murlev:0119
alter table rudi.commission_participation add column chairman integer;

