--liquibase formatted sql

--changeset mnakova:0050
create view nomenclatures.vw_imi_correspondence(imi_correspondence) as
SELECT DISTINCT a.imi_correspondence
FROM regprof.regprof_application a
WHERE a.imi_correspondence IS NOT NULL;