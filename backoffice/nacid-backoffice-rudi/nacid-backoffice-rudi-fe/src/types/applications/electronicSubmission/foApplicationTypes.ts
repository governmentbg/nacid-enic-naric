export const FoRudiApplicationSubTypes = {
  rudiAdditionalDocuments: "RUDI_ADDITIONAL_DOCUMENTS",
  rudiDuplicate: "RUDI_DUPLICATE",
};

export interface FoApplicationFilterDetails {
  tempNumber: string;
  dateLastSubmittedFrom: string;
  dateLastSubmittedTo: string;
  applicantName: string;
  applicationType: string;
  applicationSubtype: string;
  applicantNameSearchType: string;
  applicantCivilId: string;
  foStatusSelectValue: string;
  statute: boolean;
  authenticity: boolean;
  recommendation: boolean;
  signed: string;
  paid: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}

export interface FoAdditionalDocumentsFilterDetails {
  entryNumber: string;
  applicantName: string;
  dateLastSubmittedFrom: string;
  dateLastSubmittedTo: string;
  applicationType: string;
  applicationSubtype: string;
  applicantNameSearchType: string;
  applicantCivilId: string;
  foStatusSelectValue: string;
  signed: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}

export interface FoDuplicateFilterDetails {
  entryNumber: string;
  applicantName: string;
  dateLastSubmittedFrom: string;
  dateLastSubmittedTo: string;
  applicationType: string;
  applicationSubtype: string;
  applicantNameSearchType: string;
  applicantCivilId: string;
  foStatusSelectValue: string;
  signed: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}
