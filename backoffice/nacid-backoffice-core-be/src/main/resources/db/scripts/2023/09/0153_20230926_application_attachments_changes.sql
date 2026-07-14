--liquibase formatted sql

--changeset ggeorgiev:0153 splitStatements:false
alter table nomenclatures.cfg_doc_type_to_doc_category add column default_attachment_visibility varchar(10);

UPDATE nomenclatures.cfg_doc_type_to_doc_category dtdc
set default_attachment_visibility = (case when dte.direction = 'I' then 'INT' when dte.id = 1 and dtdc.template like '%e%udost%' then 'PUB' else 'INT' end)
from nomenclatures.doc_types dte where dte.id = dtdc.dte_id and dtdc.template is not null;


ALTER TABLE nomenclatures.cfg_doc_type_to_doc_category
    ADD CONSTRAINT default_attachment_visibility_check
        CHECK (nomenclatures.exists_refdata('ATTACHMENT_VISIBILITY', default_attachment_visibility));

ALTER TABLE nomenclatures.cfg_doc_type_to_doc_category
    ADD CONSTRAINT default_attachment_visibility_null_check
        CHECK ((default_attachment_visibility is null and template is null) or (template is not null and default_attachment_visibility is not null));

