--liquibase formatted sql

--changeset kehayov:core_0007
create table nomenclatures.reference_data
(
    domain varchar(50)  not null,
    code   varchar(20)  not null,
    name   varchar(255) not null,
    index  integer      not null,
    active integer      not null,
    constraint ref_data_pkey
        primary key (domain, code)
);