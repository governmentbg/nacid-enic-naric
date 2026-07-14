import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createCommissionCalendarGlobalFileValidationSchema = () => {
  initializeYup(yup);
  return yup.object().shape({});
};
