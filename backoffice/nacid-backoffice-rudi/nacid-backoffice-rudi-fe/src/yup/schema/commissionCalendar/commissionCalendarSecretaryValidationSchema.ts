import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createCommissionCalendarSecretaryValidationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
