import { StepperApplication } from "./common/stepsTypes";
import { RudiEducationDetails, WithRecognitionCategory, WithPreviousUniversityDiploma } from "./common/educationTypes";
import { RudiApplication } from "./common/applicationTypes";
import { Language, ProfGroup } from "@duosoftbg/nacid-components";

export interface DocDegreesApplication extends StepperApplication, RudiApplication {
  educationDetails: DocEducationDetails;
}

export interface DocEducationDetails
  extends RudiEducationDetails,
    WithPreviousUniversityDiploma,
    WithRecognitionCategory {
  dissertationTheme: string;
  dissertationThemeEn: string;
  dissertationDate: string;
  dissertationLanguage: Language;
  dissertationBiblioTitlesCount: string;
  dissertationPagesCount: string;
  dissertationAnnotation: string;
  dissertationAnnotationEn: string;

  gainedLevelProfGroup: ProfGroup;
}
