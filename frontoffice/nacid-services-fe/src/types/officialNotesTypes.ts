import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";
import {ReferenceData} from "@duosoftbg/nacid-components";

export interface OfficialNotesApplication extends CommonApplication, StepperApplication {
  officialNotesDetails: OfficialNotesDetails;
}

export interface OfficialNotesDetails {
  serviceType: ReferenceData;
  officialNotesKinds: string[];
  additionalInformation: string;
}
