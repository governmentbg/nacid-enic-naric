--liquibase formatted sql

--changeset ggeorgiev:0139 splitStatements:false
drop view if exists rudi.vw_applications_list;
create or replace view rudi.vw_applications_list
as
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       ase.name                                                                  AS ase_name,
       apn.entry_num,
       apn.entry_date,
       apn.efiling_id,
       CASE
           WHEN apt.legal_type = 'LE' THEN apt.legal_name
           ELSE (apt.first_name || COALESCE(' ' || apt.second_name, '')) || COALESCE(' ' || apt.last_name, '')
           END                                                                   AS applicant_name,
       (own.first_name || COALESCE(' ' || own.second_name, '')) ||COALESCE(' ' || own.last_name, '')
                                                                                 AS diploma_owner_name,
       own.civil_id                                                              AS diploma_owner_civil_id,
       uny.bg_name                                                               AS university_name,
       ucy.name                                                                  AS university_country_name,
       (SELECT array_to_string(ARRAY(SELECT tcs.speciality
                                     FROM rudi.training_course_speciality tcs
                                     WHERE tcs.tce_id = tce.id
                                     ORDER BY tcs.id),', '))                     AS speciality_name,
       ass.name                                                                  AS apn_status_name,
       ass.code                                                                  AS apn_status_code,
       apn.docflow_status_code,
       dss.name                                                                  AS docflow_status_name,
       (SELECT array_to_string(ARRAY(SELECT ccr.session_num AS session_num
                                     FROM rudi.commission_applications can
                                              JOIN rudi.commission_calendar ccr ON ccr.id = can.calendar_id
                                     WHERE can.apn_id = ran.apn_id),
                               ', ') AS array_to_string)                         AS commission_sessions,
       (SELECT count(*) ::integer AS count
        FROM rudi.application_commission_members acm
        WHERE acm.apn_id = ran.apn_id)                                           AS experts_count
        ,
       COALESCE((SELECT 0
                 FROM rudi.application_commission_members acm
                 WHERE acm.apn_id = ran.apn_id
                   AND acm.process_status = 0
                 LIMIT 1)
           , 1)                                                                  AS experts_processed_status,
       rpgp.name                                                                 AS recognized_prof_group_name,
       ard.recognized_qualification,
       rell.name                                                                 AS recognized_edu_level_name,
       (SELECT array_to_string(ARRAY(SELECT ars.speciality
                                     FROM rudi.application_recognized_speciality ars
                                     WHERE ars.apn_id = apn.id
                                     ORDER BY ars.id),', '))                     AS recognized_speciality_name,
       tce.original_edu_level_name                                               AS original_edu_level_name,

       (SELECT array_to_string(array_remove(ARRAY [CASE WHEN sar_apn.statute_flag = 1 THEN (SELECT reference_data.name FROM nomenclatures.reference_data WHERE reference_data.domain = 'SAR_APPLICATION_TYPE' AND reference_data.code = 'S') END,
                                                CASE WHEN sar_apn.authenticity_flag = 1 THEN (SELECT reference_data.name FROM nomenclatures.reference_data WHERE reference_data.domain = 'SAR_APPLICATION_TYPE' AND reference_data.code = 'A') END,
                                                CASE WHEN sar_apn.recommendation_flag = 1 THEN (SELECT reference_data.name FROM nomenclatures.reference_data WHERE reference_data.domain = 'SAR_APPLICATION_TYPE' AND reference_data.code = 'R') END], NULL), ', ') AS array_to_string)
                                                                                 AS sar_flag,
       uny.id                                                                    AS university_id,
       aru.responsible_user                                                      AS responsible_user_name,
       pdt.name                                                                  AS personal_document_type_name,
       apn.service_type                                                          AS service_type_id
FROM common.application apn
         JOIN rudi.rudi_application ran ON ran.apn_id = apn.id
         JOIN nomenclatures.application_subtype ase on ase.code = apn.ase_code
         LEFT JOIN rudi.training_course tce ON tce.apn_id = ran.apn_id
         LEFT JOIN common.person apt ON apt.id = apn.applicant_id
         LEFT JOIN common.person own ON own.id = tce.owner_id
         LEFT JOIN rudi.training_course_universities tcu ON tcu.tce_id = tce.id AND tcu.ord_num = 1
         LEFT JOIN rudi.university uny ON uny.id = tcu.uny_id
         LEFT JOIN nomenclatures.country ucy ON ucy.code = uny.country_code
         LEFT JOIN nomenclatures.reference_data ass ON ass.code = apn.status_code AND ass.domain = 'APPLICATION_STATUS'
         LEFT JOIN nomenclatures.reference_data dss ON dss.code = apn.docflow_status_code AND dss.domain = 'DOCFLOW_STATUS'
         LEFT JOIN rudi.application_recognition_details ard ON ard.apn_id = apn.id
         LEFT JOIN nomenclatures.reference_data rell on rell.domain = 'EDUCATION_LEVEL' and rell.code = ard.recognized_edu_level
         LEFT JOIN nomenclatures.prof_group rpgp ON rpgp.id = ard.recognized_prof_group_id
         LEFT JOIN rudi.sar_application sar_apn ON sar_apn.apn_id = ran.apn_id
         LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL
         LEFT JOIN nomenclatures.reference_data pdt on pdt.domain = 'PERSONAL_DOCUMENT_TYPE' and pdt.code = apn.personal_document_type_code
;
