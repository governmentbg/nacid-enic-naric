--liquibase formatted sql

--changeset veizov:0061
CREATE TABLE nomenclatures.cfg_language_to_app_type
(
    id serial NOT NULL,
    lae_code character varying(2) NOT NULL,
    ate_code character varying(20) NOT NULL,
    ase_code character varying(4) NOT NULL,
    CONSTRAINT cfg_lae_ate_pk PRIMARY KEY (id),
    CONSTRAINT cfg_lae_ate_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_lae_ate_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cfg_lae_ate_lae_fk FOREIGN KEY (lae_code)
        REFERENCES nomenclatures.language (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_language_to_app_type
    OWNER to postgres;