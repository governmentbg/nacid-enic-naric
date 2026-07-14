--liquibase formatted sql

--changeset ggeorgiev:0012
drop table rudi.training_location_examination;
create table rudi.training_location_examination (
    tce_id int,
    legitimate_flag int not null,
    CONSTRAINT tle_pk PRIMARY KEY (tce_id),
    CONSTRAINT tle_tce_fk FOREIGN KEY (tce_id)
        REFERENCES rudi.training_course (id)
);

CREATE TABLE rudi.training_location_examination_locations (
    training_location_id int not null,
    training_institution_id int,
    CONSTRAINT tlel_pk PRIMARY KEY (training_location_id, training_institution_id),
    CONSTRAINT tlel_tln_fk FOREIGN KEY (training_location_id)
        REFERENCES rudi.training_location (id),
    CONSTRAINT tlel_tin_fk FOREIGN KEY (training_institution_id)
        REFERENCES rudi.training_institution (id)
);

