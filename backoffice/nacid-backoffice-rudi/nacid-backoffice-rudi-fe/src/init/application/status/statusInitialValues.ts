import { InitialValues } from "@duosoftbg/nacid-backoffice-components";
import { StatusDetails } from "../../../types/applications/common/status/statusTypes";

export const statusInitialValues: StatusDetails = {
  status: InitialValues.forms.referenceData.referenceDataInitialValues,
  legalReason: InitialValues.forms.legalReason.legalReasonInitialValues,
  submittedDocs: "",
  docflowStatus: InitialValues.forms.referenceData.referenceDataInitialValues,
  archiveNumber: "",
};
