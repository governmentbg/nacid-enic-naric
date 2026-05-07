--liquibase formatted sql

--changeset veizov:0087
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject)
VALUES ('LIBDS', 'Доставка на документи', 101, 3, 'Доставка на документи');
