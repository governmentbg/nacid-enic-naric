--liquibase formatted sql

--changeset aneva:core_0020
--validCheckSum: 8:e4511944ac60c974b7622f2125a92e6f
--validCheckSum: 8:ba569746f59a2c0e59ceb2004c97e081
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('WORKDAY_DURATION', 'Продължителност на работния ден', 0);

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('PERSONAL_DOCUMENT_TYPE', 'Вид документ за самоличност', 0);
