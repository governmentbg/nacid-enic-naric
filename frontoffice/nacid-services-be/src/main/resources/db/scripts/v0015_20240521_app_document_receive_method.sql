--liquibase formatted sql

--changeset raneva:services_0015
CREATE TABLE services.application_document_receive_method
(
    idx smallint NOT NULL,
    apn_id integer NOT NULL,
    document_receive_method_code character varying(4),
    document_recipient_address integer,
    crf_code varchar(20),
    CONSTRAINT adrm_pk PRIMARY KEY (idx, apn_id),
    CONSTRAINT adrm_apn_fk FOREIGN KEY (apn_id)
        REFERENCES services.application (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT adrm_document_receive_method_code_fk FOREIGN KEY (document_receive_method_code)
        references nomenclatures.document_receive_method(code),
    CONSTRAINT adrm_dra_fk FOREIGN KEY (document_recipient_address) REFERENCES services.address(id),
    CONSTRAINT adrm_crf_check CHECK (nomenclatures.exists_refdata('CERTIFICATE_RECEIVE_FORM'::character varying, crf_code))
);

insert into services.application_document_receive_method (idx, apn_id, document_receive_method_code, crf_code, document_recipient_address)
select 0 as idx, id, document_receive_method_code, crf_code, document_recipient_address from services.application where crf_code is null and document_receive_method_code is not null;

insert into services.application_document_receive_method (idx, apn_id, document_receive_method_code, crf_code, document_recipient_address)
select 0 as idx, id, document_receive_method_code, crf_code, document_recipient_address from services.application where crf_code != 'PE';

insert into services.application_document_receive_method (idx, apn_id, document_receive_method_code, crf_code, document_recipient_address)
select 1 as idx, id, case when document_receive_method_code in ('DEC','D','ID') then document_receive_method_code else null end, 'PAP', document_recipient_address from services.application where crf_code = 'PE' ;

insert into services.application_document_receive_method (idx, apn_id, document_receive_method_code, crf_code,document_recipient_address)
select 0 as idx, id, case when document_receive_method_code not in ('DEC','D','ID') then document_receive_method_code else null end, 'E', null from services.application where crf_code = 'PE';