--liquibase formatted sql

--changeset murlev:0069
create or replace view rudi.vw_calendars(id, session_num, session_time, status_name, status_code) as
SELECT cl.id,
       cl.session_num,
       cl.session_time,
       rf.name                AS status_name,
       cl.session_status_code AS status_code
FROM rudi.commission_calendar cl
         JOIN nomenclatures.reference_data rf
              ON rf.code::text = cl.session_status_code::text AND rf.domain::text = 'COMMISSION_SESSION_STATUS'::text;

alter table rudi.vw_calendars
    owner to postgres;
