import { LegalReasonDetails, ReferenceDataDetails } from "@duosoftbg/nacid-backoffice-components";

export interface StatusDetails {
  status: ReferenceDataDetails;
  legalReason: LegalReasonDetails;
  submittedDocs: string;
  docflowStatus: ReferenceDataDetails;
  archiveNumber: string;
}
