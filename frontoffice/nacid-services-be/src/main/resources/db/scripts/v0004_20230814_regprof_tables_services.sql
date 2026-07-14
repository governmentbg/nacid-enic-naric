--liquibase formatted sql

--changeset raneva:services_0004

-- Table: services.regprof_application

-- DROP TABLE services.regprof_application;

CREATE TABLE services.regprof_application
(
    apn_id integer NOT NULL,
    apostille_application_flag smallint,
    CONSTRAINT regprof_ran_pk PRIMARY KEY (apn_id),
    CONSTRAINT regprof_ran_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_application
    OWNER to postgres;


-- Table: services.regprof_training_experience

-- DROP TABLE services.regprof_training_experience;

CREATE TABLE services.regprof_training_experience
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    certificate_prof_qualification character varying(255) COLLATE pg_catalog."default",
    not_restricted_flag smallint NOT NULL,
    applies_for_country character varying(4),
    CONSTRAINT regprof_rte_pk PRIMARY KEY (id),
    CONSTRAINT regprof_rte_apn_ran_fk FOREIGN KEY (apn_id)
        REFERENCES services.regprof_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_rte_afc_fk FOREIGN KEY (applies_for_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_rte_apn_unq UNIQUE (apn_id)
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_training_experience
    OWNER to postgres;

-- Table: services.regprof_training_course

-- DROP TABLE services.regprof_training_course;

CREATE TABLE services.regprof_training_course
(
    rte_id integer NOT NULL,
    education_type character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT regprof_tce_pk PRIMARY KEY (rte_id),
    CONSTRAINT regprof_tce_rte_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_experience (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_tce_ete_check CHECK (nomenclatures.exists_refdata('EDUCATION_TYPE'::character varying, education_type))
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_training_course
    OWNER to postgres;

-- Table: services.regprof_higher_training_course

-- DROP TABLE services.regprof_higher_training_course;

CREATE TABLE services.regprof_higher_training_course
(
    rte_id integer NOT NULL,
    professional_institution_id integer,
    professional_institution character varying(255),
    professional_institution_former_name_id integer,
    professional_institution_former_name character varying(255),
    graduation_document_type_id integer,
    document_number character varying(50),
    document_date date,
    document_series character varying(32),
    document_reg_number character varying(32),
    professional_qualification character varying(255),
    edu_level character varying(20),
    CONSTRAINT regprof_htc_pk PRIMARY KEY (rte_id),
    CONSTRAINT regprof_htc_gdt_fk FOREIGN KEY (graduation_document_type_id)
        REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_htc_tce_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_htc_ell_check CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL'::character varying, edu_level))
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_higher_training_course
    OWNER to postgres;

-- Table: services.regprof_postgraduate_training_course

-- DROP TABLE services.regprof_postgraduate_training_course;

CREATE TABLE services.regprof_postgraduate_training_course
(
    rte_id integer NOT NULL,
    professional_institution_id integer,
    professional_institution character varying(255),
    professional_institution_former_name_id integer,
    professional_institution_former_name character varying(255),
    graduation_document_type_id integer,
    document_number character varying(50),
    document_date date,
    document_series character varying(32),
    document_reg_number character varying(32),
    professional_qualification character varying(255),
    CONSTRAINT regprof_pgtc_pk PRIMARY KEY (rte_id),
    CONSTRAINT regprof_pgtc_gdt_fk FOREIGN KEY (graduation_document_type_id)
        REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_pgtc_tce_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_postgraduate_training_course
    OWNER to postgres;

-- Table: services.regprof_secondary_training_course

-- DROP TABLE services.regprof_secondary_training_course;

CREATE TABLE services.regprof_secondary_training_course
(
    rte_id integer NOT NULL,
    professional_institution_id integer,
    professional_institution character varying(255),
    professional_institution_former_name_id integer,
    professional_institution_former_name character varying(255),
    graduation_document_type_id integer,
    document_number character varying(50),
    document_date date,
    document_series character varying(32),
    document_reg_number character varying(32),
    qualification_rank character varying(20),
    professional_qualification_id integer,
    professional_qualification character varying(255),
    CONSTRAINT regprof_stc_pk PRIMARY KEY (rte_id),
    CONSTRAINT regprof_stc_gdt_fk FOREIGN KEY (graduation_document_type_id)
        REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_stc_tce_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_stc_qrk_check CHECK (nomenclatures.exists_refdata('QUALIFICATION_RANK'::character varying, qualification_rank))
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_secondary_training_course
    OWNER to postgres;

-- Table: services.regprof_training_course_specialities

-- DROP TABLE services.regprof_training_course_specialities;

CREATE TABLE services.regprof_training_course_specialities
(
    idx smallint NOT NULL,
    rte_id integer NOT NULL,
    secondary_speciality_id integer,
    secondary_speciality character varying(255),
    higher_speciality character varying(255),
    sdk_speciality character varying(255),
    CONSTRAINT regprof_tcs_pk PRIMARY KEY (idx, rte_id),
    CONSTRAINT regprof_tcs_rte_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_training_course_specialities
    OWNER to postgres;

-- Table: services.regprof_profession_experience

-- DROP TABLE services.regprof_profession_experience;

CREATE TABLE services.regprof_profession_experience
(
    rte_id integer NOT NULL,
    profession_name character varying(255),
    years integer,
    months integer,
    days integer,
    CONSTRAINT regprof_pee_pk PRIMARY KEY (rte_id),
    CONSTRAINT regprof_pee_rte_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_training_experience (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_profession_experience
    OWNER to postgres;


-- Table: services.regprof_profession_experience_documents

-- DROP TABLE services.regprof_profession_experience_documents;

CREATE TABLE services.regprof_profession_experience_documents
(
    idx smallint NOT NULL,
    rte_id integer NOT NULL,
    document_number character varying(100),
    document_issuer character varying(100) NOT NULL,
    document_date date,
    profession_experience_document_type_code character varying(4) NOT NULL,
    CONSTRAINT regprof_ped_pk PRIMARY KEY (idx, rte_id),
    CONSTRAINT regprof_ped_pedt_fk FOREIGN KEY (profession_experience_document_type_code)
        REFERENCES nomenclatures.profession_experience_document_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_ped_rte_fk FOREIGN KEY (rte_id)
        REFERENCES services.regprof_profession_experience (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_profession_experience_documents
    OWNER to postgres;

-- Table: services.regprof_profession_experience_document_dates

-- DROP TABLE services.regprof_profession_experience_document_dates;

CREATE TABLE services.regprof_profession_experience_document_dates
(
    idx smallint NOT NULL,
    date_from date NOT NULL,
    date_to date NOT NULL,
    workday_duration character varying(20),
    ped_idx integer,
    rte_id integer,
    CONSTRAINT pedd_pk PRIMARY KEY (idx, ped_idx, rte_id),
    CONSTRAINT pedd_ped_fk FOREIGN KEY (ped_idx, rte_id)
        REFERENCES services.regprof_profession_experience_documents (idx, rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT regprof_pedd_wdn_check CHECK (nomenclatures.exists_refdata('WORKDAY_DURATION'::character varying, workday_duration))
)

    TABLESPACE pg_default;

ALTER TABLE services.regprof_profession_experience_document_dates
    OWNER to postgres;

