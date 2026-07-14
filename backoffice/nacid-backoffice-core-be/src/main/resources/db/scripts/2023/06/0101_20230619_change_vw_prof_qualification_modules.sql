--liquibase formatted sql

--changeset akehayov:0101
drop view regprof.vw_prof_qualification_modules;

alter table regprof.regprof_training_experience
drop column prof_qualification_modules;

alter table regprof.higher_training_course
    add prof_qualification_modules varchar(255);

alter table regprof.postgraduate_training_course
    add prof_qualification_modules varchar(255);

alter table regprof.secondary_training_course
    add prof_qualification_modules varchar(255);

create or replace view regprof.vw_prof_qualification_modules(prof_qualification_modules, professional_qualification) as
SELECT DISTINCT a.prof_qualification_modules, a.professional_qualification
FROM (SELECT DISTINCT a.prof_qualification_modules, a.professional_qualification
      FROM regprof.higher_training_course a
      UNION
      SELECT DISTINCT a.prof_qualification_modules, a.professional_qualification
      FROM regprof.postgraduate_training_course a
      UNION
      SELECT DISTINCT a.prof_qualification_modules, a.professional_qualification_id::text as professional_qualification
      FROM regprof.secondary_training_course a
     ) a;