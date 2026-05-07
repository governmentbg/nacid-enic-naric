--liquibase formatted sql

--changeset ggeorgiev:0058 splitStatements:false
CREATE TABLE nomenclatures.cfg_doc_type_to_app_status
(
    id serial not null,
    dte_id int not null
        constraint dte_ass_dte_fk
            references nomenclatures.doc_types,
    ate_code varchar(4)
        constraint dte_ass_ate_fk
            references nomenclatures.application_type,
    status_code varchar(20) not null,
    CONSTRAINT dte_ass_pk PRIMARY KEY (id)
);
ALTER TABLE nomenclatures.cfg_doc_type_to_app_status
    ADD CONSTRAINT dte_ass_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));
create unique index doc_type_app_status_uk
    on nomenclatures.cfg_doc_type_to_app_status (dte_id, ate_code, status_code);