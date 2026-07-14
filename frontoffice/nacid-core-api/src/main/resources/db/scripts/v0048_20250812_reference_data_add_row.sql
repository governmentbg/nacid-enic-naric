--liquibase formatted sql

--changeset yilieva:0048 splitStatements:false
INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'CORR', 'За корекция', 7, 1);
