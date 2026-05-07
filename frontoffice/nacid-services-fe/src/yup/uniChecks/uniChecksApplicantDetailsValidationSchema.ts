import { initializeYup } from "../utils/yupUtils";
import * as yup from "yup";
import { createFullApplicantDetailsValidationSchema } from "../common/applicant/applicantDetailsValidationSchema";

export const createUniChecksApplicantDetailsValidationSchema = () => {
  initializeYup(yup);
  const schema = createFullApplicantDetailsValidationSchema().concat(createRepresentativeCapacityValidationSchema());
  return schema;
};

export const createRepresentativeCapacityValidationSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    representativeCapacity: yup.mixed().when("applicantHasRepresentative", {
      is: true,
      then: yup.string().nullable().required(),
    }),
  });
  return schema;
};
