--liquibase formatted sql

--changeset iborisov:0047 splitStatements:false
ALTER TABLE nomenclatures.country ADD COLUMN name_en VARCHAR(255), ADD COLUMN native_name VARCHAR(255);

UPDATE nomenclatures.country SET name_en='Ukraine', native_name='Україна' WHERE code='UA';
UPDATE nomenclatures.country SET name_en='United Kingdom', native_name='United Kingdom' WHERE code='GB';
UPDATE nomenclatures.country SET name_en='Russian Federation', native_name='Российская Федерация' WHERE code='RU';
UPDATE nomenclatures.country SET name_en='Netherlands', native_name='Nederland' WHERE code='NL';
UPDATE nomenclatures.country SET name_en='United States', native_name='United States' WHERE code='US';
UPDATE nomenclatures.country SET name_en='Germany', native_name='Deutschland' WHERE code='DE';
UPDATE nomenclatures.country SET name_en='Turkey', native_name='Türkiye' WHERE code='TR';
UPDATE nomenclatures.country SET name_en='France', native_name='France' WHERE code='FR';
