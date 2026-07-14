--liquibase formatted sql

--changeset ggeorgiev:0034
drop  index nomenclatures.cst_application_type_subtype_uk;

create unique index cst_application_type_subtype_uk1
    on nomenclatures.cfg_service_type (ate_code, ase_code, service_type) where (ase_code IS NOT NULL);

create unique index cst_application_type_subtype_uk2
    on nomenclatures.cfg_service_type (ate_code, service_type) where (ase_code IS NULL);