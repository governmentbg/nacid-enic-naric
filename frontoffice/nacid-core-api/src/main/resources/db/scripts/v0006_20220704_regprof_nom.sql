--liquibase formatted sql

--changeset veizov:core_0006
--validCheckSum: 8:1f5c700859c59d818f9c1599d9141dcd
--validCheckSum: 8:013429ed8831f0b0cbc346bfa690335f
create table nomenclatures.regprof_status
(
    id     integer      not null
        constraint regprof_status_pk
            primary key,
    name   varchar(255) not null,
    active integer      not null
);

INSERT INTO nomenclatures.regprof_status (id, name, active) VALUES (1, 'Издадено', 1);
INSERT INTO nomenclatures.regprof_status (id, name, active) VALUES (2, 'Отказ', 1);
INSERT INTO nomenclatures.regprof_status (id, name, active) VALUES (3, 'Обезсилено', 1);
