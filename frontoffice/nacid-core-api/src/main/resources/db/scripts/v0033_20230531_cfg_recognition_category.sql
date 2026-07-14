--liquibase formatted sql

--changeset raneva:core_0033

-- Table: nomenclatures.cfg_recognition_category_to_app_type

-- DROP TABLE nomenclatures.cfg_recognition_category_to_app_type;

CREATE TABLE nomenclatures.cfg_recognition_category_to_app_type
(
    rcy_code character varying(20) NOT NULL,
    ate_code character varying(4) NOT NULL,
    ase_code character varying(4) NOT NULL,
    CONSTRAINT rcy_ate_pk PRIMARY KEY (rcy_code, ate_code, ase_code),
    CONSTRAINT rcy_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rcy_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rcy_ate_rcy_check CHECK (nomenclatures.exists_refdata('RECOGNITION_CATEGORY'::character varying, rcy_code))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_recognition_category_to_app_type
    OWNER to postgres;