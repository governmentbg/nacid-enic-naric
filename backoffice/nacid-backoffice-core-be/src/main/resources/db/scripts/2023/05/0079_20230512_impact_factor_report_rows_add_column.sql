--liquibase formatted sql

--changeset mnakova:0079
alter table libserv.impact_factor_report_rows add column journal_rank varchar(20);
