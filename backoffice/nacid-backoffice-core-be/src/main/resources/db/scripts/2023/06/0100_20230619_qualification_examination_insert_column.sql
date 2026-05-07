--liquibase formatted sql

--changeset mnakova:0100
alter table regprof.training_course_qualification_examination add column school_subject varchar(10);
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_school_subject_check
        CHECK (nomenclatures.exists_refdata('SCHOOL_SUBJECT', school_subject));