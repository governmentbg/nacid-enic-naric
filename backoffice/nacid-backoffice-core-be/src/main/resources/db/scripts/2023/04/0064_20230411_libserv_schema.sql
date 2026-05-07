--liquibase formatted sql

--changeset veizov:0064
CREATE SCHEMA IF NOT EXISTS libserv
    AUTHORIZATION postgres;

CREATE TABLE libserv.libserv_application
(
    apn_id integer NOT NULL,
    applicant_title_before character varying(50),
    applicant_title_after character varying(50),
    CONSTRAINT libserv_apn_pkey PRIMARY KEY (apn_id),
    CONSTRAINT libserv_apn_apn_fk FOREIGN KEY (apn_id)
        REFERENCES common.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.libserv_application
    OWNER TO postgres;

-- Table: libserv.bibliographic_reference

-- DROP TABLE libserv.bibliographic_reference;

CREATE TABLE libserv.bibliographic_reference
(
    apn_id integer NOT NULL,
    search_type character varying(20) NOT NULL,
    result_kind character varying(20) NOT NULL,
    subject text NOT NULL,
    keywords character varying(500) NOT NULL,
    period_ret_from smallint NOT NULL,
    period_ret_to smallint NOT NULL,
    CONSTRAINT bre_pkey PRIMARY KEY (apn_id),
    CONSTRAINT bre_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT bre_set_check CHECK (nomenclatures.exists_refdata('BIBLIOGRAPHIC_REF_SEARCH_TYPE'::character varying, search_type)),
    CONSTRAINT bre_rek_check CHECK (nomenclatures.exists_refdata('BIBLIOGRAPHIC_REF_RESULT_KIND'::character varying, result_kind))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.bibliographic_reference
    OWNER TO postgres;

-- Table: libserv.bibliographic_reference_language

-- DROP TABLE libserv.bibliographic_reference_language;

CREATE TABLE libserv.bibliographic_reference_language
(
    apn_id integer NOT NULL,
    lae_code character varying(2) NOT NULL,
    CONSTRAINT rle_pkey PRIMARY KEY (apn_id, lae_code),
    CONSTRAINT rle_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.bibliographic_reference (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rle_lae_fk FOREIGN KEY (lae_code)
        REFERENCES nomenclatures.language (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.bibliographic_reference_language
    OWNER TO postgres;

-- Table: libserv.document_delivery

-- DROP TABLE libserv.document_delivery;

CREATE TABLE libserv.document_delivery
(
    apn_id integer NOT NULL,
    CONSTRAINT ddy_pkey PRIMARY KEY (apn_id),
    CONSTRAINT ddy_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.document_delivery
    OWNER TO postgres;


-- Table: libserv.document_delivery_details

-- DROP TABLE libserv.document_delivery_details;

CREATE TABLE libserv.document_delivery_details
(
    id serial,
    apn_id integer NOT NULL,
    bibliographic_data text,
    digital_catalogue smallint NOT NULL,
    bg_library smallint NOT NULL,
    foreign_library smallint NOT NULL,
    dct_code character varying(20) NOT NULL,
    attachment_id integer,
    CONSTRAINT ddd_pkey PRIMARY KEY (id),
    CONSTRAINT ddd_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.document_delivery (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ddd_attid_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ddd_dek_check CHECK (nomenclatures.exists_refdata('DOCUMENT_DELIVERY_COPY_TYPE'::character varying, dct_code))

)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.document_delivery_details
    OWNER TO postgres;

-- Table: libserv.inquiry

-- DROP TABLE libserv.inquiry;

CREATE TABLE libserv.inquiry
(
    apn_id integer NOT NULL,
    ink_code character varying(20),
    inquiry_aim text,
    period_from smallint NOT NULL,
    period_to smallint NOT NULL,
    previous_inquiry character varying(25),
    CONSTRAINT iny_pkey PRIMARY KEY (apn_id),
    CONSTRAINT iny_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.Libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT iny_kd_ink_check CHECK (nomenclatures.exists_refdata('INQUIRY_KIND'::character varying, ink_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.inquiry
    OWNER TO postgres;

-- Table: libserv.impact_factor_report_rows

-- DROP TABLE libserv.impact_factor_report_rows;

CREATE TABLE libserv.impact_factor_report_rows
(
    apn_id integer NOT NULL,
    title character varying(255),
    year character varying(20),
    issn character varying(20),
    impact character varying(20),
    CONSTRAINT impact_factor_report_rows_pkey PRIMARY KEY (apn_id),
    CONSTRAINT impact_factor_report_rows_inquiry_id_fkey FOREIGN KEY (apn_id)
        REFERENCES libserv.inquiry (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.impact_factor_report_rows
    OWNER TO postgres;


-- Table: libserv.official_note

-- DROP TABLE libserv.official_note;

CREATE TABLE libserv.official_note
(
    apn_id integer NOT NULL,
    onk_code character varying(20) NOT NULL,
    detailed_information text,
    dissert_field text,
    akad_field text,
    nir_field text,
    first_applicant character varying(50),
    last_applicant character varying(50),
    draft_title text,
    draft_length character varying(10),
    draft_presented text,
    draft_num character varying(20),
    draft_applicant character varying(50),
    draft_protocol character varying(20),
    draft_date character varying(20),
    CONSTRAINT one_pkey PRIMARY KEY (apn_id),
    CONSTRAINT one_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT one_det_onk_check CHECK (nomenclatures.exists_refdata('OFFICIAL_NOTE_KIND'::character varying, onk_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.official_note
    OWNER TO postgres;

-- Table: libserv.signal

-- DROP TABLE libserv.signal;

CREATE TABLE libserv.signal
(
    apn_id integer NOT NULL,
    violation_text text NOT NULL,
    violation_place text NOT NULL,
    checktime_text text,
    damage_text text,
    actions_text text,
    CONSTRAINT sil_pkey PRIMARY KEY (apn_id),
    CONSTRAINT sil_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.signal
    OWNER TO postgres;

-- Table: libserv.suggestion

-- DROP TABLE libserv.suggestion;

CREATE TABLE libserv.suggestion
(
    apn_id integer NOT NULL,
    suggestion_text text NOT NULL,
    CONSTRAINT sun_pkey PRIMARY KEY (apn_id),
    CONSTRAINT sun_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.suggestion
    OWNER TO postgres;


-- Table: libserv.public_access

-- DROP TABLE libserv.public_access;

CREATE TABLE libserv.public_access
(
    apn_id integer NOT NULL,
    about text,
    comment text,
    CONSTRAINT pas_pkey PRIMARY KEY (apn_id),
    CONSTRAINT pas_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.libserv_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.public_access
    OWNER TO postgres;

-- Table: libserv.public_access_info_form

-- DROP TABLE libserv.public_access_info_form;

CREATE TABLE libserv.public_access_info_form
(
    apn_id integer NOT NULL,
    pif_code character varying(20) NOT NULL,
    CONSTRAINT paif_pkey PRIMARY KEY (apn_id, pif_code),
    CONSTRAINT paif_apn_fk FOREIGN KEY (apn_id)
        REFERENCES libserv.public_access (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT paif_pif_check CHECK (nomenclatures.exists_refdata('PUBLIC_ACCESS_INFO_FORM'::character varying, pif_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.public_access_info_form
    OWNER TO postgres;
