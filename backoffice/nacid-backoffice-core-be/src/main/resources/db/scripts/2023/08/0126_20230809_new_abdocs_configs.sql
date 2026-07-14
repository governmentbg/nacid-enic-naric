--liquibase formatted sql

--changeset raneva:0126
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBSIG', 'Заявление за подаване на сигнал', 168, 3, 'Заявление за подаване на сигнал');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBSUG', 'Заявление за подаване на предложение', 167, 3, 'Заявление за подаване на предложение');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBPUB', 'Заявление за достъп до обществена информация', 169, 3, 'Заявление за достъп до обществена информация');