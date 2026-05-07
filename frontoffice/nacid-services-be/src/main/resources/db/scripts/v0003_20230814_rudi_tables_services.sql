--liquibase formatted sql

--changeset raneva:services_0003
-- Table: services.rudi_application

-- DROP TABLE services.rudi_application;

CREATE TABLE services.rudi_application
(
    apn_id integer NOT NULL,
    CONSTRAINT rudi_ran_pk PRIMARY KEY (apn_id),
    CONSTRAINT rudi_ran_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_application
    OWNER to postgres;

-- Table: services.rudi_training_course

-- DROP TABLE services.rudi_training_course;

CREATE TABLE services.rudi_training_course
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    diploma_num character varying(50),
    diploma_date date,
    diploma_series character varying(15),
    diploma_registration_number character varying(15),
    joint_degree_flag integer,
    training_start date,
    training_end date,
    training_duration double precision,
    duration_unit character varying(20),
    credits numeric(8,2),
    original_edu_level character varying(255),
    original_edu_level_translated character varying(255),
    qualification character varying(255),
    original_qualification character varying(255),
    school_country character varying(4),
    school_city character varying(100),
    school_name character varying(255),
    school_graduation_date date,
    school_notes text,
    prev_diploma_university_id integer,
    prev_diploma_university character varying(255),
    prev_diploma_edu_level character varying(20),
    prev_diploma_graduation_date date,
    prev_diploma_notes text,
    prev_diploma_speciality character varying(255),
    owner_id integer,
    owner_ean character varying(20),
    prof_group_id integer,
    thesis_topic text,
    thesis_topic_en text,
    thesis_defence_date date,
    thesis_bibliography integer,
    thesis_volume integer,
    thesis_annotation text,
    thesis_annotation_en text,
    thesis_language_code character varying(2),
    recognition_category_code character varying(20),
    CONSTRAINT rudi_tce_pk PRIMARY KEY (id),
    CONSTRAINT rudi_tce_pgp_fk FOREIGN KEY (prof_group_id)
        REFERENCES nomenclatures.prof_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_tle_fk FOREIGN KEY (thesis_language_code)
        REFERENCES nomenclatures.language (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_ran_fk FOREIGN KEY (apn_id)
        REFERENCES services.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_scy_fk FOREIGN KEY (school_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_owr_fk FOREIGN KEY (owner_id)
        REFERENCES services.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_dut_check CHECK (nomenclatures.exists_refdata('DURATION_UNIT'::character varying, duration_unit)),
    CONSTRAINT rudi_tce_pde_check CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL'::character varying, prev_diploma_edu_level)),
    CONSTRAINT rudi_tce_recognition_category_check CHECK (nomenclatures.exists_refdata('RECOGNITION_CATEGORY'::character varying, recognition_category_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course
    OWNER to postgres;

-- Table: services.rudi_training_course_recognition_purpose

-- DROP TABLE services.rudi_training_course_recognition_purpose;

CREATE TABLE services.rudi_training_course_recognition_purpose
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    rpe_code character varying(20) NOT NULL,
    notes character varying(255),
    CONSTRAINT rudi_tcerp_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tcerp_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tcerp_rpe_check CHECK (nomenclatures.exists_refdata('RECOGNITION_PURPOSE'::character varying, rpe_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course_recognition_purpose
    OWNER to postgres;

-- Table: services.rudi_training_course_speciality

-- DROP TABLE services.rudi_training_course_speciality;

CREATE TABLE services.rudi_training_course_speciality
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    speciality character varying(255) NOT NULL,
    original_speciality character varying(255),
    CONSTRAINT rudi_tcs_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tcs_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course_speciality
    OWNER to postgres;

-- Table: services.rudi_sar_application

-- DROP TABLE services.rudi_sar_application;

CREATE TABLE services.rudi_sar_application
(
    apn_id integer NOT NULL,
    statute_flag smallint NOT NULL,
    authenticity_flag smallint NOT NULL,
    recommendation_flag smallint NOT NULL,
    outgoing_number character varying(50),
    internal_number character varying(50),
    CONSTRAINT rudi_sar_pk PRIMARY KEY (apn_id),
    CONSTRAINT rudi_sar_apn_ran_fk FOREIGN KEY (apn_id)
        REFERENCES services.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_sar_application
    OWNER to postgres;

-- Table: services.rudi_training_course_university

-- DROP TABLE services.rudi_training_course_university;

CREATE TABLE services.rudi_training_course_university
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    uny_id integer,
    ord_num integer,
    faculty_id integer,
    uny_name character varying(255),
    faculty_name character varying(255),
    university_contact text,
    CONSTRAINT rudi_tcu_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tcu_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course_university
    OWNER to postgres;


-- Table: services.rudi_training_course_graduation_way

-- DROP TABLE services.rudi_training_course_graduation_way;

CREATE TABLE services.rudi_training_course_graduation_way
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    graduation_way_code character varying(20) NOT NULL,
    notes character varying(255),
    CONSTRAINT rudi_tcgw_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tcgw_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tcgw_gwy_check CHECK (nomenclatures.exists_refdata('GRADUATION_WAY'::character varying, graduation_way_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course_graduation_way
    OWNER to postgres;


-- Table: services.rudi_training_course_training_form

-- DROP TABLE services.rudi_training_course_training_form;

CREATE TABLE services.rudi_training_course_training_form
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    tfm_code character varying(20),
    notes character varying(255),
    CONSTRAINT rudi_tce_tfm_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tce_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tce_tfm_check CHECK (nomenclatures.exists_refdata('TRAINING_FORM'::character varying, tfm_code))
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_course_training_form
    OWNER to postgres;

-- Table: services.rudi_training_location

-- DROP TABLE services.rudi_training_location;

CREATE TABLE services.rudi_training_location
(
    idx smallint NOT NULL,
    tce_id integer NOT NULL,
    country_code character varying(4) COLLATE pg_catalog."default" NOT NULL,
    city character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT rudi_tln_pk PRIMARY KEY (idx, tce_id),
    CONSTRAINT rudi_tln_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rudi_tln_tce_fk FOREIGN KEY (tce_id)
        REFERENCES services.rudi_training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.rudi_training_location
    OWNER to postgres;