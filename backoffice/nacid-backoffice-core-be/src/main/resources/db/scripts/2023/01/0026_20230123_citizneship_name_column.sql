--liquibase formatted sql

--changeset veizov:0026
alter table nomenclatures.country add column citizenship_name varchar(255);
update nomenclatures.country c set citizenship_name = c.name where 1 = 1;
update nomenclatures.country set citizenship_name = 'Без гражданство' where code = '--';
