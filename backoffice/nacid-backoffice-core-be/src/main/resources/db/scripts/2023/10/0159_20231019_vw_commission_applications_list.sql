--liquibase formatted sql

--changeset murlev:0159
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
       ca.generated_final_doc,
       ca.abdocs_transferred
FROM rudi.vw_applications_list val
         JOIN rudi.commission_applications ca ON ca.apn_id = val.id;

alter table rudi.vw_commission_applications_list
    owner to postgres;
