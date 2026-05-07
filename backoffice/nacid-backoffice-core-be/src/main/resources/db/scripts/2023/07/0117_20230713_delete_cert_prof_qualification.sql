--liquibase formatted sql

--changeset veizov:0117
drop view if exists regprof.vw_certificate_prof_qualification;
alter table regprof.regprof_training_experience drop column certificate_prof_qualification;
