-- Table: services.application_correspondence

-- DROP TABLE services.application_correspondence;

CREATE TABLE services.application_correspondence
(
    id serial NOT NULL,
    apn_id integer NOT NULL,
    bo_attached_doc_id integer,
    about character varying (255),
    registration_number character varying(20),
    registration_date date,
    date_created timestamp with time zone NOT NULL,
    date_read timestamp with time zone,
    CONSTRAINT app_corresp_pk PRIMARY KEY (id),
    CONSTRAINT app_corresp_apn_id_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE services.application_correspondence
    OWNER to postgres;