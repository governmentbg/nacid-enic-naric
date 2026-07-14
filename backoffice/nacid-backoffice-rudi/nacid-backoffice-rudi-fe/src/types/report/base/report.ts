export type ReportGroupName = "common_report" | "applicant_report";

export type ReportGroup = { [key: string]: ReportGroupName };

type ReportFilterName =
  | "applicationType"
  | "commission"
  | "submissionMethod"
  | "diploma"
  | "status"
  | "application"
  | "documentType"
  | "documentReceiveMethod"
  | "applicationUserCreated"
  | "applicationResponsibleUser"
  | "university"
  | "legalApplicant"
  | "trainingInstitution"
  | "diplomaSpeciality"
  | "recognizedDiplomaSpeciality"
  | "naturalPersonApplicant"
  | "diplomaOwner"
  | "diplomaName"
  | "representative"
  | "serviceType";

export type ReportFiltersDefinition = {
  [key in ReportFilterName]?: {
    id: ReportFilterName;
    label: string;
  };
};

type ReportTableColumns =
  | "entryNum"
  | "entryDate"
  | "backofficeDate"
  | "ownerNames"
  | "personalIdentifier"
  | "university"
  | "eduLevel"
  | "diplomaSpeciality"
  | "recognizedEduLevel"
  | "recognizedSpeciality"
  | "applicationStatus"
  | "docflowStatus"
  | "serviceType"
  | "recognizedQualification"
  | "recognizedProfGroup"
  | "personalDocumentType";

export type ReportTableColumnsDefinition = {
  [key in ReportTableColumns]?: {
    id: ReportTableColumns;
    label: string;
    sortable: boolean;
    getValue: any;
  };
};
