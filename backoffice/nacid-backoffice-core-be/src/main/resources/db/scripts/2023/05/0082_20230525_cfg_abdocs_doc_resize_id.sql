--liquibase formatted sql

--changeset veizov:0082
ALTER TABLE nomenclatures.cfg_abdocs_document ALTER COLUMN id TYPE varchar(30);