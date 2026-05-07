--liquibase formatted sql

--changeset ggeorgiev:0082
alter table nomenclatures.doc_types drop column rejection_flag;
alter table nomenclatures.cfg_doc_type_to_doc_category add column finalization_type varchar(10);
ALTER TABLE nomenclatures.cfg_doc_type_to_doc_category
    ADD CONSTRAINT dtedc_finalization_type_check
        CHECK (nomenclatures.exists_refdata('DOCUMENT_FINALIZATION_TYPE', finalization_type));