--liquibase formatted sql

--changeset mnakova:0041
alter table rudi.training_course_diploma_examination add column state_approved_flag int not null default 0;
