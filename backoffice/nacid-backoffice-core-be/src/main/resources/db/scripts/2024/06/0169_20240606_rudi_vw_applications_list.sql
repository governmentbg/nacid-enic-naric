--liquibase formatted sql

--changeset mnakova:0169.1
drop view rudi.vw_commission_applications_list;
drop view rudi.vw_applications_list;

--changeset mnakova:0169.2
CREATE OR REPLACE VIEW rudi.vw_applications_list
AS
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       ase.name                                                                                                                                                             AS ase_name,
       apn.entry_num,
       apn.entry_date,
       apn.efiling_id,
       CASE
           WHEN apt.legal_type::text = 'LE'::text THEN apt.legal_name::text
           ELSE (apt.first_name::text || COALESCE(' '::text || apt.second_name::text, ''::text)) || COALESCE(' '::text || apt.last_name::text, ''::text)
           END                                                                                                                                                                  AS applicant_name,
       (own.first_name::text || COALESCE(' '::text || own.second_name::text, ''::text)) || COALESCE(' '::text || own.last_name::text, ''::text)                             AS diploma_owner_name,
       own.civil_id                                                                                                                                                         AS diploma_owner_civil_id,
       uny.bg_name                                                                                                                                                          AS university_name,
       ucy.name                                                                                                                                                             AS university_country_name,
       ( SELECT array_to_string(ARRAY( SELECT tcs.speciality
                                       FROM rudi.training_course_speciality tcs
                                       WHERE tcs.tce_id = tce.id
                                       ORDER BY tcs.id), ', '::text) AS array_to_string) AS speciality_name,
       ass.name                                                                                                                                                             AS apn_status_name,
       ass.code                                                                                                                                                             AS apn_status_code,
       apn.docflow_status_code,
       dss.name                                                                                                                                                             AS docflow_status_name,
       ( SELECT array_to_string(ARRAY( SELECT ccr.session_num
                                       FROM rudi.commission_applications can
                                                JOIN rudi.commission_calendar ccr ON ccr.id = can.calendar_id
                                       WHERE can.apn_id = ran.apn_id), ', '::text) AS array_to_string)                                                                      AS commission_sessions,
       ( SELECT count(*)::integer AS count
FROM rudi.application_commission_members acm
WHERE acm.apn_id = ran.apn_id)                                                                                                                                     AS experts_count,
    COALESCE(( SELECT 0
    FROM rudi.application_commission_members acm
    WHERE acm.apn_id = ran.apn_id AND acm.process_status = 0
    LIMIT 1), 1)                                                                                                                                              AS experts_processed_status,
    rpgp.name                                                                                                                                                            AS recognized_prof_group_name,
    ard.recognized_qualification,
    rell.name                                                                                                                                                            AS recognized_edu_level_name,
    ( SELECT array_to_string(ARRAY( SELECT ars.speciality
    FROM rudi.application_recognized_speciality ars
    WHERE ars.apn_id = apn.id
    ORDER BY ars.id), ', '::text) AS array_to_string)                                                                                    AS recognized_speciality_name,
    tce.original_edu_level_name,
    tce.original_edu_level_translated,
    tce.manual_temp_uni_name,
    ( SELECT array_to_string(array_remove(ARRAY[
    CASE
    WHEN sar_apn.statute_flag = 1 THEN ( SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text AND reference_data.code::text = 'S'::text)
    ELSE NULL::character varying
    END,
    CASE
    WHEN sar_apn.authenticity_flag = 1 THEN ( SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text AND reference_data.code::text = 'A'::text)
    ELSE NULL::character varying
    END,
    CASE
    WHEN sar_apn.recommendation_flag = 1 THEN ( SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text AND reference_data.code::text = 'R'::text)
    ELSE NULL::character varying
    END], NULL::character varying), ', '::text) AS array_to_string)                                                        AS sar_flag,
    uny.id                                                                                                                                                               AS university_id,
    aru.responsible_user                                                                                                                                                 AS responsible_user_name,
    pdt.name                                                                                                                                                             AS personal_document_type_name,
    apn.service_type                                                                                                                                                     AS service_type_id,
    (SELECT nullif(array_to_string(ARRAY(SELECT adrm.crf_code
    FROM common.application_document_receive_method adrm
    WHERE adrm.apn_id = apn.id), ', '::text), '') AS array_to_string)                                                                                                    AS crf_code,
    ran.legal_reason_id                                                                                                                                                  AS legal_reason_id,
    lrn.name                                                                                                                                                             AS legal_reason_name
