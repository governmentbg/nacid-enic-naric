--liquibase formatted sql

--changeset ndimov:core_0060
ALTER TABLE services.grading_scale ALTER COLUMN country_code DROP NOT NULL;
INSERT INTO services.grading_scale (id, country_code, scale_name, scale_type, start_year, end_year, description, active) VALUES (0, null, 'Липсва информация', 'LETTER', null, null, 'Липсва информация', 1) ON CONFLICT DO NOTHING;