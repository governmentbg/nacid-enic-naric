import { DocDegreesApplication, DocEducationDetails } from "../types/docDegreesTypes";
import {
  initialEducationDetails,
  initialRecognitionCategory,
  initialPreviousUniversityDiploma,
} from "./common/educationInitialValues";
import { ReferenceDataDomain, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialRudiApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import DocDegreesStepApplicant from "../views/components/services/doctorateDegrees/form/applicant/DocDegreesStepApplicant";
import DocDegreesStepEducation from "../views/components/services/doctorateDegrees/form/education/DocDegreesStepEducation";
import DocDegreesStepDocuments from "../views/components/services/doctorateDegrees/form/documents/DocDegreesStepDocuments";
import DocDegreesStepFiling from "../views/components/services/doctorateDegrees/form/filing/DocDegreesStepFiling";

export const initialDocDegreesEducation: DocEducationDetails = {
  ...initialEducationDetails,
  ...initialRecognitionCategory,
  previousUniversityDiploma: initialPreviousUniversityDiploma,

  gainedLevelProfGroup: {
    id: "",
    name: "",
    educationArea: { id: "", name: "", domain: ReferenceDataDomain.EDUCATION_AREA },
  },

  dissertationTheme: "",
  dissertationThemeEn: "",
  dissertationDate: "",
  dissertationLanguage: { id: "", name: "" },
  dissertationBiblioTitlesCount: "",
  dissertationPagesCount: "",
  dissertationAnnotation: "",
  dissertationAnnotationEn: "",
};

export const initialDocDegreesApplication: DocDegreesApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialRudiApplicantDetails,
  educationDetails: initialDocDegreesEducation,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <DocDegreesStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.docEducation",
      completed: false,
      component: <DocDegreesStepEducation />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <DocDegreesStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <DocDegreesStepFiling />,
      isEdited: false,
    },
  ],
};
