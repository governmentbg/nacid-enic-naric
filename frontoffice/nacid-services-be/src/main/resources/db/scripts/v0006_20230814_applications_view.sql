--liquibase formatted sql

--changeset raneva:services_0006

DROP VIEW IF EXISTS services.vw_application;

-- View: services.vw_application

-- DROP VIEW services.vw_application;

CREATE OR REPLACE VIEW services.vw_application
 AS
SELECT a.id,
       a.ate_code,
       a.ase_code,
       a.entry_num,
       a.entry_date,
       a.temp_number,
       a.user_created,
       a.date_created,
       substat.date_created AS last_submission_date,
       a.access_code,
       fostat.status_code AS fo_status_code,
       foref.name AS fo_status_name,
       CASE
           WHEN custat.status_code IS NOT NULL THEN r1.name
           ELSE r2.name
           END AS last_status_name,
       a.signed_flag,
       a.paid_flag,
       CASE
           WHEN drftstat.drafts_count > 1 THEN 1
           ELSE 0
           END AS reverted_flag,
       CASE
           WHEN p.legal_name IS NOT NULL THEN p.legal_name
           WHEN p.first_name IS NOT NULL AND p.second_name IS NOT NULL AND p.last_name IS NOT NULL THEN concat(p.first_name, ' ', p.second_name, ' ', p.last_name)::character varying
            WHEN p.first_name IS NOT NULL AND p.last_name IS NOT NULL THEN concat(p.first_name, ' ', p.last_name)::character varying
            ELSE p.first_name
END AS applicant_name,
        a.external_system_id
   FROM services.application a
     JOIN services.app_status_history custat ON a.id = custat.apn_id
     LEFT JOIN nomenclatures.reference_data r1 ON custat.status_code::text = r1.code::text AND r1.domain::text = 'FO_APP_STATUS'::text
     LEFT JOIN nomenclatures.reference_data r2 ON custat.bo_status_code::text = r2.code::text AND r2.domain::text = 'APPLICATION_STATUS'::text
     LEFT JOIN ( SELECT sh.id,
            sh.apn_id,
            sh.status_code,
            sh.date_created
           FROM services.app_status_history sh
          WHERE (sh.status_code::text = 'SUB'::text OR sh.status_code::text = 'SIG'::text) AND (sh.id IN ( SELECT max(shin.id) AS max
                   FROM services.app_status_history shin
                  WHERE shin.status_code::text = 'SUB'::text OR shin.status_code::text = 'SIG'::text
                  GROUP BY shin.apn_id))) substat ON a.id = substat.apn_id
     JOIN services.app_status_history fostat ON a.id = fostat.apn_id
     JOIN nomenclatures.reference_data foref ON fostat.status_code::text = foref.code::text AND foref.domain::text = 'FO_APP_STATUS'::text
     JOIN ( SELECT count(*) AS drafts_count,
            sh.apn_id
           FROM services.app_status_history sh
          WHERE sh.status_code::text = 'DRFT'::text
          GROUP BY sh.apn_id) drftstat ON a.id = drftstat.apn_id
     JOIN services.person p ON p.id = a.applicant_id
  WHERE custat.id = (( SELECT max(shin2.id) AS max
           FROM services.app_status_history shin2
          WHERE shin2.apn_id = a.id)) AND fostat.id = (( SELECT max(shin3.id) AS max
           FROM services.app_status_history shin3
          WHERE shin3.apn_id = a.id AND shin3.status_code IS NOT NULL))
  ORDER BY a.id;

ALTER TABLE services.vw_application
    OWNER TO postgres;