--liquibase formatted sql

--changeset ggeorgiev:0029
alter table nomenclatures.cfg_app_status add column initial_status_flag int not null default 0;
alter table nomenclatures.cfg_app_status alter column initial_status_flag drop not null;
