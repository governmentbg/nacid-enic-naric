--liquibase formatted sql

--changeset ggeorgiev:0015
delete from nomenclatures.ek_settlement where code = '99994';