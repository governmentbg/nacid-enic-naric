--liquibase formatted sql

--changeset akehayov:0040
alter table rudi.training_course_universities
drop constraint tcu_pk;

alter table rudi.training_course_universities
drop column id;

alter table rudi.training_course_universities
    add constraint tcu_pk
        primary key (uny_id, tce_id);