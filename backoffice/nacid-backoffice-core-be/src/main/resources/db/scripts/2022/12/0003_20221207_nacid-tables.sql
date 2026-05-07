create table common.address
(
    id        serial
        constraint address_pk
            primary key,
    contact_person varchar(255),
    email     varchar(80),
    address   text,
    post_code varchar(12),
    phone     varchar(70),
    fax       varchar(70),
    coy_code    varchar(4)
        constraint ads_acy_fk
            references nomenclatures.country,
    city_name varchar(50),
    post_box  varchar(100),
    set_code    varchar(10)
        constraint ads_set_fk
            references nomenclatures.ek_settlement,
    ate_code    varchar(20) not null
);
ALTER TABLE common.address
    ADD CONSTRAINT ads_ate_check
        CHECK (nomenclatures.exists_refdata('ADDRESS_TYPE', ate_code));


create table common.person
(
    id                serial
        constraint pen_pk
            primary key,
    first_name        varchar(100),
    second_name       varchar(100),
    last_name         varchar(100),
    legal_name        varchar(255),
    civil_id          varchar(50),
    civil_id_type     varchar(4) not null,
    foreign_identifier_type   varchar(20),
    foreign_identifier_country varchar(4)
        constraint pen_pdc_fk
            references nomenclatures.country,
    legal_type        varchar(20)   not null,
    legal_nature_type varchar(20),
    origin_country  varchar(4)
        constraint pen_bcy_fk
            references nomenclatures.country,
    origin_city        varchar(30),
    origin_set_code    varchar(10),
    birth_date         date,
    citizenship_id    varchar(4)
        constraint pen_cip_fk
            references nomenclatures.country,
    email             varchar(100) not null,
    user_name         varchar(100),
    address_id        integer
        constraint pen_ads_fk
            references common.address,
    active int not null,
    person_id 		  integer
        constraint pen_pen_id
            references common.person,
    CONSTRAINT pen_cit_fk FOREIGN KEY (civil_id_type, legal_type)
        REFERENCES nomenclatures.civil_id_type (code, legal_type)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT pen_origin_set_fk
        FOREIGN KEY (origin_set_code)
            REFERENCES nomenclatures.ek_settlement  MATCH SIMPLE
            ON UPDATE NO ACTION ON DELETE NO ACTION

);
create unique index pen_civil_id_uk on common.person (civil_id_type, civil_id) where active = 1 and civil_id_type in ('EGN', 'LNC', 'EIK');
create unique index pen_foreign_identifier_uk on common.person (foreign_identifier_type, foreign_identifier_country, civil_id) where active = 1 and civil_id_type in ('DOC');

ALTER TABLE common.person
    ADD CONSTRAINT pen_identifier_check
        CHECK ((civil_id_type in ('EGN', 'LNC', 'EIK') and civil_id is not null and foreign_identifier_type is null and foreign_identifier_country is null) OR (civil_id_type = 'DOC' and civil_id is not null and foreign_identifier_type is not null and foreign_identifier_country is not null));

ALTER TABLE common.person
    ADD CONSTRAINT pen_lte_check
        CHECK (nomenclatures.exists_refdata('LEGAL_TYPE', legal_type));
ALTER TABLE common.person
    ADD CONSTRAINT pen_lnt_check
        CHECK (nomenclatures.exists_refdata('LEGAL_NATURE_TYPE', legal_nature_type));
ALTER TABLE common.person
    ADD CONSTRAINT pen_pdt_check
        CHECK (nomenclatures.exists_refdata('FOREIGN_IDENTIFIER_TYPE', foreign_identifier_type));
--TODO:person
create table common.application
(
    id                                serial
        constraint apn_pk
            primary key,
    ate_code                            varchar(20)               not null
        constraint apn_ate_fk
            references nomenclatures.application_type,
    ase_code                            varchar(4)               not null
        constraint apn_ase_fk
            references nomenclatures.application_subtype,
    entry_num                         varchar(20)              not null,
    entry_date                        date                     not null,
    applicant_id                      integer
        constraint apn_apt_fk
            references common.person,
    representative_id                 integer
        constraint apn_ree_fk
            references common.person,
    contact_address_id                integer
        constraint apn_cas_fk
            references common.address,
    second_contact_address_id         integer
        constraint apn_sca_fk
            references common.address,
    user_created                      varchar(100) not null,
    date_created                      timestamp with time zone not null,
    official_email_communication_flag integer,
    diff_diploma_names_flag           integer    not null,
    personal_data_usage_flag          integer    not null,
    data_authentic_flag               integer    not null,
    status_code                       varchar(20)                  not null,
    docflow_status_code               varchar(20)                  not null,
    notes                             text,
    archive_num                       varchar(50),
    external_system_id                varchar(100),
    external_system_date              timestamp with time zone,
    efiling_id                        integer,
    final_status_history_id           integer,
    document_receive_method_code      varchar(4)
        constraint apn_drm_fk
            references nomenclatures.document_receive_method,
    document_recipient_address    integer
        constraint apn_ads_fk
            references common.address,
	personal_document_type_code   varchar(20),
	row_version                   integer not null
);
ALTER TABLE common.application
    ADD CONSTRAINT apn_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));
ALTER TABLE common.application
    ADD CONSTRAINT apn_dss_check
        CHECK (nomenclatures.exists_refdata('DOCFLOW_STATUS', docflow_status_code));
ALTER TABLE common.application
    ADD CONSTRAINT apn_piddte_check
        CHECK (nomenclatures.exists_refdata('PERSONAL_DOCUMENT_TYPE', personal_document_type_code));
		
create table common.application_responsible_users (
      id              serial
          primary key,
      apn_id          integer   not null
          constraint aru_apn_fk
              references common.application,
      responsible_user varchar(100) not null
);

create table common.applicant_diploma_names
(
    id                serial
        constraint and_pk
            primary key,
    apn_id  int
        constraint adn_apn_fk
            references common.application,
    first_name        varchar(100) not null,
    second_name       varchar(100),
    last_name         varchar(100),
    civil_id          varchar(50),
    civil_id_type     varchar(4),
    foreign_identifier_type   varchar(20),
    foreign_identifier_country varchar(4)
        constraint pen_pdc_fk
            references nomenclatures.country,
    CONSTRAINT adt_cit_fk FOREIGN KEY (civil_id_type)
        REFERENCES nomenclatures.civil_id_type (code)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE common.applicant_diploma_names
    ADD CONSTRAINT and_pdt_check
        CHECK (nomenclatures.exists_refdata('FOREIGN_IDENTIFIER_TYPE', foreign_identifier_type));

create table common.app_status_history
(
    id              serial
        primary key,
    apn_id          integer   not null
        constraint ash_apn_fk
            references common.application,
    status_code       varchar(20)   not null,
    legal_reason_id int
        constraint ash_lrn_fk
            references nomenclatures.legal_reason,
    commission_calendar_id     integer, --TODO:foreign key - тъпото е че таблицата за календара е в rudi схемата... Не би трябвало common-a да знае нещо за rudi...
    date_created    timestamp not null,
    user_created    varchar(100)   not null
);
ALTER TABLE common.app_status_history
    ADD CONSTRAINT ash_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));

