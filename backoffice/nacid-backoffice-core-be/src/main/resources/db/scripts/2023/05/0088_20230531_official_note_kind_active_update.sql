--liquibase formatted sql

--changeset mnakova:0088
UPDATE nomenclatures.reference_data
SET active = 0
WHERE domain = 'OFFICIAL_NOTE_KIND' and code in ('PAP', 'PRJ')