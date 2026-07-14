--liquibase formatted sql

--changeset mnakova:0060
alter table regprof.professional_institution_examination add column program_legitimate_flag int not null default 0;
alter table regprof.professional_institution_examination alter column program_legitimate_flag drop default;