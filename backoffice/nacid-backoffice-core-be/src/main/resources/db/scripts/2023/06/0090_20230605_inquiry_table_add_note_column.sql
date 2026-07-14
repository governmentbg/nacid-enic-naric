--liquibase formatted sql

--changeset mnakova:0090
alter table libserv.inquiry add column inquiry_notes text;
