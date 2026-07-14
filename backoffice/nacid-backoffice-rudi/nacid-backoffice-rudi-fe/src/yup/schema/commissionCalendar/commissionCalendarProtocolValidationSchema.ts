import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createCommissionCalendarProtocolValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
