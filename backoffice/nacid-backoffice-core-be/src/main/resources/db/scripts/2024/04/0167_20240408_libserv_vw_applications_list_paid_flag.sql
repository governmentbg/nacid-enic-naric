--liquibase formatted sql

--changeset ggeorgiev:0167.1
drop view libserv.vw_applications_list;
-- changeset ggeorgiev:0167.2 splitStatements:false
create or replace view libserv.vw_applications_list
            (id, ate_code, ase_code, entry_num, entry_date, efiling_id, applicant_name, apn_status_name, apn_status_code,
             docflow_status_code, docflow_status_name,
             responsible_user_name, applicant_title_before, applicant_title_after, keywords, period_ret_from,
             period_ret_to, br_result_kind_name, br_result_kind_code,
             br_search_type_name, br_search_type_code, subject, inq_inquiry_kind_name, inq_inquiry_kind_code,
             inquiry_aim, period_from, period_to,
             previous_inquiry, on_official_note_kind_name, on_official_note_kind_code, paid_flag)
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
       onk.code             AS on_official_note_kind_code,
       apn.paid_flag
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