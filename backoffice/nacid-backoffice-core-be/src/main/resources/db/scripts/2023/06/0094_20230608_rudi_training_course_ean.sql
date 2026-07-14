--liquibase formatted sql

--changeset veizov:0094
ALTER TABLE rudi.training_course ADD COLUMN owner_ean varchar(20);
