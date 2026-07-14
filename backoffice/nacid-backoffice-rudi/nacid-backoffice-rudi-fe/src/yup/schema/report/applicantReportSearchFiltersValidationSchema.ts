import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createApplicantReportSearchFiltersValidationSchema = () => {
  initializeYup(yup);

  //TODO
  const schema = yup.object({});

  return schema;
};
