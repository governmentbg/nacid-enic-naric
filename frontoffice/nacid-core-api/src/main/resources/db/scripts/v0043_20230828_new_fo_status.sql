--liquibase formatted sql

--changeset raneva:core_0043
--validCheckSum: 8:c661870120126860bd68550ab8aad3e3
--validCheckSum: 8:7e5afa0e16bb4f304aaa4897671af0b4
INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'ADEN', 'Отказано приемане', 6, 1);