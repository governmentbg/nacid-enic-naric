--liquibase formatted sql

--changeset veizov:core_0011
--validCheckSum: 8:526830703ec7851e812314d52d8df3c9
--validCheckSum: 8:86c771a349ffa82b09f761a82b0c3bb3
create table nomenclatures.ek_district
(
    code                  varchar(10)       not null
        constraint ek_dit_pk
            primary key,
    code2                 varchar(10),
    secondlevelregioncode varchar(10),
    name                  varchar(255),
    mainsettlementcode    varchar(10),
    alias                 varchar(200),
    description           varchar(500),
    isactive              integer,
    version               integer default 0 not null,
    nameen                varchar(200)
);


INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('BLG', '01', 'BG41', 'Благоевград', '04279', '', '', 1, 0, 'Blaevgrad');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('BGS', '02', 'BG34', 'Бургас', '07079', '', '', 1, 0, 'Burgas');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('VAR', '03', 'BG33', 'Варна', '10135', '', '', 1, 0, 'Varna');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('VTR', '04', 'BG32', 'Велико Търново', '10447', '', '', 1, 0, 'Veliko Tarnovo');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('VID', '05', 'BG31', 'Видин', '10971', '', '', 1, 0, 'Vidin');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('VRC', '06', 'BG31', 'Враца', '12259', '', '', 1, 0, 'Vratsa');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('GAB', '07', 'BG32', 'Габрово', '14218', '', '', 1, 0, 'Gabrovo');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('DOB', '08', 'BG33', 'Добрич', '72624', '', '', 1, 0, 'Dobrich');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('KRZ', '09', 'BG42', 'Кърджали', '40909', '', '', 1, 0, 'Kardzhali');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('KNL', '10', 'BG41', 'Кюстендил', '41112', '', '', 1, 0, 'Kyustendil');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('LOV', '11', 'BG31', 'Ловеч', '43952', '', '', 1, 0, 'Lovech');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('MON', '12', 'BG31', 'Монтана', '48489', '', '', 1, 0, 'Montana');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('PAZ', '13', 'BG42', 'Пазарджик', '55155', '', '', 1, 0, 'Pazardzhik');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('PER', '14', 'BG41', 'Перник', '55871', '', '', 1, 0, 'Pernik');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('PVN', '15', 'BG31', 'Плевен', '56722', '', '', 1, 0, 'Pleven');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('PDV', '16', 'BG42', 'Пловдив', '56784', '', '', 1, 0, 'Plovdiv');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('RAZ', '17', 'BG32', 'Разград', '61710', '', '', 1, 0, 'Razgrad');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('RSE', '18', 'BG32', 'Русе', '63427', '', '', 1, 0, 'Ruse');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SLS', '19', 'BG32', 'Силистра', '66425', '', '', 1, 0, 'Silistra');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SLV', '20', 'BG34', 'Сливен', '67338', '', '', 1, 0, 'Sliven');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SML', '21', 'BG42', 'Смолян', '67653', '', '', 1, 0, 'Smolyan');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SOF', '22', 'BG41', 'София (столица)', '68134', '', '', 1, 0, 'Sofia (capital)');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SFO', '23', 'BG41', 'София', '68134', '', '', 1, 0, 'Sofia');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SZR', '24', 'BG34', 'Стара Загора', '68850', '', '', 1, 0, 'Stara Zara');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('TGV', '25', 'BG33', 'Търговище', '73626', '', '', 1, 0, 'Tarvishte');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('HKV', '26', 'BG42', 'Хасково', '77195', '', '', 1, 0, 'Haskovo');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SHU', '27', 'BG33', 'Шумен', '83510', '', '', 1, 0, 'Shumen');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('JAM', '28', 'BG34', 'Ямбол', '87374', '', '', 1, 0, 'Yambol');
INSERT INTO nomenclatures.ek_district (code, code2, secondlevelregioncode, name, mainsettlementcode, alias, description, isactive, version, nameen) VALUES ('SYS', '99', 'SYS', '*', '99992', '', '', 1, 0, '');