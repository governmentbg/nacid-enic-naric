--liquibase formatted sql

--changeset mnakova:0128
drop view regprof.vw_applications_list;

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