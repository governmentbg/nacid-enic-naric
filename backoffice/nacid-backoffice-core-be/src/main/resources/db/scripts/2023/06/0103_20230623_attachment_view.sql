--liquibase formatted sql

--changeset veizov:0103
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
UNION ALL
SELECT DISTINCT tc.apn_id,
                at.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type,
                at.docflow_key
FROM rudi.university_examination_attached_docs d
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN common.attachments at
on (d.attachment_id = at.id or d.scanned_attachment_id = at.id)
    JOIN rudi.university_examination uex ON d.university_examination_id = uex.id
    JOIN rudi.training_course_university_examination tcux ON uex.id = tcux.university_examination_id
    JOIN rudi.training_course tc on tcux.tce_id = tc.id