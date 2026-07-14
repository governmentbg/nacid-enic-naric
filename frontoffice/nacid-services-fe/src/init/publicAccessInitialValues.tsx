import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import { PublicAccessApplication, PublicAccessDetails } from "../types/publicAccessTypes";
import PublicAccessStepApplicant from "../views/components/services/publicAccess/form/applicant/PublicAccessStepApplicant";
import PublicAccessStepPublicAccess from "../views/components/services/publicAccess/form/publicAccess/PublicAccessStepPublicAccess";
import PublicAccessStepDocuments from "../views/components/services/publicAccess/form/documents/PublicAccessStepDocuments";
import PublicAccessStepFiling from "../views/components/services/publicAccess/form/filing/PublicAccessStepFiling";

export const initialPublicAccessDetails: PublicAccessDetails = {
  about: "",
  comment: "",
  infoForms: [],
};

export const initialPublicAccessApplication: PublicAccessApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  publicAccessDetails: initialPublicAccessDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <PublicAccessStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.publicAccess",
      completed: false,
      component: <PublicAccessStepPublicAccess />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <PublicAccessStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <PublicAccessStepFiling />,
      isEdited: false,
    },
  ],
};
