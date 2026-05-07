--liquibase formatted sql

--changeset ndimov:core_0077
ALTER TABLE services.grading_scale_details ALTER COLUMN min_value TYPE numeric(6,3);
ALTER TABLE services.grading_scale_details ALTER COLUMN max_value TYPE numeric(6,3);