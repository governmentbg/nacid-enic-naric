--liquibase formatted sql

--changeset murlev:0138.1
drop view rudi.vw_applications_list;

--changeset murlev:0138.2
drop view if exists libserv.vw_applications_list;

--changeset murlev:0138.3
drop view regprof.vw_applications_list;

--changeset murlev:0138.4
ALTER TABLE common.application_responsible_users ALTER COLUMN date_from TYPE timestamp;

--changeset murlev:0138.5
ALTER TABLE common.application_responsible_users ALTER COLUMN date_to TYPE timestamp;

-- changeset murlev:0138.6 splitStatements:false
create or replace view libserv.vw_applications_list
            (id, ate_code, ase_code, entry_num, entry_date, efiling_id, applicant_name, apn_status_name, apn_status_code,
             docflow_status_code, docflow_status_name,
             responsible_user_name, applicant_title_before, applicant_title_after, keywords, period_ret_from,
             period_ret_to, br_result_kind_name, br_result_kind_code,
             br_search_type_name, br_search_type_code, subject, inq_inquiry_kind_name, inq_inquiry_kind_code,
             inquiry_aim, period_from, period_to,
             previous_inquiry, on_official_note_kind_name, on_official_note_kind_code)
as
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       apn.entry_num,
       apn.entry_date,
       apn.efiling_id,
       CASE
           WHEN apt.legal_type::text = 'LE'::text THEN apt.legal_name::text
           ELSE (apt.first_name::text || COALESCE(' '::text || apt.second_name::text, ''::text)) ||
                COALESCE(' '::text || apt.last_name::text, ''::text)
           END              AS applicant_name,
       ass.name             AS apn_status_name,
       ass.code             AS apn_status_code,
       apn.docflow_status_code,
       dss.name             AS docflow_status_name,
       aru.responsible_user AS responsible_user_name,
       lan.applicant_title_before,
       lan.applicant_title_after,
       br_apn.keywords,
       br_apn.period_ret_from,
       br_apn.period_ret_to,
       rk.name              AS br_result_kind_name,
       rk.code              AS br_result_kind_code,
       st.name              AS br_search_type_name,
       st.code              AS br_search_type_code,
       br_apn.subject,
       ik.name              AS inq_inquiry_kind_name,
       ik.code              AS inq_inquiry_kind_code,
       inq_apn.inquiry_aim,
       inq_apn.period_from,
       inq_apn.period_to,
       inq_apn.previous_inquiry,
       onk.name             AS on_official_note_kind_name,
       onk.code             AS on_official_note_kind_code
FROM common.application apn
         JOIN libserv.libserv_application lan
              ON lan.apn_id = apn.id
         LEFT JOIN common.person apt ON apt.id = apn.applicant_id
         LEFT JOIN nomenclatures.reference_data ass
                   ON ass.code::text = apn.status_code::text AND ass.domain::text = 'APPLICATION_STATUS'::text
         LEFT JOIN nomenclatures.reference_data dss
ON dss.code::text = apn.docflow_status_code::text AND dss.domain::text = 'DOCFLOW_STATUS'::text
    LEFT JOIN libserv.bibliographic_reference br_apn ON br_apn.apn_id = lan.apn_id
    LEFT JOIN libserv.document_delivery dd_apn ON dd_apn.apn_id = lan.apn_id
    LEFT JOIN libserv.inquiry inq_apn ON inq_apn.apn_id = lan.apn_id
    LEFT JOIN libserv.official_note on_apn ON on_apn.apn_id = lan.apn_id
    LEFT JOIN nomenclatures.reference_data rk
    ON rk.code::text = br_apn.result_kind::text AND rk.domain::text = 'BIBLIOGRAPHIC_REF_RESULT_KIND'::text
    LEFT JOIN nomenclatures.reference_data st
    ON st.code::text = br_apn.search_type::text AND st.domain::text = 'BIBLIOGRAPHIC_REF_SEARCH_TYPE'::text
    LEFT JOIN nomenclatures.reference_data ik
    ON ik.code::text = inq_apn.ink_code::text AND ik.domain::text = 'INQUIRY_KIND'::text
    LEFT JOIN nomenclatures.reference_data onk
    ON onk.code::text = on_apn.onk_code::text AND onk.domain::text = 'OFFICIAL_NOTE_KIND'::text
    LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL;


-- changeset murlev:0138.7 splitStatements:false
create
or replace view regprof.vw_applications_list
            (id, ate_code, ase_code, entry_num, entry_date, efiling_id, external_system_id, applicant_name, apn_status_name, apn_status_code,
             docflow_status_code, docflow_status_name, responsible_user_name, end_date, imi, service_type_id)
as
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       apn.entry_num,
       apn.entry_date,
       apn.efiling_id,
       apn.external_system_id,
       CASE
           WHEN apt.legal_type::text = 'LE'::text THEN apt.legal_name::text
           ELSE (apt.first_name::text || COALESCE(' '::text || apt.second_name::text, ''::text)) ||
                COALESCE(' '::text || apt.last_name::text, ''::text)
           END              AS applicant_name,
       ass.name             AS apn_status_name,
       ass.code             AS apn_status_code,
       apn.docflow_status_code,
       dss.name             AS docflow_status_name,
       aru.responsible_user AS responsible_user_name,
       ran.end_date,
       CASE
           WHEN ran.imi_correspondence IS NULL THEN false
           ELSE true
           END              AS imi,
       apn.service_type     AS service_type_id
