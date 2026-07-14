--liquibase formatted sql

--changeset aneva:core_0015

CREATE TABLE nomenclatures.civil_id_type
(
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    legal_type character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(200) COLLATE pg_catalog."default",
    active integer NOT NULL DEFAULT 1,
    CONSTRAINT pk_legal_type PRIMARY KEY (code),
    CONSTRAINT cit_lte_check CHECK (nomenclatures.exists_refdata('LEGAL_TYPE'::character varying, legal_type))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.civil_id_type
    OWNER to postgres;

CREATE UNIQUE INDEX cit_uk
    ON nomenclatures.civil_id_type USING btree
    (code COLLATE pg_catalog."default" ASC NULLS LAST, legal_type COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


CREATE TABLE nomenclatures.doc_types
(
    id integer NOT NULL,
    name character varying(80) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dte_pk PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.doc_types
    OWNER to postgres;

CREATE TABLE nomenclatures.application_type
(
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    CONSTRAINT ate_pk PRIMARY KEY (code)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.application_type
    OWNER to postgres;


CREATE TABLE nomenclatures.application_subtype
(
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ate_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    CONSTRAINT ast_pk PRIMARY KEY (code),
    CONSTRAINT ast_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.application_subtype
    OWNER to postgres;


CREATE TABLE nomenclatures.cfg_doc_type_to_app_type
(
    dte_id integer NOT NULL,
    ate_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ase_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dte_dcy_pk PRIMARY KEY (dte_id, ate_code, ase_code),
    CONSTRAINT dte_dcy_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT dte_dcy_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT dte_dcy_dte_fk FOREIGN KEY (dte_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_doc_type_to_app_type
    OWNER to postgres;


CREATE TABLE nomenclatures.document_receive_method
(
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    document_recipient_flag integer NOT NULL,
    active integer NOT NULL,
    eservices_require_payment_receipt_flag integer NOT NULL,
    CONSTRAINT document_receive_method_pk PRIMARY KEY (code)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.document_receive_method
    OWNER to postgres;


CREATE TABLE nomenclatures.graduation_document_type
(
    id serial,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    CONSTRAINT graduation_document_type_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.graduation_document_type
    OWNER to postgres;


CREATE TABLE nomenclatures.language
(
    code character varying(2) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    CONSTRAINT language_pk PRIMARY KEY (code)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.language
    OWNER to postgres;


CREATE TABLE nomenclatures.prof_group
(
    id integer NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    edu_area character varying(20) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    CONSTRAINT n_prof_group_pk PRIMARY KEY (id),
    CONSTRAINT pgp_eaa_check CHECK (nomenclatures.exists_refdata('EDUCATION_AREA'::character varying, edu_area))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.prof_group
    OWNER to postgres;

CREATE INDEX prof_group_edu_area_id_idx
    ON nomenclatures.prof_group USING btree
    (edu_area COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;


CREATE TABLE nomenclatures.profession_experience_document_type
(
    code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    active integer NOT NULL,
    for_experience_calculation_flag integer NOT NULL,
    CONSTRAINT pedt_pk PRIMARY KEY (code)
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.profession_experience_document_type
    OWNER to postgres;


CREATE TABLE nomenclatures.cfg_graduation_document_type_config
(
    graduation_document_type_id integer NOT NULL,
    education_type character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT gdtc_pk PRIMARY KEY (graduation_document_type_id, education_type),
    CONSTRAINT gdtc_gdt_fk FOREIGN KEY (graduation_document_type_id)
        REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT gdtc_ete_check CHECK (nomenclatures.exists_refdata('EDUCATION_TYPE'::character varying, education_type))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_graduation_document_type_config
    OWNER to postgres;



CREATE TABLE nomenclatures.cfg_edu_level_to_app_type
(
    ell_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ate_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ase_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ell_ate_pk PRIMARY KEY (ell_code, ate_code, ase_code),
    CONSTRAINT ell_ate_ell_check CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL'::character varying, ell_code)),
    CONSTRAINT ell_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ell_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_edu_level_to_app_type
    OWNER to postgres;


CREATE TABLE nomenclatures.cfg_graduation_way_to_app_type
(
    gwy_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ate_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    ase_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT gwy_ate_pk PRIMARY KEY (gwy_code, ate_code, ase_code),
    CONSTRAINT gwy_ate_gwy_check CHECK (nomenclatures.exists_refdata('GRADUATION_WAY'::character varying, gwy_code)),
    CONSTRAINT gwy_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT gwy_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_graduation_way_to_app_type
    OWNER to postgres;


CREATE TABLE nomenclatures.reference_data_domain
(
    domain character varying(50) NOT NULL,
    name character varying(150),
    fo_only smallint default 0,
    CONSTRAINT rddn_domain_pk PRIMARY KEY (domain)
)
    TABLESPACE pg_default;

ALTER TABLE nomenclatures.reference_data_domain
    OWNER to postgres;