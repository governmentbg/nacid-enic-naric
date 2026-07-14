--liquibase formatted sql

--changeset mnakova:core_0009 splitStatements:false
create or replace function nomenclatures.exists_refdata(p_domain character varying, p_code character varying) returns boolean
    language plpgsql
as
$$
declare v_res boolean ;
begin
SELECT case when p_code is null then true else EXISTS (SELECT 1 FROM nomenclatures.reference_data rd WHERE rd.domain = p_domain AND rd.code = p_code) end into v_res;
return v_res;
end;
$$;

alter function nomenclatures.exists_refdata(varchar, varchar) owner to postgres;

