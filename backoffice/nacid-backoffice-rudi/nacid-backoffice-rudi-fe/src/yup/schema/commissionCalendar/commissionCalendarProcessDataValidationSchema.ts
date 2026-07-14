import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createCommissionCalendarProcessDataValidationSchema = () => {
  initializeYup(yup);
  return yup.object({
    applicationId: yup.number().required(),
    calendarId: yup.number().required(),
    statusCode: yup.string().required(),
  });
};
