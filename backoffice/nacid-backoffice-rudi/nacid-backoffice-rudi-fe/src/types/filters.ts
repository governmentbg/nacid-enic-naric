export type GroupName =
  | "sf_sar_applications"
  | "sf_fo_sar_applications"
  | "sf_docrec_applications"
  | "sf_fo_docrec_applications"
  | "sf_udirec_applications"
  | "sf_fo_udirec_applications"
  | "sf_calendar_diploma_recognitions"
  | "sf_calendar__members"
  | "sf_commission_calendar"
  | "sf_common_report"
  | "sf_applicant_report"
  | "sf_fo_additional_documents_applications"
  | "sf_fo_duplicate_applications";

export type GroupKey =
  | "SAR_APPLICATIONS"
  | "FO_SAR_APPLICATIONS"
  | "DOCREC_APPLICATIONS"
  | "FO_DOCREC_APPLICATIONS"
  | "UDIREC_APPLICATIONS"
  | "FO_UDIREC_APPLICATIONS"
  | "CALENDAR_DIPLOMA_RECOGNITIONS"
  | "COMMISSION_CALENDAR"
  | "CALENDAR_MEMBERS"
  | "COMMON_REPORT"
  | "FO_ADDITIONAL_DOCUMENTS_APPLICATIONS"
  | "FO_DUPLICATE_APPLICATIONS";

export type FilterGroup = { [key in GroupKey]: GroupName };
