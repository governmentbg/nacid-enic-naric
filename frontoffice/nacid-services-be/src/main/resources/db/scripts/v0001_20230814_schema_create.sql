--liquibase formatted sql

--changeset raneva:services_0001
DROP SCHEMA IF EXISTS services CASCADE;

CREATE SCHEMA services AUTHORIZATION postgres;