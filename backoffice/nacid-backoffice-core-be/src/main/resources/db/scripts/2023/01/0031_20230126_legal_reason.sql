--liquibase formatted sql

--changeset ggeorgiev:0031
alter table rudi.rudi_application add column legal_reason_id integer;
alter table rudi.rudi_application
    add CONSTRAINT ran_legal_reason_fk FOREIGN KEY (legal_reason_id)
        REFERENCES nomenclatures.legal_reason (id);

