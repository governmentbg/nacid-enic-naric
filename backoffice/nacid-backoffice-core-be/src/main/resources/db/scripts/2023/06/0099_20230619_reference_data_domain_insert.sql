--liquibase formatted sql

--changeset mnakova:0099
INSERT INTO nomenclatures.reference_data_domain(domain, name, fo_replication_flag)
VALUES ('SCHOOL_SUBJECT', 'Учебен предмет в училище', 0);