--liquibase formatted sql

--changeset ggeorgiev:core_0049_0
--validCheckSum: 8:bffb9942182a52241909427327b6b2f2
--validCheckSum: 8:54da9de24ca936cee5a7537e0b6053c6
INSERT INTO nomenclatures.application_type (code, name, active) VALUES ('SE', 'Средно образование', 1) on conflict do nothing;

INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active) VALUES ('REC', 'SE', 'Удостоверение за средно образование', 1) on conflict do nothing;