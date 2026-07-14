import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createPublicAccessDetailsValidationSchema = () => {
  initializeYup(yup);

  const publicAccessValidationSchema = yup.object({
    about: yup.string().required(),
  });

  return publicAccessValidationSchema;
};
