--liquibase formatted sql

--changeset veizov:0128
INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_replication_flag) VALUES ('CERTIFICATE_RECEIVE_FORM', 'Форма на получаване на удостоветението', 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('CERTIFICATE_RECEIVE_FORM', 'PAP', 'хартиен носител', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('CERTIFICATE_RECEIVE_FORM', 'E', 'електронен носител', 1, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('CERTIFICATE_RECEIVE_FORM', 'PE', 'хартиен и на електронен носител', 2, 1);

alter table common.application add column crf_code varchar(20);

ALTER TABLE common.application
    ADD CONSTRAINT apn_cert_receive_form
        CHECK (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM', crf_code));
