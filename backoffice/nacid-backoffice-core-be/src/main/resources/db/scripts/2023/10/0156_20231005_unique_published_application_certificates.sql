--liquibase formatted sql

--changeset ggeorgiev:0156 splitStatements:false
CREATE UNIQUE INDEX ace_unique_published_certificate ON common.application_certificates (apn_id) WHERE (certificate_status = 'P');