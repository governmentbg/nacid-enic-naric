import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-components";

export const createCommissionCalendarFilterValidationSchema = () => {
  initializeYup(yup);
  return yup.object().shape({});
};
