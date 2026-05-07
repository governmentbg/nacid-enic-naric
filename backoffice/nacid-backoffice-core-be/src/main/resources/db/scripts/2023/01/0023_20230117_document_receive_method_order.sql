--liquibase formatted sql

--changeset ggeorgiev:0023
alter table nomenclatures.document_receive_method add column index int not null default 0;
alter table nomenclatures.document_receive_method alter column index drop default ;
alter table nomenclatures.document_receive_method add column default_flag int not null default 0;
alter table nomenclatures.document_receive_method alter column default_flag drop default;
