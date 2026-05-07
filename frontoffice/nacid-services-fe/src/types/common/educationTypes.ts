import { Country, ReferenceData } from "@duosoftbg/nacid-components";

export interface RudiEducationDetails {
  universitiesData: UniversityData[];
  diploma: Diploma;
  educationPlaces: EducationPlace[];
  startOfEducation: string;
  endOfEducation: string;
  educationDuration: string;
  educationDurationType: ReferenceData;
  educationForm: ReferenceData;
  educationFormOtherDetails: "";
  graduationWay: ReferenceData[];
  graduationWayOtherDetails: string;
  credits: string;

  originalGainedLevel: string;
  originalGainedLevelTranslated: string;
}

export interface WithPreviousUniversityDiploma {
  previousUniversityDiploma: PreviousUniversityDiploma;
}

export interface WithRecognitionCategory {
  recognitionCategory: ReferenceData;
}

export interface WithSpecialities {
  specialities: Speciality[];
  specialitySingle: Speciality;
}

export interface Speciality {
  id: string | number;
  name: string;
  originalName: string;
}

export interface WithGainedQualification {
  gainedQualification: string;
  originalGainedQualification: string;
}

export interface UniversityData {
  key: number;
  name: string;
  nameId: string | number;
  faculty: string;
  facultyId: string | number;
  universityContact: string;
}

export interface Diploma {
  series: string;
  number: string;
  registrationNumber: string;
  date: string;
}

export interface EducationPlace {
  key: number;
  country: Country;
  city: string;
}

export interface PreviousUniversityDiploma {
  universityName: string;
  universityNameId?: string | number;
  gainedLevel: ReferenceData;
  speciality: string;
  graduationYear: string;
  notes: string;
}
