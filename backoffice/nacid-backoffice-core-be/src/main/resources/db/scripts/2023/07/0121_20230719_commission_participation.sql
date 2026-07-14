--liquibase formatted sql

--changeset murlev:0121
alter table rudi.commission_participation drop column chairman;
alter table rudi.commission_participation add column position varchar(20);

