--liquibase formatted sql

--changeset ndimov:core_0057
update nomenclatures.doc_types
set name='Документ посочващ, че дипломата дава право за продължаване на образование във висши училища – оригинал и превод (с изискуемите заверки)'
where id=201;