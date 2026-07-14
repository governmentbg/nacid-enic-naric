--liquibase formatted sql

--changeset mnakova:0049
create view nomenclatures.vw_higher_qualification(higher_qualification) as
SELECT DISTINCT a.professional_qualification
FROM regprof.higher_training_course a
WHERE a.professional_qualification IS NOT NULL;

create view nomenclatures.vw_higher_speciality(higher_speciality) as
SELECT DISTINCT a.higher_speciality
FROM regprof.training_course_specialities a
WHERE a.higher_speciality IS NOT NULL;

create view nomenclatures.vw_sdk_speciality(sdk_speciality) as
SELECT DISTINCT a.sdk_speciality
FROM regprof.training_course_specialities a
WHERE a.sdk_speciality IS NOT NULL;