--liquibase formatted sql

--changeset raneva:core_0042
--validCheckSum: 8:68f2a0e4ed155067a6d0aed0c941c9c2
--validCheckSum: 8:9cafde4b76c7a9c06c22d8abe3b56c06

INSERT INTO nomenclatures.reference_data_domain(domain, name, fo_only)
    VALUES ('CERTIFICATE_RECEIVE_FORM', 'Форма на получаване на удостоветението', 0);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('CERTIFICATE_RECEIVE_FORM', 'PAP', 'хартиен носител', 0, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('CERTIFICATE_RECEIVE_FORM', 'E', 'електронен носител', 1, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('CERTIFICATE_RECEIVE_FORM', 'PE', 'хартиен и на електронен носител', 2, 1);