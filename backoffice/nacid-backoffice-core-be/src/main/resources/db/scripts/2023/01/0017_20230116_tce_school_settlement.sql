--liquibase formatted sql

--changeset ggeorgiev:0017
alter table rudi.training_course add column school_settlement_code varchar(10);
alter table rudi.training_course
    add CONSTRAINT tce_school_settlement_fk FOREIGN KEY (school_settlement_code)
        REFERENCES nomenclatures.ek_settlement (code);