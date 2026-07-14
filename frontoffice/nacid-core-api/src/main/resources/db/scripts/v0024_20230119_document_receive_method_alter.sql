--liquibase formatted sql

--changeset raneva:core_0024
alter table nomenclatures.document_receive_method add column index smallint not null default 0;
alter table nomenclatures.document_receive_method alter column index drop default ;
alter table nomenclatures.document_receive_method add column default_flag smallint not null default 0;
alter table nomenclatures.document_receive_method alter column default_flag drop default;