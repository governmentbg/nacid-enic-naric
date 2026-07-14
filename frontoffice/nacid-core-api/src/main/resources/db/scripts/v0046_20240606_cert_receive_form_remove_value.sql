--liquibase formatted sql

--changeset raneva:core_0046

DELETE FROM nomenclatures.reference_data WHERE domain = 'CERTIFICATE_RECEIVE_FORM' and code = 'PE';