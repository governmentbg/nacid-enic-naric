--liquibase formatted sql

--changeset ggeorgiev:0167
--validCheckSum: 8:bba1d736a2ab90ac4988986f6bc438e9
--validCheckSum: 8:a9a8ce760ed312614abb28f2b37e080a
create table common.application_document_receive_method (
                                                id serial constraint drm_pk
                                                    primary key,
                                                apn_id int not null
                                                    constraint drm_application_fk
                                                        references common.application                                ,
                                                document_receive_method_code varchar(4)
                                                    constraint drm_document_receive_method_code_fk
                                                        references nomenclatures.document_receive_method,
                                                document_recipient_address integer
                                                    constraint drm_ads_fk
                                                        references common.address,
                                                crf_code     varchar(20)
                                                    constraint drm_cert_receive_form
                                                        check (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM'::character varying, crf_code)) );

insert into common.application_document_receive_method (apn_id, document_receive_method_code, crf_code,document_recipient_address)
select id, document_receive_method_code, crf_code, document_recipient_address from common.application where crf_code is null and document_receive_method_code is not null;


insert into common.application_document_receive_method (apn_id, document_receive_method_code, crf_code, document_recipient_address)
select id, document_receive_method_code, crf_code, document_recipient_address from common.application where crf_code != 'PE';


insert into common.application_document_receive_method (apn_id, document_receive_method_code, crf_code,document_recipient_address)
select id, case when document_receive_method_code in ('DEC','D','ID') then document_receive_method_code else null end, 'PAP', document_recipient_address from common.application where crf_code = 'PE' ;


insert into common.application_document_receive_method (apn_id, document_receive_method_code, crf_code,document_recipient_address)
select id, case when document_receive_method_code not in ('DEC','D','ID') then document_receive_method_code else null end, 'E', null from common.application where crf_code = 'PE';


update common.application_document_receive_method adrm set crf_code = (select crf_code from nomenclatures.document_receive_method drm where drm.code = adrm.document_receive_method_code)
    where crf_code is null and apn_id in (select id from common.application where ase_code != 'SAR' and ate_code in ('RP', 'AR'));

alter table nomenclatures.document_receive_method add column crf_code varchar(20) constraint drm_cert_receive_form
    check (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM'::character varying, crf_code));

update nomenclatures.document_receive_method set crf_code = 'PAP' where code in ('DEC','D','ID');
update nomenclatures.document_receive_method set crf_code = 'E' where code not in ('DEC','D','ID');
alter table nomenclatures.document_receive_method alter column crf_code set not null;


--TODO:Tova v drug file po-natam kato re razraboti funkcionalnostta!
/*alter table common.application drop column document_receive_method_code;
alter table common.application drop column crf_code;
alter table common.application drop column document_recipient_address;
delete from nomenclatures.reference_data where domain = 'CERTIFICATE_RECEIVE_FORM' and code = 'PE';*/