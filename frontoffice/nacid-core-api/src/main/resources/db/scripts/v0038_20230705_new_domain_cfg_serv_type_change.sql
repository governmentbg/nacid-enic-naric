--liquibase formatted sql

--changeset raneva:core_0038
--validCheckSum: 8:3d84d3c36cdbfb1bf51b6e3e8be8737e
--validCheckSum: 8:0f35d115f83fa7e649f2c6ef37835bb5

INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('EXECUTION_DAYS_TYPE', 'Тип дни за изпълнение', 0);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('EXECUTION_DAYS_TYPE', 'CD', 'Календарни дни', 2, 1);

INSERT INTO nomenclatures.reference_data(
    domain, code, name, index, active)
VALUES ('EXECUTION_DAYS_TYPE', 'WD', 'Работни дни', 1, 1);

ALTER TABLE nomenclatures.cfg_service_type ADD COLUMN execution_days_type character varying(20) NOT NULL DEFAULT 'WD'::character varying;
ALTER TABLE nomenclatures.cfg_service_type ADD CONSTRAINT cst_execution_days_type_check CHECK (nomenclatures.exists_refdata('EXECUTION_DAYS_TYPE'::character varying, execution_days_type));
