--liquibase formatted sql

--changeset murlev:0158
alter table rudi.commission_applications add column generated_final_doc integer;
alter table rudi.commission_applications add column abdocs_transferred integer;