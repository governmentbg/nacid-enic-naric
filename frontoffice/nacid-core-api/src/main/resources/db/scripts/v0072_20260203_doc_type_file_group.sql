--liquibase formatted sql

--changeset ggeorgiev:core_0072
--validCheckSum: 8:9039375b3e9030fb83c1e9e00eb113f4
alter table nomenclatures.doc_types add column validation_file_group varchar(255);
update nomenclatures.doc_types set validation_file_group = 'pdf' where id != 43;
update nomenclatures.doc_types set validation_file_group = 'pdf100' where id = 43;