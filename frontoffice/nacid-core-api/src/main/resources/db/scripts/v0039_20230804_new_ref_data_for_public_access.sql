--liquibase formatted sql

--changeset raneva:core_0039
--validCheckSum: 8:bc5f90f7dd23bbf8b91e2f0d52639352

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('PUBLIC_ACCESS_INFO_FORM', 'Форма на получаване на информацията за достъп до обществена информация', 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('PUBLIC_ACCESS_INFO_FORM', 'VRE', 'Устна справка', 1, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('PUBLIC_ACCESS_INFO_FORM', 'CPA', 'Копие на хартиен носител', 2, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('PUBLIC_ACCESS_INFO_FORM', 'INV', 'Преглед на информацията - оригинал или копие', 3, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('PUBLIC_ACCESS_INFO_FORM', 'CTE', 'Копие на технически носител', 4, 1);