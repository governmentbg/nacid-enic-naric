--liquibase formatted sql

--changeset ggeorgiev:0025
alter table rudi.rudi_application alter column representative_authorized_flag drop not null;