--liquibase formatted sql

--changeset veizov:0112
alter table nomenclatures.doc_types add column abdocs_doc_type_id numeric(5);
