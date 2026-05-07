--liquibase formatted sql

--changeset ggeorgiev:0072
drop view nomenclatures.vw_recognized_profession;

alter table regprof.training_course_qualification_examination alter column recognized_profession type  text;

create view nomenclatures.vw_recognized_profession(recognized_profession) as
SELECT DISTINCT a.recognized_profession
FROM regprof.training_course_qualification_examination a
WHERE a.recognized_profession IS NOT NULL;