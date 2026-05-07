--liquibase formatted sql

--changeset ggeorgiev:core_0056
delete from nomenclatures.cfg_doc_type_to_app_type where ate_code = 'SE' and dte_id in (13, 17, 6, 136, 203, 202);
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (405, 6, 'SE', null, 'applicantDetails != null && applicantDetails.applicantHasRepresentative');
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (419, 13, 'SE', 'REC', 'false');
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (420, 17, 'SE', 'REC', 'false');
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (404, 136, 'SE', null, 'applicantDetails != null && applicantDetails.diplomaNamesDifferent');
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (402, 202, 'SE', null, 'educationDetails != null && educationDetails.hasOfficialNote != null && educationDetails.hasOfficialNote');
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (403, 203, 'SE', null, 'educationDetails != null && educationDetails.hasOfficialNote != null && educationDetails.hasOfficialNote && (educationDetails.hasCertificate == null || educationDetails.hasCertificate == false)');