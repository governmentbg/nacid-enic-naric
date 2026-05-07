--liquibase formatted sql

--changeset akehayov:0053
create view nomenclatures.vw_sdk_qualification(sdk_qualification) as
SELECT DISTINCT a.professional_qualification
FROM regprof.postgraduate_training_course a
WHERE a.professional_qualification IS NOT NULL;