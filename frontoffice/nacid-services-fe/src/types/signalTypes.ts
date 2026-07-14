import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";

export interface SignalApplication extends CommonApplication, StepperApplication {
  signalDetails: SignalDetails;
}

export interface SignalDetails {
  violationDescription: string;
  violationPlace: string;
  checkRequirement: string;
  damagesDescription: string;
  measuresTaken: string;
}
