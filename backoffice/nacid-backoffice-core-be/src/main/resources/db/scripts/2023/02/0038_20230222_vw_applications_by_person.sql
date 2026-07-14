--liquibase formatted sql

--changeset akehayov:0038
create or replace view common.vw_applications_by_person(application_id, efiling_id, date_created, status_code, docflow_status_code, ate_code, ase_code, person_role, person_id) as
SELECT a.id as application_id, a.efiling_id, a.date_created, a.status_code, a.docflow_status_code, a.ate_code, a.ase_code, a.person_role, a.person_id
FROM (SELECT a.id, a.efiling_id, a.date_created, a.status_code, a.docflow_status_code, a.ate_code, a.ase_code, 'APPLICANT' as person_role, p.id as person_id
      FROM common.application a join common.person p on a.applicant_id = p.id
      UNION
      SELECT a.id, a.efiling_id, a.date_created, a.status_code, a.docflow_status_code, a.ate_code, a.ase_code, 'REPRESENTATIVE' as person_role, p.id as person_id
      FROM common.application a join common.person p on a.representative_id = p.id
      UNION
      SELECT a.id, a.efiling_id, a.date_created, a.status_code, a.docflow_status_code, a.ate_code, a.ase_code, 'REPRESENTATIVE_COMPANY' as person_role, p.id as person_id
      FROM common.application a join common.person p on a.representative_company_id = p.id
      UNION
      SELECT a.id, a.efiling_id, a.date_created, a.status_code, a.docflow_status_code, a.ate_code, a.ase_code, 'DIPLOMA_OWNER' as person_role, p.id as person_id
      FROM common.application a
               join rudi.training_course tc on a.id = tc.apn_id
               join common.person p on tc.owner_id = p.id
     ) a;

alter table common.vw_applications_by_person
    owner to postgres;