--liquibase formatted sql

--changeset ggeorgiev:0081
create index ash_apn_idx  on common.app_status_history (apn_id);
create index ash_sts_idx  on common.app_status_history (status_code);