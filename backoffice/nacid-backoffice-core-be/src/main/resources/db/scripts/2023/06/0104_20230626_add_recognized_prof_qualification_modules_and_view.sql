--liquibase formatted sql

--changeset akehayov:0104
alter table regprof.training_course_qualification_examination
    add recognized_prof_qualification_modules varchar(255);

create
or replace view regprof.vw_recognized_prof_qualification_modules(recognized_prof_qualification_modules, recognized_profession) as
SELECT DISTINCT a.recognized_prof_qualification_modules, a.recognized_profession
FROM regprof.training_course_qualification_examination a
WHERE a.recognized_prof_qualification_modules IS NOT NULL
  and a.recognized_profession IS NOT NULL;