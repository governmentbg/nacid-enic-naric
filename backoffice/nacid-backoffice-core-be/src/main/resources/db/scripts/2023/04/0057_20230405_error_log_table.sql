--liquibase formatted sql

--changeset veizov:0057
create table common.error_log
(
    id               serial
        constraint error_log_pkey primary key,
    error_type       varchar(100)             not null,
    error_message    text                     not null,
    created_date     timestamp with time zone not null,
    resolved_date    timestamp with time zone,
    resolved_comment text,
    resolved_user    varchar(255),
    data_json        text
);