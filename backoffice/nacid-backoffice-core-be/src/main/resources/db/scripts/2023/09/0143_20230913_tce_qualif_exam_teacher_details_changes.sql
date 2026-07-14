--liquibase formatted sql

--changeset mnakova:0143.1
DROP VIEW if exists nomenclatures.vw_school_grade;
DROP VIEW if exists nomenclatures.vw_school_type;
DROP VIEW if exists nomenclatures.vw_school_age_range;
DROP VIEW if exists nomenclatures.vw_school_subject;

--changeset mnakova:0143.2
create table if not exists regprof.training_course_qualification_examination_teacher_detail
(
    id 					serial NOT NULL,
    qualif_exam_id      integer not null,
    school_grade 		varchar(255),
    school_type         varchar(255),
    school_age_range    varchar(255),
    school_subject      varchar(255),
    CONSTRAINT tce_qualif_exam_teacher_detail_pk PRIMARY KEY (id),
    CONSTRAINT tce_qualif_exam_fk FOREIGN KEY (qualif_exam_id)
        REFERENCES regprof.training_course_qualification_examination (id)
        MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

--changeset mnakova:0143.3
insert into regprof.training_course_qualification_examination_teacher_detail (qualif_exam_id, school_grade, school_type, school_age_range, school_subject)
select qe.id, qe.school_grade, qe.school_type, qe.school_age_range, qe.school_subject from regprof.training_course_qualification_examination qe
where not exists (select 1 from regprof.training_course_qualification_examination_teacher_detail qetd where qetd.qualif_exam_id = qe.id ) and (qe.school_grade is not null or qe.school_age_range is not null or qe.school_subject is not null or qe.school_type is not null)

--changeset mnakova:0143.4
create view nomenclatures.vw_school_grade(school_grade) as
SELECT DISTINCT a.school_grade
FROM regprof.training_course_qualification_examination_teacher_detail a
WHERE a.school_grade IS NOT NULL;

create view nomenclatures.vw_school_type(school_type) as
SELECT DISTINCT a.school_type
FROM regprof.training_course_qualification_examination_teacher_detail a
WHERE a.school_type IS NOT NULL;

create view nomenclatures.vw_school_age_range(school_age_range) as
SELECT DISTINCT a.school_age_range
FROM regprof.training_course_qualification_examination_teacher_detail a
WHERE a.school_age_range IS NOT NULL;

create view nomenclatures.vw_school_subject(school_subject) as
SELECT DISTINCT a.school_subject
FROM regprof.training_course_qualification_examination_teacher_detail a
WHERE a.school_subject IS NOT NULL;