create table nomenclatures.reference_data_domain(
    domain varchar(50) not null
        constraint rdd_pk primary key,
    name varchar(255) not null,
    fo_replication_flag int not null
);

create table nomenclatures.reference_data (
                                              domain varchar(50) not null
                                                  constraint rda_don_fk
                                                      references nomenclatures.reference_data_domain,
                                              code varchar(20) not null,
                                              name varchar(255) not null,
                                              index integer not null,
                                              active integer not null,
                                              CONSTRAINT ref_data_pkey PRIMARY KEY (domain, code)
);
CREATE OR REPLACE FUNCTION nomenclatures.exists_refdata(
    p_domain varchar(50),
    p_code varchar(20))
    RETURNS boolean AS
$BODY$
declare v_res boolean ;
begin
    SELECT case when p_code is null then true else EXISTS (SELECT 1 FROM nomenclatures.reference_data rd WHERE rd.domain = p_domain AND rd.code = p_code) end into v_res;
    return v_res;
end;
$BODY$
    LANGUAGE plpgsql VOLATILE
                     COST 100;

create table nomenclatures.application_type
(
    code   varchar(4)   not null
        constraint ate_pk
            primary key,
    name   varchar(255) not null,
    active integer      not null
);


create table nomenclatures.application_subtype
(
    code   varchar(4)   not null
        constraint ast_pk
            primary key,
    ate_code varchar(4)   not null
        constraint ast_ate_fk
            references nomenclatures.application_type,
    name   varchar(255) not null,
    active integer      not null
);

create table nomenclatures.civil_id_type
(
    code       varchar(4)        not null
        constraint pk_legal_type
            primary key,
    legal_type varchar(4)        not null,
    name       varchar(200),
    active     integer default 1 not null
);
ALTER TABLE nomenclatures.civil_id_type
    ADD CONSTRAINT cit_lte_check
        CHECK (nomenclatures.exists_refdata('LEGAL_TYPE', legal_type));

create unique index cit_uk on nomenclatures.civil_id_type (code, legal_type);

create table nomenclatures.country
(
    code          varchar(4)   not null
        constraint country_pk
            primary key,
    name          varchar(255) not null,
    official_name varchar(255) not null,
    active        integer      not null
);

create table nomenclatures.document_receive_method
(
    code varchar(4)
        constraint document_receive_method_pk
            primary key,
    name                              varchar(255) not null,
    document_recipient_flag           integer      not null,
    active                            integer      not null,
    eservices_require_payment_receipt_flag integer      not null
);

create table nomenclatures.ek_district
(
    code                  varchar(10)       not null
        constraint ek_dit_pk
        primary key,
    code2                 varchar(10),
    secondlevelregioncode varchar(10),
    name                  varchar(255),
    mainsettlementcode    varchar(10),
    alias                 varchar(200),
    description           varchar(500),
    isactive              integer,
    version               integer default 0 not null,
    nameen                varchar(200)
);

