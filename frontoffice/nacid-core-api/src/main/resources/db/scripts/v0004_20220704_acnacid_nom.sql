--liquibase formatted sql

--changeset veizov:core_0004
--validCheckSum: 8:0ed9159023fa85e3d50c3011652ca340
--validCheckSum: 8:16d9ba6650cdeec865bc8b587ad35e02
create table nomenclatures.acadrec_nacid_status
(
    id      serial       not null
        constraint acadrec_nacid_status_pk
            primary key,
    name    varchar(255) not null,
    active  integer      not null
);

INSERT INTO nomenclatures.acadrec_nacid_status (id, name, active) VALUES (1, 'Признато', 1);
INSERT INTO nomenclatures.acadrec_nacid_status (id, name, active) VALUES (2, 'Обезсилено', 1);
INSERT INTO nomenclatures.acadrec_nacid_status (id, name, active) VALUES (3, 'Отказано', 1);

create table nomenclatures.edu_level
(
    id     serial       not null
        constraint edu_level_pk
            primary key,
    name   varchar(255) not null,
    active integer      not null
);

INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (1, 'Специалист', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (2, 'Бакалавър', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (3, 'Магистър', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (4, 'Доктор', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (5, 'Професионален бакалавър', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (6, 'Средно специално образование', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (7, 'Специалист с права на образователно-квалификационна степен Професионален бакалавър по...', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (8, 'Полувисше образование с права на образователно-квалификационна степен Професионален бакалавър', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (9, 'Висше образование с права на образователно-квалификационна степен Магистър', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (10, 'Доктор на науките', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (11, 'Средно образование', 1);
INSERT INTO nomenclatures.edu_level (id, name, active) VALUES (12, 'ВО без степен', 1);
