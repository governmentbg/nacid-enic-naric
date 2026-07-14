--liquibase formatted sql

--changeset mnakova:0136
create view nomenclatures.vw_school_grade(school_grade) as
SELECT DISTINCT a.school_grade
FROM regprof.training_course_qualification_examination a
WHERE a.school_grade IS NOT NULL;

create view nomenclatures.vw_school_type(school_type) as
SELECT DISTINCT a.school_type
FROM regprof.training_course_qualification_examination a
WHERE a.school_type IS NOT NULL;

create view nomenclatures.vw_school_age_range(school_age_range) as
SELECT DISTINCT a.school_age_range
FROM regprof.training_course_qualification_examination a
WHERE a.school_age_range IS NOT NULL;

create view nomenclatures.vw_school_subject(school_subject) as
SELECT DISTINCT a.school_subject
FROM regprof.training_course_qualification_examination a
WHERE a.school_subject IS NOT NULL;