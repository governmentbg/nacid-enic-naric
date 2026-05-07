--liquibase formatted sql

--changeset ggeorgiev:core_0031
alter table nomenclatures.country alter column code type varchar(4);