--liquibase formatted sql

--changeset veizov:0067.1
DROP TABLE regprof.training_course_professional_institution_examination;
DROP TABLE regprof.professional_institution_examination;

--changeset veizov:0067.2
create table regprof.training_course_professional_institution_examination
(
    rte_id                                  integer                  not null
        constraint tcpie_pk
            primary key
        constraint tcpie_tce_fk
            references regprof.training_course,
    professional_institution_id             integer                  not null
        constraint institution_validity_professional_institution_id_fkey
            references regprof.professional_institution,
    examination_date                        date                     not null,
    secondary_professional_qualification_id integer
        constraint institution_validity_qual_bg_sec_id_fk
            references nomenclatures.secondary_professional_qualification,
    higher_professional_qualification       varchar(255),
    rights_educate_flag                     integer                  not null,
    legitimate_flag                         integer                  not null,
    user_created                            varchar(100)             not null,
    date_created                            timestamp with time zone not null,
    notes                                   text,
    program_legitimate_flag                 integer                  not null,
    current_accreditation_details           varchar(255),
    archive_accreditation_details           varchar(255)
);

--changeset veizov:0067.3
create index tcpie_pin_secondary_qualification_idx
    on regprof.training_course_professional_institution_examination (professional_institution_id, secondary_professional_qualification_id);

create index tcpie_pin_higher_qualification_idx
    on regprof.training_course_professional_institution_examination (professional_institution_id, higher_professional_qualification);