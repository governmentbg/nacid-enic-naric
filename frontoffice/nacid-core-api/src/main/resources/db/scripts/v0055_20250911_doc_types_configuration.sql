--liquibase formatted sql

--changeset ggeorgiev:core_0055
UPDATE nomenclatures.cfg_doc_type_to_app_type SET show_expression = 'educationDetails != null && educationDetails.hasOfficialNote != null && educationDetails.hasOfficialNote && (educationDetails.hasCertificate == null || educationDetails.hasCertificate == false)' WHERE dte_id = 203 and ate_code = 'SE';
