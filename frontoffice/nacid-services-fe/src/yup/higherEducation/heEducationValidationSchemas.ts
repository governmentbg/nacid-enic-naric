import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import {
  createRudiEducationStartEndValidationSchema,
  createRudiEducationValidationSchema,
  createWithPrevUniDiplomaValidationSchema,
  createWithSpecialitiesValidationSchema,
} from "../common/education/educationValidationSchemas";

export const createHEEducationValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup
    .object({
      recognitionAim: yup.array().min(1),
    })
    .concat(createRudiEducationValidationSchema())
    .concat(createRudiEducationStartEndValidationSchema())
    .concat(createWithPrevUniDiplomaValidationSchema())
    .concat(createWithSpecialitiesValidationSchema());

  return educationValidationSchema;
};
