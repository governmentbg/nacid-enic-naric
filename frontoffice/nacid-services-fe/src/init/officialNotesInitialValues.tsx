import { OfficialNotesDetails, OfficialNotesApplication } from "../types/officialNotesTypes";
import { ReferenceDataDomain, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { initialLibApplicantDetails } from "./common/applicantDetailsInitialValues";
import { initialDocumentDetails } from "./common/documentInitialValues";
import React from "react";
import OfficialNotesStepApplicant from "../views/components/services/officialNotes/form/applicant/OfficialNotesStepApplicant";
import OfficialNotesStepDocuments from "../views/components/services/officialNotes/form/documents/OfficialNotesStepDocuments";
import OfficialNotesStepFiling from "../views/components/services/officialNotes/form/filing/OfficialNotesStepFiling";
import OfficialNotesStepNote from "../views/components/services/officialNotes/form/note/OfficialNotesStepNote";

export const initialOfficialNotesDetails: OfficialNotesDetails = {
  serviceType: { id: "", name: "", domain: ReferenceDataDomain.SERVICE_TYPE },
  officialNotesKinds: [],
  additionalInformation: "",
};

export const initialOfficialNotesApplication: OfficialNotesApplication = {
  dataStateStatus: THUNK_STATUS.INITIAL,
  applicantDetails: initialLibApplicantDetails,
  officialNotesDetails: initialOfficialNotesDetails,
  documentDetails: initialDocumentDetails,
  steps: [
    {
      labelCode: "l.step.applicant",
      completed: false,
      component: <OfficialNotesStepApplicant />,
      isEdited: false,
    },
    {
      labelCode: "l.step.note",
      completed: false,
      component: <OfficialNotesStepNote />,
      isEdited: false,
    },
    {
      labelCode: "l.step.attachments",
      completed: false,
      component: <OfficialNotesStepDocuments />,
      isEdited: false,
    },
    {
      labelCode: "l.step.filing",
      completed: false,
      component: <OfficialNotesStepFiling />,
      isEdited: false,
    },
  ],
};
