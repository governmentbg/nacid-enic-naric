--liquibase formatted sql

--changeset aneva:core_0029
--validCheckSum: 8:e534326ed6a7986e4eab5d8d37b5cac9
--validCheckSum: 8:a93ae53dfef63e3188aa281a0c64bc0f
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('APPLICATION_STATUS', 'БО статуси на заявления', 0);