create table nomenclatures.ek_municipality
(
    code               varchar(10)       not null
        constraint ek_muy_pk
            primary key,
    districtcode      varchar(10)           not null,

    code2              varchar(10),
    mainsettlementcode varchar(10),
    category           varchar(50),
    name               varchar(255),
    alias              varchar(200),
    description        varchar(500),
    isactive           integer,
    version            integer default 0 not null,
    nameen             varchar(200),
    CONSTRAINT muy_dit_fk FOREIGN KEY (districtcode)
        REFERENCES nomenclatures.ek_district (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);


create table nomenclatures.ek_settlement
(
    code              varchar(10)       not null
        constraint ek_set_pk
            primary key,
    municipalitycode  varchar(10),
    districtcode      varchar(10),
    municipalitycode2 varchar(10),
    districtcode2     varchar(10),
    name              varchar(255),
    typename          varchar(20),
    settlementname    varchar(255),
    typecode          varchar(50),
    mayoraltycode     varchar(50),
    category          varchar(50),
    altitude          varchar(50),
    alias             varchar(200),
    description       varchar(500),
    isdistrict        integer           not null,
    isactive          integer           not null,
    version           integer default 0 not null,
    settlementnameen  varchar(255),
    postalcode        numeric(8),
    CONSTRAINT set_muy_fk FOREIGN KEY (municipalitycode)
        REFERENCES nomenclatures.ek_municipality (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT set_dit_fk FOREIGN KEY (districtcode)
        REFERENCES nomenclatures.ek_district (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

/*COPY nomenclatures.ek_settlement
    FROM 'c:/data/ekatte.csv'
    DELIMITER ',';
*/

create table nomenclatures.legal_reason
(
    id  serial
        constraint lrn_pk
            primary key,
    name               varchar(50) not null,
    active             integer     not null,
    status_code        varchar(4) not null,
    ordinance_article  varchar(255),
    regulation_article varchar(255),
    regulation_text    text
);
ALTER TABLE nomenclatures.legal_reason
    ADD CONSTRAINT lrn_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));

--TODO:Legal reasons

create table nomenclatures.regprof_service_type
(
    code           varchar(4)     not null
        constraint ste_pk
            primary key,
    name           varchar(100)   not null,
    execution_days integer        not null,
    active         integer        not null,
    liability_code  varchar(20) not null
);


CREATE TABLE nomenclatures.bologna_cycle
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    active int not null,
    CONSTRAINT bologna_cycle_pkey PRIMARY KEY (id)
);

CREATE TABLE nomenclatures.european_qualifications_framework
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    active int not null,
    CONSTRAINT european_qualifications_framework_pkey PRIMARY KEY (id)
);

CREATE TABLE nomenclatures.national_qualifications_framework
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    active int null null,
    country_code varchar(4),
    CONSTRAINT national_qualifications_framework_pkey PRIMARY KEY (id),
    CONSTRAINT national_qualifications_framework_country_id_fkey FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX national_qualifications_framework_country_id_idx
    ON nomenclatures.national_qualifications_framework
        USING btree
        (country_code);


CREATE TABLE nomenclatures.original_edu_level
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    country_code varchar(4) NOT NULL,
    edu_level varchar(20) NOT NULL,
    active int not null,
    name_translated character varying(255),
    CONSTRAINT original_edu_level_pkey PRIMARY KEY (id),
    CONSTRAINT original_edu_level_country_id_fkey FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION

);
ALTER TABLE nomenclatures.original_edu_level
    ADD CONSTRAINT oel_ell_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', edu_level));


CREATE INDEX original_edu_level_country_id_idx
    ON nomenclatures.original_edu_level
        USING btree
        (country_code);


CREATE INDEX original_edu_level_edu_level_id_idx
    ON nomenclatures.original_edu_level
        USING btree
        (edu_level);

CREATE TABLE nomenclatures.prof_group
(
    id serial NOT NULL,
    name text NOT NULL,
    edu_area varchar(20) not null,
    active int not null,
    CONSTRAINT n_prof_group_pk PRIMARY KEY (id)

);
ALTER TABLE nomenclatures.prof_group
    ADD CONSTRAINT pgp_eaa_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_AREA', edu_area));

CREATE INDEX prof_group_edu_area_id_idx
    ON nomenclatures.prof_group
        USING btree
        (edu_area);


CREATE TABLE nomenclatures.language
(
    code varchar(2) NOT NULL,
    name character varying(255) NOT NULL,
    active int not null,
    CONSTRAINT language_pk PRIMARY KEY (code)
);


CREATE TABLE nomenclatures.graduation_document_type
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    active int not null,
    CONSTRAINT graduation_document_type_pkey PRIMARY KEY (id)
);


