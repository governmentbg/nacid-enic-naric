--liquibase formatted sql

--changeset mnakova:0048
create view nomenclatures.vw_experience_profession(profession_name) as
SELECT DISTINCT a.profession_name
FROM regprof.profession_experience a
WHERE a.profession_name IS NOT NULL;