--liquibase formatted sql

--changeset ndimov:core_0061
ALTER TABLE services.se_recognition_training_course
ALTER COLUMN school_name TYPE varchar(250);