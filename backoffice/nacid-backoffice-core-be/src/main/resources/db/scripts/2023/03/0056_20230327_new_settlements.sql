--liquibase formatted sql

--changeset ggeorgiev:0056 splitStatements:false
INSERT INTO nomenclatures.ek_settlement (code, municipalitycode, districtcode, municipalitycode2, districtcode2, name, typename, settlementname, typecode, mayoraltycode, category, altitude, alias, description, isdistrict, isactive, version, settlementnameen, postalcode)
VALUES ('99991', 'PDV17', 'PDV', '2317', '16', 'с.Елешница', 'с.', 'Елешница', null, null, null, null, null, null, 0, 0, 0, 'Eleshnitsa', null);

INSERT INTO nomenclatures.ek_settlement (code, municipalitycode, districtcode, municipalitycode2, districtcode2, name, typename, settlementname, typecode, mayoraltycode, category, altitude, alias, description, isdistrict, isactive, version, settlementnameen, postalcode)
VALUES ('99990', 'BLG28', 'BLG', '0128', '01', 'с.Моравска', 'с.', 'Моравска', null, null, null, null, null, null, 0, 0, 0, 'Moravska', null);

INSERT INTO nomenclatures.ek_municipality (code, districtcode, code2, mainsettlementcode, category, name, alias, description, isactive, version, nameen)
VALUES ('SFO60', 'SFO', '2360', null, '4', 'Средногорие', '', '', 0, 0, 'Srednogorie');

INSERT INTO nomenclatures.ek_settlement (code, municipalitycode, districtcode, municipalitycode2, districtcode2, name, typename, settlementname, typecode, mayoraltycode, category, altitude, alias, description, isdistrict, isactive, version, settlementnameen, postalcode)
VALUES ('99989', 'SFO60', 'SFO', '2360', '23', 'гр.Средногорие', 'с.', 'Средногорие', null, null, null, null, null, null, 0, 0, 0, 'Srednogorie', null);