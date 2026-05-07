--liquibase formatted sql

--changeset ggeorgiev:0085 splitStatements:false
alter table rudi.training_course add column bologna_cycle_id integer;
alter table rudi.training_course add column nqf_id integer;
alter table rudi.training_course add column eqf_id integer;
alter table rudi.training_course add column acc_bologna_cycle_id integer;
alter table rudi.training_course add column acc_nqf_id integer;
alter table rudi.training_course add column acc_eqf_id integer;

alter table rudi.training_course add CONSTRAINT tce_acc_bce_fk FOREIGN KEY (acc_bologna_cycle_id)
        REFERENCES nomenclatures.bologna_cycle (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add CONSTRAINT tce_acc_eqf_fk FOREIGN KEY (acc_eqf_id)
    REFERENCES nomenclatures.european_qualifications_framework (id) MATCH SIMPLE
           ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add CONSTRAINT tce_acc_nqf_fk FOREIGN KEY (acc_nqf_id)
    REFERENCES nomenclatures.national_qualifications_framework (id) MATCH SIMPLE
              ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add CONSTRAINT tce_bce_fk FOREIGN KEY (bologna_cycle_id)
    REFERENCES nomenclatures.bologna_cycle (id) MATCH SIMPLE
                 ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add CONSTRAINT tce_eqf_fk FOREIGN KEY (eqf_id)
    REFERENCES nomenclatures.european_qualifications_framework (id) MATCH SIMPLE
                    ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add CONSTRAINT tce_nqf_fk FOREIGN KEY (nqf_id)
    REFERENCES nomenclatures.national_qualifications_framework (id) MATCH SIMPLE
                       ON UPDATE NO ACTION ON DELETE NO ACTION;
alter table rudi.training_course add column original_edu_level_name varchar(255);
alter table rudi.training_course add column original_edu_level_translated varchar(255);
update rudi.training_course tce set bologna_cycle_id = (select bologna_cycle_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set eqf_id = (select eqf_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set nqf_id = (select nqf_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set acc_bologna_cycle_id = (select acc_bologna_cycle_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set acc_nqf_id = (select acc_nqf_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set acc_eqf_id = (select acc_eqf_id from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set original_edu_level_name = (select original_edu_level_name from rudi.diploma_type dte where dte.tce_id = tce.id);
update rudi.training_course tce set original_edu_level_translated = (select original_edu_level_translated from rudi.diploma_type dte where dte.tce_id = tce.id);

alter table rudi.training_course_universities add column ord_num int not null default 0;
alter table rudi.training_course_universities alter column ord_num drop default;
alter table rudi.training_course_universities add column faculty_id int;

update rudi.training_course_universities tcu set ord_num = (select ord_num from rudi.diploma_type_university dtu join rudi.diploma_type dte on dte.id = dtu.dte_id where dte.tce_id = tcu.tce_id and dtu.uny_id = tcu.uny_id);
update rudi.training_course_universities tcu set faculty_id = (select faculty_id from rudi.diploma_type_university dtu join rudi.diploma_type dte on dte.id = dtu.dte_id where dte.tce_id = tcu.tce_id and dtu.uny_id = tcu.uny_id);

alter table rudi.training_course_universities add CONSTRAINT tcu_faculty_fk FOREIGN KEY (faculty_id)
    REFERENCES rudi.university_faculty (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION;

drop view rudi.vw_applications_list;

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
        , tce.original_edu_level_name AS original_edu_level_name
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


drop table rudi.diploma_type_attached_docs;
drop table rudi.diploma_type_university;
drop table rudi.diploma_type;

alter table rudi.training_course_universities rename column university_name to university_name_translated;