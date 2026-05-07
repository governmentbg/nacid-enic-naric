--liquibase formatted sql

--changeset akehayov:0100
alter table regprof.regprof_training_experience
    add prof_qualification_modules varchar(255);

create view regprof.vw_prof_qualification_modules(prof_qualification_modules) as
SELECT DISTINCT a.prof_qualification_modules
FROM regprof.regprof_training_experience a
WHERE a.prof_qualification_modules IS NOT NULL;