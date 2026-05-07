--liquibase formatted sql

--changeset ggeorgiev:0035
alter table nomenclatures.cfg_sar_app_status drop column legal_flag;
alter table nomenclatures.cfg_sar_app_status add column positive_flag int not null default 0;
alter table nomenclatures.cfg_sar_app_status alter column positive_flag drop default;

alter table rudi.sar_application add column statute_final_status_history_id int;
alter table rudi.sar_application add column authenticity_final_status_history_id int;
alter table rudi.sar_application add column recommendation_final_status_history_id int;

alter table rudi.sar_application
    add constraint san_statute_final_status_history_id
        foreign key (statute_final_status_history_id) references common.app_status_history;

alter table rudi.sar_application
    add constraint san_authenticity_final_status_history_id
        foreign key (authenticity_final_status_history_id) references common.app_status_history;

alter table rudi.sar_application
    add constraint san_recommendation_final_status_history_id
        foreign key (recommendation_final_status_history_id) references common.app_status_history;
