import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createSignalDetailsValidationSchema = () => {
  initializeYup(yup);

  const signalDetailsValidationSchema = yup.object({
    violationDescription: yup.string().required(),
    violationPlace: yup.string().required(),
  });

  return signalDetailsValidationSchema;
};
