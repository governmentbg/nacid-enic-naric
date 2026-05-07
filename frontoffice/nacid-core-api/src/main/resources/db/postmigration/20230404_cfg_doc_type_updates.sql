UPDATE nomenclatures.cfg_doc_type_to_app_type
    SET show_expression='inquiryDetails != null && inquiryDetails.inquiryKinds != null && (inquiryDetails.inquiryKinds.size() > 1 || !inquiryDetails.containsKindCode(''IMP''))'
    WHERE dte_id=148 and ate_code='LIB' and ase_code='INQ';