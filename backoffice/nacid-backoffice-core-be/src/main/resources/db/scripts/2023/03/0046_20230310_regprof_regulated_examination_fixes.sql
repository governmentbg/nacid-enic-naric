--liquibase formatted sql

--changeset ggeorgiev:0046
drop table regprof.application_regulated_examination;
drop table regprof.regulated_examination;


CREATE TABLE regprof.application_regulated_profession_examination
(
    apn_id integer NOT NULL,
    country_code varchar(4) not null,
    examination_date date not null,
    notes text,
    date_created time with time zone NOT NULL DEFAULT now(),
    user_created varchar(100) not null,
    profession character varying(150),
    regulated_flag integer,
    CONSTRAINT are_pk PRIMARY KEY (apn_id),
    CONSTRAINT ren_coy_fk FOREIGN KEY (country_code)
        REFERENCES nomenclatures.country (code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT are_apn_fk FOREIGN KEY (apn_id)
        REFERENCES regprof.regprof_application (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);