import { SuggestionDetails, SuggestionApplication } from "../types/suggestionTypes";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import SuggestionStepApplicant from "../views/components/services/suggestion/form/applicant/SuggestionStepApplicant";
import SuggestionStepSuggestion from "../views/components/services/suggestion/form/suggestion/SuggestionStepSuggestion";
import SuggestionStepDocuments from "../views/components/services/suggestion/form/documents/SuggestionStepDocuments";
import SuggestionStepFiling from "../views/components/services/suggestion/form/filing/SuggestionStepFiling";

export const initialSuggestionDetails: SuggestionDetails = {
  suggestion: "",
};

export const initialSuggestionApplication: SuggestionApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  suggestionDetails: initialSuggestionDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <SuggestionStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.suggestion",
      completed: false,
      component: <SuggestionStepSuggestion />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <SuggestionStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <SuggestionStepFiling />,
      isEdited: false,
    },
  ],
};
