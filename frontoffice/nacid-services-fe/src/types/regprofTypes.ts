import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";
import { CommonApplicantDetails } from "./common/applicantDetailsTypes";
import {
  Country,
  EducationType,
  GraduationDocType,
  NaturalPersonIdentifier,
  NaturalPersonNames,
  ProfExperienceDocType,
  ReferenceData,
} from "@duosoftbg/nacid-components";
import { Speciality } from "./common/educationTypes";

export interface RegprofApplication extends StepperApplication, CommonApplication {
  applicantDetails: RegprofApplicantDetails;
  educationDetails: RegprofEducationDetails;
}

export interface RegprofApplicantDetails extends CommonApplicantDetails {
  qualificationNamesDifferent: boolean;
  qualificationNames: QualificationDocumentNames;
}

export interface QualificationDocumentNames extends NaturalPersonNames, NaturalPersonIdentifier {}

export interface RegprofEducationDetails {
  serviceType: ReferenceData;
  country: Country;
  educationSelected: boolean;
  experienceSelected: boolean;
  education: RegprofEducation;
  experience: RegprofExperience;
  nonRevokedRightToPractice: boolean;
  professionalQualificationRequested: string;
}

export interface RegprofEducation {
  kind: EducationType;
  educationEntryHigher: RegprofEducationEntry;
  educationEntrySecondary: RegprofEducationEntry;
  educationEntryADQ: RegprofEducationEntry;
}

export interface RegprofExperience {
  profession: string;
  experienceDocuments: ExperienceDocument[];
}

export interface ExperienceDocument {
  key: number;
  type: ProfExperienceDocType;
  documentNumber: string;
  documentDate: string;
  institutionName: string;
  workPeriods: WorkPeriod[];
}

export interface WorkPeriod {
  key: number;
  fromDate: string;
  toDate: string;
  workDayHours: ReferenceData;
}

export interface RegprofEducationEntry {
  oldEducationInstitutionName: string;
  oldEducationInstitutionId: string | number;
  newEducationInstitutionName: string;
  newEducationInstitutionId: string | number;
  professionalQualification: string;
  professionalQualificationId: string;
  specialities: Speciality[];
  specialitySingle: Speciality;
  documentKind: GraduationDocType;
  documentSeries: string;
  documentNumber: string;
  documentRegistrationNumber: string;
  documentDate: string;

  qualificationRank: ReferenceData;
  eduLevel: ReferenceData;
}
