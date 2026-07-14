--liquibase formatted sql

--changeset ggeorgiev:0150 splitStatements:false
DROP VIEW if exists common.vw_attachments;

CREATE view common.vw_attachments
as
SELECT DISTINCT d.apn_id,
                att.id        as attachment_id,
                dt.name      as doc_type_name,
                dt.direction as direction,
                att.file_name,
                att.bucket_name,
                att.file_location,
                att.content_type
FROM common.application_attached_docs d
         JOIN common.application_attached_doc_attachments aada on aada.application_attached_doc_id = d.id
         JOIN nomenclatures.doc_types dt on d.doc_type_id = dt.id
         JOIN common.attachments att on att.id = aada.attachment_id
UNION ALL
SELECT DISTINCT d.apn_id,
                at.id                 as attachment_id,
                'Библиографски данни' as doc_type_name,
                'О'                   as direction,
                at.file_name,
                at.bucket_name,
                at.file_location,
                at.content_type
FROM libserv.document_delivery_details d
         JOIN common.attachments at
              on d.attachment_id = at.id;

CREATE TABLE rudi.training_course_university_examination_competent_institutions
(
    id serial NOT NULL,
    competent_institution_id integer NOT NULL,
    training_course_university_examination_id integer NOT NULL,
    CONSTRAINT tcueci_pk PRIMARY KEY (id),
    CONSTRAINT tcueci_cin_fk FOREIGN KEY (competent_institution_id)
        REFERENCES rudi.competent_institution (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT tcueci_uen_fk FOREIGN KEY (training_course_university_examination_id)
        REFERENCES rudi.training_course_university_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
INSERT INTO rudi.training_course_university_examination_competent_institutions (competent_institution_id, training_course_university_examination_id)
select competent_institution_id, tcue.id
from rudi.university_examination_competent_institutions ueci
join rudi.training_course_university_examination tcue on tcue.university_examination_id = ueci.university_examination_id
order by tcue.id;

CREATE TABLE rudi.training_course_university_examination_training_forms
(
    id serial NOT NULL,
    training_course_university_examination_id integer NOT NULL,
    training_form_code varchar(20) not null,
    notes character varying(255),
    CONSTRAINT tcuetf_pk PRIMARY KEY (id),
    CONSTRAINT tcuetf_uen_fk FOREIGN KEY (training_course_university_examination_id)
        REFERENCES rudi.training_course_university_examination (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE rudi.training_course_university_examination_training_forms
    ADD CONSTRAINT tcuetf_training_form_check
        CHECK (nomenclatures.exists_refdata('TRAINING_FORM', training_form_code));

INSERT INTO rudi.training_course_university_examination_training_forms (training_course_university_examination_id, training_form_code, notes)
select tcue.id, training_form_code, uetf.notes from rudi.university_examination_training_forms uetf
join rudi.training_course_university_examination tcue on tcue.university_examination_id = uetf.university_examination_id
order by tcue.id;


CREATE INDEX aad_doc_category_idx ON common.application_attached_docs(doc_category);
CREATE INDEX aada_application_attached_doc_id_idx ON common.application_attached_doc_attachments(application_attached_doc_id);
CREATE INDEX tcuead_training_course_university_examination_idx ON rudi.training_course_university_examination_attached_docs(training_course_university_examination_id);
CREATE INDEX tcuetf_training_course_university_examination_idx ON rudi.training_course_university_examination_training_forms(training_course_university_examination_id);
CREATE INDEX tcueci_training_course_university_examination_idx ON rudi.training_course_university_examination_competent_institutions(training_course_university_examination_id);