import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createOfficialNotesDetailsValidationSchema = () => {
  initializeYup(yup);

  const officialNotesDetailsValidationSchema = yup.object({
    serviceType: yup.object({
      id: yup.string().required(),
    }),
    officialNotesKinds: yup.array().min(1),
    additionalInformation: yup.string().optional(),
  });

  return officialNotesDetailsValidationSchema;
};
