--liquibase formatted sql

--changeset raneva:core_0041
--validCheckSum: 8:5731c9d39f0a005fef4af0f26b688bc4
--validCheckSum: 8:c091f4e2ca9c5a51a1b2e2456704f2d2
INSERT INTO nomenclatures.application_subtype(
    code, ate_code, name, active)
VALUES ('SIG', 'LIB', 'Сигнал', 1);

INSERT INTO nomenclatures.application_subtype(
    code, ate_code, name, active)
VALUES ('SUG', 'LIB', 'Предложение', 1);

INSERT INTO nomenclatures.application_subtype(
    code, ate_code, name, active)
VALUES ('PUB', 'LIB', 'Достъп до обществена информация', 1);