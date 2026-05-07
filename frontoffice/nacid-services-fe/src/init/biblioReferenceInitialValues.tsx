import { BibliographicReferenceDetails, BiblioReferenceApplication } from "../types/biblioReferenceTypes";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import BiblioReferenceStepApplicant from "../views/components/services/bibliographicReference/form/applicant/BiblioReferenceStepApplicant";
import BiblioReferenceStepDocuments from "../views/components/services/bibliographicReference/form/documents/BiblioReferenceStepDocuments";
import BiblioReferenceStepFiling from "../views/components/services/bibliographicReference/form/filing/BiblioReferenceStepFiling";
import BiblioReferenceStepBibliographic from "../views/components/services/bibliographicReference/form/bibliographic/BiblioReferenceStepBibliographic";

export const initialBibliographicReferenceDetails: BibliographicReferenceDetails = {
  foreignSearch: false,
  nacidSearch: false,
  foreignSearchKind: null,
  nacidSearchKind: null,
  keywords: "",
  searchLanguages: [],
  searchFrom: "",
  searchTo: "",
  theme: "",
};

export const initialBiblioReferenceApplication: BiblioReferenceApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  bibliographicReferenceDetails: initialBibliographicReferenceDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <BiblioReferenceStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.bibliographicDetails",
      completed: false,
      component: <BiblioReferenceStepBibliographic />,
      isEdited: false,
    },
    {
      labelCode: "l.step.otherAttachments",
      completed: false,
      component: <BiblioReferenceStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <BiblioReferenceStepFiling />,
      isEdited: false,
    },
  ],
};
