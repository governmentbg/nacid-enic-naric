import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER } from "@duosoftbg/nacid-components";
import { CommissionCalendarFilterDetails } from "../../types/commissionCalendar/commissionCalendarTypes";

export const commissionCalendarFilterInitialValues: CommissionCalendarFilterDetails = {
  sessionNum: "",
  sessionStatusCode: "",
  sessionTimeFrom: undefined,
  sessionTimeTo: undefined,
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "sessionTime",
};
