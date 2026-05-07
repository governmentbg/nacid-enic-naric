--liquibase formatted sql

--changeset raneva:core_0036
INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.arUniEntryRequest', 'serviceDefinition', '{"content":""}', 'htmlContent', 150, true);