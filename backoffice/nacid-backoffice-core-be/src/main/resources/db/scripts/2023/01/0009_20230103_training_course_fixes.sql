--liquibase formatted sql

--changeset ggeorgiev:0009
ALTER TABLE rudi.training_course
    ADD CONSTRAINT tce_prev_diploma_edu_level_code
        CHECK (nomenclatures.exists_refdata('EDUCATION_LEVEL', prev_diploma_edu_level));

ALTER TABLE rudi.training_course drop column prev_diploma_country;
ALTER TABLE rudi.training_course drop column prev_diploma_city;
ALTER TABLE rudi.training_location drop column training_institution_id;

CREATE TABLE rudi.training_location_examination (
    id serial,
    training_location_id int not null,
    training_institution_id int,
    CONSTRAINT tle_tln_fk FOREIGN KEY (training_location_id)
        REFERENCES rudi.training_location (id),
    CONSTRAINT tle_tin_fk FOREIGN KEY (training_institution_id)
        REFERENCES rudi.training_institution (id)
);

alter table rudi.training_course_training_form drop column id;
alter table rudi.training_course_training_form add primary key (tce_id);