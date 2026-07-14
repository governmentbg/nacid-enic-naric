--liquibase formatted sql

--changeset murlev:core_0059
INSERT INTO nomenclatures.reference_data_domain(domain, name, fo_only) VALUES ('SE_STATUS', 'Статуси - средно образование', 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SE_STATUS', 'DEN', 'Отказ', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Отказ', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SE_STATUS', 'ACK', 'Признато', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Признато', index = 0, active = 1;

