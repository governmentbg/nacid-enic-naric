import {
  Diploma,
  RudiEducationDetails,
  PreviousUniversityDiploma,
  UniversityData,
  EducationPlace,
  Speciality,
  WithRecognitionCategory,
} from "../../types/common/educationTypes";
import { ReferenceDataDomain } from "@duosoftbg/nacid-components";
import { initialCountryEmpty } from "./addressInitialValues";

export const initialUniversityData: UniversityData = {
  key: 10,
  name: "",
  nameId: "",
  faculty: "",
  facultyId: "",
  universityContact: "",
};

export const initialEducationPlace: EducationPlace = {
  key: 10,
  country: initialCountryEmpty,
  city: "",
};

export const initialDiploma: Diploma = {
  date: undefined,
  number: "",
  registrationNumber: "",
  series: "",
};

export const initialPreviousUniversityDiploma: PreviousUniversityDiploma = {
  gainedLevel: { id: "", name: "", domain: ReferenceDataDomain.EDUCATION_LEVEL },
  notes: "",
  graduationYear: "",
  universityName: "",
  speciality: "",
};

export const initialEducationDetails: RudiEducationDetails = {
  universitiesData: [initialUniversityData],
  diploma: initialDiploma,
  educationPlaces: [initialEducationPlace],
  startOfEducation: "",
  endOfEducation: "",
  educationDuration: "",
  educationDurationType: { id: "", name: "", domain: ReferenceDataDomain.DURATION_UNIT },
  educationForm: { id: "", name: "", domain: ReferenceDataDomain.TRAINING_FORM },
  educationFormOtherDetails: "",
  graduationWay: [],
  graduationWayOtherDetails: "",
  credits: "",
  originalGainedLevel: "",
  originalGainedLevelTranslated: "",
};

export const initialSpeciality: Speciality = {
  id: "",
  name: "",
  originalName: "",
};

export const initialRecognitionCategory: WithRecognitionCategory = {
  recognitionCategory: { id: "", name: "", domain: ReferenceDataDomain.RECOGNITION_CATEGORY },
};
