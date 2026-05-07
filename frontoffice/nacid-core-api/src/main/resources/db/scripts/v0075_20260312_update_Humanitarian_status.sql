--liquibase formatted sql

--changeset ndimov:core_0075
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('HUMANITARIAN_STATUS', 'TMPS', 'Временна закрила', 0, 1) ON CONFLICT DO NOTHING ;
UPDATE nomenclatures.reference_data SET name = 'Хуманитарен статут', index = 0, active = 1 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'HS';
UPDATE nomenclatures.reference_data SET name = 'Бежанец', index = 0, active = 1 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'REFS';
UPDATE nomenclatures.reference_data SET name = 'Специална закрила', index = 0, active = 1 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'SPS';
UPDATE nomenclatures.reference_data SET name = 'Временна закрила', index = 0, active = 1 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'TMPS';
