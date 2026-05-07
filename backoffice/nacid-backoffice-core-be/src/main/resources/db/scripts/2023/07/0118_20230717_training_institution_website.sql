--liquibase formatted sql

--changeset ggeorgiev:0118
alter table rudi.training_institution add column web_site varchar(255);
