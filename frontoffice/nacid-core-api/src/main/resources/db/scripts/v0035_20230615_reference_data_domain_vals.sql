--liquibase formatted sql

--changeset raneva:core_0035
--validCheckSum: 8:fd83e593c758c88c6e5ebf4a8979e5ab
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('ACADEMIC_RECOGNITION_UNI_ENTRY_REQUEST_STATUS', 'Статус на записите към регистъра, създадени от университети', 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('ACADEMIC_RECOGNITION_UNI_ENTRY_REQUEST_STATUS', 'DRFT', 'Чернова', 1, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('ACADEMIC_RECOGNITION_UNI_ENTRY_REQUEST_STATUS', 'SUB', 'Подадено', 2, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('ACADEMIC_RECOGNITION_UNI_ENTRY_REQUEST_STATUS', 'ACC', 'Прието', 3, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('ACADEMIC_RECOGNITION_UNI_ENTRY_REQUEST_STATUS', 'REJ', 'Отказано', 4, 1);