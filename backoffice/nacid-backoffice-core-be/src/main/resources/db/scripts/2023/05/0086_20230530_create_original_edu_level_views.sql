--liquibase formatted sql

--changeset akehayov:0086
create view nomenclatures.vw_original_edu_level(original_edu_level_name) as
SELECT DISTINCT a.original_edu_level_name
FROM rudi.training_course a
WHERE a.original_edu_level_name IS NOT NULL;

create view nomenclatures.vw_original_edu_level_translated(original_edu_level_translated) as
SELECT DISTINCT a.original_edu_level_translated
FROM rudi.training_course a
WHERE a.original_edu_level_translated IS NOT NULL;