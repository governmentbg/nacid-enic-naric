--liquibase formatted sql

--changeset ggeorgiev:core_0054
create table nomenclatures.cfg_copy_type_to_app_type
(
    id              serial    not null
        constraint cte_ate_pk
            primary key,
    copy_type_code          varchar(20)    not null
        constraint ctat_cte_check
            check (nomenclatures.exists_refdata('COPY_TYPE'::character varying, copy_type_code)),
    ate_code        varchar(4) not null
        constraint ctat_ate_ate_fk
            references nomenclatures.application_type,
    ase_code        varchar(4)
        constraint ctat_ate_ase_fk
            references nomenclatures.application_subtype
);

INSERT INTO nomenclatures.cfg_copy_type_to_app_type (copy_type_code, ate_code)
select cte.code, ate.code from nomenclatures.application_type ate, nomenclatures.reference_data cte
where domain = 'COPY_TYPE' and ate.code != 'SE';
INSERT INTO nomenclatures.cfg_copy_type_to_app_type (copy_type_code, ate_code) VALUES ('E', 'SE');
INSERT INTO nomenclatures.cfg_copy_type_to_app_type (copy_type_code, ate_code) VALUES ('COP', 'SE');