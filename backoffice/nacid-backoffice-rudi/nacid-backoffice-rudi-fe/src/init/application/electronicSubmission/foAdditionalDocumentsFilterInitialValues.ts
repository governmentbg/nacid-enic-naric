import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER } from "@duosoftbg/nacid-components";
import { FoRudiApplicationType, TextSearchType } from "@duosoftbg/nacid-backoffice-components";
import {
  FoAdditionalDocumentsFilterDetails,
  FoRudiApplicationSubTypes,
} from "../../../types/applications/electronicSubmission/foApplicationTypes";

export const foAdditionalDocumentsFilterInitialValues: FoAdditionalDocumentsFilterDetails = {
  entryNumber: "",
  applicantName: "",
  dateLastSubmittedFrom: "",
  dateLastSubmittedTo: "",
  applicationType: FoRudiApplicationType.academicRecognition,
  applicationSubtype: FoRudiApplicationSubTypes.rudiAdditionalDocuments,
  applicantNameSearchType: TextSearchType.CONTAINS_WORDS,
  applicantCivilId: "",
  foStatusSelectValue: "",
  signed: "",
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "lastSubmissionDate",
};
