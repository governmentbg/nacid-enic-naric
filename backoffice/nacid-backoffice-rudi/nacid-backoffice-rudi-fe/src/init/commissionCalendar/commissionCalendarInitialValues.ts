import { CommissionCalendarDetails } from "../../types/commissionCalendar/commissionCalendarTypes";
import { ReferenceDataDomainKey } from "@duosoftbg/nacid-components";

export const commissionCalendarInitialValues: CommissionCalendarDetails = {
  id: null,
  sessionNum: "",
  sessionTime: "",
  notes: "",
  status: {
    id: "SCD",
    name: "",
    domain: "COMMISSION_SESSION_STATUS" as ReferenceDataDomainKey,
  },
};
