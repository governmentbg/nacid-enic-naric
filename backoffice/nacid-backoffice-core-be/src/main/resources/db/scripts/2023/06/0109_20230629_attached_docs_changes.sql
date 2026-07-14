--liquibase formatted sql

--changeset ggeorgiev:0109
alter table common.application_attached_docs rename column docflow_id to registration_number;
alter table common.application_attached_docs rename column docflow_date to registration_date;
alter table common.application_attached_docs add column docflow_id varchar(30);

alter table regprof.profession_experience_examination_attached_docs rename column docflow_id to registration_number;
alter table regprof.profession_experience_examination_attached_docs rename column docflow_date to registration_date;
alter table regprof.profession_experience_examination_attached_docs add column docflow_id varchar(30);


alter table regprof.training_course_document_examination_attached_docs rename column docflow_id to registration_number;
alter table regprof.training_course_document_examination_attached_docs rename column docflow_date to registration_date;
alter table regprof.training_course_document_examination_attached_docs add column docflow_id varchar(30);


alter table rudi.training_course_diploma_examination_attached_docs rename column docflow_id to registration_number;
alter table rudi.training_course_diploma_examination_attached_docs rename column docflow_date to registration_date;
alter table rudi.training_course_diploma_examination_attached_docs add column docflow_id varchar(30);

alter table rudi.university_examination_attached_docs rename column docflow_id to registration_number;
alter table rudi.university_examination_attached_docs rename column docflow_date to registration_date;
alter table rudi.university_examination_attached_docs add column docflow_id varchar(30);