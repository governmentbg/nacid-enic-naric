--liquibase formatted sql

--changeset mmurlev:0049 splitStatements:false
INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'PUB', 'Издадено', 8, 1);
