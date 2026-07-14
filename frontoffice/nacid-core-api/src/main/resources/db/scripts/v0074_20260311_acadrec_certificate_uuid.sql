--liquibase formatted sql

--changeset ggeorgiev:core_0075
alter table registers.acadrec_nacid alter column certificate_uuid type varchar(100);