--liquibase formatted sql

--changeset raneva:services_0005

-- Table: services.lib_bibliographic_reference

-- DROP TABLE services.lib_bibliographic_reference;

CREATE TABLE services.lib_bibliographic_reference
(
    apn_id integer NOT NULL,
    search_bg_flag smallint,
    search_foreign_flag smallint,
    result_kind_bg character varying(20),
    result_kind_foreign character varying(20),
    subject text,
    keywords character varying(500),
    period_ret_from smallint,
    period_ret_to smallint,
    CONSTRAINT lib_bre_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_bre_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_bre_rekbg_check CHECK (nomenclatures.exists_refdata('BIBLIOGRAPHIC_REF_RESULT_KIND'::character varying, result_kind_bg)),
    CONSTRAINT lib_bre_rekfor_check CHECK (nomenclatures.exists_refdata('BIBLIOGRAPHIC_REF_RESULT_KIND'::character varying, result_kind_foreign))

)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_bibliographic_reference
    OWNER TO postgres;

-- Table: services.lib_bibliographic_reference_language

-- DROP TABLE services.lib_bibliographic_reference_language;

CREATE TABLE services.lib_bibliographic_reference_language
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    lae_code character varying(2) NOT NULL,
    CONSTRAINT lib_rle_pkey PRIMARY KEY (apn_id, idx),
    CONSTRAINT lib_rle_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.lib_bibliographic_reference (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_rle_lae_fk FOREIGN KEY (lae_code)
        REFERENCES nomenclatures.language (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_bibliographic_reference_language
    OWNER TO postgres;

-- Table: services.lib_document_delivery

-- DROP TABLE services.lib_document_delivery;

CREATE TABLE services.lib_document_delivery
(
    apn_id integer NOT NULL,
    CONSTRAINT lib_ddy_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_ddy_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_document_delivery
    OWNER TO postgres;


-- Table: services.lib_document_delivery_details

-- DROP TABLE services.lib_document_delivery_details;

CREATE TABLE services.lib_document_delivery_details
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    bibliographic_data text,
    digital_catalogue smallint NOT NULL,
    bg_library smallint NOT NULL,
    foreign_library smallint NOT NULL,
    dct_code character varying(20),
    file_id character varying(100),
    file_name character varying(255),
    relative_path character varying(100),
    root_directory character varying(50),
    CONSTRAINT lib_ddd_pkey PRIMARY KEY (idx, apn_id),
    CONSTRAINT lib_ddd_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.lib_document_delivery (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_ddd_dek_check CHECK (nomenclatures.exists_refdata('DOCUMENT_DELIVERY_COPY_TYPE'::character varying, dct_code))

)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_document_delivery_details
    OWNER TO postgres;

-- Table: services.lib_inquiry

-- DROP TABLE services.lib_inquiry;

CREATE TABLE services.lib_inquiry
(
    apn_id integer NOT NULL,
    inquiry_aim text,
    period_from smallint,
    period_to smallint,
    previous_inquiry character varying(25),
    CONSTRAINT lib_iny_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_iny_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_inquiry
    OWNER TO postgres;

-- Table: services.lib_inquiry_kind

-- DROP TABLE services.lib_inquiry_kind;

CREATE TABLE services.lib_inquiry_kind
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    ink_code character varying(20) NOT NULL,
    CONSTRAINT lib_iny_det_pkey PRIMARY KEY (apn_id, idx),
    CONSTRAINT lib_iny_det_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.lib_inquiry (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_iny_kd_ink_check CHECK (nomenclatures.exists_refdata('INQUIRY_KIND'::character varying, ink_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_inquiry_kind
    OWNER TO postgres;


-- Table: services.lib_official_note

-- DROP TABLE services.lib_official_note;

CREATE TABLE services.lib_official_note
(
    apn_id integer NOT NULL,
    detailed_information text,
    CONSTRAINT lib_one_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_one_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_official_note
    OWNER TO postgres;

-- Table: services.lib_official_note_details

-- DROP TABLE services.lib_official_note_details;

CREATE TABLE services.lib_official_note_details
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    onk_code character varying(20) NOT NULL,
    CONSTRAINT lib_one_det_pkey PRIMARY KEY (apn_id, idx),
    CONSTRAINT lib_one_det_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.lib_official_note (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_one_det_onk_check CHECK (nomenclatures.exists_refdata('OFFICIAL_NOTE_KIND'::character varying, onk_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_official_note_details
    OWNER TO postgres;

-- Table: services.lib_signal

-- DROP TABLE services.lib_signal;

CREATE TABLE services.lib_signal
(
    apn_id integer NOT NULL,
    violation_text text,
    violation_place text,
    checktime_text text,
    damage_text text,
    actions_text text,
    CONSTRAINT lib_sil_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_sil_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_signal
    OWNER TO postgres;

-- Table: services.lib_suggestion

-- DROP TABLE services.lib_suggestion;

CREATE TABLE services.lib_suggestion
(
    apn_id integer NOT NULL,
    suggestion_text text,
    CONSTRAINT lib_sun_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_sun_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_suggestion
    OWNER TO postgres;


-- Table: services.lib_public_access

-- DROP TABLE services.lib_public_access;

CREATE TABLE services.lib_public_access
(
    apn_id integer NOT NULL,
    about text,
    comment text,
    CONSTRAINT lib_pas_pkey PRIMARY KEY (apn_id),
    CONSTRAINT lib_pas_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_public_access
    OWNER TO postgres;

-- Table: services.lib_public_access_info_form

-- DROP TABLE services.lib_public_access_info_form;

CREATE TABLE services.lib_public_access_info_form
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    pif_code character varying(20) NOT NULL,
    CONSTRAINT lib_paif_pkey PRIMARY KEY (apn_id, idx),
    CONSTRAINT lib_paif_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.lib_public_access (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lib_paif_pif_check CHECK (nomenclatures.exists_refdata('PUBLIC_ACCESS_INFO_FORM'::character varying, pif_code))
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE services.lib_public_access_info_form
    OWNER TO postgres;