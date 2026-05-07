--liquibase formatted sql

--changeset veizov:0070

DELETE FROM nomenclatures.cfg_app_status WHERE ase_code in ('SIG','SUG','PUB');
DELETE FROM nomenclatures.application_subtype WHERE code in ('SIG','SUG','PUB');
DELETE FROM nomenclatures.reference_data_domain WHERE domain = 'PUBLIC_ACCESS_INFO_FORM';
DROP TABLE libserv.public_access_info_form;
DROP TABLE libserv.public_access;
DROP TABLE libserv.signal;
DROP TABLE libserv.suggestion;
