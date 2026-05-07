--liquibase formatted sql

--changeset ggeorgiev:0108
CREATE TABLE nomenclatures.cfg_legal_reason_to_app_type
(
    lrn_id integer NOT NULL,
    ate_code character varying(20) NOT NULL,
    ase_code character varying(4) NOT NULL,
    CONSTRAINT cfg_lrn_ate_pk PRIMARY KEY (lrn_id, ate_code, ase_code),
    CONSTRAINT cfg_lrn_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code),
    CONSTRAINT cfg_lrn_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code),
    CONSTRAINT cfg_lrn_ate_lrn_fk FOREIGN KEY (lrn_id)
        REFERENCES nomenclatures.legal_reason (id)
);