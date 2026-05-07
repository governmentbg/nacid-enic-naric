--liquibase formatted sql

--changeset akehayov:0155 splitStatements:false
create table common.correspondence_docs
(
    id                  serial
        primary key,
    attached_doc_id     int
        constraint correspondence_docs_application_attached_docs_id_fk
            references common.application_attached_docs,
    registration_number varchar(20),
    registration_date   date,
    finalization_date   date,
    fo_send_date        date,
    fo_read_date        date,
    date_created        timestamp default now()
);



CREATE OR REPLACE FUNCTION common.fill_correspondence_docs()
    RETURNS trigger AS
$BODY$

BEGIN
    IF (old.docflow_id is null and new.docflow_id is not null and (select (select direction from nomenclatures.doc_types where id = new.doc_type_id) = 'O') and new.doc_category = 'AA') then
        INSERT INTO common.correspondence_docs (attached_doc_id) VALUES (new.id);
END IF;
return new;
END;

$BODY$
LANGUAGE plpgsql VOLATILE COST 100;


create or replace trigger fill_correspondence_docs_trigger
    after update
on common.application_attached_docs
    for each row
execute procedure common.fill_correspondence_docs();