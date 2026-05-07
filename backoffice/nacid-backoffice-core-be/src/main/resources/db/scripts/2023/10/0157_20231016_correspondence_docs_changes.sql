--liquibase formatted sql

--changeset akehayov:0157 splitStatements:false
alter table common.correspondence_docs
drop column finalization_date;

alter table common.correspondence_docs
alter column fo_send_date type timestamp using fo_send_date::timestamp;

alter table common.correspondence_docs
alter column fo_read_date type timestamp using fo_read_date::timestamp;