--liquibase formatted sql

--changeset mnakova:0170
--validCheckSum: 8:da32b4871010e6677892aad60db6ffb8
--validCheckSum: 8:86b2f4a02f29ec22b9c9e5f8fda9537c
create table migration_fixes.temp_document_receive_method(
    id int not null,
    document_receive_method_code varchar(10),
    crf_code varchar(10),
    document_recipient_address int
);
insert into migration_fixes.temp_document_receive_method (id, document_receive_method_code, crf_code, document_recipient_address)
    select id, document_receive_method_code, crf_code, document_recipient_address from common.application;
alter table common.application drop column document_receive_method_code;
alter table common.application drop column crf_code;
alter table common.application drop column document_recipient_address;
delete from nomenclatures.reference_data where domain = 'CERTIFICATE_RECEIVE_FORM' and code = 'PE';