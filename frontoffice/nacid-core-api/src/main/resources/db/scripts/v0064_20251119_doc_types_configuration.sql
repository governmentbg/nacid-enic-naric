--liquibase formatted sql

--changeset ggeorgiev:core_0064
INSERT INTO nomenclatures.cfg_doc_type_to_app_type (id, dte_id, ate_code, ase_code, show_expression) VALUES (421, 110, 'SE', null, 'false');