FROM common.application apn
    JOIN rudi.rudi_application ran ON ran.apn_id = apn.id
    JOIN nomenclatures.application_subtype ase ON ase.code::text = apn.ase_code::text
    LEFT JOIN rudi.training_course tce ON tce.apn_id = ran.apn_id
    LEFT JOIN common.person apt ON apt.id = apn.applicant_id
    LEFT JOIN common.person own ON own.id = tce.owner_id
    LEFT JOIN rudi.training_course_universities tcu ON tcu.tce_id = tce.id AND tcu.ord_num = 1
    LEFT JOIN rudi.university uny ON uny.id = tcu.uny_id
    LEFT JOIN nomenclatures.country ucy ON ucy.code::text = uny.country_code::text
    LEFT JOIN nomenclatures.reference_data ass ON ass.code::text = apn.status_code::text AND ass.domain::text = 'APPLICATION_STATUS'::text
    LEFT JOIN nomenclatures.reference_data dss ON dss.code::text = apn.docflow_status_code::text AND dss.domain::text = 'DOCFLOW_STATUS'::text
    LEFT JOIN rudi.application_recognition_details ard ON ard.apn_id = apn.id
    LEFT JOIN nomenclatures.reference_data rell ON rell.domain::text = 'EDUCATION_LEVEL'::text AND rell.code::text = ard.recognized_edu_level::text
    LEFT JOIN nomenclatures.prof_group rpgp ON rpgp.id = ard.recognized_prof_group_id
    LEFT JOIN rudi.sar_application sar_apn ON sar_apn.apn_id = ran.apn_id
    LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL
    LEFT JOIN nomenclatures.reference_data pdt ON pdt.domain::text = 'PERSONAL_DOCUMENT_TYPE'::text AND pdt.code::text = apn.personal_document_type_code::text
    LEFT JOIN nomenclatures.legal_reason lrn on lrn.id = ran.legal_reason_id;



--changeset mnakova:0169.3
create or replace view rudi.vw_commission_applications_list
            (id, ate_code, ase_code, ase_name, entry_num, entry_date, efiling_id, applicant_name, diploma_owner_name,
             diploma_owner_civil_id, university_name, university_country_name, speciality_name, apn_status_name,
             apn_status_code, docflow_status_code, docflow_status_name, commission_sessions, experts_count,
             experts_processed_status, recognized_prof_group_name, recognized_qualification, recognized_edu_level_name,
             recognized_speciality_name, original_edu_level_name, original_edu_level_translated, manual_temp_uni_name,
             sar_flag, university_id, responsible_user_name, personal_document_type_name, service_type_id, crf_code,
             calendar_id, motives, applicant_info, generated_final_doc, abdocs_transferred)
as
SELECT val.id,
       val.ate_code,
       val.ase_code,
       val.ase_name,
       val.entry_num,
       val.entry_date,
       val.efiling_id,
       val.applicant_name,
       val.diploma_owner_name,
       val.diploma_owner_civil_id,
       val.university_name,
       val.university_country_name,
       val.speciality_name,
       val.apn_status_name,
       val.apn_status_code,
       val.docflow_status_code,
       val.docflow_status_name,
       val.commission_sessions,
       val.experts_count,
       val.experts_processed_status,
       val.recognized_prof_group_name,
       val.recognized_qualification,
       val.recognized_edu_level_name,
       val.recognized_speciality_name,
       val.original_edu_level_name,
       val.original_edu_level_translated,
       val.manual_temp_uni_name,
       val.sar_flag,
       val.university_id,
       val.responsible_user_name,
       val.personal_document_type_name,
       val.service_type_id,
       val.crf_code,
       ca.calendar_id,
       ca.motives,
       ca.applicant_info,
       CASE
           WHEN atd.id IS NOT NULL THEN 1
           ELSE 0
           END AS generated_final_doc,
       CASE
           WHEN atd.docflow_id IS NOT NULL THEN 1
           ELSE 0
           END AS abdocs_transferred
FROM rudi.vw_applications_list val
         JOIN rudi.commission_applications ca ON ca.apn_id = val.id
         LEFT JOIN common.application_attached_docs atd ON ca.attached_doc_id = atd.id;