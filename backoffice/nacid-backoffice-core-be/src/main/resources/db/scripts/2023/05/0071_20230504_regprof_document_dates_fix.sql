--liquibase formatted sql

--changeset ggeorgiev:0071
alter table regprof.higher_training_course alter column document_date type varchar(50) using to_char(document_date, 'dd.MM.yyyy');
alter table regprof.secondary_training_course alter column document_date type varchar(50) using to_char(document_date, 'dd.MM.yyyy');
alter table regprof.postgraduate_training_course alter column document_date type varchar(50) using to_char(document_date, 'dd.MM.yyyy');
alter table regprof.profession_experience_documents alter column document_date type varchar(50) using to_char(document_date, 'dd.MM.yyyy');