import { FilterGroup } from "../../../types/filters";
import { sarFilterInitialValues } from "../../../init/application/sarFilterInitialValues";
import { udirecFilterInitialValues } from "../../../init/application/udirecFilterInitialValues";
import { docrecFilterInitialValues } from "../../../init/application/docrecFilterInitialValues";
import { commissionCalendarFilterInitialValues } from "../../../init/commissionCalendar/commissionCalendarFilterInitialValues";
import { commonReportSearchFiltersInitialValues } from "../../../init/report/commonReportSearchFiltersInitialValues";
import { InitialValues } from "@duosoftbg/nacid-backoffice-components";
import { foSarFilterInitialValues } from "../../../init/application/electronicSubmission/foSarFilterInitialValues";
import { foDocrecFilterInitialValues } from "../../../init/application/electronicSubmission/foDocrecFilterInitialValues";
import { foUdirecFilterInitialValues } from "../../../init/application/electronicSubmission/foUdirecFilterInitialValues";
import { foAdditionalDocumentsFilterInitialValues } from "../../../init/application/electronicSubmission/foAdditionalDocumentsFilterInitialValues";
import { foDuplicateFilterInitialValues } from "../../../init/application/electronicSubmission/foDuplicateFilterInitialValues";

export const SEARCH_FILTERS_GROUP: FilterGroup = {
  SAR_APPLICATIONS: "sf_sar_applications",
  FO_SAR_APPLICATIONS: "sf_fo_sar_applications",
  DOCREC_APPLICATIONS: "sf_docrec_applications",
  FO_DOCREC_APPLICATIONS: "sf_fo_docrec_applications",
  UDIREC_APPLICATIONS: "sf_udirec_applications",
  FO_UDIREC_APPLICATIONS: "sf_fo_udirec_applications",
  FO_ADDITIONAL_DOCUMENTS_APPLICATIONS: "sf_fo_additional_documents_applications",
  CALENDAR_DIPLOMA_RECOGNITIONS: "sf_calendar_diploma_recognitions",
  COMMISSION_CALENDAR: "sf_commission_calendar",
  CALENDAR_MEMBERS: "sf_calendar__members",
  COMMON_REPORT: "sf_common_report",
  FO_DUPLICATE_APPLICATIONS: "sf_fo_duplicate_applications",
};

export const SEARCH_TABLE_CONFIG = {
  [SEARCH_FILTERS_GROUP.SAR_APPLICATIONS]: {
    initValue: sarFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.FO_SAR_APPLICATIONS]: {
    initValue: foSarFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.FO_DOCREC_APPLICATIONS]: {
    initValue: foDocrecFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.FO_UDIREC_APPLICATIONS]: {
    initValue: foUdirecFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.DOCREC_APPLICATIONS]: {
    initValue: docrecFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.UDIREC_APPLICATIONS]: {
    initValue: udirecFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.CALENDAR_DIPLOMA_RECOGNITIONS]: {
    initValue: udirecFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.COMMISSION_CALENDAR]: {
    initValue: commissionCalendarFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.CALENDAR_MEMBERS]: {
    initValue: InitialValues.filter.commissionMember.commissionMembersFilterInitialValues,
  },
  [SEARCH_FILTERS_GROUP.COMMON_REPORT]: {
    initValue: commonReportSearchFiltersInitialValues,
    hasSearchStarted: false,
  },
  [SEARCH_FILTERS_GROUP.FO_ADDITIONAL_DOCUMENTS_APPLICATIONS]: {
    initValue: foAdditionalDocumentsFilterInitialValues,
    hasSearchStarted: true,
  },
  [SEARCH_FILTERS_GROUP.FO_DUPLICATE_APPLICATIONS]: {
    initValue: foDuplicateFilterInitialValues,
    hasSearchStarted: true,
  },
};
