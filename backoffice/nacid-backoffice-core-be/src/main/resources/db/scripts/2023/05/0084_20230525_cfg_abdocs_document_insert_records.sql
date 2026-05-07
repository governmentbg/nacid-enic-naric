--liquibase formatted sql

--changeset murlev:0084

INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('ARDOC', 'Заявление за докторска степен от чужбина', 156, 3, 'Заявление за признаване на докторски степени');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('ARSAR', 'Заявление за статут, автентичност, препоръка', 155, 3, 'Заявление за проверка на статут, автентичност, препоръка');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('ARUDI', 'Заявление за признаване на диплома от чужбина', 154, 3, 'Заявление за академично признаване');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBBRFDBIR', 'Библиографска справка от чужди бази данни', 162, 3, 'Библиографска справка от чужди бази данни');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBBRNTPB', 'Библиографска справка от бази данни на НАЦИД', 163, 3, 'Библиографска справка от бази данни на НАЦИД');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBINQCIT', 'Справка за цитирания на публикации', 160, 3, 'Справка за цитирания на публикации');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBINQIFCIT', 'Справка за импакт фактор на цитиращи публикации', 164, 3, 'Справка за импакт фактор на цитиращи публикации');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBINQIMP', 'Справка за импакт фактор на авторски публикации', 161, 3, 'Справка за импакт фактор на авторски публикации');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBONDIS', 'Служебна бележка за защитен дисертационен труд', 158, 3, 'Служебна бележка за защитен дисертационен труд');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('LIBONPOS', 'Служебна бележка за заемана академична длъжност', 159, 3, 'Служебна бележка за заемана академична длъжност');
INSERT INTO nomenclatures.cfg_abdocs_document (id, name, doc_type_id, doc_reg_type_id, doc_subject) VALUES ('RP', 'Удостоверение за професионална квалификация', 157, 3, 'Удостоверение за професионална квалификация');
