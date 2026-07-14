--liquibase formatted sql

--changeset mnakova:0061
alter table regprof.professional_institution_examination add column current_accreditation_details varchar(255);
alter table regprof.professional_institution_examination add column archive_accreditation_details varchar(255);
