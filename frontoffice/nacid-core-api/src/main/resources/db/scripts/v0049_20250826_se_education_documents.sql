--liquibase formatted sql

--changeset yilieva:core_0049.1
INSERT INTO nomenclatures.doc_types (id, name, active) VALUES (200, 'Диплома', 1) on conflict do nothing;

INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (200, 'ORG', 'SE', 'REC', 'rule.documentDetails.attachments.se.diplomaOriginal', NULL);
INSERT INTO nomenclatures.cfg_doc_type_requirement(dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression)
    VALUES (200, 'TRA', 'SE', 'REC', 'rule.documentDetails.attachments.se.diplomaTranslation', NULL);