--liquibase formatted sql

--changeset ggeorgiev:0084 splitStatements:false
alter table rudi.diploma_type add column original_edu_level_name varchar(255);
alter table rudi.diploma_type add column original_edu_level_translated varchar(255);
update rudi.diploma_type dte set original_edu_level_name = (select name from nomenclatures.original_edu_level oell where oell.id = dte.original_edu_level);
update rudi.diploma_type dte set original_edu_level_translated = (select name_translated from nomenclatures.original_edu_level oell where oell.id = dte.original_edu_level);
drop view rudi.vw_applications_list;
alter table rudi.diploma_type drop column original_edu_level;
drop table nomenclatures.original_edu_level;
alter table rudi.diploma_type drop column edu_level;
alter table rudi.diploma_type add column tce_id int;
update rudi.diploma_type dte set tce_id = (select id from rudi.training_course tce where tce.diploma_type_id = dte.id limit 1);
delete from rudi.diploma_type_university where dte_id in (select id from rudi.diploma_type where tce_id is null);
delete from rudi.diploma_type where tce_id is null;
alter table rudi.diploma_type alter column tce_id set not null;
alter table rudi.training_course drop column diploma_type_id;

alter table rudi.diploma_type add CONSTRAINT dte_tce_fk FOREIGN KEY (tce_id)
    REFERENCES rudi.training_course (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION;
create unique index dte_uk
    on rudi.diploma_type (tce_id);

create
    or replace view rudi.vw_applications_list
            (id, ate_code, ase_code, entry_num, entry_date, applicant_name, university_name, university_country_name,
             speciality_name, apn_status_name, apn_status_code, docflow_status_code, docflow_status_name,
             commission_sessions, experts_count, experts_processed_status, recognized_prof_group_name,
             recognized_qualification, original_edu_level_name, sar_flag, university_id, responsible_user_name)
as
SELECT apn.id,
       apn.ate_code,
       apn.ase_code,
       apn.entry_num,
       apn.entry_date,
       CASE
           WHEN apt.legal_type::text = 'LE'::text THEN apt.legal_name::text
           ELSE (apt.first_name::text || COALESCE(' '::text || apt.second_name::text, ''::text)) ||
                COALESCE(' '::text || apt.last_name::text, ''::text)
           END                                                 AS applicant_name,
       uny.bg_name                                             AS university_name,
       ucy.name                                                AS university_country_name,
       (SELECT array_to_string(ARRAY(SELECT tcs.speciality
                                     FROM rudi.training_course_speciality tcs
                                     WHERE tcs.tce_id = tce.id
                                     ORDER BY tcs.id),
                               ', '::text) AS array_to_string) AS speciality_name,
       ass.name                                                AS apn_status_name,
       ass.code                                                AS apn_status_code,
       apn.docflow_status_code,
       dss.name                                                AS docflow_status_name,
       (SELECT array_to_string(ARRAY(SELECT ccr.session_num::text AS session_num
                                     FROM rudi.commission_applications can
                                              JOIN rudi.commission_calendar ccr ON ccr.id = can.calendar_id
                                     WHERE can.apn_id = ran.apn_id),
                               ', '::text) AS array_to_string) AS commission_sessions,
       (SELECT count(*) ::integer AS count
        FROM rudi.application_commission_members acm
        WHERE acm.apn_id = ran.apn_id) AS experts_count
        , COALESCE ((SELECT 0
                     FROM rudi.application_commission_members acm
                     WHERE acm.apn_id = ran.apn_id
                       AND acm.process_status = 0
                     LIMIT 1)
           , 1) AS experts_processed_status
        , rpgp.name AS recognized_prof_group_name
        , ard.recognized_qualification
        , dte.original_edu_level_name AS original_edu_level_name
        , (
           SELECT array_to_string(array_remove(ARRAY [
                                                   CASE
                                                       WHEN sar_apn.statute_flag = 1 THEN (SELECT reference_data.name
                                                                                           FROM nomenclatures.reference_data
                                                                                           WHERE reference_data.domain::text = 'SAR_APPLICATION_TYPE'::text
                                                                                             AND reference_data.code::text = 'S'::text)
                                                       ELSE NULL :: character varying
                                                       END, CASE
                                                                WHEN sar_apn.authenticity_flag = 1 THEN (SELECT reference_data.name
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
                   END], NULL :: character varying), ', '::text) AS array_to_string) AS sar_flag, uny.id AS university_id, aru.responsible_user AS responsible_user_name
FROM common.application apn
         JOIN rudi.rudi_application ran
              ON ran.apn_id = apn.id
         LEFT JOIN rudi.training_course tce ON tce.apn_id = ran.apn_id
         LEFT JOIN common.person apt ON apt.id = apn.applicant_id
         LEFT JOIN rudi.diploma_type dte ON dte.tce_id = tce.id
         LEFT JOIN rudi.diploma_type_university dtu ON dtu.dte_id = dte.id AND dtu.ord_num = 1
         LEFT JOIN rudi.university uny ON uny.id = dtu.uny_id
         LEFT JOIN nomenclatures.country ucy ON ucy.code::text = uny.country_code::text
         LEFT JOIN nomenclatures.reference_data ass
                   ON ass.code::text = apn.status_code::text AND ass.domain::text = 'APPLICATION_STATUS'::text
         LEFT JOIN nomenclatures.reference_data dss
                   ON dss.code::text = apn.docflow_status_code::text AND dss.domain::text = 'DOCFLOW_STATUS'::text
         LEFT JOIN rudi.application_recognition_details ard ON ard.apn_id = apn.id
         LEFT JOIN nomenclatures.prof_group rpgp ON rpgp.id = ard.recognized_prof_group_id
         LEFT JOIN rudi.sar_application sar_apn ON sar_apn.apn_id = ran.apn_id
         LEFT JOIN common.application_responsible_users aru ON apn.id = aru.apn_id AND aru.date_to IS NULL;
