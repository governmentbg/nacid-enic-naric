--liquibase formatted sql

--changeset mnakova:core_0057
INSERT INTO nomenclatures.cfg_doc_type_requirement (dte_id, cte_code, ate_code, ase_code, requirement_key, requirement_expression, template_url) VALUES (6, null, 'SE', 'REC', 'rule.documentDetails.attachments.se.hasRepresentative', 'applicantDetails != null && applicantDetails.applicantHasRepresentative', null);

