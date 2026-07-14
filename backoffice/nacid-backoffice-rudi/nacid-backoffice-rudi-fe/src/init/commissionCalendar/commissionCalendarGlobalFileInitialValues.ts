import { GlobalFileDetails } from "../../types/commissionCalendar/commissionCalendarTypes";
import { DocumentTypes } from "@duosoftbg/nacid-backoffice-components";

export const commissionCalendarGlobalFileInitialValues: GlobalFileDetails = {
  documentType: DocumentTypes.CERTIFICATE,
  attachments: [],
  isDraft: false,
};
