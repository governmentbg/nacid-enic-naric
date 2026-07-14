--liquibase formatted sql

--changeset raneva:core_0040
--validCheckSum: 8:8d0642fa3580b15077085bfc395ed430
INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'DRFT', 'Чернова', 1, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'FIN', 'Приключена', 2, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'SUB', 'Подадена без е-подпис', 3, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'SIG', 'Подадена с е-подпис', 4, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('FO_APP_STATUS', 'ACC', 'Приета', 5, 1);