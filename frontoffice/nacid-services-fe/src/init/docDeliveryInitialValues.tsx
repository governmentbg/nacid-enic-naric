import {
  DocBibliographicDetails,
  DocBibliographicEntryDetails,
  DocDeliveryApplication,
} from "../types/docDeliveryTypes";
import { ReferenceDataDomain, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails, initialFile } from "./common/documentInitialValues";
import React from "react";
import DocDeliveryStepApplicant from "../views/components/services/documentDelivery/form/applicant/DocDeliveryStepApplicant";
import DocDeliveryStepBibliographic from "../views/components/services/documentDelivery/form/bibliographic/DocDeliveryStepBibliographic";
import DocDeliveryStepFiling from "../views/components/services/documentDelivery/form/filing/DocDeliveryStepFiling";

export const initialBibliographicEntryDetails: DocBibliographicEntryDetails = {
  key: 10,
  bibliographicDataText: "",
  deliveryResultKind: { id: "", name: "", domain: ReferenceDataDomain.DOCUMENT_DELIVERY_COPY_TYPE },
  electronicCatalogues: false,
  bgLibraries: false,
  foreignLibraries: false,
  file: initialFile,
  forRemoval: false,
};

export const initialBibliographicDetails: DocBibliographicDetails = {
  entries: [],
};

export const initialDocDeliveryApplication: DocDeliveryApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  bibliographicDetails: initialBibliographicDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <DocDeliveryStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.bibliographicDetails",
      completed: false,
      component: <DocDeliveryStepBibliographic />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <DocDeliveryStepFiling />,
      isEdited: false,
    },
  ],
};
