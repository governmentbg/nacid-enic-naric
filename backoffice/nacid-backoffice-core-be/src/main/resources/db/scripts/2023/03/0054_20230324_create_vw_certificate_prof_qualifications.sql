--liquibase formatted sql

--changeset akehayov:0053
create view regprof.vw_certificate_prof_qualification(certificate_prof_qualification) as
SELECT DISTINCT a.certificate_prof_qualification
FROM regprof.regprof_training_experience a
WHERE a.certificate_prof_qualification IS NOT NULL;