import { UniChecksEducationDetails, UniChecksApplication } from "../types/uniChecksTypes";
import React from "react";
import { initialUniChecksApplicantDetails } from "./common/applicantDetailsInitialValues";
import {
  initialEducationDetails,
  initialRecognitionCategory,
  initialSpeciality,
} from "./common/educationInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import { ReferenceDataDomain, THUNK_STATUS } from "@duosoftbg/nacid-components";
import UniChecksStepApplicant from "../views/components/services/uniChecks/form/applicant/UniChecksStepApplicant";
import UniChecksStepDocuments from "../views/components/services/uniChecks/form/documents/UniChecksStepDocuments";
import UniChecksStepEducation from "../views/components/services/uniChecks/form/education/UniChecksStepEducation";
import UniChecksStepFiling from "../views/components/services/uniChecks/form/filing/UniChecksStepFiling";
import { initialServicesNaturalPerson } from "./common/personInitialValues";

export const initialUniChecksEducationDetails: UniChecksEducationDetails = {
  ...initialEducationDetails,
  ...initialRecognitionCategory,
  nacidOutgoingNumber: "",
  statute: false,
  authenticity: false,
  recommendation: false,
  serviceType: { id: "", name: "", domain: ReferenceDataDomain.SERVICE_TYPE },
  applicantIncomingNumber: "",

  specialities: [],
  specialitySingle: initialSpeciality,
  gainedQualification: "",
  originalGainedQualification: "",
  diplomaHolder: initialServicesNaturalPerson,
  diplomaHolderEan: "",
};

export const initialUniChecksApplication: UniChecksApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialUniChecksApplicantDetails,
  educationDetails: initialUniChecksEducationDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <UniChecksStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.diplomaEducation",
      completed: false,
      component: <UniChecksStepEducation />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <UniChecksStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <UniChecksStepFiling />,
      isEdited: false,
    },
  ],
};
