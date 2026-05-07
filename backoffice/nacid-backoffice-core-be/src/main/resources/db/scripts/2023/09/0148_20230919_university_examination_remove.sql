--liquibase formatted sql

--changeset ggeorgiev:0148 splitStatements:false
alter table rudi.training_course_university_examination add column uny_id int;
alter table rudi.training_course_university_examination add column examination_date date;
alter table rudi.training_course_university_examination add column user_created varchar(100);
alter table rudi.training_course_university_examination add column communicated_flag int;
alter table rudi.training_course_university_examination add column recognized_flag int;
alter table rudi.training_course_university_examination add column training_location_code varchar(20);
alter table rudi.training_course_university_examination add column joint_degree_flag int;


ALTER TABLE rudi.training_course_university_examination
    ADD CONSTRAINT tcue_training_location_check
        CHECK (nomenclatures.exists_refdata('UNI_EXAM_TRAINING_LOCATION', training_location_code));

CREATE INDEX tcue_uny_idx
    ON rudi.training_course_university_examination
        USING btree
        (uny_id);
ALTER table rudi.training_course_university_examination
    add CONSTRAINT tcue_uny_fk FOREIGN KEY (uny_id)
        REFERENCES rudi.university (id);

UPDATE rudi.training_course_university_examination tcue
SET uny_id = uen.uny_id,
    examination_date = uen.examination_date,
    user_created = uen.user_created,
    communicated_flag = uen.communicated_flag,
    recognized_flag = uen.recognized_flag,
    training_location_code = uen.training_location_code,
    joint_degree_flag = uen.joint_degree_flag,
    notes = (case when tcue.notes is not null and uen.notes is not null then tcue.notes||' '||uen.notes when tcue.notes is not null and uen.notes is null then tcue.notes when tcue.notes is null and uen.notes is not null then uen.notes else null end)
FROM rudi.university_examination uen
WHERE uen.id = tcue.university_examination_id;

alter table rudi.training_course_university_examination alter column uny_id set not null;
alter table rudi.training_course_university_examination alter column user_created set not null;
alter table rudi.training_course_university_examination alter column examination_date set not null;
alter table rudi.training_course_university_examination alter column communicated_flag set not null;
alter table rudi.training_course_university_examination alter column recognized_flag set not null;
alter table rudi.training_course_university_examination alter column joint_degree_flag set not null;