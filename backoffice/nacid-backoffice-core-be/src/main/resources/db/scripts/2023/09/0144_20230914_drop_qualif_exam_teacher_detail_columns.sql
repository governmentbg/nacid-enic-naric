--liquibase formatted sql

--changeset mnakova:0144
alter table regprof.training_course_qualification_examination drop column if exists school_type;
alter table regprof.training_course_qualification_examination drop column if exists school_grade;
alter table regprof.training_course_qualification_examination drop column if exists school_subject;
alter table regprof.training_course_qualification_examination drop column if exists school_age_range;