alter table common.application add CONSTRAINT apn_fash_fk FOREIGN KEY (final_status_history_id)
    REFERENCES common.app_status_history (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION;

create table common.app_docflow_status_history
(
    id              serial
        primary key,
    apn_id          integer   not null
        constraint adsh_apn_fk
            references common.application,
    docflow_status_code       varchar(20)   not null,
    date_created    timestamp not null,
    user_created    varchar(100)   not null
);
ALTER TABLE common.app_docflow_status_history
    ADD CONSTRAINT adsh_sts_check
        CHECK (nomenclatures.exists_refdata('DOCFLOW_STATUS', docflow_status_code));

create table regprof.regprof_application
(
    apn_id                     integer
        constraint ran_pk primary key
        constraint ran_apn_fk references common.application,
    regprof_service_type_id    varchar(4)
        constraint ran_rst_fk
            references nomenclatures.regprof_service_type,
    end_date                   date,
    imi_correspondence         varchar(100),
    apostille_application_flag integer not null,
    application_country        varchar(4),
    CONSTRAINT ran_application_country_fk FOREIGN KEY (application_country)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE rudi.university
(
    id serial NOT NULL,
    country_code varchar(4) NOT NULL,
    bg_name character varying(255) NOT NULL,
    org_name character varying(255),
    address_id int not null,
    web_site character varying(255),
    active int not null,
    url_diploma_register text,
    university_generic_name varchar(255),
    CONSTRAINT uny_pk PRIMARY KEY (id),
    CONSTRAINT uny_ads_fk FOREIGN KEY (address_id)
        REFERENCES common.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT uny_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX uny_bgname_idx
    ON rudi.university
    USING btree
    (bg_name COLLATE pg_catalog."default");

CREATE INDEX uny_orgname_idx
    ON rudi.university
    USING btree
    (org_name COLLATE pg_catalog."default");

CREATE INDEX uny_coy_idx
    ON rudi.university
    USING btree
    (country_code);




CREATE TABLE rudi.university_faculty
(
    id serial NOT NULL,
    uny_id integer NOT NULL,
    name character varying(255) NOT NULL,
    original_name character varying(255),
    active int not null,
    CONSTRAINT ufy_pk PRIMARY KEY (id),
    CONSTRAINT ufy_uny_fk FOREIGN KEY (uny_id)
        REFERENCES rudi.university (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX ufy_uny_idx
    ON rudi.university_faculty
    USING btree
    (uny_id);


create table rudi.rudi_application
(
    apn_id                         integer
        constraint ran_apn_fk references common.application
        constraint ran_pk primary key,
    bg_address_owner               varchar(1) not null
        constraint ran_bg_address_owner_check
            check ((bg_address_owner)::text = ANY ((ARRAY ['A'::character varying, 'R'::character varying])::text[])),
    representative_authorized_flag integer not null,
    submitted_docs text
);



CREATE TABLE rudi.diploma_type
(
    id serial NOT NULL,
    visual_elements_descr text,
    protection_elements_descr text,
    number_format_descr text,
    notes text,
    active int not null,
    title character varying(255) NOT NULL,
    edu_level varchar(20),
    is_joint_degree integer,
    original_edu_level integer,
    bologna_cycle_id integer,
    nqf_id integer,
    eqf_id integer,
    acc_bologna_cycle_id integer,
    acc_nqf_id integer,
    acc_eqf_id integer,
    base_country varchar(4) not null
        constraint dte_bcy_fk
            references nomenclatures.country,
    type varchar(1) not null
        constraint dte_type_check
            check ((type)::text = ANY ((ARRAY ['N'::character varying, 'D'::character varying])::text[])), --N - normal, D - doctorate
    CONSTRAINT dte_pk PRIMARY KEY (id),
    CONSTRAINT dte_acc_bce_fk FOREIGN KEY (acc_bologna_cycle_id)
        REFERENCES nomenclatures.bologna_cycle (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_acc_eqf_fk FOREIGN KEY (acc_eqf_id)
        REFERENCES nomenclatures.european_qualifications_framework (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_acc_nqf_fk FOREIGN KEY (acc_nqf_id)
        REFERENCES nomenclatures.national_qualifications_framework (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_bce_fk FOREIGN KEY (bologna_cycle_id)
        REFERENCES nomenclatures.bologna_cycle (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_eqf_fk FOREIGN KEY (eqf_id)
        REFERENCES nomenclatures.european_qualifications_framework (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_nqf_fk FOREIGN KEY (nqf_id)
        REFERENCES nomenclatures.national_qualifications_framework (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dte_oel_fk FOREIGN KEY (original_edu_level)
        REFERENCES nomenclatures.original_edu_level (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.diploma_type
    ADD CONSTRAINT dte_ell_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', edu_level));





CREATE TABLE rudi.training_course (
                                      id serial NOT NULL,
                                      apn_id int not null,
                                      diploma_num character varying(50),
                                      diploma_date date,
                                      diploma_type_id integer,
                                      joint_degree_flag integer,--ima go v diploma type i se prehvyrlq ot tam...
                                      training_start date,
                                      training_end date,
                                      training_duration double precision,
                                      duration_unit varchar(20),
                                      credits numeric(8,2),
                                      qualification varchar(255),
                                      school_country varchar(4),
                                      school_city character varying(100),
                                      school_name character varying(255),
                                      school_graduation_date date,
                                      school_notes text,
                                      base_university_id integer,
                                      prev_diploma_country varchar(4),
                                      prev_diploma_city character varying(30),
                                      prev_diploma_university_id integer,
                                      prev_diploma_edu_level varchar(20),
                                      prev_diploma_graduation_date date,
                                      prev_diploma_notes text,
                                      prev_diploma_speciality varchar(255),
                                      graduation_document_type_id integer,
                                      credit_hours integer,
                                      ects_credits integer,
                                      owner_id integer,
                                      prof_group_id integer,
                                      diploma_series character varying(15),
                                      diploma_registration_number character varying(15),
                                      thesis_topic text,
                                      thesis_topic_en text,
                                      thesis_defence_date date,
                                      thesis_bibliography integer,
                                      thesis_volume integer,
                                      thesis_annotation text,
                                      thesis_annotation_en text,
                                      thesis_language_code varchar(2),
                                      original_qualification varchar(255),
                                      CONSTRAINT training_course_pk PRIMARY KEY (id),
                                      CONSTRAINT tce_ran_fk FOREIGN KEY (apn_id)
                                          REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT diploma_type_training_course_fk1 FOREIGN KEY (diploma_type_id)
                                          REFERENCES rudi.diploma_type (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT tce_scy_fk FOREIGN KEY (school_country)
                                          REFERENCES nomenclatures.country (code) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT tce_pdc_fk FOREIGN KEY (prev_diploma_country)
                                          REFERENCES nomenclatures.country (code) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT tc_prof_group_fk FOREIGN KEY (prof_group_id)
                                          REFERENCES nomenclatures.prof_group (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT tc_thesis_language_id_fk FOREIGN KEY (thesis_language_code)
                                          REFERENCES nomenclatures.language (code) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT training_course_graduation_document_type_fk FOREIGN KEY (graduation_document_type_id)
                                          REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT training_course_owner_id_fk FOREIGN KEY (owner_id)
                                          REFERENCES common.person (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT training_course_prev_diploma_university FOREIGN KEY (prev_diploma_university_id)
                                          REFERENCES rudi.university (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION,
                                      CONSTRAINT training_course_base_university FOREIGN KEY (base_university_id)
                                          REFERENCES rudi.university (id) MATCH SIMPLE
                                          ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.training_course
    ADD CONSTRAINT tce_dut_check
        CHECK (nomenclatures.exists_refdata('DURATION_UNIT', duration_unit));

CREATE INDEX tce_dut_idx
    ON rudi.training_course
        USING btree
        (duration_unit);
CREATE INDEX tce_apn_idx
    ON rudi.training_course
        USING btree
        (apn_id);

CREATE INDEX tce_pgp_idx
    ON rudi.training_course
        USING btree
        (prof_group_id);

CREATE INDEX tce_dte_idx
    ON rudi.training_course
        USING btree
        (diploma_type_id);
CREATE INDEX tce_gdt_idx
    ON rudi.training_course
        USING btree
        (graduation_document_type_id);

CREATE INDEX tce_owr_idx
    ON rudi.training_course
        USING btree
        (owner_id);

CREATE INDEX tce_tle_idx
    ON rudi.training_course
        USING btree
        (thesis_language_code);



CREATE TABLE rudi.application_recognition_purpose
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    rpe_code varchar(20) not null,
    notes character varying(255),
    CONSTRAINT arp_pk PRIMARY KEY (id),
    CONSTRAINT arp_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.application_recognition_purpose
    ADD CONSTRAINT arp_rpe_check
        CHECK (nomenclatures.exists_refdata('RECOGNITION_PURPOSE', rpe_code));

CREATE INDEX arp_apn_idx
    ON rudi.application_recognition_purpose
        USING btree
        (apn_id);

CREATE INDEX arp_rpe_idx
    ON rudi.application_recognition_purpose
        USING btree
        (rpe_code);
CREATE TABLE rudi.training_course_training_form
(
    id serial NOT NULL,
    tce_id integer NOT NULL,
    tfm_code varchar(20),
    notes character varying(255),
    CONSTRAINT training_form_pk PRIMARY KEY (id),
    CONSTRAINT tfm_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.training_course_training_form
    ADD CONSTRAINT tctf_tfm_check
        CHECK (nomenclatures.exists_refdata('TRAINING_FORM', tfm_code));

CREATE INDEX tctf_tce_idx
    ON rudi.training_course_training_form
        USING btree
        (tce_id);

CREATE INDEX tctf_tfm_idx
    ON rudi.training_course_training_form
        USING btree
        (tfm_code);



CREATE TABLE rudi.training_institution
(
    id serial NOT NULL,
    name character varying(100) NOT NULL,
    country_code varchar(4) NOT NULL,
    address_id int,
    active int not null,
    CONSTRAINT tin_pk PRIMARY KEY (id),
    CONSTRAINT tin_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tin_ads_fk FOREIGN KEY (address_id)
        REFERENCES common.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tin_name_coy_code_uk UNIQUE (name, country_code)
);

CREATE TABLE rudi.training_course_speciality
(
    id serial NOT NULL,
    tce_id integer NOT NULL,
    speciality varchar(255) NOT NULL,
    original_speciality varchar(255),
    CONSTRAINT tcs_pk PRIMARY KEY (id),
    CONSTRAINT tcs_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX tcs_tce_idx
    ON rudi.training_course_speciality
        USING btree
        (tce_id);


CREATE TABLE rudi.training_course_graduation_way
(
    id serial NOT NULL,
    tce_id integer NOT NULL,
    graduation_way_code varchar(20) not null,
    notes character varying(255),
    CONSTRAINT tcgw_pk PRIMARY KEY (id),
    CONSTRAINT tcgw_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.training_course_graduation_way
    ADD CONSTRAINT tcgw_gwy_check
        CHECK (nomenclatures.exists_refdata('GRADUATION_WAY', graduation_way_code));

CREATE INDEX tcgw_tce_idx
    ON rudi.training_course_graduation_way
        USING btree
        (tce_id);

CREATE INDEX tcgw_gwy_idx
    ON rudi.training_course_graduation_way
        USING btree
        (graduation_way_code);


CREATE TABLE rudi.training_location
(
    id serial NOT NULL,
    tce_id int not null,
    country_code varchar(4) not null,
    city character varying(30),
    training_institution_id integer,
    CONSTRAINT tln_pkey PRIMARY KEY (id),
    CONSTRAINT tln_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tln_tin_fk FOREIGN KEY (training_institution_id)
        REFERENCES rudi.training_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tln_coy_fok FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX tln_coy_idx
    ON rudi.training_location
        USING btree
        (country_code);


CREATE INDEX tln_tce_idx
    ON rudi.training_location
        USING btree
        (tce_id);

CREATE INDEX tln_tin_idx
    ON rudi.training_location
        USING btree
        (training_institution_id);




CREATE TABLE rudi.diploma_type_university
(
    id serial NOT NULL,
    dte_id integer NOT NULL,
    uny_id integer NOT NULL,
    ord_num integer,
    faculty_id integer,
    CONSTRAINT dtu_pk PRIMARY KEY (id),
    CONSTRAINT dtu_dte_fk FOREIGN KEY (dte_id)
        REFERENCES rudi.diploma_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dtu_uny_fk FOREIGN KEY (uny_id)
        REFERENCES rudi.university (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dtu_faculty_fk FOREIGN KEY (faculty_id)
        REFERENCES rudi.university_faculty (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX dtu_uny_idx
    ON rudi.diploma_type_university
        USING btree
        (uny_id);

CREATE INDEX dtu_dte_idx
    ON rudi.diploma_type_university
        USING btree
        (dte_id);

CREATE INDEX dtu_faculty_idx
    ON rudi.diploma_type_university
        USING btree
        (faculty_id);




CREATE TABLE rudi.application_recognized_speciality
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    speciality varchar(255) NOT NULL,
    CONSTRAINT ars_pk PRIMARY KEY (id),
    CONSTRAINT ars_apn_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX ars_tce_fk
    ON rudi.application_recognized_speciality
        USING btree
        (apn_id);
CREATE INDEX ars_spy_idx
    ON rudi.application_recognized_speciality
        USING btree
        (speciality);

create table rudi.application_recognition_details
(
    apn_id integer not null,
    recognized_edu_level varchar(20),
    recognized_qualification varchar(255),
    recognized_prof_group_id integer,
    CONSTRAINT ard_pk PRIMARY KEY (apn_id),
    CONSTRAINT ard_apn_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT ard_rpgp_fk FOREIGN KEY (recognized_prof_group_id)
        REFERENCES nomenclatures.prof_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.application_recognition_details
    ADD CONSTRAINT ard_rel_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', recognized_edu_level));
CREATE INDEX ard_rpgp_idx
    ON rudi.application_recognition_details
        USING btree
        (recognized_prof_group_id);



CREATE TABLE rudi.training_institution_university
(
    id serial NOT NULL,
    uny_id integer NOT NULL,
    tin_id integer NOT NULL,
    CONSTRAINT tiu_pk PRIMARY KEY (id),
    CONSTRAINT tiu_uny_fk FOREIGN KEY (uny_id)
        REFERENCES rudi.university (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tiy_tin_fk FOREIGN KEY (tin_id)
        REFERENCES rudi.training_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX tiu_tin_idx
    ON rudi.training_institution_university
        USING btree
        (tin_id);

CREATE INDEX tiu_uny_idx
    ON rudi.training_institution_university
        USING btree
        (uny_id);


CREATE TABLE common.attachments
(
    id serial NOT NULL,
    file_name character varying(255),
    content_type character varying(50),
    file_size integer,
    file_location character varying(100),
    bucket_name character varying(50) NOT NULL,
    content bytea,
    CONSTRAINT att_pk PRIMARY KEY (id)
);

CREATE TABLE common.attachment_deletes
(
    id serial NOT NULL,
    bucket_name character varying(50) NOT NULL,
    file_location character varying(255) NOT NULL,
    status integer NOT NULL,
    attachment_id integer NOT NULL,
    CONSTRAINT ade_pk PRIMARY KEY (id)
);

CREATE OR REPLACE FUNCTION common.delete_attachment()
    RETURNS trigger AS
$BODY$

BEGIN
    INSERT INTO common.attachment_deletes (attachment_id, bucket_name, file_location, status) VALUES (old.id, old.bucket_name, old.file_location, 0);
    return new;
END;

$BODY$
    LANGUAGE plpgsql VOLATILE
                     COST 100;


CREATE TRIGGER delete_attachment_trigger
    AFTER UPDATE OR DELETE
    ON common.attachments
    FOR EACH ROW
EXECUTE PROCEDURE common.delete_attachment();



CREATE TABLE common.application_attached_docs
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    description text,
    doc_type_id integer not null,
    copy_type_code varchar(20),
    docflow_id character varying(20),
    docflow_date date,
    attachment_id integer NOT NULL,
    scanned_attachment_id integer,
    CONSTRAINT aad_pk PRIMARY KEY (id),
    CONSTRAINT aad_apn_fk FOREIGN KEY (apn_id)
        REFERENCES common.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT add_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT attached_docs_document_type_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT add_scanned_att_id FOREIGN KEY (scanned_attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE common.application_attached_docs
    ADD CONSTRAINT aad_cte_check
        CHECK (nomenclatures.exists_refdata('COPY_TYPE', copy_type_code));

CREATE INDEX aad_dte_idx
    ON common.application_attached_docs
        USING btree
        (doc_type_id);


CREATE INDEX aad_apn_idx
    ON common.application_attached_docs
        USING btree
        (apn_id);


CREATE TABLE rudi.commission_member
(
    id serial NOT NULL,
    first_name        varchar(100),
    second_name       varchar(100),
    last_name         varchar(100),
    civil_id          varchar(50),
    civil_id_type     varchar(4),
    degree character varying(30),
    institution character varying(255),
    division character varying(255),
    title character varying(255),
    commission_position varchar(20),
    prof_group_id integer,
    iban character varying(30),
    bic character varying(10),
    active int not null,
    address_id int not null,
    CONSTRAINT cmr_pk PRIMARY KEY (id),
    CONSTRAINT cmr_pgp_fk FOREIGN KEY (prof_group_id)
        REFERENCES nomenclatures.prof_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT cmr_ads_fk FOREIGN KEY (address_id)
        REFERENCES common.address (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT pen_cit_fk FOREIGN KEY (civil_id_type)
        REFERENCES nomenclatures.civil_id_type (code)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION

);
alter table rudi.commission_member add CONSTRAINT pen_cit_check check (civil_id_type in ('EGN', 'LNC'));

ALTER TABLE rudi.commission_member
    ADD CONSTRAINT cmr_cpn_check
        CHECK (nomenclatures.exists_refdata('COMMISSION_POSITION', commission_position));


CREATE TABLE rudi.application_commission_members
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    commission_member_id integer NOT NULL,
    notes text,
    course_content text,
    edu_level varchar(20),
    qualification varchar(255),
    previous_board_decisions text,
    similar_bulgarian_programs text,
    member_position_code varchar(4),
    legal_reason_id int,
    process_status integer NOT NULL DEFAULT 0,
    CONSTRAINT acm_pk PRIMARY KEY (id),
    CONSTRAINT acm_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acm_cmr_fk FOREIGN KEY (commission_member_id)
        REFERENCES rudi.commission_member (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acm_lrn_fk FOREIGN KEY (legal_reason_id)
        REFERENCES nomenclatures.legal_reason (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT application_expert_position_fkey FOREIGN KEY (member_position_code)
        REFERENCES nomenclatures.commission_member_position (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.application_commission_members
    ADD CONSTRAINT acm_ell_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', edu_level));

CREATE INDEX acm_apn_idx
    ON rudi.application_commission_members
        USING btree
        (apn_id);

CREATE INDEX acm_cmr_idx
    ON rudi.application_commission_members
        USING btree
        (commission_member_id);


CREATE TABLE rudi.application_commission_member_statements
(
    id serial NOT NULL,
    description text,
    doc_type_id integer NOT NULL,
    apn_id integer NOT NULL,
    commission_member_id integer NOT NULL,
    attachment_id integer,
    CONSTRAINT acms_pk PRIMARY KEY (id),
    CONSTRAINT acms_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acms_cmr_fk FOREIGN KEY (commission_member_id)
        REFERENCES rudi.commission_member (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acms_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acms_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX acms_apn_idx
    ON rudi.application_commission_member_statements
        USING btree
        (apn_id);

CREATE INDEX acms_cmr_idx
    ON rudi.application_commission_member_statements
        USING btree
        (commission_member_id);


CREATE TABLE rudi.application_commission_member_specialities
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    commission_member_id integer NOT NULL,
    speciality varchar(255) NOT NULL,
    CONSTRAINT acmsy_pk PRIMARY KEY (id),
    CONSTRAINT acmsy_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acmsy_cmr_fk FOREIGN KEY (commission_member_id)
        REFERENCES rudi.commission_member (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT acmsy_uk UNIQUE (apn_id, commission_member_id, speciality)
);

CREATE INDEX acmsy_cmr_idx
    ON rudi.application_commission_member_specialities
        USING btree
        (commission_member_id);

CREATE INDEX acmsy_apn_idx
    ON rudi.application_commission_member_specialities
        USING btree
        (apn_id);


CREATE TABLE rudi.commission_calendar
(
    id serial NOT NULL,
    session_num integer NOT NULL,
    session_time timestamp with time zone NOT NULL,
    notes text,
    session_status_code varchar(20) NOT NULL,
    commission_protocol_id integer,
    user_created varchar(100) not null,
    date_created timestamp with time zone not null,
    CONSTRAINT ccr_pk PRIMARY KEY (id),
    CONSTRAINT ccr_dot_fk FOREIGN KEY (commission_protocol_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.commission_calendar
    ADD CONSTRAINT ccr_session_status_check
        CHECK (nomenclatures.exists_refdata('COMMISSION_SESSION_STATUS', session_status_code));


CREATE TABLE rudi.commission_applications
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    calendar_id integer NOT NULL,
    motives text,
    applicant_info text,
    CONSTRAINT can_pk PRIMARY KEY (id),
    CONSTRAINT can_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT can_ccr_fk FOREIGN KEY (calendar_id)
        REFERENCES rudi.commission_calendar (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX can_apn_idx
    ON rudi.commission_applications
        USING btree
        (apn_id);

CREATE INDEX can_ccr_idx
    ON rudi.commission_applications
        USING btree
        (calendar_id);

CREATE TABLE rudi.commission_participation
(
    id serial NOT NULL,
    commission_member_id integer NOT NULL,
    calendar_id integer NOT NULL,
    notified integer,
    participated integer,
    CONSTRAINT cpn_pk PRIMARY KEY (id),
    CONSTRAINT cpn_ccr_fk FOREIGN KEY (calendar_id)
        REFERENCES rudi.commission_calendar (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT cpn_cmr_fk FOREIGN KEY (commission_member_id)
        REFERENCES rudi.commission_member (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX cpn_cmr_idx
    ON rudi.commission_participation
        USING btree
        (commission_member_id);

CREATE INDEX cpn_ccr_idx
    ON rudi.commission_participation
        USING btree
        (calendar_id);



CREATE TABLE rudi.diploma_type_attached_docs
(
    id serial NOT NULL,
    description text,
    doc_type_id integer NOT NULL,
    dte_id integer NOT NULL,
    attachment_id integer NOT NULL,
    CONSTRAINT dtad_pk PRIMARY KEY (id),
    CONSTRAINT dtad_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dtad_dte_fk FOREIGN KEY (dte_id)
        REFERENCES rudi.diploma_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX dtad_dte_idx
    ON rudi.diploma_type_attached_docs
        USING btree
        (dte_id);

CREATE TABLE rudi.competent_institution
(
  id serial NOT NULL,
  coy_code varchar(4) NOT NULL,
  name character varying(255) NOT NULL,
  original_name character varying(255),
  url character varying(255),
  notes text,
  active int not null,
  address_id int not null
      constraint cin_ads_fk
          references common.address,
  CONSTRAINT cin_pk PRIMARY KEY (id)
);



CREATE TABLE rudi.university_examination
(
    id serial NOT NULL,
    uny_id integer NOT NULL,
    user_created varchar(100) NOT NULL,
    examination_date date NOT NULL,
    communicated_flag integer not null,
    recognized_flag integer not null,
    notes text,
    training_location_code varchar(20),
    joint_degree_flag integer not null,
    CONSTRAINT uen_pk PRIMARY KEY (id),
    CONSTRAINT uen_uny_fk FOREIGN KEY (uny_id)
        REFERENCES rudi.university (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.university_examination
    ADD CONSTRAINT uen_tln_check
        CHECK (nomenclatures.exists_refdata('UNI_EXAM_TRAINING_LOCATION', training_location_code));

CREATE INDEX uen_uny_idx
    ON rudi.university_examination
        USING btree
        (uny_id);


CREATE TABLE rudi.university_examination_training_forms
(
  id serial NOT NULL,
  university_examination_id integer NOT NULL,
  training_form_code varchar(20) not null,
  notes character varying(255),
  CONSTRAINT uetf_pk PRIMARY KEY (id),
  CONSTRAINT uetf_uen_fk FOREIGN KEY (university_examination_id)
      REFERENCES rudi.university_examination (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.university_examination_training_forms
    ADD CONSTRAINT uetf_tfm_check
        CHECK (nomenclatures.exists_refdata('TRAINING_FORM', training_form_code));



CREATE TABLE rudi.university_examination_competent_institutions
(
    id serial NOT NULL,
    competent_institution_id integer NOT NULL,
    university_examination_id integer NOT NULL,
    CONSTRAINT ueci_pk PRIMARY KEY (id),
    CONSTRAINT ueci_cin_fk FOREIGN KEY (competent_institution_id)
    REFERENCES rudi.competent_institution (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT ueci_uen_fk FOREIGN KEY (university_examination_id)
    REFERENCES rudi.university_examination (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX ueci_cin_idx
    ON rudi.university_examination_competent_institutions
        USING btree
        (competent_institution_id);
CREATE INDEX ueci_uen_idx
    ON rudi.university_examination_competent_institutions
        USING btree
        (university_examination_id);
		


CREATE TABLE rudi.university_examination_attached_docs
(
    id serial NOT NULL,
    university_examination_id integer NOT NULL,
    description text,
    doc_type_id integer not null,
    copy_type_code varchar(20),
    docflow_id character varying(20),
    docflow_date date,
    attachment_id integer NOT NULL,
    scanned_attachment_id integer,
    CONSTRAINT uead_pk PRIMARY KEY (id),
    CONSTRAINT uead_uen_fk FOREIGN KEY (university_examination_id)
        REFERENCES rudi.university_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT uead_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT uead_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT uead_scanned_att_id FOREIGN KEY (scanned_attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.university_examination_attached_docs
    ADD CONSTRAINT uead_cte_check
        CHECK (nomenclatures.exists_refdata('COPY_TYPE', copy_type_code));

CREATE INDEX uead_dte_idx
    ON rudi.university_examination_attached_docs
        USING btree
        (doc_type_id);


CREATE INDEX uead_uen_idx
    ON rudi.university_examination_attached_docs
        USING btree
        (university_examination_id);
		
		
CREATE TABLE rudi.training_course_university_examination
(
  id serial NOT NULL,
  tce_id integer NOT NULL,
  university_examination_id integer NOT NULL,
  notes text,
  CONSTRAINT tcue_pk PRIMARY KEY (id),
  CONSTRAINT tcue_tce_fk FOREIGN KEY (tce_id)
      REFERENCES rudi.training_course (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tcue_uen_fk FOREIGN KEY (university_examination_id)
      REFERENCES rudi.university_examination (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--TODO:Ako daden university_examination е вързан към повече от 1 заявление, то той да не може да се редактира!!!!
CREATE INDEX tcue_tce_idx
  ON rudi.training_course_university_examination
  USING btree
  (tce_id);

CREATE INDEX tcue_uen_idx
  ON rudi.training_course_university_examination
  USING btree
  (university_examination_id);



CREATE TABLE rudi.training_course_diploma_examination
(
    id serial NOT NULL,
    tce_id integer NOT NULL,
    examination_date date NOT NULL,
    notes text,
    authentic_flag integer,
    competent_institution_id integer,
    institution_communicated_flag integer NOT NULL,
    university_communicated_flag integer NOT NULL,
    found_in_register_flag integer NOT NULL DEFAULT 0,
    CONSTRAINT tcde_pk PRIMARY KEY (id),
    CONSTRAINT tcde_cin_fk FOREIGN KEY (competent_institution_id)
        REFERENCES rudi.competent_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcde_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX tcde_tce_idx
    ON rudi.training_course_diploma_examination
        USING btree
        (tce_id);
		
		
		


CREATE TABLE rudi.training_course_diploma_examination_attached_docs
(
    id serial NOT NULL,
    description text,
    doc_type_id integer NOT NULL,
    training_course_diploma_examination_id integer NOT NULL,
    docflow_id character varying(20),
    docflow_date date,
    copy_type_code varchar(20),
    attachment_id integer NOT NULL,
    scanned_attachment_id integer,
    CONSTRAINT daad_pk PRIMARY KEY (id),
    CONSTRAINT daad_tcde_fk FOREIGN KEY (training_course_diploma_examination_id)
        REFERENCES rudi.training_course_diploma_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dead_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dead_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT dead_scanned_att_id FOREIGN KEY (scanned_attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX dead_tcde_idx
    ON rudi.training_course_diploma_examination_attached_docs
        USING btree
        (training_course_diploma_examination_id);



create table common.application_certificates
(
    id serial not null,
    apn_id int not null,
    certificate_number varchar(30) not null,
    uuid uuid not null,
    certificate_status varchar(20) not null,
    application_attached_doc_id integer,
    CONSTRAINT ace_pk PRIMARY KEY (id),
    CONSTRAINT ace_apn_fk FOREIGN KEY (apn_id)
        REFERENCES common.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT ace_aad_fk FOREIGN KEY (application_attached_doc_id)
        REFERENCES common.application_attached_docs (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE common.application_certificates
    ADD CONSTRAINT ace_css_check
        CHECK (nomenclatures.exists_refdata('CERTIFICATE_STATUS', certificate_status));

create table rudi.sar_application
(
    apn_id int not null,
    statute_flag int not null,
    authenticity_flag int not null,
    recommendation_flag int not null,
    CONSTRAINT sar_apn_pk PRIMARY KEY (apn_id),
    CONSTRAINT sar_apn_ran_fk FOREIGN KEY (apn_id)
        REFERENCES rudi.rudi_application (apn_id) MATCH SIMPLE

);
create unique index sar_ran_uk on rudi.sar_application (apn_id);

create table if not exists common.content_management
(
    id               varchar(100) not null
    constraint content_management_pk
    primary key,
    type             varchar(255) not null,
    data             text         not null,
    data_template    varchar(255) not null,
    content_order    integer      not null,
    alias            varchar(255),
    date_created     timestamp with time zone default now(),
    date_last_update timestamp with time zone,
                                   user_last_update varchar(255),
    active           boolean                  default false
    );

alter table common.content_management
    owner to postgres;





create table regprof.regprof_training_experience (
    id serial not null
        constraint rte_pk
            primary key,
    apn_id int not null,
    certificate_prof_qualification varchar(255),
    not_restricted_flag integer not null,
    regulated_education_training_flag integer not null,
    CONSTRAINT rte_apn_ran_fk FOREIGN KEY (apn_id)
        REFERENCES regprof.regprof_application (apn_id) MATCH SIMPLE
);
CREATE INDEX rte_apn_idx
    ON regprof.regprof_training_experience
        USING btree
        (apn_id);

CREATE TABLE regprof.professional_institution
(
    id serial NOT NULL,
    country_code varchar(4) not null,
    name character varying(255) not null,
    web_site character varying(255),
    active int not null,
    url_diploma_register text,
    address_id int not null
        constraint pin_ads_fk
            references common.address,
    CONSTRAINT pin_pk PRIMARY KEY (id),
    CONSTRAINT pin_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
create table regprof.professional_institution_education_types
(
    professional_institution_id integer not null
        constraint piet_pin_fk
            references regprof.professional_institution,
    education_type varchar(20) not null,
    CONSTRAINT piet_pk PRIMARY KEY (professional_institution_id, education_type)
);
ALTER TABLE regprof.professional_institution_education_types
    ADD CONSTRAINT piet_education_type_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_TYPE', education_type));

CREATE TABLE regprof.professional_institution_former_names
(
    id serial NOT NULL,
    professional_institution_id integer NOT NULL,
    former_name character varying(255) NOT NULL,
    CONSTRAINT pifn_pk PRIMARY KEY (id),
    CONSTRAINT pifn_pin_fk FOREIGN KEY (professional_institution_id)
        REFERENCES regprof.professional_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT pifn_uk UNIQUE (professional_institution_id, former_name)
);
CREATE INDEX pifn_pin_idx
    ON regprof.professional_institution_former_names
        USING btree
        (professional_institution_id);
create table regprof.training_course (
    rte_id int not null
        constraint tce_rte_fk
            references regprof.regprof_training_experience
        constraint tce_pk
            primary key,
    education_type varchar(20)

);
ALTER TABLE regprof.training_course
    ADD CONSTRAINT tce_education_type_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_TYPE', education_type));

/***
  TODO:vsichki xxx_training_course imat dosta ednakvi poleta kato professional_institution_id, professional_institution_former_name_id, graduation_document_type_id, document_number, document_date, document_series, document_reg_number
  TODO:Dali moje da se izmisli nqkakva base tablica????
  TODO:Realno secondary ima 2 razlichni poleta -> qualification_rank + professional_qualification_id, higher -> professional_qualification + edu_level, postgraduate -> professional_qualification
  TODO:Dokato pri SDK, trqbva da sa popylneni i higher i postgraduate!
 */
create table regprof.secondary_training_course (
    rte_id integer not null
        constraint stc_pk primary key
        constraint stc_tce_fk
            references regprof.training_course,
    professional_institution_id integer
        constraint stc_pin_fk
            references regprof.professional_institution,
    professional_institution_former_name_id integer
        constraint stc_pifn_fk
            references regprof.professional_institution_former_names,
    graduation_document_type_id integer
        constraint stc_gdt_fk
            references nomenclatures.graduation_document_type,
    document_number character varying(50),
    document_date date,
    document_series character varying(32),
    document_reg_number character varying(32),
    qualification_rank varchar(20),
    professional_qualification_id int
        constraint stc_pqn_fk
            references nomenclatures.secondary_professional_qualification
);
ALTER TABLE regprof.secondary_training_course
    ADD CONSTRAINT stc_qualification_rank_check
        CHECK (nomenclatures.exists_refdata('QUALIFICATION_RANK', qualification_rank));

create table regprof.higher_training_course (
       rte_id integer not null
           constraint htc_pk primary key
           constraint htc_tce_fk
               references regprof.training_course,
       professional_institution_id integer
           constraint htc_pin_fk
               references regprof.professional_institution,
       professional_institution_former_name_id integer
           constraint htc_pifn_fk
               references regprof.professional_institution_former_names,
       graduation_document_type_id integer
           constraint htc_gdt_fk
               references nomenclatures.graduation_document_type,
       document_number character varying(50),
       document_date date,
       document_series character varying(32),
       document_reg_number character varying(32),
       professional_qualification varchar(255),
       edu_level varchar(20)
);
ALTER TABLE regprof.higher_training_course
    ADD CONSTRAINT htc_ell_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', edu_level));

create table regprof.postgraduate_training_course (
    rte_id integer not null
        constraint pgtc_pk primary key
        constraint pgtc_tce_fk
            references regprof.training_course,
    professional_institution_id integer
        constraint pgtc_pin_fk
            references regprof.professional_institution,
    professional_institution_former_name_id integer
        constraint pgtc_pifn_fk
            references regprof.professional_institution_former_names,
    graduation_document_type_id integer
        constraint pgtc_gdt_fk
            references nomenclatures.graduation_document_type,
    document_number character varying(50),
    document_date date,
    document_series character varying(32),
    document_reg_number character varying(32),
    professional_qualification varchar(255)
);

CREATE TABLE regprof.profession_experience
(
    rte_id integer NOT NULL,
    profession_name varchar(255),
    years integer,
    months integer,
    days integer,
    CONSTRAINT pee_pk PRIMARY KEY (rte_id),
    CONSTRAINT pee_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.regprof_training_experience (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE regprof.profession_experience_documents
(
    id serial NOT NULL,
    rte_id integer NOT NULL,
    document_number character varying(100),
    document_issuer character varying(100) NOT NULL,
    document_date date,
    profession_experience_document_type_code varchar(4) not null,
    CONSTRAINT ped_pk PRIMARY KEY (id),
    CONSTRAINT ped_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.profession_experience(rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT ped_pedt_fk FOREIGN KEY (profession_experience_document_type_code)
        REFERENCES nomenclatures.profession_experience_document_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX ped_rte_idx
    ON regprof.profession_experience_documents
        USING btree
        (rte_id);

CREATE TABLE regprof.profession_experience_document_dates
(
    id serial NOT NULL,
    date_from date NOT NULL,
    date_to date NOT NULL,
    workday_duration varchar(20),
    ped_id integer,
    CONSTRAINT pedd_pk PRIMARY KEY (id),
    CONSTRAINT pedd_ped_fk FOREIGN KEY (ped_id)
        REFERENCES regprof.profession_experience_documents (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE regprof.profession_experience_document_dates
    ADD CONSTRAINT pedd_workday_duration_check
        CHECK (nomenclatures.exists_refdata('WORKDAY_DURATION', workday_duration));

CREATE INDEX pedd_ped_idx
    ON regprof.profession_experience_document_dates
        USING btree
        (ped_id);

CREATE TABLE regprof.professional_institution_examination
(
    id serial NOT NULL,
    professional_institution_id integer NOT NULL,
    examination_date date NOT NULL,
    secondary_professional_qualification_id integer,
    higher_professional_qualification varchar(255),
    rights_educate_flag integer NOT NULL,
    legitimate_flag integer NOT NULL,
    user_created varchar(100) NOT NULL,
    date_created timestamp with time zone NOT NULL,
    notes text,
    CONSTRAINT institution_validity_pkey PRIMARY KEY (id),
    CONSTRAINT institution_validity_professional_institution_id_fkey FOREIGN KEY (professional_institution_id)
        REFERENCES regprof.professional_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT institution_validity_qual_bg_sec_id_fk FOREIGN KEY (secondary_professional_qualification_id)
        REFERENCES nomenclatures.secondary_professional_qualification (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX pie_pin_secondary_qualification_idx
    ON regprof.professional_institution_examination
        USING btree
        (professional_institution_id, secondary_professional_qualification_id);
CREATE INDEX pie_pin_higher_qualification_idx
    ON regprof.professional_institution_examination
        USING btree
        (professional_institution_id, higher_professional_qualification);

CREATE TABLE regprof.training_course_professional_institution_examination
(
    professional_institution_examination_id integer NOT NULL,
    ret_id integer NOT NULL,
    notes text,
    CONSTRAINT tcpie_pk PRIMARY KEY (professional_institution_examination_id, ret_id),
    CONSTRAINT tcpie_pie_fk FOREIGN KEY (professional_institution_examination_id)
        REFERENCES regprof.professional_institution_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcpie_tce_fk FOREIGN KEY (ret_id)
        REFERENCES regprof.training_course (rte_id)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE regprof.training_course_document_examination
(
    rte_id integer,
    document_examination_date date NOT NULL,
    training_document_examination_source varchar(20),
    CONSTRAINT tcde_pk PRIMARY KEY (rte_id),
    CONSTRAINT tcde_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE regprof.training_course_document_examination
    ADD CONSTRAINT tcde_training_document_examination_source_check
        CHECK (nomenclatures.exists_refdata('TRAINING_DOCUMENT_EXAMINATION_SOURCE', training_document_examination_source));

CREATE TABLE regprof.training_course_document_examination_attached_docs
(
    id serial NOT NULL,
    rte_id integer not null,
    description text,
    doc_type_id integer not null,
    copy_type_code varchar(20),
    docflow_id character varying(20),
    docflow_date date,
    attachment_id integer NOT NULL,
    scanned_attachment_id integer,
    CONSTRAINT tcdead_pk PRIMARY KEY (id),
    CONSTRAINT tcdead_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.training_course_document_examination (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcdead_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcdead_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcdead_scanned_att_id FOREIGN KEY (scanned_attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE regprof.training_course_document_examination_attached_docs
    ADD CONSTRAINT tcdead_cte_check
        CHECK (nomenclatures.exists_refdata('COPY_TYPE', copy_type_code));

CREATE INDEX tcdead_dte_idx
    ON regprof.training_course_document_examination_attached_docs
        USING btree
        (doc_type_id);


CREATE INDEX tcdead_rte_idx
    ON regprof.training_course_document_examination_attached_docs
        USING btree
        (rte_id);

CREATE TABLE regprof.profession_experience_examination
(
    rte_id integer NOT NULL,
    experience_document_recognized_flag integer,
    not_restricted_flag integer NOT NULL,
    article_item_id integer,
    CONSTRAINT peen_pk PRIMARY KEY (rte_id),
    CONSTRAINT peen_pen_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.profession_experience (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT peen_aim_fk FOREIGN KEY (article_item_id)
        REFERENCES nomenclatures.regprof_article_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE regprof.profession_experience_examination_attached_docs
(
    id serial NOT NULL,
    rte_id integer not null,
    description text,
    doc_type_id integer not null,
    copy_type_code varchar(20),
    docflow_id character varying(20),
    docflow_date date,
    attachment_id integer NOT NULL,
    scanned_attachment_id integer,
    CONSTRAINT peead_pk PRIMARY KEY (id),
    CONSTRAINT peead_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.profession_experience_examination (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT peead_att_fk FOREIGN KEY (attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT peead_dte_fk FOREIGN KEY (doc_type_id)
        REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT peead_scanned_att_id FOREIGN KEY (scanned_attachment_id)
        REFERENCES common.attachments (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE regprof.profession_experience_examination_attached_docs
    ADD CONSTRAINT peead_cte_check
        CHECK (nomenclatures.exists_refdata('COPY_TYPE', copy_type_code));

CREATE INDEX peead_dte_idx
    ON regprof.profession_experience_examination_attached_docs
        USING btree
        (doc_type_id);


CREATE INDEX peead_rte_idx
    ON regprof.profession_experience_examination_attached_docs
        USING btree
        (rte_id);

CREATE TABLE regprof.regulated_examination
(
    id serial NOT NULL,
    country_code varchar(4) not null,
    examination_date date not null,
    notes text,
    date_created time with time zone NOT NULL DEFAULT now(),
    user_created varchar(100) not null,
    profession character varying(150),
    regulated_flag integer,
    CONSTRAINT ren_pk PRIMARY KEY (id),
    CONSTRAINT ren_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE regprof.application_regulated_examination
(
    ren_id integer NOT NULL,
    apn_id integer NOT NULL,
    notes text,
    CONSTRAINT are_pk PRIMARY KEY (ren_id, apn_id),
    CONSTRAINT are_ren_fk FOREIGN KEY (ren_id)
        REFERENCES regprof.regulated_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT are_apn_fk FOREIGN KEY (apn_id)
        REFERENCES regprof.regprof_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE regprof.training_course_specialities
(
    id serial NOT NULL,
    rte_id integer NOT NULL,
    secondary_speciality_id integer,
    higher_speciality varchar(255),
    sdk_speciality varchar(255),
    CONSTRAINT tcs_pk PRIMARY KEY (id),
    CONSTRAINT tcs_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.regprof_training_experience (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcs_ssy_fk FOREIGN KEY (secondary_speciality_id)
        REFERENCES nomenclatures.secondary_speciality (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX tcs_rte_idx
    ON regprof.training_course_specialities
        USING btree
        (rte_id);


CREATE TABLE regprof.training_course_qualification_examination
(
    id serial NOT NULL,
    rte_id integer,
    recognized_edu_level varchar(20),
    recognized_qualification_degree varchar(20),
    recognized_profession varchar(255),
    article_item_id integer,
    recognized_qualification_teacher_flag integer NOT NULL DEFAULT 0,
    school_grade varchar(20),
    school_type varchar(20),
    school_age_range varchar(20),
    CONSTRAINT tcqe_pk PRIMARY KEY (id),
    CONSTRAINT tcqe_rte_fk FOREIGN KEY (rte_id)
        REFERENCES regprof.training_course (rte_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcqe_aim_fk FOREIGN KEY (article_item_id)
        REFERENCES nomenclatures.regprof_article_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_recognized_edu_level_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', recognized_edu_level));
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_recognized_qualification_degree_check
        CHECK (nomenclatures.exists_refdata('QUALIFICATION_DEGREE', recognized_qualification_degree));
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_school_grade_check
        CHECK (nomenclatures.exists_refdata('SCHOOL_GRADE', school_grade));
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_school_type_check
        CHECK (nomenclatures.exists_refdata('SCHOOL_TYPE', school_type));
ALTER TABLE regprof.training_course_qualification_examination
    ADD CONSTRAINT tcqe_school_age_range_check
        CHECK (nomenclatures.exists_refdata('SCHOOL_AGE_RANGE', school_age_range));

CREATE INDEX tcqe_rte_idx
    ON regprof.training_course_qualification_examination
        USING btree
        (rte_id);
CREATE TABLE common.application_properties (
      code character varying(50) NOT NULL,
      value text,
      description text,
      CONSTRAINT apy_pk PRIMARY KEY (code)
);

CREATE TABLE common.change_record_log
(
    id serial NOT NULL,
    record_id varchar(255),
    application_name varchar(255) not null,
    service character varying(255) NOT NULL,
    operation character varying(255) NOT NULL,
    user_changed character varying(255) NOT NULL,
    date_changed timestamp with time zone NOT NULL,

    before text,
    after text,
    object_class character varying(255),
    CONSTRAINT change_record_log_pkey PRIMARY KEY (id)
);


create or replace view rudi.vw_applications_list as
select apn.id, apn.ate_code, apn.ase_code, entry_num, entry_date,
       case when apt.legal_type = 'LE' then apt.legal_name else apt.first_name || coalesce(' '||apt.second_name,'') ||coalesce(' '||apt.last_name, '') end as applicant_name,
       uny.bg_name university_name, ucy.name as university_country_name,
       (select array_to_string(array(select tcs.speciality from rudi.training_course_speciality tcs where tcs.tce_id = tce.id order by tcs.id) , ', ')) as speciality_name,
       ass.name as apn_status_name, ass.code apn_status_code, apn.docflow_status_code, dss.name as docflow_status_name,
       (select array_to_string(array(select ccr.session_num::text from rudi.commission_applications can join rudi.commission_calendar ccr on ccr.id = can.calendar_id where can.apn_id = ran.apn_id), ', ')) commission_sessions,
       (select count(*)::integer from rudi.application_commission_members acm where acm.apn_id = ran.apn_id) experts_count,
       coalesce((select 0 from rudi.application_commission_members acm where acm.apn_id = ran.apn_id and process_status = 0 limit 1), 1) experts_processed_status,
       rpgp.name as recognized_prof_group_name, ard.recognized_qualification as recognized_qualification, ell.name as edu_level_name, ell.code as edu_level_code,
       (select array_to_string(array_remove(array[(case when statute_flag = 1 then (select name from nomenclatures.reference_data where domain = 'SAR_APPLICATION_TYPE' and code = 'S') end), (case when authenticity_flag = 1 then (select name from nomenclatures.reference_data where domain = 'SAR_APPLICATION_TYPE' and code = 'A') end), (case when recommendation_flag = 1 then (select name from nomenclatures.reference_data where domain = 'SAR_APPLICATION_TYPE' and code = 'R') end)], null), ', ')) as sar_flag
from common.application apn
         join rudi.rudi_application ran on ran.apn_id = apn.id
         left join rudi.training_course tce on (tce.apn_id = ran.apn_id)
         left join common.person apt on (apt.id = apn.applicant_id)
         left join rudi.diploma_type dte on dte.id = tce.diploma_type_id
         left join rudi.diploma_type_university dtu on dtu.dte_id = dte.id and dtu.ord_num = 1
         left join rudi.university uny on (uny.id = dtu.uny_id)
         left join nomenclatures.country ucy on (ucy.code = uny.country_code)
         left join nomenclatures.reference_data ass on (ass.code = apn.status_code and ass.domain = 'APPLICATION_STATUS')
         left join nomenclatures.reference_data dss on (dss.code = apn.docflow_status_code and dss.domain = 'DOCFLOW_STATUS')
    --left join eservices.rudi_application eapn on (eapn.application_id = apn.id)
    --left join eservices.rudi_signed_docs esd on (esd.ext_app_id = eapn.id)
         left join rudi.application_recognition_details ard on ard.apn_id = apn.id
        left join nomenclatures.prof_group rpgp on rpgp.id = ard.recognized_prof_group_id
         left join nomenclatures.reference_data ell on ell.code = dte.edu_level and ell.domain = 'EDUCATION_LEVEL'
         left join rudi.sar_application sar_apn on sar_apn.apn_id = ran.apn_id;


create or replace function get_year_month_days_label(p_years int , p_months int, p_days int)
    RETURNS character varying AS
$BODY$
DECLARE
    result VARCHAR[];
BEGIN
    if (p_years is not null) then result = array_append(result, p_years ||' '||(case when p_years = 1 then 'година' else 'години'end)); end if;
    if (p_months is not null) then result = array_append(result,p_months ||' '||(case when p_months = 1 then 'месец' else 'месеца'end));end if;
    if (p_days is not null) then result = array_append(result, p_days ||' '||(case when p_days = 1 then 'ден' else 'дни'end));end if;
    RETURN array_to_string(result, ', ');
END;
$BODY$
    LANGUAGE plpgsql IMMUTABLE;


create view nomenclatures.vw_speciality as
select a.speciality from
    (select speciality from rudi.training_course_speciality
     union
     select speciality from rudi.application_recognized_speciality
     union
     select speciality from rudi.application_commission_member_specialities
     union
     select prev_diploma_speciality from rudi.training_course) as a
where a.speciality is not null;


create view nomenclatures.vw_qualification as
select a.qualification from
    (select qualification from rudi.training_course
     union
     select recognized_qualification from rudi.application_recognition_details
     union
     select qualification from rudi.application_commission_members
    ) as a
where a.qualification is not null;


create view nomenclatures.vw_original_qualification(original_qualification) as
SELECT DISTINCT a.original_qualification
FROM rudi.training_course a
WHERE a.original_qualification IS NOT NULL;

create view nomenclatures.vw_original_speciality(original_speciality) as
SELECT DISTINCT a.original_speciality
FROM rudi.training_course_speciality a
WHERE a.original_speciality IS NOT NULL;
