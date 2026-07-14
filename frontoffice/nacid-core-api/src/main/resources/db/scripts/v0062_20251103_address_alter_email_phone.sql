--liquibase formatted sql

--changeset ndimov:core_0062
ALTER TABLE services.address ALTER COLUMN email TYPE VARCHAR(100);
ALTER TABLE services.address ALTER COLUMN phone TYPE VARCHAR(120);
