--liquibase formatted sql

--changeset ggeorgiev:0019 splitStatements:false
create table common.person_university_additional_details (
     person_id int not null primary key,
     letter_recipient text not null,
     letter_greeting varchar(150) not null,
     CONSTRAINT puad_person_fk FOREIGN KEY (person_id)
         REFERENCES common.person (id)
);
CREATE OR REPLACE FUNCTION common.get_person_legal_nature_type(p_id integer)
    RETURNS varchar AS
$BODY$
declare v_res varchar ;
begin
    SELECT case when p_id is null then null else (select legal_nature_type from common.person where id = p_id) end into v_res;
    return v_res;
end;
$BODY$
    LANGUAGE plpgsql VOLATILE
                     COST 100;

ALTER TABLE common.person_university_additional_details
    ADD CONSTRAINT puad_person_legal_nature_type_check
        CHECK (common.get_person_legal_nature_type(person_id) = 'U');