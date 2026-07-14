--liquibase formatted sql

--changeset murlev:core_0063
INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active)
VALUES ('ARD', 'AR', 'Допълнителни документи към услуги академично признаване и професионални квалификации', 1)
    ON CONFLICT (code) DO NOTHING;

INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active)
VALUES ('RPD', 'RP', 'Допълнителни документи към услуги регулирани професии', 1)
    ON CONFLICT (code) DO NOTHING;

INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active)
VALUES ('LIBD', 'LIB', 'Допълнителни документи към услуги библиографски услуги ', 1)
    ON CONFLICT (code) DO NOTHING;

INSERT INTO nomenclatures.application_subtype (code, ate_code, name, active)
VALUES ('SED', 'SE', 'Допълнителни документи към услуги средно образование', 1)
    ON CONFLICT (code) DO NOTHING;


INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.rudiAdditionalDoc', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.libservAdditionalDoc', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.seAdditionalDoc', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.regprofAdditionalDoc', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);