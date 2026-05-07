import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createMyCorrespondenceFilterValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    //TODO
  });

  return schema;
};
