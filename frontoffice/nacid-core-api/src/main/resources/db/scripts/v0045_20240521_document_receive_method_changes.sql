--liquibase formatted sql

--changeset raneva:core_0045

alter table nomenclatures.document_receive_method add column crf_code varchar(20) constraint drm_cert_receive_form
    check (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM'::character varying, crf_code));

update nomenclatures.document_receive_method set crf_code = 'PAP' where code in ('DEC','D','ID');
update nomenclatures.document_receive_method set crf_code = 'E' where code not in ('DEC','D','ID');
alter table nomenclatures.document_receive_method alter column crf_code set not null;