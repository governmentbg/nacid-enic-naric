--liquibase formatted sql

--changeset murlev:core_0003
--validCheckSum: 8:94d881ea7013c000832e532d8872ed22
--validCheckSum: 8:20be767fb132c9bc1a639cf847a6c0d4
create table common.content_management
(
    id               varchar(100)  not null
        constraint content_management_pk
            primary key,
    type             varchar(255) not null,
    data             text         not null,
    data_template    varchar(255) not null,
    content_order    int          not null,
    alias            varchar(255),
    date_created     timestamp with time zone default now(),
    date_last_update timestamp with time zone,
    user_last_update varchar(255),
    active           boolean                  default false
);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('adminConsole', 'adminConsole', '[]', 'adminConsole', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('contacts', 'contacts', '{}', 'contacts', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('law', 'law', '{}', 'law', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('sitemap', 'sitemap', '[]', 'sitemap', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.higherEducationRecognition', 'serviceDefinition', '{"content":""}', 'htmlContent', 1, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.doctorateDegrees.doctor', 'serviceDefinition', '{"content":""}', 'htmlContent', 2, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.doctorateDegrees.doctorOfScience', 'serviceDefinition', '{"content":""}', 'htmlContent', 3, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.uniChecks.academicStatus', 'serviceDefinition', '{"content":""}', 'htmlContent', 4, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.uniChecks.documentAuthenticity', 'serviceDefinition', '{"content":""}', 'htmlContent', 5, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.uniChecks.issueRecommendation', 'serviceDefinition', '{"content":""}', 'htmlContent', 6, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.nonRegulatedProfessions', 'serviceDefinition', '{"content":""}', 'htmlContent', 7, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.publicAccess', 'serviceDefinition', '{"content":""}', 'htmlContent', 8, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.suggestion', 'serviceDefinition', '{"content":""}', 'htmlContent', 9, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.signal', 'serviceDefinition', '{"content":""}', 'htmlContent', 10, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.officialNotes.researchProject', 'serviceDefinition', '{"content":""}', 'htmlContent', 11, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.officialNotes.scientificPaper', 'serviceDefinition', '{"content":""}', 'htmlContent', 12, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.officialNotes.dissertationThesis', 'serviceDefinition', '{"content":""}', 'htmlContent', 13, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.officialNotes.academicPosition', 'serviceDefinition', '{"content":""}', 'htmlContent', 14, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.inquiry.impactFactor', 'serviceDefinition', '{"content":""}', 'htmlContent', 15, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.inquiry.publicationCitings', 'serviceDefinition', '{"content":""}', 'htmlContent', 16, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.analyticProducts', 'serviceDefinition', '{"content":""}', 'htmlContent', 17, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.bibliographicReferences.nacidDbs', 'serviceDefinition', '{"content":""}', 'htmlContent', 18, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.bibliographicReferences.foreignDbs', 'serviceDefinition', '{"content":""}', 'htmlContent', 19, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.documentDelivery.libraries', 'serviceDefinition', '{"content":""}', 'htmlContent', 20, true);

INSERT INTO common.content_management (id, type, data, data_template, content_order, active)
VALUES ('serviceDefinition.documentDelivery.nacid', 'serviceDefinition', '{"content":""}', 'htmlContent', 21, true);
