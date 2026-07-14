import { StepperApplication } from "./common/stepsTypes";
import {
  RudiEducationDetails,
  WithGainedQualification,
  WithPreviousUniversityDiploma,
  WithSpecialities,
} from "./common/educationTypes";
import { RudiApplication } from "./common/applicationTypes";
import { Country, ReferenceData } from "@duosoftbg/nacid-components";

export interface HERecognitionApplication extends StepperApplication, RudiApplication {
  educationDetails: HEEducationDetails;
}

export interface HEEducationDetails
  extends RudiEducationDetails,
    WithPreviousUniversityDiploma,
    WithSpecialities,
    WithGainedQualification {
  highSchoolDiploma?: HighSchoolDiploma;
  recognitionAim: ReferenceData[];
  recognitionAimOtherDetails: string;
}

export interface HighSchoolDiploma {
  country: Country;
  city: string;
  school: string;
  graduationYear: string;
  notes: string;
}
