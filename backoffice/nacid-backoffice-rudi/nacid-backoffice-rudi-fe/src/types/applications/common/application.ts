export type ApplicationGroupName = "sar_application" | "udirec_application" | "docrec_application";

export type ApplicationGroup = { [key: string]: ApplicationGroupName };

type ApplicationTableColumns =
  | "entryNum"
  | "entryDate"
  | "applicantName"
  | "diplomaOwnerName"
  | "universityName"
  | "universityCountryName"
  | "eduLevelName"
  | "specialityName"
  | "apnStatusName"
  | "docflowStatusName"
  | "sarFlag"
  | "responsibleUserName"
  | "recognizedProfGroupName"
  | "recognizedQualification"
  | "backofficeDate";

export type ApplicationTableColumnsDefinition = {
  [key in ApplicationTableColumns]?: {
    id: ApplicationTableColumns;
    label: string;
    sortable: boolean;
    getValue: any;
    getStyle: any;
  };
};
