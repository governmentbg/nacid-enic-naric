--liquibase formatted sql

--changeset ggeorgiev:0044
alter table regprof.profession_experience_documents add column experience_calculation_flag integer not null default 0;
alter table regprof.profession_experience_documents alter column experience_calculation_flag drop default;
