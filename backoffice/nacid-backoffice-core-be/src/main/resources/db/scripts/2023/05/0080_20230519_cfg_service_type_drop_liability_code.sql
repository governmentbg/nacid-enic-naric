--liquibase formatted sql

--changeset ggeorgiev:0080
alter table nomenclatures.cfg_service_type drop column liability_code;
