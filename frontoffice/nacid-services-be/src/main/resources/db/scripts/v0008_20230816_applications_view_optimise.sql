--liquibase formatted sql

--changeset raneva:services_0008
create index ash_status_code_idx
    on services.app_status_history (status_code);

create index ash_apn_id_idx
    on services.app_status_history (apn_id);

create index aplication_id_idx
    on services.application (id);

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
       (SELECT dc.date_created FROM services.app_status_history dc WHERE dc.status_code in ('SUB', 'SIG') and dc.apn_id = a.id order by dc.id desc limit 1) AS last_submission_date,
       a.access_code,
       lastfostat.status_code AS fo_status_code,
       lastfostatref.name AS fo_status_name,
       COALESCE(fostat.name, bostat.name) as last_status_name,
       a.signed_flag,
       a.paid_flag,
       CASE WHEN (SELECT count(drf.*) FROM services.app_status_history drf WHERE drf.apn_id = a.id AND drf.status_code = 'DRFT') > 1 THEN 1 ELSE 0 END as reverted_flag,
       CASE
           WHEN p.legal_name IS NOT NULL THEN p.legal_name
           WHEN p.first_name IS NOT NULL AND p.second_name IS NOT NULL AND p.last_name IS NOT NULL THEN concat(p.first_name, ' ', p.second_name, ' ', p.last_name)::character varying
           WHEN p.first_name IS NOT NULL AND p.last_name IS NOT NULL THEN concat(p.first_name, ' ', p.last_name)::character varying
           ELSE p.first_name
       END AS applicant_name,
       a.external_system_id
FROM services.application a
         JOIN services.app_status_history custat ON a.id = custat.apn_id and custat.id = (select sh.id FROM services.app_status_history sh where sh.apn_id = a.id ORDER BY sh.id DESC LIMIT 1)
         LEFT JOIN nomenclatures.reference_data fostat ON custat.status_code = fostat.code AND fostat.domain = 'FO_APP_STATUS'
         LEFT JOIN nomenclatures.reference_data bostat ON custat.bo_status_code = bostat.code AND bostat.domain = 'APPLICATION_STATUS'
         JOIN services.app_status_history lastfostat ON a.id = lastfostat.apn_id and lastfostat.id = (select sh.id from services.app_status_history sh where sh.apn_id = a.id and sh.status_code is not null ORDER BY sh.id DESC LIMIT 1)
         JOIN nomenclatures.reference_data lastfostatref ON lastfostat.status_code = lastfostatref.code AND lastfostatref.domain = 'FO_APP_STATUS'
         JOIN services.person p ON p.id = a.applicant_id
ORDER BY a.id;

ALTER TABLE services.vw_application
    OWNER TO postgres;
