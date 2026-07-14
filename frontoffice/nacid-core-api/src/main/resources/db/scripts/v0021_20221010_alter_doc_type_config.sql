--liquibase formatted sql

--changeset aneva:core_0021
DROP TABLE nomenclatures.cfg_doc_type_to_app_type;

CREATE TABLE nomenclatures.cfg_doc_type_to_app_type
(
    id integer NOT NULL,
    dte_id integer NOT NULL,
    ate_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ase_code character varying(4) COLLATE pg_catalog."default",
    CONSTRAINT dte_ate_pk PRIMARY KEY (id),
    CONSTRAINT dte_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT dte_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT dte_ate_dte_fk FOREIGN KEY (dte_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_doc_type_to_app_type
    OWNER to postgres;


ALTER TABLE nomenclatures.doc_types ADD COLUMN active integer NOT NULL default 0;