--liquibase formatted sql

--changeset ggeorgiev:core_0058
UPDATE nomenclatures.cfg_doc_type_requirement set requirement_expression = '!T(bg.duosoft.nacidshareddata.util.security.SecurityUtils).hasRole(''FO_employee_app_submit'') && applicantDetails != null && applicantDetails.applicantHasRepresentative' WHERE dte_id = 6 and ate_code = 'SE';
