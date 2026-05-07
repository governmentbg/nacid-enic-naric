--liquibase formatted sql

--changeset akehayov:0041
alter table rudi.university
drop column university_generic_name;