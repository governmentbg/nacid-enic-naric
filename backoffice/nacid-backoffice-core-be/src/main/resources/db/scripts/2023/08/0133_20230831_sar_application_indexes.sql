--liquibase formatted sql

--changeset ggeorgiev:0133 splitStatements:false
CREATE INDEX sar_application_statute_flag_idx ON rudi.sar_application(statute_flag);
CREATE INDEX sar_application_authenticity_flag_idx ON rudi.sar_application(authenticity_flag);
CREATE INDEX sar_application_recommendation_flag_idx ON rudi.sar_application(recommendation_flag);