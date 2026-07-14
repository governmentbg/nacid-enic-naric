--liquibase formatted sql

--changeset mnakova:0113
alter table rudi.application_commission_member_statements add column docflow_id varchar(30);
