--liquibase formatted sql

--changeset veizov:0008
DROP TABLE common.applicant_diploma_names;

create table common.applicant_diploma_names
(
    apn_id                         integer    not null
        constraint and_pk
            primary key
        constraint adn_apn_fk
            references common.application,
    first_name                 varchar(100) not null,
    second_name                varchar(100),
    last_name                  varchar(100),
    civil_id                   varchar(50),
    civil_id_type              varchar(4)
        constraint adt_cit_fk
            references nomenclatures.civil_id_type,
    foreign_identifier_type    varchar(20)
        constraint and_pdt_check
            check (nomenclatures.exists_refdata('FOREIGN_IDENTIFIER_TYPE'::character varying, foreign_identifier_type)),
    foreign_identifier_country varchar(4)
        constraint pen_pdc_fk
            references nomenclatures.country
);

alter table common.applicant_diploma_names
    owner to postgres;
