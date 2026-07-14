import { SignalDetails, SignalApplication } from "../types/signalTypes";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import SignalStepApplicant from "../views/components/services/signal/form/applicant/SignalStepApplicant";
import SignalStepSignal from "../views/components/services/signal/form/signal/SignalStepSignal";
import SignalStepDocuments from "../views/components/services/signal/form/documents/SignalStepDocuments";
import SignalStepFiling from "../views/components/services/signal/form/filing/SignalStepFiling";

export const initialSignalDetails: SignalDetails = {
  checkRequirement: "",
  damagesDescription: "",
  measuresTaken: "",
  violationDescription: "",
  violationPlace: "",
};

export const initialSignalApplication: SignalApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  signalDetails: initialSignalDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <SignalStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.signal",
      completed: false,
      component: <SignalStepSignal />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <SignalStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <SignalStepFiling />,
      isEdited: false,
    },
  ],
};
