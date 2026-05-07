--liquibase formatted sql

--changeset akehayov:0160 splitStatements:false

CREATE
OR REPLACE FUNCTION common.fill_correspondence_docs()
    RETURNS trigger AS
$BODY$

BEGIN
    IF
(old.docflow_id is null and new.docflow_id is not null and (select (select direction from nomenclatures.doc_types where id = new.doc_type_id) = 'O') and
    (select (select efiling_id from common.application where id = new.apn_id) is not null)
    and new.doc_category = 'AA') then
        INSERT INTO common.correspondence_docs (attached_doc_id) VALUES (new.id);
END IF;
return new;
END;

$BODY$
LANGUAGE plpgsql VOLATILE COST 100;


create
or replace trigger fill_correspondence_docs_trigger
    after
update
    on common.application_attached_docs
    for each row
    execute procedure common.fill_correspondence_docs();