CREATE TABLE nomenclatures.doc_types
(
  id serial NOT NULL,
  name character varying(80) NOT NULL,
  direction varchar(1) NOT NULL CHECK(direction in ('I','O')),
  rejection_flag int,
  active int not null,
  CONSTRAINT dte_pk PRIMARY KEY (id)
);
CREATE TABLE nomenclatures.cfg_doc_type_to_doc_category
(
  id serial not null,
  dte_id int not null,
  dcy_code varchar(20) not null,
  ate_code varchar(4)  --TODO:ZA oth, ate_code e null, no spored men i za tqh moje da se nameri pravilniq ate_code!!!!
        constraint dte_dcy_ate_fk
            references nomenclatures.application_type,
  ase_code varchar(4)
        constraint dte_dcy_ase_fk
            references nomenclatures.application_subtype,
  condition varchar(255),
  additional_description varchar(255),
  template varchar(255),
  docflow_flag integer not null,
  CONSTRAINT dte_dcy_pk PRIMARY KEY (id),
  CONSTRAINT dte_dcy_dte_fk FOREIGN KEY (dte_id)
     REFERENCES nomenclatures.doc_types (id) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE nomenclatures.cfg_doc_type_to_doc_category
    ADD CONSTRAINT dte_dcy_dcy_check
        CHECK (nomenclatures.exists_refdata('DOC_CATEGORY', dcy_code));




CREATE TABLE nomenclatures.commission_member_position
(
    code varchar(4) NOT NULL,
    name character varying(100) NOT NULL,
    active int not null,
    related_app_status_code varchar(20),
    CONSTRAINT cmp_pk PRIMARY KEY (code)
);

ALTER TABLE nomenclatures.commission_member_position
    ADD CONSTRAINT cmp_ras_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', related_app_status_code));


CREATE INDEX cmp_ras_idx
    ON nomenclatures.commission_member_position
        USING btree
        (related_app_status_code);



		
		

CREATE TABLE nomenclatures.external_nomenclatures_map
(
    id serial not null,
	system character varying(50) NOT NULL,
	nomenclature_type character varying(50) NOT NULL,
    internal_nom_id character varying(20) NOT NULL,
    condition1 character varying(50),
    condition2 character varying(50),
    external_nom_id character varying(20) NOT NULL
);




create table nomenclatures.cfg_app_status (
         id serial not null
             constraint cas_pk
                 primary key,
         ate_code                            varchar(20)               not null
             constraint cas_ate_fk
                 references nomenclatures.application_type,
         ase_code                            varchar(4)               null
             constraint cas_ase_fk
                 references nomenclatures.application_subtype,
         status_code                       varchar(20)                  not null,
         legal_flag                        integer not null,
         commission_flag                   integer not null,
         active                            integer not null
);
ALTER TABLE nomenclatures.cfg_app_status
    ADD CONSTRAINT cas_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));
create unique index cas_ate_ase_uk1 on nomenclatures.cfg_app_status (ate_code, ase_code, status_code) where ase_code is not null;
create unique index cas_ate_ase_uk2 on nomenclatures.cfg_app_status (ate_code, status_code) where ase_code is null;

create table nomenclatures.cfg_sar_app_status (
                                                     sar_ate                      varchar(20)               not null,
                                                     status_code                       varchar(20)                  not null,
                                                     legal_flag                        integer not null,
                                                     CONSTRAINT cfg_sas_pk PRIMARY KEY (sar_ate, status_code)
);
ALTER TABLE nomenclatures.cfg_sar_app_status
    ADD CONSTRAINT csas_sar_ate_check
        CHECK (nomenclatures.exists_refdata('SAR_APPLICATION_TYPE', sar_ate));
ALTER TABLE nomenclatures.cfg_sar_app_status
    ADD CONSTRAINT csas_sts_check
        CHECK (nomenclatures.exists_refdata('APPLICATION_STATUS', status_code));

