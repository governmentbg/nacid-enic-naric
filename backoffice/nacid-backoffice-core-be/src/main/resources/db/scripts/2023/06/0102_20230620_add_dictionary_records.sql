--liquibase formatted sql

--changeset akehayov:0102
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Адреси', 1, 'AddressServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Прикачени файлове към заявления', 1, 'ApplicationAttachmentServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Прикачени файлове', 1, 'AttachmentServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Аналитични продукти', 1, 'BibliographicReferenceServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Справки', 1, 'CfgReportSqlService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Типове услуги', 1, 'CfgServiceTypeService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Календар на съвет (САП)', 1, 'CommissionCalendarServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Компетентни институции', 1, 'CompetentInstitutionService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Дипломи', 1, 'DiplomaTypeService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Документни услуги', 1, 'DocumentDeliveryServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Типове документи', 1, 'DocumentTypeService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Грешки', 1, 'ErrorLogServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Цитирания и импакт фактор', 1, 'InquiryServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Библиотечно-информационни услуги: Промяна на статус', 1, 'LibservApplicationStatusChange');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Национални квалификационни рамки', 1, 'NationalQualificationsFrameworkService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Служебни бележки', 1, 'OfficialNoteServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Оригинални степени на образование', 1, 'OriginalEduLevelService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Лица', 1, 'PersonServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Обучаваща институция', 1, 'ProfInstitutionService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Обединени номенклатури', 1, 'ReferenceDataService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Заявления за професионални квалификации', 1, 'RegprofApplicationServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Професионални квалификации: Промяна на статус', 1, 'RegprofApplicationStatusChange');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Заявления за академично признаване', 1, 'RudiApplicationServiceImpl');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Академично признаване: Промяна на статус', 1, 'RudiApplicationStatusChange');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Институции, провеждащи обучението', 1, 'TrainingInstitutionService');
INSERT INTO nomenclatures.dictionary(name, active, code) values ('Университети', 1, 'UniversityServiceImpl');
