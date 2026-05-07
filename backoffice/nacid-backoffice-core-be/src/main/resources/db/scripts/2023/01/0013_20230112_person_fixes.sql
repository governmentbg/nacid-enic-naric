--liquibase formatted sql

--changeset ggeorgiev:0013
alter table common.person drop column address_id;
alter table common.person drop column user_name;
delete from common.address where ate_code = 'PA';
delete from nomenclatures.reference_data where domain = 'ADDRESS_TYPE' and code = 'PA';