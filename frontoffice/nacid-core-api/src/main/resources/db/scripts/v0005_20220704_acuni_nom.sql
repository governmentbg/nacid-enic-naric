--liquibase formatted sql

--changeset veizov:core_0005
--validCheckSum: 8:6054daacef7872eb922ef4249b07ecf1
--validCheckSum: 8:b27da553972572f388862e2fb49470a9
create table nomenclatures.acadrec_uni_status
(
    id     serial       not null
        constraint acadrec_uni_status_pk
            primary key,
    name   varchar(255) not null,
    active integer      not null
);

INSERT INTO nomenclatures.acadrec_uni_status (id, name, active) VALUES (1, 'Призната', 1);
INSERT INTO nomenclatures.acadrec_uni_status (id, name, active) VALUES (3, 'Прекратена процедура', 1);
INSERT INTO nomenclatures.acadrec_uni_status (id, name, active) VALUES (4, 'Отказ', 1);
INSERT INTO nomenclatures.acadrec_uni_status (id, name, active) VALUES (2, 'Отменена (предпоследна)', 1);