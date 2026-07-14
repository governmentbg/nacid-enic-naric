--liquibase formatted sql

--changeset kehayov:0075
alter table regprof.professional_institution_education_types
    add id serial;

alter table regprof.professional_institution_education_types
drop constraint piet_pk;

alter table regprof.professional_institution_education_types
    add primary key (id);

alter table regprof.professional_institution_education_types
    add unique (id);

create unique index inst_id_ed_type_unique
    on regprof.professional_institution_education_types (professional_institution_id, education_type);
