import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";
import { ReferenceData } from "@duosoftbg/nacid-components";

export interface PublicAccessApplication extends CommonApplication, StepperApplication {
  publicAccessDetails: PublicAccessDetails;
}

export interface PublicAccessDetails {
  about: string;
  comment: string;
  infoForms: ReferenceData[];
}
