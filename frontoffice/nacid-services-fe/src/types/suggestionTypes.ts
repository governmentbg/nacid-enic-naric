import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";

export interface SuggestionApplication extends CommonApplication, StepperApplication {
  suggestionDetails: SuggestionDetails;
}

export interface SuggestionDetails {
  suggestion: string;
}
