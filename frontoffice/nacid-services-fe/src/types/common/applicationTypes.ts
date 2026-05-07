import { RudiApplicantDetails, CommonApplicantDetails } from "./applicantDetailsTypes";
import { RudiEducationDetails } from "./educationTypes";
import { DocumentDetails } from "./documentTypes";
import { DocumentReceiveMethod, ReceiverAddress } from "@duosoftbg/nacid-components";

export interface CommonApplication {
  dataStateStatus: string;
  id?: number;
  documentDetails: DocumentDetails;
  applicantDetails: CommonApplicantDetails;

  entryNumber?: string;
  entryDate?: string;
  submittedOrFinalized?: boolean;
}

export interface RudiApplication extends CommonApplication {
  applicantDetails: RudiApplicantDetails;
  educationDetails: RudiEducationDetails;
}

export enum FoApplicationStatus {
  DRAFT = "DRAFT",
  FINALIZED = "FINALIZED",
  SUBMITTED = "SUBMITTED",
  SUBMITTED_WITH_SIGNATURE = "SUBMITTED_WITH_SIGNATURE",
  ACCEPTED = "ACCEPTED",
}

export interface ApplicationDocumentReceiveMethod {
  resultReceive: DocumentReceiveMethod;
  receiverAddress: ReceiverAddress;
}
