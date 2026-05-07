--liquibase formatted sql

--changeset veizov:0062
CREATE TABLE common.application_notes
(
    id           serial,
    apn_id       integer                  NOT NULL,
    created_user character varying(100)   NOT NULL,
    created_date timestamp with time zone NOT NULL,
    note         text                     NOT NULL,
    CONSTRAINT apn_notes_pkey PRIMARY KEY (id),
    CONSTRAINT apn_notes_apn_fk FOREIGN KEY (apn_id)
        REFERENCES common.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
)
    WITH (
        OIDS = FALSE
        );
ALTER TABLE common.application_notes
    OWNER TO postgres;