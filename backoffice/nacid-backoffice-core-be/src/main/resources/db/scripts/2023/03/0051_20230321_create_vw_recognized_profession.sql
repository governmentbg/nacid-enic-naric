--liquibase formatted sql

--changeset mnakova:0051
create view nomenclatures.vw_recognized_profession(recognized_profession) as
SELECT DISTINCT a.recognized_profession
FROM regprof.training_course_qualification_examination a
WHERE a.recognized_profession IS NOT NULL;