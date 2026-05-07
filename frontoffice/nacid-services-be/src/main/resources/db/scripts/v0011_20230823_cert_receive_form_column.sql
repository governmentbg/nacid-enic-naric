--liquibase formatted sql

--changeset raneva:services_0011
ALTER TABLE services.application ADD COLUMN crf_code character varying (20);

ALTER TABLE services.application ADD CONSTRAINT apn_cert_receive_form CHECK (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM'::character varying, crf_code));