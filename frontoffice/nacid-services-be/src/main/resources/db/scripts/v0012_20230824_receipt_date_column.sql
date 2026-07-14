--liquibase formatted sql

--changeset raneva:services_0012
ALTER TABLE services.application_receipts ADD COLUMN date_created timestamp with time zone;