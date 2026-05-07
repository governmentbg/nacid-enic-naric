--liquibase formatted sql

--changeset ggeorgiev:0154 splitStatements:false
alter table nomenclatures.doc_types add column abdocs_autoinsert_flag int not null default 0;
alter table nomenclatures.doc_types add column abdocs_task_result varchar(50);
alter table nomenclatures.doc_types add column abdocs_task_user varchar(100);