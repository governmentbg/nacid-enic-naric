--liquibase formatted sql

--changeset ggeorgiev:0037
create table rudi.training_course_universities (
    id serial
        constraint tcu_pk
            primary key,
    tce_id int not null,
    uny_id int not null,
    university_name varchar(255) not null
);
create unique index tcu_uk
    on rudi.training_course_universities (tce_id, uny_id);
alter table rudi.training_course_universities
    add constraint tcu_tce_fk
        foreign key (tce_id) references rudi.training_course;

alter table rudi.training_course_universities
    add constraint tcu_uny_fk
        foreign key (uny_id) references rudi.university;