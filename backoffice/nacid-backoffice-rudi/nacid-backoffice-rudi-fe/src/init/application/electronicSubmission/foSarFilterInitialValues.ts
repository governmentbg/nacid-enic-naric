import {
  FoRudiApplicationSubType,
  FoRudiApplicationType,
  TextSearchType,
} from "@duosoftbg/nacid-backoffice-components";
import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER } from "@duosoftbg/nacid-components";
import { FoApplicationFilterDetails } from "../../../types/applications/electronicSubmission/foApplicationTypes";

export let foSarFilterInitialValues: FoApplicationFilterDetails;
foSarFilterInitialValues = {
  tempNumber: "",
  applicantName: "",
  dateLastSubmittedFrom: "",
  dateLastSubmittedTo: "",
  applicationType: FoRudiApplicationType.academicRecognition,
  applicationSubtype: FoRudiApplicationSubType.uniChecks,
  applicantNameSearchType: TextSearchType.CONTAINS_WORDS,
  applicantCivilId: "",
  foStatusSelectValue: "",
  statute: false,
  authenticity: false,
  recommendation: false,
  signed: "",
  paid: "",
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "id",
};
