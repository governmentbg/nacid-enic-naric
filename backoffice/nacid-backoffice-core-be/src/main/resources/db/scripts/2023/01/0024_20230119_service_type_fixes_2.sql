--liquibase formatted sql

--changeset ggeorgiev:0024
alter table nomenclatures.cfg_service_type add column service_type varchar(20) not null;
ALTER TABLE nomenclatures.cfg_service_type
    ADD CONSTRAINT cst_service_type_check
        CHECK (nomenclatures.exists_refdata('SERVICE_TYPE', service_type));