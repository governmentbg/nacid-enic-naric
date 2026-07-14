--liquibase formatted sql

--changeset ggeorgiev:core_0051
alter table nomenclatures.doc_types alter column name type varchar(255);

