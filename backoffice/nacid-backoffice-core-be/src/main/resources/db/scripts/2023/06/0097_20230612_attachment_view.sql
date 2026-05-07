--liquibase formatted sql

--changeset veizov:0097
--validCheckSum: 8:43da51bcfb7770c905f8e8aed445cdf0
--validCheckSum: 8:df9d9b604c2eb4e12e5c04404c6ea224
DROP VIEW if exists common.vw_attachments;

CREATE view common.vw_attachments
as
SELECT DISTINCT d.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM common.application_attached_docs d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN common.attachments at on (d.attachment_id = at.id OR d.scanned_attachment_id = at.id)
UNION ALL
SELECT DISTINCT d.apn_id,
                at.id as attachment_id,
                NULL  as doc_type_name,
                NULL  as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM libserv.document_delivery_details d
         JOIN common.attachments at
on d.attachment_id = at.id
UNION ALL
SELECT DISTINCT e.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM regprof.profession_experience_examination_attached_docs d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN regprof.regprof_training_experience e on e.id = d.rte_id
         JOIN common.attachments at
on (d.attachment_id = at.id or d.scanned_attachment_id = at.id)
UNION ALL
SELECT DISTINCT e.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM regprof.training_course_document_examination_attached_docs d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN regprof.regprof_training_experience e on e.id = d.rte_id
         JOIN common.attachments at
on (d.attachment_id = at.id or d.scanned_attachment_id = at.id)
UNION ALL
SELECT DISTINCT d.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM rudi.application_commission_member_statements d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN common.attachments at
on d.attachment_id = at.id
UNION ALL
SELECT DISTINCT te.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM rudi.training_course_diploma_examination_attached_docs d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN common.attachments at
on (d.attachment_id = at.id or d.scanned_attachment_id = at.id)
    JOIN rudi.training_course_diploma_examination de on d.training_course_diploma_examination_id = de.id
    JOIN rudi.training_course te on de.tce_id = te.id