--liquibase formatted sql

--changeset ggeorgiev:0155 splitStatements:false
INSERT INTO common.application_properties (code, value, description) VALUES ('RUDI_CERTIFICATE_WIDTH', '120', 'Ширина на rudi certificate-a');
INSERT INTO common.application_properties (code, value, description) VALUES ('RUDI_CERTIFICATE_HEIGHT', '120', 'Височина на rudi certificate-a');
INSERT INTO common.application_properties (code, value, description) VALUES ('REGPROF_CERTIFICATE_WIDTH', '120', 'Ширина на regprof certificate-a');
INSERT INTO common.application_properties (code, value, description) VALUES ('REGPROF_CERTIFICATE_HEIGHT', '120', 'Височина на regprof certificate-a');