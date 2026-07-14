--liquibase formatted sql

--changeset ggeorgiev:0150 splitStatements:false
alter table nomenclatures.cfg_report_sql add column date_updated timestamp;
update nomenclatures.cfg_report_sql set date_updated = '1970-01-01';
alter table nomenclatures.cfg_report_sql alter column date_updated set not null;