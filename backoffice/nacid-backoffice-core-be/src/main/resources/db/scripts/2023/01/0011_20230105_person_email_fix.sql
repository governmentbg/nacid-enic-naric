--liquibase formatted sql

--changeset ggeorgiev:0011
alter table common.person alter column email drop not null;