--liquibase formatted sql

--changeset veizov:0116
alter table regprof.regprof_application
    add column application_prof_qualification varchar(255);

UPDATE regprof.regprof_application a
SET application_prof_qualification = (SELECT t.certificate_prof_qualification
                                      FROM regprof.regprof_training_experience t
                                      where t.apn_id = a.apn_id)
WHERE 1 = 1;

create view regprof.vw_application_prof_qualification(application_prof_qualification) as
SELECT DISTINCT a.application_prof_qualification
FROM regprof.regprof_application a
WHERE a.application_prof_qualification IS NOT NULL;