FROM common.application apn
         JOIN regprof.regprof_application ran ON ran.apn_id = apn.id
         LEFT JOIN common.person apt ON apt.id = apn.applicant_id
         LEFT JOIN nomenclatures.reference_data ass
                   ON ass.code::text = apn.status_code::text AND ass.domain::text = 'APPLICATION_STATUS'::text
         LEFT JOIN nomenclatures.reference_data dss
ON dss.code::text = apn.docflow_status_code::text AND dss.domain::text = 'DOCFLOW_STATUS'::text
    LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL;


-- changeset murlev:0138.8 splitStatements:false
create
or replace view rudi.vw_applications_list
            (id, ate_code, ase_code, entry_num, entry_date, efiling_id, applicant_name, diploma_owner_name, university_name, university_country_name,
             speciality_name, apn_status_name, apn_status_code, docflow_status_code, docflow_status_name,
             commission_sessions, experts_count, experts_processed_status, recognized_prof_group_name,
             recognized_qualification, original_edu_level_name, sar_flag, university_id, responsible_user_name, service_type_id)
as
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       apn.entry_num,
       apn.entry_date,
       apn.efiling_id,
       CASE
           WHEN apt.legal_type::text = 'LE'::text THEN apt.legal_name::text
           ELSE (apt.first_name::text || COALESCE(' '::text || apt.second_name::text, ''::text)) ||
                COALESCE(' '::text || apt.last_name::text, ''::text)
           END                                                                   AS applicant_name,
       (own.first_name::text || COALESCE(' '::text || own.second_name::text, ''::text)) ||
       COALESCE(' '::text || own.last_name::text, ''::text)                      AS diploma_owner_name,
       uny.bg_name                                                               AS university_name,
       ucy.name                                                                  AS university_country_name,
       (SELECT array_to_string(ARRAY(SELECT tcs.speciality
                                     FROM rudi.training_course_speciality tcs
                                     WHERE tcs.tce_id = tce.id
                                     ORDER BY tcs.id),
                               ', '::text) AS array_to_string)                   AS speciality_name,
       ass.name                                                                  AS apn_status_name,
       ass.code                                                                  AS apn_status_code,
       apn.docflow_status_code,
       dss.name                                                                  AS docflow_status_name,
       (SELECT array_to_string(ARRAY(SELECT ccr.session_num::text AS session_num
                                     FROM rudi.commission_applications can
                                              JOIN rudi.commission_calendar ccr ON ccr.id = can.calendar_id
                                     WHERE can.apn_id = ran.apn_id),
                               ', '::text) AS array_to_string)                   AS commission_sessions,
       (SELECT count(*) ::integer AS count
FROM rudi.application_commission_members acm
WHERE acm.apn_id = ran.apn_id)                                           AS experts_count
    ,
    COALESCE((SELECT 0
    FROM rudi.application_commission_members acm
    WHERE acm.apn_id = ran.apn_id
  AND acm.process_status = 0
    LIMIT 1)
    , 1)                                                                  AS experts_processed_status
    ,
    rpgp.name                                                                 AS recognized_prof_group_name
    ,
    ard.recognized_qualification
    ,
    tce.original_edu_level_name                                               AS original_edu_level_name
    ,
    (SELECT array_to_string(array_remove(ARRAY [
    CASE
    WHEN sar_apn.statute_flag = 1 THEN (SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text
    AND reference_data.code::text = 'S'::text)
    ELSE NULL :: character varying
    END, CASE
    WHEN sar_apn.authenticity_flag = 1
    THEN (SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text
    AND reference_data.code::text = 'A'::text)
    ELSE NULL :: character varying
    END, CASE
    WHEN sar_apn.recommendation_flag = 1
    THEN (SELECT reference_data.name
    FROM nomenclatures.reference_data
    WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text
    AND reference_data.code::text = 'R'::text)
    ELSE NULL :: character varying
    END], NULL :: character varying), ', '::text) AS array_to_string) AS sar_flag,
    uny.id                                                                    AS university_id,
    aru.responsible_user                                                      AS responsible_user_name,
    apn.service_type                                                          AS service_type_id
FROM common.application apn
    JOIN rudi.rudi_application ran
ON ran.apn_id = apn.id
    LEFT JOIN rudi.training_course tce ON tce.apn_id = ran.apn_id
    LEFT JOIN common.person apt ON apt.id = apn.applicant_id
    LEFT JOIN common.person own ON own.id = tce.owner_id
    LEFT JOIN rudi.training_course_universities tcu ON tcu.tce_id = tce.id AND tcu.ord_num = 1
    LEFT JOIN rudi.university uny ON uny.id = tcu.uny_id
    LEFT JOIN nomenclatures.country ucy ON ucy.code::text = uny.country_code::text
    LEFT JOIN nomenclatures.reference_data ass
    ON ass.code::text = apn.status_code::text AND ass.domain::text = 'APPLICATION_STATUS'::text
    LEFT JOIN nomenclatures.reference_data dss
    ON dss.code::text = apn.docflow_status_code::text AND dss.domain::text = 'DOCFLOW_STATUS'::text
    LEFT JOIN rudi.application_recognition_details ard ON ard.apn_id = apn.id
    LEFT JOIN nomenclatures.prof_group rpgp ON rpgp.id = ard.recognized_prof_group_id
    LEFT JOIN rudi.sar_application sar_apn ON sar_apn.apn_id = ran.apn_id
    LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL;




