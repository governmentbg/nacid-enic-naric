--liquibase formatted sql

--changeset ggeorgiev:0016
alter table common.application alter column official_email_communication_flag set not null;