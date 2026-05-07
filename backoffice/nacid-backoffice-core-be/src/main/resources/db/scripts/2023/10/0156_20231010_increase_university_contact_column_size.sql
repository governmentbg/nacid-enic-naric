--liquibase formatted sql

--changeset veizov:0156
alter table rudi.training_course_universities
alter column university_contact type varchar(300) using university_contact::varchar(300);