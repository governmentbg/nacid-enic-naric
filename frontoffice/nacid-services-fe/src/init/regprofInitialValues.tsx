import {
  ExperienceDocument,
  RegprofApplicantDetails,
  RegprofEducation,
  RegprofEducationDetails,
  RegprofEducationEntry,
  RegprofExperience,
  RegprofApplication,
  WorkPeriod,
} from "../types/regprofTypes";
import { ReferenceDataDomain, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import RegprofStepApplicant from "../views/components/services/regprof/form/applicant/RegprofStepApplicant";
import RegprofStepEducation from "../views/components/services/regprof/form/education/RegprofStepEducation";
import RegprofStepDocuments from "../views/components/services/regprof/form/documents/RegprofStepDocuments";
import RegprofStepFiling from "../views/components/services/regprof/form/filing/RegprofStepFiling";
import { initialCommonApplicantDetails, initialResultReceive } from "./common/applicantDetailsInitialValues";
import { initialCountryEmpty, initialReceiverAddress } from "./common/addressInitialValues";
import { IdentifierType } from "@duosoftbg/nacid-frontoffice-components";
import { initialSpeciality } from "./common/educationInitialValues";

export const initialRegprofApplicantDetails: RegprofApplicantDetails = {
  ...initialCommonApplicantDetails,

  agreeDataUsage: false,

  qualificationNames: {
    firstName: "",
    middleName: "",
    lastName: "",
    personalId: "",
    personalNacidId: "",
    personalIdType: IdentifierType.NATIONAL_ID,
    foreignerIdentifierCountry: { id: "", name: "" },
    foreignerIdentifierKind: { id: "", name: "", domain: "FOREIGN_IDENTIFIER_TYPE" },
  },
  qualificationNamesDifferent: false,
  resultReceive: null,
  resultReceiveElectronic: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  resultReceivePaper: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  certificateReceiveForms: [],
};

export const initialRegprofEducationEntry: RegprofEducationEntry = {
  qualificationRank: { id: "", name: "", domain: ReferenceDataDomain.QUALIFICATION_RANK },
  eduLevel: { id: "", name: "", domain: ReferenceDataDomain.EDUCATION_LEVEL },
  documentDate: "",
  documentKind: { id: "", name: "", educationTypes: [] },
  documentNumber: "",
  oldEducationInstitutionName: "",
  oldEducationInstitutionId: "",
  newEducationInstitutionName: "",
  newEducationInstitutionId: "",
  documentRegistrationNumber: "",
  documentSeries: "",
  professionalQualification: "",
  professionalQualificationId: "",
  specialities: [],
  specialitySingle: initialSpeciality,
};

export const initialRegprofEducation: RegprofEducation = {
  kind: null,
  educationEntrySecondary: initialRegprofEducationEntry,
  educationEntryHigher: initialRegprofEducationEntry,
  educationEntryADQ: initialRegprofEducationEntry,
};

export const initialWorkPeriod: WorkPeriod = {
  key: 10,
  fromDate: "",
  toDate: "",
  workDayHours: { id: "", name: "", domain: ReferenceDataDomain.WORKDAY_DURATION },
};

export const initialRegprofExperienceDocument: ExperienceDocument = {
  key: 10,
  documentDate: "",
  documentNumber: "",
  institutionName: "",
  type: { id: "", name: "", forExperienceCalculation: false },
  workPeriods: [initialWorkPeriod],
};

export const initialRegprofExperience: RegprofExperience = {
  profession: "",
  experienceDocuments: [initialRegprofExperienceDocument],
};

export const initialRegprofEducationDetails: RegprofEducationDetails = {
  serviceType: { id: "", name: "", domain: ReferenceDataDomain.SERVICE_TYPE },
  country: initialCountryEmpty,
  educationSelected: false,
  experienceSelected: false,
  education: initialRegprofEducation,
  experience: initialRegprofExperience,
  nonRevokedRightToPractice: false,
  professionalQualificationRequested: "",
};

export const initialRegprofApplication: RegprofApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialRegprofApplicantDetails,
  educationDetails: initialRegprofEducationDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <RegprofStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.education.experience",
      completed: false,
      component: <RegprofStepEducation />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <RegprofStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <RegprofStepFiling />,
      isEdited: false,
    },
  ],
};
