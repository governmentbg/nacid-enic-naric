--liquibase formatted sql

--changeset kehayov:0145
alter table rudi.training_course_universities
    add university_contact varchar(255);
alter table rudi.training_course
    add manual_temp_uni_name varchar(255);





