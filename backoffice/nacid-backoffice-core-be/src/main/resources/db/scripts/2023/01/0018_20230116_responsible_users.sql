--liquibase formatted sql

--changeset ggeorgiev:0018
alter table common.application_responsible_users add column date_from date;
update common.application_responsible_users ru set date_from = (select entry_date from common.application where id = ru.apn_id);
alter table common.application_responsible_users alter column date_from set not null;
alter table common.application_responsible_users add column date_to date;