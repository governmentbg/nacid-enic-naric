import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";
import { Language } from "@duosoftbg/nacid-components";

export interface BiblioReferenceApplication extends CommonApplication, StepperApplication {
  bibliographicReferenceDetails: BibliographicReferenceDetails;
}

export interface BibliographicReferenceDetails {
  foreignSearch: boolean;
  nacidSearch: boolean;
  foreignSearchKind: string;
  nacidSearchKind: string;
  theme: string;
  keywords: string;
  searchFrom: string;
  searchTo: string;
  searchLanguages: Language[];
}
