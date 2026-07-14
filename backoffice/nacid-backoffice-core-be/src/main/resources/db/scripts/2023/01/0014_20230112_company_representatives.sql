--liquibase formatted sql

--changeset ggeorgiev:0014.1 splitStatements:false
alter table common.person drop column person_id;
alter table common.application add column representative_company_id int;
alter table common.application
    add CONSTRAINT apn_representative_company_fk FOREIGN KEY (representative_company_id)
        REFERENCES common.person (id);

CREATE OR REPLACE FUNCTION common.get_person_legal_type(p_id integer)
    RETURNS varchar AS
$BODY$
declare v_res varchar ;
begin
    SELECT case when p_id is null then null else (select legal_type from common.person where id = p_id) end into v_res;
    return v_res;
end;
$BODY$
    LANGUAGE plpgsql VOLATILE
                     COST 100;

--changeset ggeorgiev:0014.2
ALTER TABLE common.application
    ADD CONSTRAINT apn_representative_check
        CHECK (representative_id is null or common.get_person_legal_type(representative_id) = 'NP');

ALTER TABLE common.application
    ADD CONSTRAINT apn_representative_company_check
        CHECK (representative_company_id is null or (representative_company_id is not null and representative_id is not null and common.get_person_legal_type(representative_company_id) = 'LE'));