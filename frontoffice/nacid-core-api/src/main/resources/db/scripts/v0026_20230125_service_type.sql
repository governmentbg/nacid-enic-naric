--liquibase formatted sql

--changeset aneva:core_0026
--validCheckSum: 8:e0dbdcd21e0de6465e69c25f1440fc2b
INSERT INTO nomenclatures.reference_data_domain(
    domain, name, fo_only)
VALUES ('SERVICE_TYPE', 'Тип услуга', 0);

CREATE TABLE nomenclatures.cfg_service_type
(
    id integer NOT NULL,
    ate_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ase_code character varying(4) COLLATE pg_catalog."default",
    execution_days smallint,
    liability_code character varying(20) COLLATE pg_catalog."default",
    service_type_code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cst_pk PRIMARY KEY (id),
    CONSTRAINT cst_ase_fk FOREIGN KEY (ase_code)
        REFERENCES nomenclatures.application_subtype (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cst_ate_fk FOREIGN KEY (ate_code)
        REFERENCES nomenclatures.application_type (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT cst_service_type_check CHECK (nomenclatures.exists_refdata('SERVICE_TYPE'::character varying, service_type_code))
)

    TABLESPACE pg_default;

ALTER TABLE nomenclatures.cfg_service_type
    OWNER to postgres;