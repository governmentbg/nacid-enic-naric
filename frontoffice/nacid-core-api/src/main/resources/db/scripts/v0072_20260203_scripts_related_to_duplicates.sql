--liquibase formatted sql

--changeset mnakova:core_0072.1
INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active) VALUES ('RPDU', 'RP', 'Дубликат на удостоверение за професионална квалификация', 1);
INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active) VALUES ('ARDU', 'AR', 'Дубликат на удостоверение за академично признаване', 1);
INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active) VALUES ('SEDU', 'SE', 'Дубликат на удостоверение или уверение за признаване на завършено в чужбина средно образование', 1);

--changeset mnakova:core_0072.2
INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.rudiDuplicate', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.seDuplicate', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.regprofDuplicate', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

--changeset mnakova:core_0072.3
UPDATE nomenclatures.cfg_doc_type_requirement SET requirement_expression='applicationType.getCode() == ''AR'' && applicationSubtype.getCode() != ''ARD'' && applicationSubtype.getCode() != ''ARDU'''
WHERE dte_id=113 and requirement_key='rule.documentDetails.attachments.declarationPersonalDetails';
