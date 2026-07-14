--liquibase formatted sql

--changeset mnakova:core_0008
--validCheckSum: 8:4b48785f1092ea68df706357f09011fa
--validCheckSum: 8:931096dec62215d5d089c7c276c42922
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'SPE', 'Специалист', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'BAC', 'Бакалавър', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'MAS', 'Магистър', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'DOC', 'Доктор', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'PBA', 'Професионален бакалавър', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'SSE', 'Средно специално образование', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'SPB', 'Специалист с права на образователно-квалификационна степен Професионален бакалавър по...', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'SHS', 'Полувисше образование с права на образователно-квалификационна степен Професионален бакалавър', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'HBA', 'Висше образование с права на образователно-квалификационна степен Магистър', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'DSC', 'Доктор на науките', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'SE', 'Средно образование', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('EDUCATION_LEVEL', 'HWD', 'ВО без степен', 0, 1);
