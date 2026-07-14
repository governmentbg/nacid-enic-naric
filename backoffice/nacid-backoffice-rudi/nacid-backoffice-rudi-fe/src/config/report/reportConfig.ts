import { REPORT_FILTERS_DEFINITION } from "./filters/definition";
import { ReportGroup } from "../../types/report/base/report";
import { commonReportSearchFiltersInitialValues } from "../../init/report/commonReportSearchFiltersInitialValues";
import { REPORT_TABLE_COLUMNS_DEFINITION } from "./table/definition";

export const REPORT_GROUP: ReportGroup = {
  COMMON_REPORT: "common_report",
};

export const REPORT_CONFIG = {
  [REPORT_GROUP.COMMON_REPORT]: {
    filters: [
      { id: REPORT_FILTERS_DEFINITION.applicationType.id, required: true },
      { id: REPORT_FILTERS_DEFINITION.commission.id, required: true },
      { id: REPORT_FILTERS_DEFINITION.submissionMethod.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.serviceType.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.diploma.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.status.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.application.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.documentType.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.documentReceiveMethod.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.applicationUserCreated.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.applicationResponsibleUser.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.university.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.legalApplicant.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.trainingInstitution.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.diplomaSpeciality.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.recognizedDiplomaSpeciality.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.naturalPersonApplicant.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.diplomaOwner.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.diplomaName.id, required: false },
      { id: REPORT_FILTERS_DEFINITION.representative.id, required: false },
    ],
    tableColumns: [
      { id: REPORT_TABLE_COLUMNS_DEFINITION.entryNum.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.entryDate.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.backofficeDate.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.ownerNames.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.personalIdentifier.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.university.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.eduLevel.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.diplomaSpeciality.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.recognizedEduLevel.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.recognizedSpeciality.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.recognizedQualification.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.recognizedProfGroup.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.applicationStatus.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.docflowStatus.id, active: true },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.personalDocumentType.id, active: false },
      { id: REPORT_TABLE_COLUMNS_DEFINITION.serviceType.id, active: false },
    ],
    initialValue: commonReportSearchFiltersInitialValues,
  },
};
