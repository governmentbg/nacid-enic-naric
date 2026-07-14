--liquibase formatted sql

--changeset aneva:core_0018
--validCheckSum: 8:4080ed08dbbeb1241453588b4a8b6d9b
--validCheckSum: 8:4ba9b7c6509f3d4c07c1e7b1ec3047bc
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('ACADEMIC_RECOGNITION_NACID_STATUS', 'Статуси - академично признаване НАЦИД', 1);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('REGPROF_STATUS', 'Статуси - професионални квалификации', 1);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('FO_APP_STATUS', 'Статуси - заявки във ФО', 1);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('FOREIGN_IDENTIFIER_TYPE', 'Вид чуждестранен идентификатор', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('LEGAL_TYPE', 'Вид лице', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('LEGAL_NATURE_TYPE', 'Вид юридическо лице', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('CURRENCY', 'Вид валута', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('ADDRESS_TYPE', 'Вид адрес', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('GRADUATION_WAY', 'Начин на дипломиране/придобиване на степен', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('TRAINING_FORM', 'Форма на обучение', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('QUALIFICATION_RANK', 'Разред', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('RECOGNITION_PURPOSE', 'Цел на признаването', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('PAYMENT_TYPE', 'Начин на плащане', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('EDUCATION_TYPE', 'Вид обучение', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('EDUCATION_LEVEL', 'Степен на образование', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('EDUCATION_AREA', 'Област на образование', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('DURATION_UNIT', 'Единица за продължителност на образованието', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('COPY_TYPE', 'Форма на прикачен файл', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('ACADEMIC_RECOGNITION_UNI_STATUS', 'Статуси - академично признаване университети', 0);