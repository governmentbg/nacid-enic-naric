--liquibase formatted sql

--changeset mnakova:0078.1
DROP TABLE libserv.impact_factor_report_rows;

--changeset mnakova:0078.2
CREATE TABLE libserv.impact_factor_report_rows
(
    id serial,
    apn_id integer NOT NULL,
    title character varying(255),
    year character varying(20),
    issn character varying(20),
    impact character varying(20),
    CONSTRAINT impact_factor_report_rows_pkey PRIMARY KEY (id),
    CONSTRAINT impact_factor_report_rows_inquiry_id_fkey FOREIGN KEY (apn_id)
        REFERENCES libserv.inquiry (apn_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
        );
ALTER TABLE libserv.impact_factor_report_rows
    OWNER TO postgres;