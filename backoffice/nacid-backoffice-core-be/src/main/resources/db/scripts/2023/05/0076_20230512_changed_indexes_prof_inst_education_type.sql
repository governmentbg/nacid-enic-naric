--liquibase formatted sql

--changeset kehayov:0076
alter table regprof.professional_institution_education_types
drop constraint professional_institution_education_types_id_key;

alter table regprof.professional_institution_education_types
    rename constraint professional_institution_education_types_pkey to piet_pk;

alter table regprof.professional_institution_education_types
    add constraint piet_uk
        unique (professional_institution_id, education_type);


