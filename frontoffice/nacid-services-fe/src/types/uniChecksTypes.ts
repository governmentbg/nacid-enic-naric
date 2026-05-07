import { StepperApplication } from "./common/stepsTypes";
import { CommonApplication } from "./common/applicationTypes";
import {
  RudiEducationDetails,
  WithRecognitionCategory,
  WithGainedQualification,
  WithSpecialities,
} from "./common/educationTypes";
import { ServicesNaturalPerson } from "./common/personTypes";
import { ReferenceData } from "@duosoftbg/nacid-components";

export interface UniChecksApplication extends StepperApplication, CommonApplication {
  educationDetails: UniChecksEducationDetails;
}

export interface UniChecksEducationDetails
  extends RudiEducationDetails,
    WithSpecialities,
    WithGainedQualification,
    WithRecognitionCategory {
  statute: boolean;
  authenticity: boolean;
  recommendation: boolean;
  serviceType: ReferenceData;
  nacidOutgoingNumber: string;
  applicantIncomingNumber: string;
  diplomaHolder: ServicesNaturalPerson;
  diplomaHolderEan: string;
}
