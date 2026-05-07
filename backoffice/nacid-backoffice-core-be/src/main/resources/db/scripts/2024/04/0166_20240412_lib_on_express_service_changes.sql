--liquibase formatted sql

--changeset kehayov:0166
INSERT INTO nomenclatures.cfg_service_type (ate_code, ase_code, execution_days, service_type, execution_days_type) VALUES ('LIB', 'ON', 0, 'E', 'WD');
INSERT INTO nomenclatures.cfg_service_type (ate_code, ase_code, execution_days, service_type, execution_days_type) VALUES ('LIB', 'ON', 0, 'S', 'WD');

