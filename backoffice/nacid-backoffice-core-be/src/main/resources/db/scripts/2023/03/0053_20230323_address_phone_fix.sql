--liquibase formatted sql

--changeset ggeorgiev:0052
alter table common.address alter column phone type varchar(120);