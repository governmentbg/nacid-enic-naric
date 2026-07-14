--liquibase formatted sql

--changeset veizov:0027
CREATE TABLE common.personal_nacid_id
(
    value varchar(12) primary key,
    user_generated varchar(255)
);