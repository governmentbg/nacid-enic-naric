--liquibase formatted sql

--changeset akehayov:0055
create view regprof.vw_profession_name(profession_name) as
SELECT DISTINCT a.profession_name
FROM regprof.profession_experience a
WHERE a.profession_name IS NOT NULL;