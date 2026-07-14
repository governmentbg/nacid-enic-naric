--liquibase formatted sql

--changeset aneva:core_0030
DROP TABLE IF EXISTS nomenclatures.cfg_language_to_app_type;
DROP TABLE IF EXISTS nomenclatures.cfg_doc_type_requirement;

-- Table: nomenclatures.cfg_language_to_app_type

-- DROP TABLE nomenclatures.cfg_language_to_app_type

CREATE TABLE nomenclatures.cfg_language_to_app_type
(
    id serial NOT NULL,
    lae_code character varying(2) NOT NULL,
    ate_code character varying(20) NOT NULL,
    ase_code character varying(4) NOT NULL,
    CONSTRAINT cfg_lae_ate_pk PRIMARY KEY (id),
    CONSTRAINT cfg_lae_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_lae_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_lae_ate_lae_fk FOREIGN KEY (lae_code)
        REFERENCES nomenclatures.language (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_language_to_app_type
    OWNER to postgres;



-- Table: nomenclatures.cfg_doc_type_requirement

-- DROP TABLE nomenclatures.cfg_doc_type_requirement

CREATE TABLE nomenclatures.cfg_doc_type_requirement
(
    id serial NOT NULL,
    dte_id integer NOT NULL,
    cte_code character varying(20),
    ate_code character varying(20) NOT NULL,
    ase_code character varying(4),
    requirement_key character varying(100) NOT NULL,
    requirement_expression character varying (255),
    template_url character varying (255),
    CONSTRAINT cfg_dtr_pk PRIMARY KEY (id),
    CONSTRAINT cfg_dtr_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_dtr_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_dtr_cte_check CHECK (nomenclatures.exists_refdata('COPY_TYPE'::character varying, cte_code))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_doc_type_requirement
    OWNER to postgres;

