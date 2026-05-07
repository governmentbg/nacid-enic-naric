--liquibase formatted sql

--changeset mnakova:0137.1
DELETE FROM nomenclatures.reference_data
WHERE domain = 'SCHOOL_TYPE';

DELETE FROM nomenclatures.reference_data
WHERE domain = 'SCHOOL_SUBJECT';

DELETE FROM nomenclatures.reference_data
WHERE domain = 'SCHOOL_GRADE';

DELETE FROM nomenclatures.reference_data
WHERE domain = 'SCHOOL_AGE_RANGE';

--changeset mnakova:0137.2
DELETE FROM nomenclatures.reference_data_domain
WHERE domain = 'SCHOOL_TYPE';

DELETE FROM nomenclatures.reference_data_domain
WHERE domain = 'SCHOOL_SUBJECT';

DELETE FROM nomenclatures.reference_data_domain
WHERE domain = 'SCHOOL_GRADE';

DELETE FROM nomenclatures.reference_data_domain
WHERE domain = 'SCHOOL_AGE_RANGE';