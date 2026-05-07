import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";

export interface InquiryApplication extends CommonApplication, StepperApplication {
  inquiryDetails: InquiryDetails;
}

export interface InquiryDetails {
  inquiryKinds: string[];
  inquiryAim: string;
  periodFrom: string;
  periodTo: string;
  previousInquiryNum: string;
}
