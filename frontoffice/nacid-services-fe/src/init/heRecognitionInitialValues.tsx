import HERecognitionStepApplicant from "../views/components/services/higherEducation/form/applicant/HERecognitionStepApplicant";
import HERecognitionStepEducation from "../views/components/services/higherEducation/form/education/HERecognitionStepEducation";
import React from "react";
import HERecognitionStepDocuments from "../views/components/services/higherEducation/form/documents/HERecognitionStepDocuments";
import HERecognitionStepFiling from "../views/components/services/higherEducation/form/filing/HERecognitionStepFiling";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import {
  initialEducationDetails,
  initialPreviousUniversityDiploma,
  initialSpeciality,
} from "./common/educationInitialValues";
import { HEEducationDetails, HERecognitionApplication } from "../types/heRecognitionTypes";
import { initialRudiApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";

export const initialHERecognitionEducation: HEEducationDetails = {
  ...initialEducationDetails,
  previousUniversityDiploma: initialPreviousUniversityDiploma,
  specialities: [],
  specialitySingle: initialSpeciality,
  gainedQualification: "",
  originalGainedQualification: "",
  recognitionAim: [],
  recognitionAimOtherDetails: "",
};

export const initialHeRecognitionApplication: HERecognitionApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialRudiApplicantDetails,
  educationDetails: initialHERecognitionEducation,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <HERecognitionStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.education",
      completed: false,
      component: <HERecognitionStepEducation />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <HERecognitionStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <HERecognitionStepFiling />,
      isEdited: false,
    },
  ],
};
