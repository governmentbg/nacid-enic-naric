--liquibase formatted sql

--changeset aneva:core_0025
--validCheckSum: 8:ae5aa9c83960df52a74c112cafe852a2
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('HUMANITARIAN_STATUS', 'Хуманитарен статус', 0);