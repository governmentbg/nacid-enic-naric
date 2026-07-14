--liquibase formatted sql

--changeset ggeorgiev:core_0034
--validCheckSum: 8:4ec6c99133b251824cb4984c127cae31
update nomenclatures.reference_data_domain set fo_only = 1 where domain = 'ACADEMIC_RECOGNITION_UNI_STATUS';

INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('REGPROF_STATUS', 'WEAK', 'Обезсилено', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Обезсилено', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('REGPROF_STATUS', 'DEN', 'Отказ', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Отказ', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('REGPROF_STATUS', 'ACK', 'Признато', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Признато', index = 0, active = 1;

INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_NACID_STATUS', 'ACK', 'Признато', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Признато', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_NACID_STATUS', 'WEAK', 'Обезсилено', 0, 1)on conflict on constraint ref_data_pkey do update set name = 'Обезсилено', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_NACID_STATUS', 'DEN', 'Отказ', 0, 1)on conflict on constraint ref_data_pkey do update set name = 'Отказ', index = 0, active = 1;


INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_UNI_STATUS', 'ACK', 'Призната', 0, 1)on conflict on constraint ref_data_pkey do update set name = 'Признато', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_UNI_STATUS', 'DEN', 'Отказ', 0, 1)on conflict on constraint ref_data_pkey do update set name = 'Отказ', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_UNI_STATUS', 'TER', 'Прекратена процедура', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Прекратена процедура', index = 0, active = 1;
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ACADEMIC_RECOGNITION_UNI_STATUS', 'ANUL', 'Отменена (предпоследна)', 0, 1) on conflict on constraint ref_data_pkey do update set name = 'Отменена (предпоследна)', index = 0, active = 1;
