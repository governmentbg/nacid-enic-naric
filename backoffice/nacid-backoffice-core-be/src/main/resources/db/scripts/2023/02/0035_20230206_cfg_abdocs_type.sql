--liquibase formatted sql

--changeset dveizov:0035
create table nomenclatures.cfg_abdocs_document
(
    id                 varchar(10)  not null
        constraint cadte_pk
            primary key,
    name               varchar(255) not null,
    doc_type_id numeric(5)   not null,
    doc_reg_type_id integer      not null
);