create table nomenclatures.cfg_graduation_document_type_config (
    graduation_document_type_id int,
    country_code varchar(4),
    education_type varchar(20),
    CONSTRAINT gdtc_pk PRIMARY KEY (graduation_document_type_id, country_code, education_type),
    CONSTRAINT gdtc_gdt_fk FOREIGN KEY (graduation_document_type_id)
        REFERENCES nomenclatures.graduation_document_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT gdtc_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE nomenclatures.cfg_graduation_document_type_config
    ADD CONSTRAINT gdtc_ete_check
        CHECK (nomenclatures.exists_refdata('EDUCATION_TYPE', education_type));


CREATE TABLE nomenclatures.secondary_profession_group
(
    id serial NOT NULL,
    name character varying(150),
    active int not null,
    code character varying(10),
    CONSTRAINT spg_pk PRIMARY KEY (id)
);

CREATE TABLE nomenclatures.secondary_professional_qualification
(
    id serial NOT NULL,
    name character varying(200) NOT NULL,
    profession_group_id integer,
    active int not null,
    code character varying(10),
    CONSTRAINT spq_pk PRIMARY KEY (id),
    CONSTRAINT spq_pgp_fk FOREIGN KEY (profession_group_id)
        REFERENCES nomenclatures.secondary_profession_group (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX spq_pgp_fk
    ON nomenclatures.secondary_professional_qualification
        USING btree
        (profession_group_id);


CREATE TABLE nomenclatures.secondary_speciality
(
    id serial NOT NULL,
    name character varying(150) NOT NULL,
    professional_qualification_id integer NOT NULL,
    qualification_degree varchar(20),
    active int not null,
    code character varying(10),
    CONSTRAINT n_secondary_edu_specialiy_pk PRIMARY KEY (id),
    CONSTRAINT prof_qual_fk FOREIGN KEY (professional_qualification_id)
        REFERENCES nomenclatures.secondary_professional_qualification (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE nomenclatures.secondary_speciality
    ADD CONSTRAINT ssy_qualification_degree_check
        CHECK (nomenclatures.exists_refdata('QUALIFICATION_DEGREE', qualification_degree));

CREATE INDEX ssy_pqn_idx
    ON nomenclatures.secondary_speciality
        USING btree
        (professional_qualification_id);

CREATE INDEX ssy_qde_idx
    ON nomenclatures.secondary_speciality
        USING btree
        (qualification_degree);

CREATE TABLE nomenclatures.profession_experience_document_type
(
    code varchar(4),
    name character varying(100) NOT NULL,
    active int not null,
    for_experience_calculation_flag integer NOT NULL,
    CONSTRAINT pedt_pk PRIMARY KEY (code)
);

CREATE TABLE nomenclatures.regprof_article_directive
(
    id serial,
    name character varying(100) NOT NULL,
    active int not null,
    CONSTRAINT rad_pk PRIMARY KEY (id)
);

CREATE TABLE nomenclatures.regprof_article_item
(
    id serial NOT NULL,
    article_directive_id int NOT NULL,
    name character varying(255) NOT NULL,
    active int not null,
    qualification_level_label character varying,
    CONSTRAINT rai_pk PRIMARY KEY (id),
    CONSTRAINT rai_rad_fk FOREIGN KEY (article_directive_id)
        REFERENCES nomenclatures.regprof_article_directive (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX article_item_article_directive_id_idx
    ON nomenclatures.regprof_article_item
        USING btree
        (article_directive_id);

CREATE TABLE nomenclatures.cfg_edu_level_to_app_type
(
    ell_code character varying(20) NOT NULL,
    ate_code character varying(4) NOT NULL,
    ase_code character varying(4) NOT NULL,
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
);


CREATE TABLE nomenclatures.cfg_graduation_way_to_app_type
(
    gwy_code character varying(20) NOT NULL,
    ate_code character varying(4) NOT NULL,
    ase_code character varying(4) NOT NULL,
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
);

create table nomenclatures.cfg_report_sql
(
    code varchar(50) not null,
    description varchar(255) not null,
    sql_expression text not null,
    many_rows_flag int not null,
    group_flag int not null,
    start_text text,
    end_text text,
    separator_text text,
    CONSTRAINT rsl_pk PRIMARY KEY (code)
);
create table nomenclatures.cfg_report_field
(
    code varchar(40) not null,
    description varchar(255),
    sql_code varchar(50) not null,
    field_type varchar(20) not null,
    CONSTRAINT rfd_pk PRIMARY KEY (code),
    CONSTRAINT rfd_mms_fk FOREIGN KEY (sql_code)
        REFERENCES nomenclatures.cfg_report_sql (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
alter table nomenclatures.cfg_report_field
    ADD CONSTRAINT rfd_fte_check
        CHECK (nomenclatures.exists_refdata('REPORT_FIELD_TYPE', field_type));

create table nomenclatures.dictionary
(
    code   varchar(100) not null
        constraint dictionary_pk
            primary key,
    name   varchar(255) not null,
    active integer      not null
);