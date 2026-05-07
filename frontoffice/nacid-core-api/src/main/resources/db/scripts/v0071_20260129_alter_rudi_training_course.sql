--liquibase formatted sql

--changeset murlev:core_0071
alter table services.rudi_training_course
    add column scientific_supervisor varchar(1500),
    add column scientific_supervisor_en varchar(1500),
    add column reviewers varchar(1500),
    add column reviewers_en varchar(1500),
    add column jury_chair varchar(1500),
    add column jury_chair_en varchar(1500),
    add column jury_members varchar(1500),
    add column jury_members_en varchar(1500);
