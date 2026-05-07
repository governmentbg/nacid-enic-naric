--liquibase formatted sql

--changeset raneva:core_0044

UPDATE nomenclatures.cfg_doc_type_requirement SET requirement_expression='applicationType.getCode() == ''AR'''
    WHERE dte_id=113 and requirement_key='rule.documentDetails.attachments.declarationPersonalDetails';

