--liquibase formatted sql

--changeset ggeorgiev:core_0065
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('SE_STATUS', 'WEAK', 'Обезсилено', 0, 1) on conflict do nothing ;