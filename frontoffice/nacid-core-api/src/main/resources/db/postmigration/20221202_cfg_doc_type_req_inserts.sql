--Common
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, template_url)
    VALUES (113, null, 'AR', null, 'rule.documentDetails.attachments.declarationPersonalDetails', 'https://portal.nacid.bg/documents/DataUsageDeclaration.doc');

-- Higher education
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (4, 'ORG', 'AR', 'UDI', 'rule.documentDetails.attachments.universityDiplomaOriginal', null);

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (4, 'TRA', 'AR', 'UDI', 'rule.documentDetails.attachments.universityDiplomaTranslation', null);

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (15, 'ORG', 'AR', 'UDI', 'rule.documentDetails.attachments.diplomaAttachmentOriginal', null);

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (15, 'TRA', 'AR', 'UDI', 'rule.documentDetails.attachments.diplomaAttachmentTranslation', null);

-- UNI checks
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (4, null, 'AR', 'SAR', 'rule.documentDetails.attachments.universityDiploma', 'educationDetails == null || educationDetails.recognitionCategory == null || educationDetails.recognitionCategory.id == null || (educationDetails.recognitionCategory.id != ''DOC'' && educationDetails.recognitionCategory.id != ''DSC'' && educationDetails.recognitionCategory.id != ''PER'')');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (15, null, 'AR', 'SAR', 'rule.documentDetails.attachments.diplomaAttachment', 'educationDetails == null || educationDetails.recognitionCategory == null || educationDetails.recognitionCategory.id == null || (educationDetails.recognitionCategory.id != ''DOC'' && educationDetails.recognitionCategory.id != ''DSC'')');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (43, null, 'AR', 'SAR', 'rule.documentDetails.attachments.dissertation', 'educationDetails != null && educationDetails.recognitionCategory != null && (''DOC'' == educationDetails.recognitionCategory.id || ''DSC'' == educationDetails.recognitionCategory.id)');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (110, null, 'AR', 'SAR', 'rule.documentDetails.attachments.originalApplicantApplication', null);

-- Doc degrees
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (118, null, 'AR', 'DOC', 'rule.documentDetails.attachments.otherProofs', 'educationDetails != null && educationDetails.containsGraduationWayCode(''OTH'')');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key)
    VALUES (5, 'ORG', 'AR', 'DOC', 'rule.documentDetails.attachments.scienceDegreeDiplomaOriginal');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key)
    VALUES (5, 'TRA', 'AR', 'DOC', 'rule.documentDetails.attachments.scienceDegreeDiplomaTranslation');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (43, null, 'AR', 'DOC', 'rule.documentDetails.attachments.dissertation', 'educationDetails != null && educationDetails.containsGraduationWayCode(''DIS'')');

---Others
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, template_url)
    VALUES (126, null, 'RP', 'RP', 'rule.documentDetails.attachments.rightToPracticeProfessionDeclaration', 'https://nacid.bg/att_files/adm_usl/1601_4_deklaraciya.doc');

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (147, null, 'LIB', 'INQ', 'rule.documentDetails.attachments.authorsPublicationsList', 'inquiryDetails != null && (inquiryDetails.previousInquiryNum ==null || inquiryDetails.previousInquiryNum.isEmpty())');