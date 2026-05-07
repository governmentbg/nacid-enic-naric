--liquibase formatted sql

--changeset ggeorgiev:0088 splitStatements:false
drop view if exists rudi.vw_rudi_registry ;

create or replace view rudi.vw_rudi_registry as
select id, entry_num, entry_date, docflow_status_code, status_code, applicant_name, university_name, university_country_name, nullif(training_institution_name,'') training_institution_name, original_edu_level_name, original_speciality_name, original_qualification_name,
       recognized_speciality_name, recognized_edu_level, recognized_qualification_name, last_commission_calendar_date, legal_reason_name,
       case when status_code = 'WEAK' THEN coalesce(nullif(invalidated_cert_numbers,'')||', ','')||coalesce(decision_docflow_number,'')
            else coalesce(NULLIF(validated_cert_number,''), nullif(invalidated_cert_numbers,''), nullif(decision_docflow_number,''))
           end as decision_number, certificate_uuid
from
    (SELECT apn.id,
            apn.entry_num,
            apn.entry_date,
            apn.docflow_status_code,
            fsh.status_code,
            CASE
                WHEN apn.diff_diploma_names_flag = 1 THEN (adn.first_name || COALESCE(' ' || adn.second_name, '')) || COALESCE(' ' || adn.last_name, '')
                ELSE (owr.first_name || COALESCE(' ' || owr.second_name, '')) || COALESCE(' ' || owr.last_name, '')
                END                                                                                                             AS applicant_name,
            (select array_to_string(array(select uny.bg_name
                                          from rudi.university uny
                                                   join rudi.training_course_universities tcu on tcu.uny_id = uny.id
                                          where tcu.tce_id = tce.id order by tcu.ord_num),','))                                 AS university_name,
            (select array_to_string(array(select distinct name from (select ucy.name
                                                                     from rudi.university uny
                                                                              join rudi.training_course_universities tcu on tcu.uny_id = uny.id
                                                                              join nomenclatures.country ucy on uny.country_code = ucy.code
                                                                     where tcu.tce_id = tce.id order by tcu.ord_num) as _inner),','))                      AS university_country_name,

            (select array_to_string(array(select tin.name from rudi.training_institution tin
                                                                   join rudi.training_location_examination_locations tlel on tlel.training_institution_id = tin.id
                                                                   join rudi.training_location tln on tln.id = tlel.training_location_id and tln.tce_id = tce.id), ', ')) AS training_institution_name,
            tce.original_edu_level_name                                                                                         AS original_edu_level_name,
            ( SELECT array_to_string(ARRAY( SELECT tcs.speciality
                                            FROM rudi.training_course_speciality tcs
                                            WHERE tcs.tce_id = tce.id
                                            ORDER BY tcs.id), ', '))                                                            AS original_speciality_name,
            tce.qualification                                                                                                   AS original_qualification_name,
            ( SELECT array_to_string(ARRAY( SELECT ars.speciality
                                            FROM rudi.application_recognized_speciality ars
                                            WHERE ars.apn_id = apn.id
                                            ORDER BY ars.id), ', '))                                                            AS recognized_speciality_name,
            rell.code                                                                                                           AS recognized_edu_level,
            ard.recognized_qualification                                                                                        AS recognized_qualification_name,
            ccr.session_time                                                                                                    AS last_commission_calendar_date,



            lrn.name                                                                                                            AS legal_reason_name,
            ( SELECT certificate_number
              FROM common.application_certificates ace
              WHERE ace.apn_id = apn.id AND ace.certificate_status = 'P'
              ORDER BY ace.id DESC
              LIMIT 1)                                                                                                          AS validated_cert_number,

            ( SELECT array_to_string(ARRAY( SELECT certificate_number
                                            FROM common.application_certificates ace
                                            WHERE ace.apn_id = apn.id  AND certificate_status = 'I'), ', '))                    AS invalidated_cert_numbers,
            (( SELECT ace.uuid
               FROM common.application_certificates ace
               WHERE ace.apn_id = apn.id
               ORDER BY ace.id DESC
               LIMIT 1))                                                                                                        AS certificate_uuid,
            case when fsh.status_code = 'DEN' then
                     (select att.docflow_id||'/'||to_char(att.docflow_date, 'DD.MM.YYYY')
                      from common.application_attached_docs att
                               join nomenclatures.cfg_doc_type_to_doc_category dtdc on dtdc.dte_id = att.doc_type_id and dtdc.finalization_type = 'F' and dtdc.ate_code = apn.ate_code and (dtdc.ase_code is null or dtdc.ase_code = apn.ase_code)
                      where att.apn_id = apn.id and att.docflow_date is not null and att.docflow_id is not null
                      order by att.id desc limit 1
                     ) end                                                                                                      AS decision_docflow_number

     FROM common.application apn
              join rudi.rudi_application ran on ran.apn_id = apn.id
              join rudi.training_course tce on tce.apn_id = apn.id
              left join rudi.application_recognition_details ard on ard.apn_id = apn.id
              left join nomenclatures.reference_data rell on rell.domain = 'EDUCATION_LEVEL' and rell.code = ard.recognized_edu_level
         --               left join rudi.university uny on uny.id = tce.base_university_id
--               left join nomenclatures.country ucy on ucy.code = uny.country_code
              join common.person owr on owr.id = tce.owner_id
              left join nomenclatures.legal_reason lrn on lrn.id = ran.legal_reason_id
              left join common.applicant_diploma_names adn on adn.apn_id = apn.id
              left join rudi.commission_applications can on can.id = (select can2.id from rudi.commission_applications can2 where can2.apn_id = apn.id order by can2.calendar_id desc limit 1)
              left join rudi.commission_calendar ccr on ccr.id = can.calendar_id
              join common.app_status_history fsh on fsh.id = apn.final_status_history_id
              where apn.ase_code in ('UDI', 'DOC') and apn.docflow_status_code != 'POS' and (apn.status_code in ('ACK', 'WEAK') or (apn.status_code = 'DEN' and apn.entry_date >='2009-04-09'))) a