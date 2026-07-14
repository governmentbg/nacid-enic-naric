--liquibase formatted sql

--changeset ggeorgiev:0086 splitStatements:false
create table nomenclatures.cfg_recognition_category_to_app_type
(
    rcy_code varchar(20) not null
        constraint rcy_ate_rcy_check
            check (nomenclatures.exists_refdata('RECOGNITION_CATEGORY'::character varying, rcy_code)),
    ate_code varchar(4)  not null
        constraint rcy_ate_ate_fk
            references nomenclatures.application_type,
    ase_code varchar(4)  not null
        constraint rcy_ate_ase_fk
            references nomenclatures.application_subtype,
    constraint rcy_ate_pk
        primary key (rcy_code, ate_code, ase_code)
);

alter table rudi.training_course add column recognition_category_code varchar(10);

alter table rudi.training_course add constraint tce_recognition_category_check
  CHECK (nomenclatures.exists_refdata('RECOGNITION_CATEGORY', recognition_category_code));