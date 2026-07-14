--liquibase formatted sql

--changeset raneva:services_0002

-- Table: services.multiple_application

-- DROP TABLE services.multiple_application;

CREATE TABLE services.multiple_application
(
    id serial NOT NULL,
    CONSTRAINT multiapp_pk PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE services.multiple_application
    OWNER to postgres;

-- Table: services.address

-- DROP TABLE services.address;

CREATE TABLE services.address
(
    id serial NOT NULL,
    contact_person character varying(255),
    email character varying(80),
    address text,
    post_code character varying(12),
    phone character varying(70),
    fax character varying(70),
    coy_code character varying(4),
    city_name character varying(50),
    post_box character varying(100),
    set_code character varying(10),
    ate_code character varying(20) NOT NULL,
    CONSTRAINT ads_pk PRIMARY KEY (id),
    CONSTRAINT ads_coy_fk FOREIGN KEY (coy_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ads_set_fk FOREIGN KEY (set_code)
        REFERENCES nomenclatures.ek_settlement (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ads_ate_check CHECK (nomenclatures.exists_refdata('ADDRESS_TYPE'::character varying, ate_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.address
    OWNER to postgres;


-- Table: services.person

-- DROP TABLE services.person;

CREATE TABLE services.person
(
    id serial NOT NULL,
    first_name character varying(100),
    second_name character varying(100),
    last_name character varying(100),
    legal_name character varying(255),
    civil_id character varying(50),
    civil_id_type character varying(4) NOT NULL,
    foreign_identifier_type character varying(20),
    foreign_identifier_country character varying(4),
    legal_type character varying(20) NOT NULL,
    legal_nature_type character varying(20),
    origin_country character varying(4),
    origin_city character varying(30),
    origin_set_code character varying(10),
    birth_date date,
    citizenship_id character varying(4),
    email character varying(100),
    user_name character varying(100),
    humanitarian_status_code character varying(20),
    title character varying(150),
    CONSTRAINT pen_pk PRIMARY KEY (id),
    CONSTRAINT pen_ocoy_fk FOREIGN KEY (origin_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pen_oset_fk FOREIGN KEY (origin_set_code)
        REFERENCES nomenclatures.ek_settlement (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pen_cip_fk FOREIGN KEY (citizenship_id)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pen_cit_fk FOREIGN KEY (civil_id_type, legal_type)
        REFERENCES nomenclatures.civil_id_type (code, legal_type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pen_fic_fk FOREIGN KEY (foreign_identifier_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT pen_identifier_check CHECK ((civil_id_type::text = ANY (ARRAY['EGN'::character varying, 'LNC'::character varying, 'EIK'::character varying]::text[])) AND civil_id IS NOT NULL AND foreign_identifier_type IS NULL AND foreign_identifier_country IS NULL OR civil_id_type::text = 'DOC'::text AND civil_id IS NOT NULL AND foreign_identifier_type IS NOT NULL AND foreign_identifier_country IS NOT NULL),
    CONSTRAINT pen_lte_check CHECK (nomenclatures.exists_refdata('LEGAL_TYPE'::character varying, legal_type)),
    CONSTRAINT pen_lnt_check CHECK (nomenclatures.exists_refdata('LEGAL_NATURE_TYPE'::character varying, legal_nature_type)),
    CONSTRAINT pen_pdt_check CHECK (nomenclatures.exists_refdata('FOREIGN_IDENTIFIER_TYPE'::character varying, foreign_identifier_type)),
    CONSTRAINT pen_humanitarian_status_check CHECK (nomenclatures.exists_refdata('HUMANITARIAN_STATUS'::character varying, humanitarian_status_code)),
    CONSTRAINT pen_humanitarian_status_civil_id_type_check CHECK (humanitarian_status_code IS NULL OR humanitarian_status_code IS NOT NULL AND (civil_id_type::text = ANY (ARRAY['EGN'::character varying, 'LNC'::character varying]::text[])))
)

    TABLESPACE pg_default;

ALTER TABLE services.person
    OWNER to postgres;

-- Table: services.application

-- DROP TABLE services.application;

CREATE TABLE services.application
(
    id serial NOT NULL,
    ate_code character varying(20) NOT NULL,
    ase_code character varying(4),
    entry_num character varying(20),
    entry_date date,
    temp_number character varying(20),
    applicant_id integer,
    representative_id integer,
    contact_address_id integer,
    user_created character varying(100) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    document_receive_method_code character varying(4),
    document_recipient_address integer,
    personal_data_usage_flag smallint NOT NULL,
    data_authentic_flag smallint NOT NULL,
    diff_diploma_names_flag smallint,
    access_code character varying(50),
    representative_company_id character varying(50),
    representative_capacity character varying(600),
    service_type_code character varying(20),
    applicant_title_before character varying(50),
    applicant_title_after character varying(50),
    signed_flag smallint,
    paid_flag smallint,
    multiple_application_id integer,
    external_system_id character varying(100),
    external_system_document_id character varying(100),
    CONSTRAINT apn_pk PRIMARY KEY (id),
    CONSTRAINT apn_dra_fk FOREIGN KEY (document_recipient_address)
        REFERENCES services.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_apt_fk FOREIGN KEY (applicant_id)
        REFERENCES services.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_cas_fk FOREIGN KEY (contact_address_id)
        REFERENCES services.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_drm_fk FOREIGN KEY (document_receive_method_code)
        REFERENCES nomenclatures.document_receive_method (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_ree_fk FOREIGN KEY (representative_id)
        REFERENCES services.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_multiapp_fk FOREIGN KEY (multiple_application_id)
        REFERENCES services.multiple_application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT apn_service_type_check CHECK (nomenclatures.exists_refdata('SERVICE_TYPE'::character varying, service_type_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.application
    OWNER to postgres;

-- Table: services.application_notes

-- DROP TABLE services.application_notes;

CREATE TABLE services.application_notes
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    note_text text NOT NULL,
    date_created timestamp with time zone NOT NULL,
    user_created character varying(100) NOT NULL,
    date_updated timestamp with time zone,
    user_updated character varying(100),
    CONSTRAINT apn_notes_pk PRIMARY KEY (id),
    CONSTRAINT adn_notes_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.application_notes
    OWNER to postgres;

-- Table: services.applicant_diploma_names

-- DROP TABLE services.applicant_diploma_names;

CREATE TABLE services.applicant_diploma_names
(
    apn_id integer,
    first_name character varying(100) NOT NULL,
    second_name character varying(100),
    last_name character varying(100),
    civil_id character varying(50),
    civil_id_type character varying(4),
    foreign_identifier_type character varying(20),
    foreign_identifier_country character varying(4),
    CONSTRAINT adn_pk PRIMARY KEY (apn_id),
    CONSTRAINT adn_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT adn_cit_fk FOREIGN KEY (civil_id_type)
        REFERENCES nomenclatures.civil_id_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT adn_coy_fk FOREIGN KEY (foreign_identifier_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT adn_fit_check CHECK (nomenclatures.exists_refdata('FOREIGN_IDENTIFIER_TYPE'::character varying, foreign_identifier_type))
)

    TABLESPACE pg_default;

ALTER TABLE services.applicant_diploma_names
    OWNER to postgres;

-- Table: services.app_status_history

-- DROP TABLE services.app_status_history;

CREATE TABLE services.app_status_history
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    status_code character varying(20),
    bo_status_code character varying(20),
    date_created timestamp with time zone NOT NULL,
    reason_msg character varying(255),
    user_created character varying(100) NOT NULL,
    CONSTRAINT ash_pk PRIMARY KEY (id),
    CONSTRAINT ash_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ash_sts_check CHECK (nomenclatures.exists_refdata('FO_APP_STATUS'::character varying, status_code)),
    CONSTRAINT ash_bosts_check CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS'::character varying, bo_status_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.app_status_history
    OWNER to postgres;


-- Table: services.attachments

-- DROP TABLE services.attachments;

CREATE TABLE services.attachments
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    file_id character varying(100),
    file_name character varying(255),
    relative_path character varying(100),
    root_directory character varying(50) NOT NULL,
    CONSTRAINT att_pk PRIMARY KEY (idx, apn_id),
    CONSTRAINT att_unique UNIQUE (file_id, relative_path, root_directory)
)

    TABLESPACE pg_default;

ALTER TABLE services.attachments
    OWNER to postgres;

-- Table: services.application_attached_docs

-- DROP TABLE services.application_attached_docs;

CREATE TABLE services.application_attached_docs
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    description text,
    doc_type_id integer,
    copy_type_code character varying(20),
    CONSTRAINT aad_pk PRIMARY KEY (idx, apn_id),
    CONSTRAINT aad_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT aad_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT aad_cte_check CHECK (nomenclatures.exists_refdata('COPY_TYPE'::character varying, copy_type_code)),
    CONSTRAINT aad_dt_desc_check CHECK (doc_type_id IS NOT NULL OR description IS NOT NULL)
)

    TABLESPACE pg_default;

ALTER TABLE services.application_attached_docs
    OWNER to postgres;

-- Table: services.application_receipts

-- DROP TABLE services.application_receipts;

CREATE TABLE services.application_receipts
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    file_id character varying(100),
    file_name character varying(255),
    relative_path character varying(100),
    root_directory character varying(50) NOT NULL,
    status_code character varying(20) NOT NULL,
    active smallint NOT NULL,
    CONSTRAINT apr_pk PRIMARY KEY (idx, apn_id),
    CONSTRAINT apr_unique UNIQUE (file_id, relative_path, root_directory),
    CONSTRAINT apr_stc_check CHECK (nomenclatures.exists_refdata('FO_APP_STATUS'::character varying, status_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.application_receipts
    OWNER to postgres;