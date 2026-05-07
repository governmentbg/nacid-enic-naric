import { InquiryDetails, InquiryApplication } from "../types/inquiryTypes";
import { THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import InquiryStepApplicant from "../views/components/services/inquiry/form/applicant/InquiryStepApplicant";
import InquiryStepDocuments from "../views/components/services/inquiry/form/documents/InquiryStepDocuments";
import InquiryStepFiling from "../views/components/services/inquiry/form/filing/InquiryStepFiling";
import InquiryStepInquiry from "../views/components/services/inquiry/form/inquiry/InquiryStepInquiry";

export const initialInquiryDetails: InquiryDetails = {
  inquiryKinds: [],
  inquiryAim: "",
  periodFrom: "",
  periodTo: "",
  previousInquiryNum: "",
};

export const initialInquiryApplication: InquiryApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  inquiryDetails: initialInquiryDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <InquiryStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.inquiry",
      completed: false,
      component: <InquiryStepInquiry />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <InquiryStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <InquiryStepFiling />,
      isEdited: false,
    },
  ],
};
