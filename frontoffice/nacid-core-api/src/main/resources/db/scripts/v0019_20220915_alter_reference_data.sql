--liquibase formatted sql

--changeset aneva:core_0019
ALTER TABLE nomenclatures.reference_data ADD CONSTRAINT rda_domain_fk
    FOREIGN KEY (domain)
        REFERENCES nomenclatures.reference_data_domain (domain) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;