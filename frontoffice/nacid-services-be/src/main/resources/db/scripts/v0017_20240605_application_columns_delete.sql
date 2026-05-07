--liquibase formatted sql

--changeset raneva:services_0017

alter table services.application drop column crf_code;
alter table services.application drop column document_receive_method_code;
alter table services.application drop column document_recipient_address;

