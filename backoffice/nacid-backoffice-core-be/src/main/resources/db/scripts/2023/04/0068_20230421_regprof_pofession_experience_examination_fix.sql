--liquibase formatted sql

--changeset ggeorgiev:0068.1
alter table regprof.profession_experience_examination drop column not_restricted_flag;