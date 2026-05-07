--liquibase formatted sql

--changeset ggeorgiev:0043
alter table common.personal_nacid_id alter column value type varchar(50);
