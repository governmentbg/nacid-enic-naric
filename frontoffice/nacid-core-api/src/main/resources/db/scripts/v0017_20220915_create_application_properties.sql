--liquibase formatted sql

--changeset aneva:core_0017
CREATE TABLE common.application_properties
(
    property_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    property_value text COLLATE pg_catalog."default",
    property_description text COLLATE pg_catalog."default",
    CONSTRAINT apy_pk PRIMARY KEY (property_name)
)

    TABLESPACE pg_default;

ALTER TABLE common.application_properties
    OWNER to postgres;