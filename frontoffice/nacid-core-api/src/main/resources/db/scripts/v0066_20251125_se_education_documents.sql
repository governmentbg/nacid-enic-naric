--liquibase formatted sql

--changeset murlev:core_0066
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (127, 'SE', 'REC', 'rule.documentDetails.attachments.se.feePaidDocument', 'educationDetails != null && ((educationDetails.hasCertificate != null && educationDetails.hasCertificate) || (educationDetails.hasVerificationLetter != null && educationDetails.hasVerificationLetter) || (educationDetails.hasOfficialNote != null && educationDetails.hasOfficialNote))');