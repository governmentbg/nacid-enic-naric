--liquibase formatted sql


--changeset raneva:core_0023
--validCheckSum: 8:2c81bf26add02ede4819b330b9596a3d
UPDATE nomenclatures.country SET name='Неопределена', official_name='Неопределена' WHERE code='--';
DELETE FROM nomenclatures.ek_settlement where code = '99994';