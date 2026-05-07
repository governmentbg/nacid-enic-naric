--liquibase formatted sql

--changeset ggeorgiev:0140 splitStatements:false
drop index if exists rudi.tce_apn_uk;
drop index if exists rudi.tce_apn_idx;
create unique index tce_apn_uk on rudi.training_course (apn_id);

drop index if exists regprof.rte_apn_idx;
drop index if exists regprof.rte_apn_uk;
create unique index rte_apn_uk on regprof.regprof_training_experience (apn_id);