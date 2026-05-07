--liquibase formatted sql

--changeset mnakova:0142
ALTER TABLE libserv.document_delivery_details ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE libserv.document_delivery_details ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE common.application_attached_docs ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE common.application_attached_docs ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE regprof.profession_experience_examination_attached_docs ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE regprof.profession_experience_examination_attached_docs ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE regprof.training_course_document_examination_attached_docs ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE regprof.training_course_document_examination_attached_docs ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE rudi.application_commission_member_statements ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE rudi.application_commission_member_statements ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE rudi.training_course_diploma_examination_attached_docs ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE rudi.training_course_diploma_examination_attached_docs ALTER COLUMN date_created SET DEFAULT now();

ALTER TABLE rudi.university_examination_attached_docs ADD COLUMN IF NOT EXISTS date_created TIMESTAMP WITH TIME ZONE;
ALTER TABLE rudi.university_examination_attached_docs ALTER COLUMN date_created SET DEFAULT now();