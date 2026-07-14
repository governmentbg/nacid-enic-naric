--liquibase formatted sql

--changeset ggeorgiev:0021

--will be inserted by the migration code
--INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_replication_flag) VALUES ('HUMANITARIAN_STATUS', 'Хуманитарен статус', 0);
--INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('HUMANITARIAN_STATUS', 'REFS', 'Статут на бежанец', 0, 1);
--INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('HUMANITARIAN_STATUS', 'HS', 'Хуманитарен статут', 0, 1);
--INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('HUMANITARIAN_STATUS', 'SPS', 'Статут на специална закрила', 0, 1);
alter table common.person add column humanitarian_status_code varchar(20);

ALTER TABLE common.person
    ADD CONSTRAINT pen_humanitarian_status_check
        CHECK (nomenclatures.exists_refdata('HUMANITARIAN_STATUS', humanitarian_status_code));

ALTER TABLE common.person
    ADD CONSTRAINT pen_humanitarian_status_civil_id_type_check
        CHECK (humanitarian_status_code is null or (humanitarian_status_code is not null and civil_id_type in ('EGN', 'LNC')));
