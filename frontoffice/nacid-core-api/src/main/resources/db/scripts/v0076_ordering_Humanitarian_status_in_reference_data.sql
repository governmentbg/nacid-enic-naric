--liquibase formatted sql

--changeset ndimov:core_0076
UPDATE nomenclatures.reference_data SET index = 2 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'HS';
UPDATE nomenclatures.reference_data SET index = 0 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'TMPS';
UPDATE nomenclatures.reference_data SET index = 1 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'SPS';
UPDATE nomenclatures.reference_data SET index = 3 WHERE domain = 'HUMANITARIAN_STATUS' AND code = 'REFS';