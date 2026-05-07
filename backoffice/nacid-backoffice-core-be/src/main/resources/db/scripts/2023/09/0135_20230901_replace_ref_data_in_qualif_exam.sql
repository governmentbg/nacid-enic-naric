--liquibase formatted sql

--changeset mnakova:0135.1
alter table regprof.training_course_qualification_examination add column school_grade_txt varchar(255);
alter table regprof.training_course_qualification_examination add column school_type_txt varchar(255);
alter table regprof.training_course_qualification_examination add column school_age_range_txt varchar(255);
alter table regprof.training_course_qualification_examination add column school_subject_txt varchar(255);

--changeset mnakova:0135.2
UPDATE regprof.training_course_qualification_examination e
SET school_grade_txt = (
    SELECT r.name
    FROM nomenclatures.reference_data r
    WHERE r.code = e.school_grade and r.domain='SCHOOL_GRADE'
) where e.school_grade is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_type_txt = (
    SELECT r.name
    FROM nomenclatures.reference_data r
    WHERE r.code = e.school_type and r.domain='SCHOOL_TYPE'
) where e.school_type is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_age_range_txt = (
    SELECT r.name
    FROM nomenclatures.reference_data r
    WHERE r.code = e.school_age_range and r.domain='SCHOOL_AGE_RANGE'
) where e.school_age_range is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_subject_txt = (
    SELECT r.name
    FROM nomenclatures.reference_data r
    WHERE r.code = e.school_subject and r.domain='SCHOOL_SUBJECT'
) where e.school_subject is not null;

--changeset mnakova:0135.3
UPDATE regprof.training_course_qualification_examination e
SET school_grade = null where school_grade is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_type = null where school_type is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_age_range = null where school_age_range is not null;

UPDATE regprof.training_course_qualification_examination e
SET school_subject = null where school_subject is not null;

--changeset mnakova:0135.4
ALTER TABLE regprof.training_course_qualification_examination
DROP COLUMN school_grade;

ALTER TABLE regprof.training_course_qualification_examination
DROP COLUMN school_type;

ALTER TABLE regprof.training_course_qualification_examination
DROP COLUMN school_age_range;

ALTER TABLE regprof.training_course_qualification_examination
DROP COLUMN school_subject;

--changeset mnakova:0135.5
ALTER TABLE regprof.training_course_qualification_examination
    RENAME COLUMN school_grade_txt TO school_grade;

ALTER TABLE regprof.training_course_qualification_examination
    RENAME COLUMN school_type_txt TO school_type;

ALTER TABLE regprof.training_course_qualification_examination
    RENAME COLUMN school_age_range_txt TO school_age_range;

ALTER TABLE regprof.training_course_qualification_examination
    RENAME COLUMN school_subject_txt TO school_subject;