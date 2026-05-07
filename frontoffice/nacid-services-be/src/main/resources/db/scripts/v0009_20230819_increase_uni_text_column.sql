--liquibase formatted sql

--changeset raneva:services_0009
ALTER TABLE services.rudi_training_course_university ALTER COLUMN uny_name TYPE character varying(700);