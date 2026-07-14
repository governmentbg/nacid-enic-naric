--liquibase formatted sql

--changeset ggeorgiev:0135.1 splitStatements:false
CREATE USER regix WITH PASSWORD 'regix';
do
$$
    begin
        execute format('grant connect on database %I to %I', current_database(), 'regix');
    end;
$$;
GRANT USAGE ON SCHEMA rudi TO regix;
ALTER ROLE regix SET search_path TO rudi;

--changeset ggeorgiev:0135.2 splitStatements:false
DROP VIEW IF EXISTS rudi.vw_regix_applications ;
CREATE OR REPLACE VIEW rudi.vw_regix_applications AS
SELECT apn.entry_num as app_num,
       apn.entry_date as app_date,
       COALESCE(ass.name, 'В процес на обработка'::character varying) AS application_recognition_status,
       lrn.name AS legal_reason,
       CASE
           WHEN adn.apn_id is not null THEN adn.first_name
           ELSE pen.first_name
           END AS fname,
       CASE
           WHEN adn.apn_id is not null THEN adn.second_name
           ELSE pen.second_name
           END AS sname,
       CASE
           WHEN adn.apn_id is not null THEN adn.last_name
           ELSE pen.last_name
           END AS lname,
       pen.civil_id,
       CASE pen.civil_id_type
           WHEN 'EGN' THEN 1
           WHEN 'LNC' THEN 2
           WHEN 'DOC' THEN 3
           END as civil_id_type,
       rell.name AS recognized_edu_level,
       ard.recognized_qualification AS recognized_qualification_name,
       uny.org_name AS university_original_name,
       uny.bg_name AS university_bg_name,
       unyc.name AS university_country_name,
       unya.address AS university_address_details,
       coalesce(unys.name, unya.city_name) AS university_city,
       ARRAY( SELECT ace.certificate_number
              FROM common.application_certificates ace
              WHERE ace.apn_id = apn.id and ace.certificate_status != 'D'
              ORDER BY ace.id DESC) AS certificate_numbers,
       ARRAY( SELECT rsp.speciality
              FROM rudi.application_recognized_speciality rsp
              WHERE rsp.apn_id = apn.id) AS recognized_specialities
FROM common.application apn
         join rudi.rudi_application ran on ran.apn_id = apn.id
         JOIN rudi.training_course tce ON tce.apn_id = apn.id
         JOIN common.person pen ON pen.id = tce.owner_id
         LEFT JOIN common.applicant_diploma_names adn on adn.apn_id = apn.id
         LEFT join rudi.application_recognition_details ard on ard.apn_id = apn.id
         LEFT JOIN nomenclatures.reference_data rell on rell.domain = 'EDUCATION_LEVEL' and rell.code = ard.recognized_edu_level
         JOIN rudi.training_course_universities buny ON buny.tce_id = tce.id AND buny.ord_num = 1
         JOIN rudi.university uny ON uny.id = buny.uny_id

         JOIN nomenclatures.country unyc ON unyc.code = uny.country_code
         LEFT JOIN common.address unya on unya.id = uny.address_id
         LEFT JOIN nomenclatures.ek_settlement unys on unys.code = unya.set_code
         JOIN common.app_status_history shy ON shy.id = apn.final_status_history_id
         LEFT JOIN nomenclatures.reference_data ass ON ass.domain = 'APPLICATION_STATUS' and ass.code = shy.status_code
         LEFT JOIN nomenclatures.legal_reason lrn ON lrn.id = shy.legal_reason_id

WHERE apn.ase_code = 'UDI' AND apn.docflow_status_code <> 'POS' AND (shy.status_code in ('WEAK', 'DEN', 'ACK')) AND
    CASE
        WHEN shy.status_code = 'DEN' THEN apn.entry_date >= '2009-04-01'::date
        ELSE 1 = 1
        END;

GRANT SELECT ON TABLE rudi.vw_regix_applications TO regix;