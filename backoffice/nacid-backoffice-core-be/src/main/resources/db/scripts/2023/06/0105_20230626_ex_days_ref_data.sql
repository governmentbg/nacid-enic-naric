--liquibase formatted sql

--changeset veizov:0105
INSERT INTO nomenclatures.reference_data_domain(domain, name, fo_replication_flag)
VALUES ('EXECUTION_DAYS_TYPE', 'Тип дни за изпълнение', 0);

INSERT INTO nomenclatures.reference_data(domain, code, name, index, active)
VALUES ('EXECUTION_DAYS_TYPE', 'WD', 'Работни дни', 1, 1);

INSERT INTO nomenclatures.reference_data(domain, code, name, index, active)
VALUES ('EXECUTION_DAYS_TYPE', 'CD', 'Календарни дни', 2, 1);

alter table nomenclatures.cfg_service_type add column execution_days_type varchar(20) not null default 'WD';
ALTER TABLE nomenclatures.cfg_service_type
    ADD CONSTRAINT cst_execution_days_type_check
        CHECK (nomenclatures.exists_refdata('EXECUTION_DAYS_TYPE', execution_days_type));
