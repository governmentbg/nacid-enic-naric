--liquibase formatted sql

--changeset mnakova:0114
alter table libserv.document_delivery_details add column docflow_id varchar(30);
alter table libserv.document_delivery_details add column doc_type_id integer not null constraint ddd_dte_fk references nomenclatures.doc_types;
