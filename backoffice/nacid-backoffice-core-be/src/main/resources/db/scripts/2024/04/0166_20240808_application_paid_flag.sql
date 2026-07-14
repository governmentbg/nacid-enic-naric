--liquibase formatted sql

--changeset ggeorgiev:0166
alter table common.application add column paid_flag int;
