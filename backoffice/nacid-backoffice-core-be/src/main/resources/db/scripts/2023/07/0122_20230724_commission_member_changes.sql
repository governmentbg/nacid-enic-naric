--liquibase formatted sql

--changeset murlev:0122
alter table rudi.commission_participation drop column position;
alter table rudi.commission_participation add column chairman integer;
update rudi.commission_member set commission_position = 'MEM' where commission_position in ('CHR','VCHR');
delete from nomenclatures.reference_data where domain = 'COMMISSION_POSITION' and code in ('CHR','VCHR');
