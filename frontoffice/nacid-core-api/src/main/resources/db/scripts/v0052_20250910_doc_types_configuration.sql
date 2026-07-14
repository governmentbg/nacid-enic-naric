--liquibase formatted sql

--changeset ggeorgiev:core_0052
update nomenclatures.doc_types set name = 'Диплома – оригинал и превод (с изискуемите заверки)' where id = 200;
INSERT INTO nomenclatures.doc_types (id, name, active) VALUES (201, 'Документ за право за продължаване на висше образование – оригинал и превод (с изискуемите заверки)', 1) on conflict do nothing;
INSERT INTO nomenclatures.doc_types (id, name, active) VALUES (202, 'Документи с предмети, оценки и класове – оригинал и превод (с изискуемите заверки)', 1) on conflict do nothing;
INSERT INTO nomenclatures.doc_types (id, name, active) VALUES (203, 'Удостоверение, издадено от РУО', 1) on conflict do nothing;
delete from nomenclatures.cfg_doc_type_requirement where dte_id = 200 and ate_code = 'SE';
INSERT INTO nomenclatures.cfg_doc_type_requirement (dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression, template_url) VALUES (200, null, 'SE', 'REC', 'rule.documentDetails.attachments.se.diploma', 'educationDetails != null && ((educationDetails.hasCertificate != null && educationDetails.hasCertificate) || (educationDetails.hasVerificationLetter != null && educationDetails.hasVerificationLetter))', null);
INSERT INTO nomenclatures.cfg_doc_type_requirement (dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression, template_url) VALUES (201, null, 'SE', 'REC', 'rule.documentDetails.attachments.se.documentContinueHigherEducation', 'educationDetails != null && educationDetails.hasCertificate != null && educationDetails.hasCertificate', null);
INSERT INTO nomenclatures.cfg_doc_type_requirement (dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression, template_url) VALUES (202, null, 'SE', 'REC', 'rule.documentDetails.attachments.se.documentSubjectGradeClasses', 'educationDetails != null && educationDetails.hasOfficialNote != null && educationDetails.hasOfficialNote', null);
INSERT INTO nomenclatures.cfg_doc_type_requirement (dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression, template_url) VALUES (136, null, 'SE', 'REC', 'rule.documentDetails.attachments.se.differentDiplomaNames', 'applicantDetails != null && applicantDetails.diplomaNamesDifferent', null);

