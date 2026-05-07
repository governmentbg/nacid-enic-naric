--liquibase formatted sql

--changeset ggeorgiev:0134 splitStatements:false
alter table common.application add column efiling_signed_flag int;
