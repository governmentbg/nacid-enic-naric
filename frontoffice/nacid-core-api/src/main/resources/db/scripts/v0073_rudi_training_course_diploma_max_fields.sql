--liquibase formatted sql

--changeset ggeorgiev:core_0073
alter table services.rudi_training_course  alter column diploma_series type varchar(20);
alter table services.rudi_training_course  alter column diploma_registration_number type varchar(20);