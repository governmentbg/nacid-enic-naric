--liquibase formatted sql

--changeset ggeorgiev:0149 splitStatements:false
INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_replication_flag) VALUES ('ATTACHMENT_VISIBILITY', 'Видимост на прикачените файлове', 0);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ATTACHMENT_VISIBILITY', 'INT', 'Вътрешен', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('ATTACHMENT_VISIBILITY', 'PUB', 'Публичен', 0, 1);

INSERT INTO nomenclatures.reference_data_domain (domain, name, fo_replication_flag) VALUES ('DOC_TYPE_DIRECTION', 'Посока на типа документ', 0);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('DOC_TYPE_DIRECTION', 'I', 'Входящ', 0, 1);
INSERT INTO nomenclatures.reference_data (domain, code, name, index, active) VALUES ('DOC_TYPE_DIRECTION', 'O', 'Изходящ', 0, 1);

ALTER TABLE nomenclatures.doc_types
    ADD CONSTRAINT dte_direction_check
        CHECK (nomenclatures.exists_refdata('DOC_TYPE_DIRECTION', direction));


ALTER table common.application_attached_docs add column doc_category varchar(10);
ALTER TABLE common.application_attached_docs
    ADD CONSTRAINT doc_category_check
        CHECK (nomenclatures.exists_refdata('DOC_CATEGORY', doc_category));
UPDATE common.application_attached_docs set doc_category = 'AA';
alter table common.application_attached_docs alter column doc_category set not null;


INSERT INTO common.application_attached_docs (apn_id, description, doc_type_id, copy_type_code, registration_number, registration_date, attachment_id, scanned_attachment_id, docflow_id, doc_category, date_created)
select tce.apn_id, ad.description, ad.doc_type_id, ad.copy_type_code, ad.registration_number, ad.registration_date, ad.attachment_id, ad.scanned_attachment_id, ad.docflow_id, 'DEA', ad.date_created
from rudi.training_course_diploma_examination_attached_docs ad
         join rudi.training_course_diploma_examination dan on ad.training_course_diploma_examination_id = dan.id
         join rudi.training_course tce on tce.id = dan.tce_id;

INSERT INTO common.application_attached_docs (apn_id, description, doc_type_id, copy_type_code, registration_number, registration_date, attachment_id, scanned_attachment_id, docflow_id, doc_category, date_created)
select rte.apn_id, ad.description, ad.doc_type_id, ad.copy_type_code, ad.registration_number, ad.registration_date, ad.attachment_id, ad.scanned_attachment_id, ad.docflow_id, 'DAA', ad.date_created
from regprof.training_course_document_examination_attached_docs ad
         join regprof.regprof_training_experience rte on rte.id = ad.rte_id;

INSERT INTO common.application_attached_docs (apn_id, description, doc_type_id, copy_type_code, registration_number, registration_date, attachment_id, scanned_attachment_id, docflow_id, doc_category, date_created)
select rte.apn_id, ad.description, ad.doc_type_id, ad.copy_type_code, ad.registration_number, ad.registration_date, ad.attachment_id, ad.scanned_attachment_id, ad.docflow_id, 'EDV', ad.date_created
from regprof.profession_experience_examination_attached_docs ad
         join regprof.regprof_training_experience rte on rte.id = ad.rte_id;

alter table common.application_attached_docs add column temp_id int;


create table rudi.training_course_university_examination_attached_docs (
   id serial primary key,
   training_course_university_examination_id int not null,
   application_attached_docs_id int not null,
   CONSTRAINT tcuead_training_course_university_examination_fk FOREIGN KEY (training_course_university_examination_id)
       REFERENCES rudi.training_course_university_examination (id) MATCH SIMPLE
       ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT tcuead_application_attached_docs_fk FOREIGN KEY (application_attached_docs_id)
       REFERENCES common.application_attached_docs (id) MATCH SIMPLE
       ON UPDATE NO ACTION ON DELETE NO ACTION
);


INSERT INTO common.application_attached_docs (apn_id, description, doc_type_id, copy_type_code, registration_number, registration_date, attachment_id, scanned_attachment_id, docflow_id, doc_category, date_created, temp_id)
select tce.apn_id, ad.description, ad.doc_type_id, ad.copy_type_code, ad.registration_number, ad.registration_date, ad.attachment_id, ad.scanned_attachment_id, ad.docflow_id, 'UEA', ad.date_created, tcue.id
from rudi.university_examination_attached_docs ad
         join rudi.university_examination uen on ad.university_examination_id = uen.id
         join rudi.training_course_university_examination tcue on uen.id = tcue.university_examination_id
         join rudi.training_course tce on tcue.tce_id = tce.id;

INSERT INTO rudi.training_course_university_examination_attached_docs (training_course_university_examination_id, application_attached_docs_id)
select temp_id, id
from common.application_attached_docs where doc_category = 'UEA';


INSERT INTO common.application_attached_docs (apn_id, description, doc_type_id, copy_type_code, registration_number, registration_date, attachment_id, scanned_attachment_id, docflow_id, doc_category, date_created, temp_id)
SELECT apn_id, description, doc_type_id, null, null, null, attachment_id, null, docflow_id, 'CMA', date_created, id
from rudi.application_commission_member_statements;
alter table rudi.application_commission_member_statements add column application_attached_doc_id int;
alter table rudi.application_commission_member_statements add  CONSTRAINT acms_application_attached_doc_fk FOREIGN KEY (application_attached_doc_id)
    REFERENCES common.application_attached_docs (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION;

update rudi.application_commission_member_statements acms set  application_attached_doc_id = aad.id
from common.application_attached_docs aad where aad.doc_category = 'CMA' and aad.temp_id = acms.id;

alter table rudi.application_commission_member_statements alter column application_attached_doc_id set not null;

alter table common.application_attached_docs drop column temp_id;

create table common.application_attached_doc_attachments (
  id serial primary key,
  application_attached_doc_id int not null,
  attachment_id int not null,
  attachment_visibility varchar(10) not null,
  CONSTRAINT aada_application_attached_doc_fk FOREIGN KEY (application_attached_doc_id)
      REFERENCES common.application_attached_docs (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT aada_attachment_fk FOREIGN KEY (attachment_id)
      REFERENCES common.attachments (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE common.application_attached_doc_attachments
    ADD CONSTRAINT attachment_visibility_check
        CHECK (nomenclatures.exists_refdata('ATTACHMENT_VISIBILITY', attachment_visibility));

INSERT INTO common.application_attached_doc_attachments (application_attached_doc_id, attachment_id, attachment_visibility)
SELECT aad.id, attachment_id, 'INT'
from common.application_attached_docs aad
join nomenclatures.doc_types dte on dte.id = aad.doc_type_id;

INSERT INTO common.application_attached_doc_attachments (application_attached_doc_id, attachment_id, attachment_visibility)
SELECT aad.id, scanned_attachment_id, 'PUB'
from common.application_attached_docs aad
         join nomenclatures.doc_types dte on dte.id = aad.doc_type_id
where scanned_attachment_id is not null;