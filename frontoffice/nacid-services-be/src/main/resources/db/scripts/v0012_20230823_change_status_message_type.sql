--liquibase formatted sql

--changeset raneva:services_0012
ALTER TABLE services.app_status_history ALTER COLUMN reason_msg TYPE text;