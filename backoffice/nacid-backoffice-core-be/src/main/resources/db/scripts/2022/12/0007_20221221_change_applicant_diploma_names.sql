--liquibase formatted sql

--changeset veizov:0007
ALTER TABLE common.applicant_diploma_names ALTER COLUMN apn_id SET NOT NULL;
ALTER TABLE common.applicant_diploma_names ADD CONSTRAINT adn_apn_id_unique UNIQUE (apn_id);
