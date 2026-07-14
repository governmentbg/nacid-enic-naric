--liquibase formatted sql

--changeset murlev:core_0067

UPDATE nomenclatures.cfg_doc_type_requirement SET requirement_expression='applicationType.getCode() == ''AR'' && applicationSubtype.getCode() != ''ARD'''
    WHERE dte_id=113 and requirement_key='rule.documentDetails.attachments.declarationPersonalDetails';

