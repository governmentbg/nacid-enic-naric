--liquibase formatted sql

--changeset ggeorgiev:0055 splitStatements:false
CREATE OR REPLACE FUNCTION common.delete_attachment()
    RETURNS trigger AS
$BODY$

BEGIN
    -- if delete (new.id is null) or update and bucket_name(or file_location) is changed
    IF (new.id is null or (new.bucket_name != old.bucket_name or old.file_location != new.file_location)) then
        INSERT INTO common.attachment_deletes (attachment_id, bucket_name, file_location, status) VALUES (old.id, old.bucket_name, old.file_location, 0);
    END IF;
    return new;
END;

$BODY$
    LANGUAGE plpgsql VOLATILE COST 100;