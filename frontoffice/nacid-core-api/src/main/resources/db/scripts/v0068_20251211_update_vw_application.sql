--liquibase formatted sql

--changeset ndimov:core_0068
drop view services.vw_application;

create view services.vw_application
            (id, ate_code, ase_code, entry_num, entry_date, temp_number, user_created, date_created,
             last_submission_date, access_code, fo_status_code, fo_status_name, last_status_name, signed_flag,
             paid_flag, reverted_flag, applicant_name, applicant_civil_id, representative_name, external_system_id,
             notes_count, service_type_id, se_original_document_waiting_flag)
as
SELECT a.id,
       a.ate_code,
       a.ase_code,
       a.entry_num,
       a.entry_date,
       a.temp_number,
       a.user_created,
       a.date_created,
       (SELECT dc.date_created
        FROM services.app_status_history dc
        WHERE (dc.status_code::text = ANY (ARRAY ['SUB'::character varying::text, 'SIG'::character varying::text]))
          AND dc.apn_id = a.id
        ORDER BY dc.id DESC
           LIMIT 1)                          AS last_submission_date,
       a.access_code,
       lastfostat.status_code             AS fo_status_code,
       lastfostatref.name                 AS fo_status_name,
       COALESCE(fostat.name, bostat.name) AS last_status_name,
       a.signed_flag,
       a.paid_flag,
       CASE
           WHEN lastfostat.status_code::text = 'CORR'::text THEN 1
           ELSE 0
END                            AS reverted_flag,
       CASE
           WHEN p.legal_name IS NOT NULL THEN p.legal_name
           WHEN p.first_name IS NOT NULL AND p.second_name IS NOT NULL AND p.last_name IS NOT NULL
               THEN concat(p.first_name, ' ', p.second_name, ' ', p.last_name)::character varying
           WHEN p.first_name IS NOT NULL AND p.last_name IS NOT NULL
               THEN concat(p.first_name, ' ', p.last_name)::character varying
           ELSE p.first_name
END                            AS applicant_name,
       p.civil_id                         AS applicant_civil_id,
       CASE
           WHEN rep.legal_name IS NOT NULL THEN rep.legal_name
           WHEN rep.first_name IS NOT NULL AND rep.second_name IS NOT NULL AND rep.last_name IS NOT NULL
               THEN concat(rep.first_name, ' ', rep.second_name, ' ', rep.last_name)::character varying
           WHEN rep.first_name IS NOT NULL AND rep.last_name IS NOT NULL
               THEN concat(rep.first_name, ' ', rep.last_name)::character varying
           ELSE rep.first_name
END                            AS representative_name,
       a.external_system_id,
       (SELECT count(*) AS count
        FROM services.application_notes apnotes
        WHERE apnotes.apn_id = a.id)      AS notes_count,
       a.service_type_code                AS service_type_id,
       sra.original_document_waiting      AS se_original_document_waiting_flag
FROM services.application a
         JOIN services.app_status_history custat ON a.id = custat.apn_id AND custat.id = ((SELECT sh.id
                                                                                           FROM services.app_status_history sh
                                                                                           WHERE sh.apn_id = a.id
                                                                                           ORDER BY sh.id DESC
                                                                                           LIMIT 1))
         LEFT JOIN nomenclatures.reference_data fostat
                   ON custat.status_code::text = fostat.code::text AND fostat.domain::text = 'FO_APP_STATUS'::text
         LEFT JOIN nomenclatures.reference_data bostat
                   ON custat.bo_status_code::text = bostat.code::text AND bostat.domain::text = 'APPLICATION_STATUS'::text
         JOIN services.app_status_history lastfostat ON a.id = lastfostat.apn_id AND lastfostat.id = ((SELECT sh.id
                                                                                                       FROM services.app_status_history sh
                                                                                                       WHERE sh.apn_id = a.id
                                                                                                         AND sh.status_code IS NOT NULL
                                                                                                       ORDER BY sh.id
                                                                                                           DESC
                                                                                                       LIMIT 1))
         JOIN nomenclatures.reference_data lastfostatref ON lastfostat.status_code::text = lastfostatref.code::text AND
                                                            lastfostatref.domain::text = 'FO_APP_STATUS'::text
         JOIN services.person p ON p.id = a.applicant_id
         LEFT JOIN services.se_recognition_application sra ON sra.apn_id = a.id
         LEFT JOIN services.person rep ON rep.id = a.representative_id
ORDER BY a.id;


