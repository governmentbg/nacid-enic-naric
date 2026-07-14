import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import {
  createRudiEducationStartEndValidationSchema,
  createRudiEducationValidationSchema,
  createWithRecognitionCategoryValidationSchema,
  createWithSpecialitiesValidationSchema,
} from "../common/education/educationValidationSchemas";
import { createNaturalPersonFullValidationSchema } from "../common/person/personValidationSchemas";
import i18n from "i18next";

export const createUniChecksEducationValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup
    .object({
      serviceType: yup.object({
        id: yup.string().required(),
      }),
      diplomaHolder: createNaturalPersonFullValidationSchema(),
      statute: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
        const { authenticity, recommendation } = this.parent;
        if (!authenticity && !recommendation) return value !== null && value;
        return true;
      }),
    })
    .concat(createRudiEducationValidationSchema())
    .concat(createRudiEducationStartEndValidationSchema())
    .concat(createWithRecognitionCategoryValidationSchema())
    .concat(createWithSpecialitiesValidationSchema());

  return educationValidationSchema;
};
