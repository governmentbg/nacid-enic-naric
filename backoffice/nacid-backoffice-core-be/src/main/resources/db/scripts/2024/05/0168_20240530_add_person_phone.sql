--liquibase formatted sql

--changeset murlev:0168
alter table common.person add phone varchar(120);
