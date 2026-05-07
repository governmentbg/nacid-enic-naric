--liquibase formatted sql

--changeset ggeorgiev:0132 splitStatements:false
alter table nomenclatures.cfg_service_type
    rename constraint cas_ase_fk to cst_ase_fk;
alter table nomenclatures.cfg_service_type
    rename constraint cas_ate_fk to cst_ate_fk;


CREATE INDEX rda_don_fk_idx ON nomenclatures.reference_data(domain);
CREATE INDEX ast_ate_fk_idx ON nomenclatures.application_subtype(ate_code);
CREATE INDEX muy_dit_fk_idx ON nomenclatures.ek_municipality(districtcode);
CREATE INDEX set_muy_fk_idx ON nomenclatures.ek_settlement(municipalitycode);
CREATE INDEX set_dit_fk_idx ON nomenclatures.ek_settlement(districtcode);
CREATE INDEX dte_dcy_ate_fk_idx ON nomenclatures.cfg_doc_type_to_doc_category(ate_code);
CREATE INDEX dte_dcy_ase_fk_idx ON nomenclatures.cfg_doc_type_to_doc_category(ase_code);
CREATE INDEX dte_dcy_dte_fk_idx ON nomenclatures.cfg_doc_type_to_doc_category(dte_id);
CREATE INDEX cas_ate_fk_idx ON nomenclatures.cfg_app_status(ate_code);
CREATE INDEX cas_ase_fk_idx ON nomenclatures.cfg_app_status(ase_code);
CREATE INDEX gdtc_gdt_fk_idx ON nomenclatures.cfg_graduation_document_type_config(graduation_document_type_id);
CREATE INDEX gdtc_coy_fk_idx ON nomenclatures.cfg_graduation_document_type_config(country_code);
CREATE INDEX ell_ate_ate_fk_idx ON nomenclatures.cfg_edu_level_to_app_type(ate_code);
CREATE INDEX ell_ate_ase_fk_idx ON nomenclatures.cfg_edu_level_to_app_type(ase_code);
CREATE INDEX gwy_ate_ate_fk_idx ON nomenclatures.cfg_graduation_way_to_app_type(ate_code);
CREATE INDEX gwy_ate_ase_fk_idx ON nomenclatures.cfg_graduation_way_to_app_type(ase_code);
CREATE INDEX rfd_mms_fk_idx ON nomenclatures.cfg_report_field(sql_code);
CREATE INDEX ads_acy_fk_idx ON common.address(coy_code);
CREATE INDEX ads_set_fk_idx ON common.address(set_code);
CREATE INDEX pen_pdc_fk_idx ON common.person(foreign_identifier_country);
CREATE INDEX pen_bcy_fk_idx ON common.person(origin_country);
CREATE INDEX pen_cip_fk_idx ON common.person(citizenship_id);
CREATE INDEX pen_cit_fk_idx ON common.person(civil_id_type);
CREATE INDEX pen_origin_set_fk_idx ON common.person(origin_set_code);
CREATE INDEX apn_ate_fk_idx ON common.application(ate_code);
CREATE INDEX apn_ase_fk_idx ON common.application(ase_code);
CREATE INDEX apn_apt_fk_idx ON common.application(applicant_id);
CREATE INDEX apn_efiling_id_idx ON common.application(efiling_id);
CREATE INDEX aru_apn_fk_idx ON common.application_responsible_users(apn_id);
CREATE INDEX ash_lrn_fk_idx ON common.app_status_history(legal_reason_id);
CREATE INDEX adsh_apn_fk_idx ON common.app_docflow_status_history(apn_id);
CREATE INDEX tin_coy_fk_idx ON rudi.training_institution(country_code);
CREATE INDEX ace_apn_fk_idx ON common.application_certificates(apn_id);
CREATE INDEX pin_coy_fk_idx ON regprof.professional_institution(country_code);
CREATE INDEX rle_apn_fk_idx ON libserv.bibliographic_reference_language(apn_id);
CREATE INDEX apn_notes_apn_fk_idx ON common.application_notes(apn_id);
CREATE INDEX tlel_tln_fk_idx ON rudi.training_location_examination_locations(training_location_id);
CREATE INDEX impact_factor_report_rows_inquiry_id_fkey_idx ON libserv.impact_factor_report_rows(apn_id);
CREATE INDEX cst_ate_fk_idx ON nomenclatures.cfg_service_type(ate_code);
CREATE INDEX cst_ase_fk_idx ON nomenclatures.cfg_service_type(ase_code);
CREATE INDEX tcu_tce_fk_idx ON rudi.training_course_universities(tce_id);
CREATE INDEX dte_ass_dte_fk_idx ON nomenclatures.cfg_doc_type_to_app_status(dte_id);
CREATE INDEX dte_ass_ate_fk_idx ON nomenclatures.cfg_doc_type_to_app_status(ate_code);
CREATE INDEX cfg_lae_ate_ase_fk_idx ON nomenclatures.cfg_language_to_app_type(ase_code);
CREATE INDEX cfg_lae_ate_ate_fk_idx ON nomenclatures.cfg_language_to_app_type(ate_code);
CREATE INDEX ddd_apn_fk_idx ON libserv.document_delivery_details(apn_id);
CREATE INDEX rcy_ate_ate_fk_idx ON nomenclatures.cfg_recognition_category_to_app_type(ate_code);
CREATE INDEX rcy_ate_ase_fk_idx ON nomenclatures.cfg_recognition_category_to_app_type(ase_code);
CREATE INDEX publication_period_rows_apn_fk_idx ON libserv.publication_period_rows(apn_id);
CREATE INDEX publication_rows_period_fk_idx ON libserv.publication_rows(publication_period_id);
CREATE INDEX cfg_lrn_ate_ase_fk_idx ON nomenclatures.cfg_legal_reason_to_app_type(ase_code);
CREATE INDEX cfg_lrn_ate_ate_fk_idx ON nomenclatures.cfg_legal_reason_to_app_type(ate_code);
CREATE INDEX cfg_lrn_ate_lrn_fk_idx ON nomenclatures.cfg_legal_reason_to_app_type(lrn_id);
CREATE INDEX citation_rows_publication_fk_idx ON libserv.citation_rows(publication_id);