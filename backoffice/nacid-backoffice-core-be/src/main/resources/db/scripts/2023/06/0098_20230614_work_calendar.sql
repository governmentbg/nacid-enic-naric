--liquibase formatted sql

--changeset veizov:0098
create table nomenclatures.work_calendar_holiday
(
    id date
        constraint wcdf_pk
            primary key,
    description text,
    user_last_update varchar(100) not null,
    date_last_update timestamp with time zone not null
);