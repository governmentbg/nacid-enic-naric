--liquibase formatted sql

--changeset raneva:services_0016
update services.application_document_receive_method adrm
    set crf_code = (select crf_code from nomenclatures.document_receive_method drm where drm.code = adrm.document_receive_method_code)
    where crf_code is null and apn_id in (select id from services.application where ase_code != 'SAR' and ate_code in ('RP', 'AR'));
