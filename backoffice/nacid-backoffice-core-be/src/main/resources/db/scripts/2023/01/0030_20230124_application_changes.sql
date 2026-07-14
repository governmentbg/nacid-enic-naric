--liquibase formatted sql

--changeset ggeorgiev:0030
alter table common.application add column representative_capacity varchar(255);
alter table rudi.sar_application add column outgoing_number varchar(50);
alter table rudi.sar_application add column internal_number varchar(50);


