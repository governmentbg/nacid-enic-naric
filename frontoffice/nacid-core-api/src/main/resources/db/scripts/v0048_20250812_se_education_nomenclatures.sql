--liquibase formatted sql

--changeset mnakova:core_0048.1
INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_only) VALUES ('SEC_EDU_RECOGNITION_PURPOSE', 'Цел на признаването', 0);

--changeset mnakova:core_0048.2
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SEC_EDU_RECOGNITION_PURPOSE', 'AHE', 'Продължаване на образование', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SEC_EDU_RECOGNITION_PURPOSE', 'ODL', 'Кандидатстване за получаване на удостоверение за управление на МПС', 1, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SEC_EDU_RECOGNITION_PURPOSE', 'ALM', 'Достъп до пазара на труда', 2, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SEC_EDU_RECOGNITION_PURPOSE', 'OTH', 'Друго', 100, 1);
