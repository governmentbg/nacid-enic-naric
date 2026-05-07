--liquibase formatted sql

--changeset ggeorgiev:0021
create table rudi.training_course_program_examination (
    tce_id int not null constraint tcpe_pk primary key,
    legitimate_flag int not null,
    training_program_type varchar(20) not null
);

ALTER TABLE rudi.training_course_program_examination
    ADD CONSTRAINT tcpm_program_type_check
        CHECK (nomenclatures.exists_refdata('TRAINING_PROGRAM_TYPE', training_program_type));
