--liquibase formatted sql

--changeset aneva:core_0016
DROP TABLE nomenclatures.acadrec_nacid_status;
DROP TABLE nomenclatures.acadrec_uni_status;
DROP TABLE nomenclatures.getting_result_method;
DROP TABLE nomenclatures.regprof_status;