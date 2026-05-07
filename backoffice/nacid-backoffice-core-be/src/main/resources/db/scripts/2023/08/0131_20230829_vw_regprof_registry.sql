--liquibase formatted sql

--changeset ggeorgiev:0131 splitStatements:false
drop view if exists regprof.vw_regprof_registry;
CREATE OR REPLACE VIEW regprof.vw_regprof_registry AS
select a.id, a.entry_num, a.entry_date, a.status_code, a.docflow_status_code, a.applicant_name, a.application_country_name, a.recognized_profession_name,
       case when status_code = 'WEAK' THEN coalesce(nullif(invalidated_cert_numbers,'')||(case when nullif(denial_decision_docflow_number,'') is null then '' else ', ' end),'')||coalesce(nullif(denial_decision_docflow_number,''),'')
            else coalesce(NULLIF(validated_cert_number,''), nullif(invalidated_cert_numbers,''), nullif(denial_decision_docflow_number,''))
           end as decision_number, certificate_uuid from (
                                                             SELECT DISTINCT apn.id,
                                                                             apn.entry_num,
                                                                             apn.entry_date,
                                                                             fsh.status_code,
                                                                             apn.docflow_status_code,
                                                                             CASE
                                                                                 WHEN apn.diff_diploma_names_flag = 1 THEN (adn.first_name || COALESCE(' ' || adn.second_name, '')) || COALESCE(' ' || adn.last_name, '')
                                                                                 ELSE (apt.first_name || COALESCE(' ' || apt.second_name, '')) || COALESCE(' ' || apt.last_name, '')
                                                                                 END                                                                                                                                         AS applicant_name,
                                                                             acy.name                                                                                                                                        AS application_country_name,
                                                                             ( SELECT certificate_number
                                                                               FROM common.application_certificates ace
                                                                               WHERE ace.apn_id = apn.id AND ace.certificate_status = 'P'
                                                                               ORDER BY ace.id DESC
                                                                               LIMIT 1)                                                                                                                                      AS validated_cert_number,

                                                                             ( SELECT array_to_string(ARRAY( SELECT certificate_number
                                                                                                             FROM common.application_certificates ace
                                                                                                             WHERE ace.apn_id = apn.id  AND certificate_status = 'W'), ', '))                                                AS invalidated_cert_numbers,
                                                                             COALESCE(tcqe.recognized_profession, pee.profession_name)                                                                                       AS recognized_profession_name,
                                                                             (( SELECT ace.uuid
                                                                                FROM common.application_certificates ace
                                                                                WHERE ace.apn_id = apn.id and ace.certificate_status = 'P'
                                                                                ORDER BY ace.id DESC
                                                                                LIMIT 1))::uuid                                                                                                                              AS certificate_uuid,
                                                                             case when fsh.status_code = 'DEN' then
                                                                                      (select att.registration_number||'/'||to_char(att.registration_date, 'DD.MM.YYYY')
                                                                                       from common.application_attached_docs att
                                                                                                join nomenclatures.cfg_doc_type_to_doc_category dtdc on dtdc.dte_id = att.doc_type_id and dtdc.finalization_type = 'F' and dtdc.ate_code = apn.ate_code and (dtdc.ase_code is null or dtdc.ase_code = apn.ase_code)
                                                                                       where att.apn_id = apn.id and att.registration_date is not null and att.registration_number is not null
                                                                                       order by att.id desc limit 1
                                                                                      ) end                                                                                                                                 AS denial_decision_docflow_number
                                                             FROM  common.application apn
                                                                       join regprof.regprof_application ran on ran.apn_id = apn.id
                                                                       left join common.applicant_diploma_names adn on adn.apn_id = apn.id
                                                                       JOIN common.app_status_history fsh ON fsh.id = apn.final_status_history_id
                                                                       join regprof.regprof_training_experience rte on rte.apn_id = apn.id
                                                                       JOIN regprof.training_course tce ON tce.rte_id = rte.id
                                                                       JOIN common.person apt ON apt.id = apn.applicant_id
                                                                       LEFT JOIN regprof.profession_experience pee ON pee.rte_id = rte.id
                                                                       LEFT JOIN regprof.training_course_qualification_examination tcqe ON tcqe.rte_id = rte.id
                                                                       LEFT JOIN nomenclatures.country acy ON acy.code = ran.application_country) as a
where a.docflow_status_code != 'POS' and (a.status_code in ('CE', 'CQ', 'CQE') or a.status_code = 'WEAK' or a.status_code = 'DEN');