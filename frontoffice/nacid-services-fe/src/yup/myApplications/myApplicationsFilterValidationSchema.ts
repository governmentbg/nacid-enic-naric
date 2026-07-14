import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createMyApplicationsFilterValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    //TODO
  });

  return schema;
};
